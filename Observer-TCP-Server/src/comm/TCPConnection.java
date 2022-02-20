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

    }

    // -------------------------------

    @Override
    public void run() {

        try {

            server = new ServerSocket(port);
            System.out.println("\u001B[34m" + "Waiting connection" + "\u001B[37m");
            socket = server.accept();
            System.out.println("\u001B[32m" + "Accept connection" + "\u001B[37m");


            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void commands(String line) throws IOException {

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
                onRTTListener.onRTT();
                break;
            case "speed":
                onSpeedListener.onSpeed();
                break;

            default:
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
