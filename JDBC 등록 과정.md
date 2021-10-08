# JDBC 등록 6단계

> NetBeans IDE 8.2, heidSQL사용

## 1. 드라이버 다운

- mariaDB JDBC driver 검색해서 다운

- 드라이버 이름 : org.mariadb.jdbc.Driver

  DB URL : "jdbc:mariadb://서버아이피:3306(사용포트)/데이터베이스명"

- 오라클 : "jdbc:oracle://localhost:1521/db이름"

## 2. 드라이버 등록

```java
class.forNmae("ort.mariadb.jdbc.Driver");
 
```

## 3. 연결

```java
Connection connection = DriverManager.getConnection("jdbc:mariadb://loalhost:3306/testDB?user=root&password=1234");
```



## 4. Statement 생성

```java
Statement stmt = connection.createStatement();
```

## 5. SQL 전송

```java
ResultSet rs =stmt.excuteQueary("select * from member");
```

## 6. 결과 받기

```java
Strign id = rs.getString("id");
Strign name = rs.getString("name");
Strign addr = rs.getString("addr");
```

## 7. 자원 종료

```java
rs.close();
stmt.close();
connection.close();
```







