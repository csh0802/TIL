
package test1.stock.server;

import com.sun.corba.se.impl.encoding.CDROutputObject;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.net.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import test1.stock.vo.Member;

public class StockServer {
    
    public void ready() throws Exception{
        ServerSocket ss = new ServerSocket(9999);
        while(true){
            Socket s = ss.accept();
            ObjectInputStream in = new ObjectInputStream(s.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
            ServerThread t = new ServerThread(s,in,out);
            t.start();
        } 
    }
    
    class ServerThread extends Thread{
        Socket s;
        ObjectInputStream in;
        ObjectOutputStream out;
        private ServerThread(Socket s, ObjectInputStream in, ObjectOutputStream out) {
            this.s = s;
            this.in = in;
            this.out = out;
        }
        @Override
        public void run(){
            
                try {
                    while(true){
                        Member m = (Member)in.readObject();
                        System.out.println(m.getId());
                        System.out.println(m.getName());
                        System.out.println(m.getAddr());
                        //읽어온 데이터에 대한 DB처리 필요
                    }
                } catch (IOException ex) {
                    System.out.println("client 접속 종료");
                } catch (Exception ex) {            
                Logger.getLogger(StockServer.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }
    }
    public static void main(String[] args) throws Exception{
        new StockServer().ready();
    }
}
