package comm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

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

    private ServerSocket server;
    private Socket socket;
    private int port;
    BufferedReader br;
    BufferedWriter bw;

    public void setPort(int port) {
        this.port = port;
    }

    // Observer
    public OnSpeedListener onSpeedListener;
    public OnInterfaceListener onInterfaceListener;
    public OnTimeListener onTimeListener;
    public OnIpListener onIpListener;
    public OnRTTListener onRTTListener;
    public OnMessageListener onMessageListener;

    public void setOnMessageListener(OnMessageListener onMessageListener) {
        this.onMessageListener = onMessageListener;
    }

    public void setOnSpeedListener(OnSpeedListener onSpeedListener) {
        this.onSpeedListener = onSpeedListener;
    }

    public void setOnInterfaceListener(OnInterfaceListener onInterfaceListener) {
        this.onInterfaceListener = onInterfaceListener;
    }

    public void setOnTimeListener(OnTimeListener onTimeListener) {
        this.onTimeListener = onTimeListener;
    }

    public void setOnIpListener(OnIpListener onIpListener) {
        this.onIpListener = onIpListener;
    }

    public void setOnRTTListener(OnRTTListener onRTTListener) {
        this.onRTTListener = onRTTListener;
    }

    public void subscribe(Application application) {
        setOnInterfaceListener(application);
        setOnIpListener(application);
        setOnRTTListener(application);
        setOnSpeedListener(application);
        setOnTimeListener(application);
        setOnMessageListener(application);

    }

    // -------------------------------

    @Override
    public void run() {

        while (true) {

            try {
                handshake();
                String message = br.readLine();
                if (message !=null){
                    commands(message);
                }
               
            } catch (IOException e) {

                try {
                    server.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

        }
    }

    public void handshake() throws IOException {

        server = new ServerSocket(port);
        onMessageListener.showMessage("\u001B[34m" + "Waiting connection" + "\u001B[37m");
        socket = server.accept();
        onMessageListener.showMessage("\u001B[32m" + "Accept connection" + "\u001B[37m");

        br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

    }

    public void commands(String line) throws IOException {
        String message="";



        if (line.contains(":")){
            String [] split=line.split(":");
            line=split[0];
            message=split[1];
        }

        switch (line) {
            case "remoteIpconfig":
                onIpListener.onIp();
                break;
            case "interface":
                onInterfaceListener.OnInterface();
                break;
            case "whatTimeIsIt":
                onTimeListener.OnTime();
                break;
            case "RTT":
                onRTTListener.onRTT(message);
                break;
            case "speed":
                onSpeedListener.onSpeed(message);
                break;

            default:
                sendMessage("\u001B[31m" + "Invalid command" + "\u001B[37m");
                break;
        }
    }

    public void sendMessage(String msg) {
        new Thread(
                () -> {
                    try {
                        bw.write(msg + "\n");
                        bw.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
    }






}
