package app;

import java.util.Scanner;

import comm.TCPConnection;
import event.*;

public class Application implements OnInterfaceListener, OnIpListener, OnRTTListener, OnTimeListener, OnSpeedListener,OnMessageListener {
    private Scanner scanner = new Scanner(System.in);

    private TCPConnection tcpConnection;

    public Application() {
        this.tcpConnection = TCPConnection.getInstance();
        tcpConnection.setPort(5000);
        tcpConnection.setIp("127.0.0.1");
        tcpConnection.subscribe(this);
    }

    public void init() {
    tcpConnection.start();

        while (true) {
            String line = scanner.nextLine();

            tcpConnection.sendMessage(line);

        }

    }

    @Override
    public void OnInterface() {
        // TODO Auto-generated method stub

    }

    @Override
    public void onSpeed() {
        // TODO Auto-generated method stub

    }

    @Override
    public void OnTime() {
        // TODO Auto-generated method stub

    }

    @Override
    public void onRTT() {
        // TODO Auto-generated method stub

    }

    @Override
    public void onIp() {
        // TODO Auto-generated method stub

    }

    public Boolean commands(String line) {
        boolean valid=true;

        switch (line) {
            case "remoteIpconfig":
               // onIp();
                break;

            case "interface":
                //OnInterface();

                break;

            case "whatTimeIsIt":
                //OnTime();
                break;

            case "RTT":
                //onRTT();
                break;
            case "speed":
                //onSpeed();
                break;

            default:
            valid=false;
                break;
        }
        return valid;
    }

    @Override
    public void showMessage(String line) {
        System.out.println(line);
        
    }

}
