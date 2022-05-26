package Org;

import DataBase.Response;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerUtil {
    ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
    public ServerUtil() {
    }

    public void sendResponseToClient(Response response, SocketChannel client) throws IOException {
        fixedThreadPool.submit(()-> {
            try {
                client.write(ByteBuffer.wrap(serialize(response)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private byte[] serialize(Response rsp) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        ) {
            objectOutputStream.writeObject(rsp);
            return byteArrayOutputStream.toByteArray();
        }
    }
}
