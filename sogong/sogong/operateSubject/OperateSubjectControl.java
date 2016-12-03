package operateSubject;

import java.sql.*;


public class OperateSubjectControl {

   static final String id = "root";
   static final String passwd="7942";
   static final String driverName = "com.mysql.jdbc.Driver";
   static final String dbURL = "jdbc:mysql://localhost:3306/software" ; // 디비 스키마 부분 다들 만들때 software
   
   
   Connection con = null;
   Statement stmt = null;
   ResultSet rs = null;
   PreparedStatement pstmt = null;
   
   String dbTable = "OperateSubject";
   // insert into db테이블명 values(?,?,?); -> ? 갯수는 디비에 애트리뷰트의 갯수와 같아야함
   
   

   
   
   public OperateSubjectControl() {
      // TODO Auto-generated constructor stub
      try {

         Class.forName(driverName); 
         con = DriverManager.getConnection(dbURL,id, passwd);
         
      } catch (Exception e) {
         e.printStackTrace();

      } 
   }
   
   
   public void insert(OperateSubjectData o)
   {
      try{
         
         String insertQuery = "INSERT INTO `" + dbTable + "` VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
         
         pstmt = con.prepareStatement(insertQuery); // prepareStatement에서 해당 sql을 미리 컴파일한다.
         
         //애튜리뷰트에 값을 대입함
         pstmt.setString(1,"test");
         pstmt.setString(2,"passwd");
         pstmt.setString(3,"김철수");
         pstmt.setString(4,"김철수");
         pstmt.setInt(5,1);
         pstmt.setString(6,"김철수");
         pstmt.setString(7,"김철수");
         pstmt.setString(8,"김철수");
         pstmt.setString(9,"김철수");
         pstmt.setString(10,"김철수");
         pstmt.setString(11,"김철수");

         
         pstmt.executeUpdate(); //쿼리를 실행한다.
         System.out.println("operateSubject 테이블에 새로운 레코드를 추가했습니다.");        // 성공시 메시지 출력
         


      } catch (Exception e) {
         e.printStackTrace();
         System.out.println(e.getMessage());
      }
            

   }
   
   public void delete(OperateSubject o)
   {
      try {
         String deleteQuery = "delete from `"+ dbTable +"` where memberID = ? ";
         
          pstmt = con.prepareStatement(deleteQuery); 
             
          pstmt.setString(1,"test");
          
          pstmt.executeUpdate();
          
          System.out.println("삭제 성공");
      } catch (Exception e) {
         e.printStackTrace();
         System.out.println(e.getMessage());
      }
      
   }
   
   public void update(MemberData m)
   {
      
      try {
         String updateQuery = "update from `"+ dbTable +"` set name = ? where email =? ";
         
         String sql = "select memberID from memberdata where memberID = ?";

         pstmt = con.prepareStatement(sql);

         pstmt.setString(1, "A0000001");

         rs = pstmt.executeQuery();

         
         while( rs.next() )
         {

            String rId= rs.getString("memberID");
            
            
            if ( rId.equals("A0000001")  )
            {
               
                
               //바꾸고 싶은 애트리뷰트는 set 다음에 명시된것
               //where 애트리뷰트가 바꾸고 싶은 행을 찾는 것
               updateQuery = "update memberdata set name = ? where ID = ?"; 
      
               pstmt = con.prepareStatement(updateQuery);
      
               pstmt.setString(1,"asdfsd");
      
               pstmt.setString(2,"a0001");
      
               int count = pstmt.executeUpdate();
               
               System.out.println(count);
               
               System.out.println("변경완료");
            }
         }
      } catch (Exception e) {
         e.printStackTrace();
         System.out.println(e.getMessage());
      }
   }
   
   public void select(MemberData m)
   {
      try
        {
         String selectQuery = "SELECT * FROM `" + dbTable +"`";
         
            //질의를 할 Statement 만들기 
            stmt = con.createStatement();
            
            rs = stmt.executeQuery(selectQuery); //조회 쿼리결과를 rs에 넣음
            
            System.out.println("--- 테이블 student 내용 조회 ---");
            
            //rs의 내용을 가져옴
            while (rs.next())
            {
               
               
                System.out.print(rs.getString(1) + "\t");//첫번째 애튜리뷰트값 
                System.out.print(rs.getString(2) + "\t");//두번째
                System.out.print(rs.getString(3) + "\t");
                System.out.println(rs.getString(4));
                
                //여러 데이터 타입이 가능함
                //blob도 잇음 
            }
            
        }
        catch (Exception e)
        {            
            System.out.println(e.getMessage());
            e.printStackTrace();
        }


   }
   
   public void close()
   {
      if ( stmt != null ) try{stmt.close();}catch(SQLException sqle){}
      if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}            // PreparedStatement 객체 해제

      if(con != null) try{con.close();}catch(SQLException sqle){}            // Connection 해제

   }
   
   public static void main(String[] args)
   {
      MemberControl m = new MemberControl();
      MemberData m1 = new MemberData();
      
      m.insert(m1);
      m.select(m1);
      m.delete(m1);
      m.update(m1);
      m.close();
      
   }

   
}