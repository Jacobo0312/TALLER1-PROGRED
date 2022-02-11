package comm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class TCPConnection extends Thread {

    private ServerSocket server;
    private Socket socket;
    private int port;


    public OnMessageListener listener;
    public OnByeListener byeListener;

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public void run() {

        try {

            server = new ServerSocket(port);
            System.out.println("Waiting connection");
            socket = server.accept();
            System.out.println("Accept connection");
            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            while (true) {

                String message = br.readLine();
                listener.onMessage(message);
                if (message == null) {
                    break;
                }else if (message.equals("bye")){
                    byeListener.onBye();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

 

    // OBSERVER

    public void setListener(OnMessageListener listener) {
        this.listener = listener;
    }

    public interface OnMessageListener {
        public void onMessage(String message);

    }

    public interface OnByeListener{
        public void onBye();
    }

    public void setOnBye(OnByeListener byeListener) {
        this.byeListener = byeListener;
    }

    public void close() throws IOException {
        try {
            socket.close();
        } catch (SocketException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }


}