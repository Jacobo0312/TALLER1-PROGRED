
package comm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import app.Application;
import event.*;

public class TCPConnection extends Thread {

    // Singleton

    private static TCPConnection instance = null;

    private TCPConnection() {
    }

    public static synchronized TCPConnection getInstance() {

        if (instance == null) {
            instance = new TCPConnection();
        }

        return instance;

    }

    // -------------------------------

    private Socket socket;
    private String ip;
    private int port;
    BufferedWriter bw;
    BufferedReader br;
    boolean conn=false;

    public void setPort(int port) {
        this.port = port;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public void run() {

        
      while (!conn) {
          try {
              handshake();
        conn=true;
          } catch (Exception e) {
              onMessageListener.showMessage("\u001B[34m" + "Waiting connection" + "\u001B[37m");
          }
      }
      



    }

    public void handshake() throws UnknownHostException, IOException {
        socket = new Socket(ip, port);
        onMessageListener.showMessage("Connect");
        bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        conn=true;
    }

    public void sendMessage(String line) {
        new Thread(() -> {
            try {
                bw.write(line + "\n");
                bw.flush();
                String message = br.readLine();
                onMessageListener.showMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

    }

    // OBSERVER

    public OnSpeedListener onSpeedListener;
    public OnInterfaceListener onInterfaceListener;
    public OnTimeListener onTimeListener;
    public OnIpListener onIpListener;
    public OnRTTListener onRTTListener;
    public OnMessageListener onMessageListener;

    public void subscribe(Application listener) {

        this.onInterfaceListener = listener;
        this.onIpListener = listener;
        this.onRTTListener = listener;
        this.onSpeedListener = listener;
        this.onTimeListener = listener;
        this.onMessageListener = listener;

    }

    // ----------------------------------------------------

}