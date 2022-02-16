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

    // -------------------------------

    @Override
    public void run() {

        try {

            server = new ServerSocket(port);
            System.out.println("Waiting connection");
            socket = server.accept();
            System.out.println("Accept connection");
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            while (true) {

                String message = br.readLine();
                bw.write("Recibido\n");
                bw.flush();

                if (message == null) {
                    break;
                } else if (message.equals("bye")) {
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void subscribe(Application application) {
        setOnInterfaceListener(application);
        setOnIpListener(application);
        setOnRTTListener(application);
        setOnSpeedListener(application);
        setOnTimeListener(application);
        
    }

 



}
