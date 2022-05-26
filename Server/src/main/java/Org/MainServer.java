package Org;

import java.io.IOException;

public class MainServer {
    public static void main(String[] args)   {
        Server server = new Server();
        try {
            server.run();
        }catch (IOException e){
            return;
        }
    }
}
