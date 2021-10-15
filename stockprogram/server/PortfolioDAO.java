
package test1.stock.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import test1.stock.vo.Member;
import test1.stock.vo.Stock;

public class PortfolioDAO {
    public void insertPortfolio(int memberNo, String stockCode, int quantity)throws Exception{//create
         //1. 드라이버 등록
        Class.forName("org.mariadb.jdbc.Driver");
        
        //2. 연결
        Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/testDB","root","1234");

        //3. Statement 생성
        PreparedStatement stmt = con.prepareStatement(
                "insert into portfolio(memberNo,stockCode,quantity) values(?,?,?)");
        stmt.setInt(1, memberNo);
        stmt.setString(2,stockCode);
        stmt.setInt(3,quantity);
        
        //4. SQL 전송
        ResultSet rs = stmt.executeQuery();
                  
             
        stmt.close();
        con.close();               
    }
    public int selectPortfolio(int memberNo, String stockCode)throws Exception{//read
         //1. Driver 등록
        Class.forName("org.mariadb.jdbc.Driver");
        
        //2. 연결
        Connection con= DriverManager.getConnection("jdbc:mariadb://localhost:3306/testDB","root","1234");
        
        //3. Statement 생성 - 편의성, 성능, 보안상 PreparedStatement가 좋음
        PreparedStatement stmt = con.prepareStatement(
                "select * from portfolio where memberNo=? and stockCode=?");  //data들어갈 위치 ?로 표시
        stmt.setInt(1, memberNo); //data binding작업
        stmt.setString(2, stockCode);
        
        //4. SQL 전송
        ResultSet rs = stmt.executeQuery();
        
        //5. 결과 받기
        if(rs.next()){//보유 주식이 있을 때
            int quantity=rs.getInt("quantity");
            
            return quantity;
        }
        return 0;
    }
     public void updatePortfolio(int memberNo, String stockCode,int quantity)throws Exception{//update
         //1. 드라이버 등록
        Class.forName("org.mariadb.jdbc.Driver");

        //2. 연결
        Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/testDB", "root", "1234");
     
        //3. Statement 생성 - 택배회사 역할
        PreparedStatement stmt2 = con.prepareStatement(
                "update portfolio set quantity=? where memberNo=? and stockCode=?");
        stmt2.setInt(1, quantity); 
        stmt2.setInt(2, memberNo);
        stmt2.setString(3, stockCode);

        //4. SQL 전송
        int i = stmt2.executeUpdate(); 
        System.out.println(i + "행이 update 되었습니다.");

        //6. 자원 종료
        stmt2.close();
        con.close();

    }
      public void deletetPortfolio(){//delete
        
    }
}
