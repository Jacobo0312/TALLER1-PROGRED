
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
    long time=0;

    public void setPort(int port) {
        this.port = port;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public long getTime(){
        return this.time;
    }

    @Override
    public void run() {

        while (!conn) {
            try {
                handshake();
            } catch (UnknownHostException e) {
                conn=false;
            } catch (IOException e) {
                conn=false;
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
                if(line.length()<100){
                    onMessageListener.showMessage(message);
                }
                time=System.currentTimeMillis();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                socket.close();
                handshake();
            } catch (IOException e) {
                //e.printStackTrace();

            }
            conn=false;
        }).start();

    }

    // OBSERVER

    public OnMessageListener onMessageListener;

    public void subscribe(Application listener) {

        this.onMessageListener = listener;

    }

    // ----------------------------------------------------

}