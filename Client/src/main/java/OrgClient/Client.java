package OrgClient;

import User.Authorization;
import User.User;
import DataBase.Response;
import User.Request;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {
    private ClientUtil clientUtil;
    private ComandReader comandReader;
    private Scanner scanner;
    Script script;
    private Authorization authorization;
    private User user;
    ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
    public Client() {
        this.clientUtil = new ClientUtil();
        this.comandReader = new ComandReader();
        this.scanner = new Scanner(System.in);
        this.user =  new User();
        this.script = new Script(this.comandReader, this.clientUtil, this.user);
        this.authorization = new Authorization(this.user, this.scanner);
    }

    public boolean checkIfScriptCommand(String command){
        String[] com = command.trim().toLowerCase(Locale.ROOT).split("\\s+");
        String[] args = Arrays.copyOfRange(com, 1, com.length);
        if (com[0].equals("execute_script") && args.length!=0) return true;
        return false;
    }

    public void run() throws IOException {
        fixedThreadPool.submit(() -> {
            System.out.println("Введите номер порта, по которому вы хотите подключиться : ");
            int port = Integer.parseInt(scanner.nextLine().trim());
            InetAddress InetAddress = null;
            try {
                InetAddress = Inet4Address.getByName("localhost");
            } catch (UnknownHostException e) {
                e.printStackTrace();
                System.out.println("Ошибка хоста!");
            }

            try (Socket socket = new Socket(InetAddress, port);
                 Scanner scanner = new Scanner(System.in);
            ) {
                //Выделение байтового буфера
                ByteBuffer buffer = ByteBuffer.allocate(65000);
                Response response = new Response();
                while (true) {
                    authorization.userAuthReg();
                    Request authReq = new Request(user);
                    clientUtil.sendRequestToServer(authReq, socket);
                    response = clientUtil.receive(socket); //null
                    System.out.println(response.getBody());
                    if (!authorization.userExist(response)) {
                        continue;
                    }else {
                        break;
                    }
                }
                user.setRegistration(false);
                user.setAuthorization(false);
                System.out.println("Приложение запущено!");
                System.out.println("Имя пользователя: " + user.getUsername());
                System.out.println("Введите exit для заверешния работы приложения");
                while (true) {
                    System.out.println("Введите команду :");
                    String req = scanner.nextLine();
                    while (true) {
                        if (checkIfScriptCommand(req)) {
                            script.executeScript(req, socket, buffer);
                        } else {
                            Request request1 = comandReader.getRequestFromCommand(req, user);
                            request1.setUser(user);
                            if (request1.getCommand().equals("no_command")) {
                                req = scanner.nextLine();
                                continue;
                            }
                            clientUtil.sendRequestToServer(request1, socket);
                            System.out.println(clientUtil.receive(socket).getBody());
                            buffer.clear();
                            if (request1.getCommand().equals("exit")) {
                                System.out.println("Приложение завершает работу!");
                                System.exit(0);
                            }
                        }
                        req = scanner.nextLine();

                    }
                }
            } catch (SocketException e) {
                System.err.println("Нет подключения по данному порту!");
            } catch (IllegalArgumentException e2) {
                System.err.println("Нет соединения с сервером!");
            } catch (StreamCorruptedException e3) {
                System.err.println("Нет соединения с сервером!");
            } catch (NullPointerException e4) {
                System.out.println("Некорректная команда!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}

