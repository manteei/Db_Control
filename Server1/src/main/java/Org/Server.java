package Org;

import DataBase.UserConnector;
import Org.RequestSet;
import Org.ResponseSet;
import User.CommandLine;
import DataBase.DBManager;
import User.Request;
import DataBase.Response;
import WorkClass.Commander;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;

public class Server {
    private RequestSet requestSet;
    private Scanner scanner;
    private ServerUtil serverUtil;
    private ResponseSet responseSet;
    private CommandLine commandLine;
    private Commander commander;
    private Response response;
    private UserConnector userConnector;
    ForkJoinPool pool = new ForkJoinPool(5);
    public Server() {
        this.requestSet = new RequestSet();
        scanner = new Scanner(System.in);
        this.serverUtil = new ServerUtil();
        this.responseSet = new ResponseSet();
        this.response = new Response();
        this.userConnector = new UserConnector();
    }


    public void run() throws IOException {
        this.commandLine = new CommandLine();
        this.commander = new Commander(this.commandLine);
        System.out.println("Введите номер порта, по которому вы хотите подключиться: ");
        int port = Integer.parseInt(scanner.nextLine().trim());
        DBManager.load(this.commandLine);

        if (commandLine.getSize() == 0) {
            System.out.println("Добавьте объекты с помощью команды add");
        } else System.out.println("Объкты из файла загружены!");

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //Создание селектора
        Selector selector = Selector.open();
        //Привязка сокет канала к адресу (IP-сокета (имя хоста+номер порта))
        serverSocketChannel.bind(new InetSocketAddress("localhost", port));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("Сервер запущен!");

        ByteBuffer request = ByteBuffer.allocate(65000);
            while (true) {
                    try {
                        selector.selectNow();
                        Set<SelectionKey> selectionKeySet = selector.selectedKeys();
                        Iterator<SelectionKey> iterator = selectionKeySet.iterator();
                        while (iterator.hasNext()) {
                            SelectionKey key = iterator.next();
                            iterator.remove();
                            if (!key.isValid()) {
                                System.out.println("Key isn't valid!");
                                continue;
                            }
                            if (key.isAcceptable()) {
                                System.out.println("Запрос на подключение клиента!");
                                SocketChannel client = serverSocketChannel.accept();
                                client.configureBlocking(false);
                                client.register(selector, SelectionKey.OP_READ);
                                System.out.println("Клиент подключен!");
                            } else if (key.isReadable()) {
                                System.out.println("Попытка чтения из канала!");
                                SocketChannel client = (SocketChannel) key.channel();
                                Request requestFromClient = requestSet.receive(client);
                                if (requestFromClient.getUser().isRegistration() || requestFromClient.getUser().isAuthorization()) {
                                    String responseString = userConnector.connectUser(requestFromClient.getUser(), response);
                                    response.setBody(responseString);
                                    responseSet.setResponse(response);
                                    request.clear();
                                    key.interestOps(SelectionKey.OP_WRITE);
                                } else {
                                    pool.execute(() -> {
                                        String responseString = commander.execute(requestFromClient);
                                        response.setBody(responseString);
                                        responseSet.setResponse(response);
                                        request.clear();
                                        key.interestOps(SelectionKey.OP_WRITE);
                                    });
                                }

                            } else if (key.isWritable()) {
                                key.interestOps(SelectionKey.OP_READ);
                                SocketChannel client = (SocketChannel) key.channel();
                                serverUtil.sendResponseToClient(response, client);
                                if (response.getBody().equals("Клиент отключился!")) {
                                    System.out.println("Клиент " + client.toString() + " отключился!");
                                    key.interestOps(SelectionKey.OP_CONNECT);
                                }
                            }
                        }

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
            }
    }
}
