
package test1.stock.server;

import java.sql.*;
import test1.stock.vo.Stock;

public class StockDAO {
    
    public String selectStockBySymbol(String symbol) throws Exception{
        //1. 드라이버 등록
        Class.forName("org.mariadb.jdbc.Driver");
        
        //2. 연결
        Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/testDB","root","1234");

        //3. Statement 생성
        PreparedStatement stmt = con.prepareStatement("select * from stock where symbol=?");
        stmt.setString(1, symbol);
        
        //4. SQL 전송
        ResultSet rs = stmt.executeQuery();
        
        //5. 결과 얻기
       
        if(rs.next()){
            String code = rs.getString("code");
            
            return code;
        }
        rs.close();
        stmt.close();
        con.close();
        
        return null;
    }
    
    public Stock selectStock(String stockCode) throws Exception{
        //1. 드라이버 등록
        Class.forName("org.mariadb.jdbc.Driver");
        
        //2. 연결
        Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/testDB","root","1234");

        //3. Statement 생성
        PreparedStatement stmt = con.prepareStatement("select * from stock where code=?");
        stmt.setString(1, stockCode);
        
        //4. SQL 전송
        ResultSet rs = stmt.executeQuery();
        
        //5. 결과 얻기
        Stock stock = null;
        if(rs.next()){
            String symbol = rs.getString("symbol");
            int price = rs.getInt("price");
            System.out.println(symbol+","+price);
            stock = new Stock(stockCode, symbol, price);
        }
        rs.close();
        stmt.close();
        con.close();
        
        return stock;
    }
    
    public void updateStock(){
        
    }
}
