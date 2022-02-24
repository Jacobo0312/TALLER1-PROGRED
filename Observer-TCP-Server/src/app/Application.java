package app;


import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Calendar;

import comm.TCPConnection;
import event.*;

public class Application implements OnInterfaceListener,OnIpListener,OnRTTListener,OnTimeListener,OnSpeedListener,OnMessageListener{

    private  TCPConnection tcpConnection;

    public Application(){
        this.tcpConnection=TCPConnection.getInstance(); 
        tcpConnection.subscribe(this);
    }


    public void init() {
        tcpConnection.start();
        tcpConnection.setPort(5050);
    }


    @Override
    public void onSpeed(String msg) {
        tcpConnection.sendMessage(msg);
                
    }


    @Override
    public void OnTime() {
            tcpConnection.sendMessage(">>> "+Calendar.getInstance().getTime()+"\n");
    }


    @Override
    public void onRTT(String msg) {
     tcpConnection.sendMessage(msg);

    }


    @Override
    public void onIp() {
        try {
            tcpConnection.sendMessage(">>> "+InetAddress.getLocalHost());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        
    }


    @Override
    public void OnInterface() {
        try {
            tcpConnection.sendMessage(">>> "+NetworkInterface.getByInetAddress(InetAddress.getLocalHost()));
        } catch (UnknownHostException | SocketException e) {
            e.printStackTrace();
        }



        
    }


    @Override
    public void showMessage(String line) {
        System.out.println(line);
        
    }



  


 
    
}
