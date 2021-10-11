
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
import test1.stock.vo.MyProtocol;

public class StockServer {

    public void ready() throws Exception {
        ServerSocket ss = new ServerSocket(9999);
        System.out.println("server ready");
        while (true) {
            Socket s = ss.accept();
            ObjectInputStream in = new ObjectInputStream(s.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
            ServerThread t = new ServerThread(s, in, out);
            t.start();
        }
    }

    class ServerThread extends Thread {

        Socket s;
        ObjectInputStream in;
        ObjectOutputStream out;

        private ServerThread(Socket s, ObjectInputStream in, ObjectOutputStream out) {
            this.s = s;
            this.in = in;
            this.out = out;
        }

        @Override
        public void run() {

            try {
                while (true) {
                    MyProtocol p = (MyProtocol) in.readObject();
                    if (p.getSign().equals(MyProtocol.signMsg[0])) { //INSERT_MEMBER
                        //==는 주소값을 비교하는 연산자인데                        
                        //equals()대신 ==을 쓰기엔 다른 머신이라 저장되는 주소값이 달라질 수 있음!!
                        Member m = (Member) p.getParameterObj();
                        System.out.println(m.getId());
                        System.out.println(m.getName());
                        System.out.println(m.getAddr());
                        //읽어온 데이터에 대한 DB처리 필요
                        MemberDAO memberDAO = new MemberDAO();
                        Member findMember = memberDAO.selectMember(m.getId());
                        if (findMember != null) {//id 중복 상태
                            out.writeObject("ID가 중복되었습니다");
                        } else {
                            memberDAO.insertMember(m);
                            out.writeObject("등록되었습니다.");
                        }
                    } else if (p.getSign().equals(MyProtocol.signMsg[1])) { //SELECT_MEMBER
                        String id = (String) p.getParameterObj();
                        MemberDAO memberDAO = new MemberDAO();
                        Member findMember = memberDAO.selectMember(id);
                        if (findMember == null) {
                            out.writeObject("해당 고객 정보가 없습니다");
                        } else {
                            out.writeObject(findMember);

                        }
                    }else if (p.getSign().equals("UPDATE_MEMBER")) {
                        Member m = (Member) p.getParameterObj();
                        MemberDAO memberDAO = new MemberDAO();
                        memberDAO.updateMember(m);
                    }else if(p.getSign().equals("DELETE_MEMBER")){
                         String id = (String) p.getParameterObj();
                        MemberDAO memberDAO = new MemberDAO();
                        Member findMember = memberDAO.selectMember(id);
                        if (findMember == null) {
                            out.writeObject("해당 고객 정보가 없습니다");
                        }else {
                            memberDAO.deleteMember(id);
                            out.writeObject("정상적으로 삭제되었습니다.");
                        }
                    }
                }
            } catch (IOException ex) {
                System.out.println("client 접속 종료");
            } catch (Exception ex) {
                Logger.getLogger(StockServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new StockServer().ready();
    }
}
