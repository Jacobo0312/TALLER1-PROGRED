package app;


import comm.TCPConnection;
import event.*;

public class Application implements OnInterfaceListener,OnIpListener,OnRTTListener,OnTimeListener,OnSpeedListener{

    private  TCPConnection tcpConnection;

    public Application(){
        this.tcpConnection=TCPConnection.getInstance(); 
        tcpConnection.subscribe(this);
    }


    public void init() {
        tcpConnection.start();
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


    @Override
    public void OnInterface() {
        // TODO Auto-generated method stub
        
    }



  


 
    
}
