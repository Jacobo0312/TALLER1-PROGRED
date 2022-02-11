package app;

import java.io.IOException;

import comm.TCPConnection;
import comm.TCPConnection.OnByeListener;

public class Application implements TCPConnection.OnMessageListener,OnByeListener{

    private  TCPConnection tcpConnection;

    public Application(){
        this.tcpConnection=new TCPConnection(); 
        tcpConnection.setListener(this);
        tcpConnection.setOnBye(this);
        tcpConnection.setPort(5000);
    }


    public void init() {
        tcpConnection.start();
    }



    @Override
    public void onMessage(String message) {
       System.out.println(message);
    }


    @Override
    public void onBye() {
        try {
            tcpConnection.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
}
