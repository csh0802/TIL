
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
import test1.stock.vo.Portfolio;
import test1.stock.vo.Stock;
import test1.stock.vo.StockSign;


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
                        
                    if(p.getSign()!=null){
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
                        } else if (p.getSign().equals("UPDATE_MEMBER")) {
                            Member m = (Member) p.getParameterObj();
                            MemberDAO memberDAO = new MemberDAO();
                            memberDAO.updateMember(m);
                        } else if (p.getSign().equals("DELETE_MEMBER")) {
                            String id = (String) p.getParameterObj();
                            MemberDAO memberDAO = new MemberDAO();
                            Member findMember = memberDAO.selectMember(id);
                            if (findMember == null) {
                                out.writeObject("해당 고객 정보가 없습니다");
                            } else {
                                memberDAO.deleteMember(id);
                                out.writeObject("정상적으로 삭제되었습니다.");
                            }
                        }
                    }else{
                        StockSign sign2 = p.getSign2();
                        if(sign2.equals(StockSign.SELECT_STOCK)){
                            StockDAO stockDAO = new StockDAO();
                            String stockCode=(String)p.getParameterObj();
                            Stock stock = stockDAO.selectStock(stockCode);
                            if(stock==null)
                                out.writeObject("주식코드가 틀렸습니다");
                            else
                                out.writeObject(stock);
                        }else if(sign2.equals(StockSign.BUY_STOCK)){//매수
                            Portfolio portfolio = (Portfolio)p.getParameterObj();                                                       
                            //memberNo 찾기
                            MemberDAO memberDAO = new MemberDAO();
                            Member m = memberDAO.selectMember(portfolio.getId());
                            if(m==null){//고객이 없을 때
                                out.writeObject("해당 고객이 없습니다");
                            }else{//고객이 있을 때
                                int memberNo=m.getNo();
                                //stockCode 찾기
                                StockDAO stockDAO = new StockDAO();
                                String stockCode = stockDAO.selectStockBySymbol(portfolio.getSymbol());
                                if(stockCode==null){//주식이 없을 때
                                     out.writeObject("해당 주식이 없습니다");
                                }else{//고객, 주식 존재 => 매수 가능한 상황
                                    //보유 주식인지 신규주식인지 파악
                                     PortfolioDAO portfolioDAO = new PortfolioDAO();
                                    int quantity = portfolioDAO.selectPortfolio(memberNo, stockCode);
                                    if(quantity==0){//보유 주식이 없을 때
                                        //insert portfolio
                                        portfolioDAO.insertPortfolio(memberNo, stockCode, portfolio.getQuantity());
                                    }else{//보유 주식이 있을 때
                                        //update portfolio
                                        portfolioDAO.updatePortfolio(memberNo,stockCode,quantity+portfolio.getQuantity());
                                    }
                                    out.writeObject("매수가 완료되었습니다.");
                                }
                            }
                                  
                        }
                    }                        
                }//end while                  
            
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
