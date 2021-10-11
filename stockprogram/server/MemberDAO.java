
package test1.stock.server;

import java.sql.*;
import test1.stock.vo.Member;

public class MemberDAO {
    public void insertMember(Member vo) throws Exception{
        //1. 드라이버 등록
        Class.forName("org.mariadb.jdbc.Driver");
        
        //2. 연결
        Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/testDB","root","1234");
        //Connection connection = DriverManager.getConnection("jdbc:mariadb://loalhost:3306/testDB?user=root&password=1234");
        
        //3. Statement 생성 - 택배회사 역할
//        Statement stmt=con.createStatement();
        //데이터를 set하는 binding을 통해 전달할 수도 있음, 
        PreparedStatement stmt2=con.prepareStatement("insert into member(id,name,addr) values(?,?,?)");
        stmt2.setString(1,vo.getId());
        stmt2.setString(2,vo.getName());
        stmt2.setString(3,vo.getAddr());

        //4. SQL 전송
//        ResultSet rs=stmt.executeQuery("insert into member (in,name,addr) "
//                + "values("+vo.getId()+","+ vo.getName() + "," + vo.getAddr()+ ")"); // 데이터 수동입력 -> 불편, 보안 취약
        int i = stmt2.executeUpdate(); // insert된 행의 개수
        System.out.println(i+"행이 insert 되었습니다.");
        
        //6. 자원 종료
        stmt2.close();
        con.close();
        
    }
    public Member selectMember(String id) throws Exception{
        //1. Driver 등록
        Class.forName("org.mariadb.jdbc.Driver");
        
        //2. 연결
        Connection con= DriverManager.getConnection("jdbc:mariadb://localhost:3306/testDB","root","1234");
        
        //3. Statement 생성 - 편의성, 성능, 보안상 PreparedStatement가 좋음
        PreparedStatement stmt = con.prepareStatement(
                "select * from member where id=?");  //data들어갈 위치 ?로 표시
        stmt.setString(1, id); //data binding작업
        
        //4. SQL 전송
        ResultSet rs = stmt.executeQuery();
        
        //5. 결과 받기
        if(rs.next()){
            String name = rs.getString("name");
            String addr = rs.getString("addr");
            Member m = new Member(id,name,addr);
            return m;
        }
        return null;
        
        
        
    
    }
    public void updateMember(Member vo) throws Exception{
        //1. 드라이버 등록
        Class.forName("org.mariadb.jdbc.Driver");

        //2. 연결
        Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/testDB", "root", "1234");
     
        //3. Statement 생성 - 택배회사 역할
        PreparedStatement stmt2 = con.prepareStatement("update member set name=?, addr=? where id=?");
        stmt2.setString(1, vo.getName()); 
        stmt2.setString(2, vo.getAddr());
        stmt2.setString(3, vo.getId());

        //4. SQL 전송
        int i = stmt2.executeUpdate(); 
        System.out.println(i + "행이 update 되었습니다.");

        //6. 자원 종료
        stmt2.close();
        con.close();

    }
    public void deleteMember(String id) throws Exception{
        //1. 드라이버 등록
        Class.forName("org.mariadb.jdbc.Driver");

        //2. 연결
        Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/testDB", "root", "1234");
     
        //3. Statement 생성 - 택배회사 역할
        PreparedStatement stmt2 = con.prepareStatement("delete from member where id=?");
        stmt2.setString(1, id);      

        //4. SQL 전송
        int i = stmt2.executeUpdate(); 
        System.out.println(i + "행이 delete 되었습니다.");

        //6. 자원 종료
        stmt2.close();
        con.close();
    }
}
