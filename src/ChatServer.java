import java.io.IOException;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer{
    ServerSocket serverSocket;
    ArrayList <Client> clients = new ArrayList<>();

    public ChatServer() throws IOException {
        serverSocket = new ServerSocket(1234); // создаем сокет на порту 1234
    }
    public void sendToAll (String message){
        for (Client clients : clients){
            clients.receive(message);
        }
    }
    public void run() {
        while(true) {
            System.out.println("Waiting...");
            try {
                // ждем клиента из сети
                Socket socket = serverSocket.accept();
                System.out.println("Client connected!");
                // создаем клиента
                clients.add(new Client(socket, this));

            } catch (IOException exception){
                exception.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new ChatServer().run();
    }
}
