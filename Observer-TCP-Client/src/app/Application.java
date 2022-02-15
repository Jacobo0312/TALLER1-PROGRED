package app;

import java.util.Scanner;

import comm.TCPConnection;

public class Application implements TCPConnection.OnMessageListener{
    private Scanner scanner = new Scanner(System.in);


    private  TCPConnection tcpConnection;

    public Application(){
        this.tcpConnection=TCPConnection.getInstance(); 
        tcpConnection.setPort(5000);
        tcpConnection.setIp("127.0.0.1");
        tcpConnection.setListener(this);
    }


    public void init() {
        tcpConnection.start();
        
		while(true) {
			String line = scanner.nextLine();
            tcpConnection.sendMessage(line);
			
		}
       
    }



    @Override
    public void showMessage(String message) {
        System.out.println(message);
        
    }

    
    
}
