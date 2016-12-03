<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8" import="java.sql.*"%>
<%
   request.setCharacterEncoding("utf-8");
%>
<%@ page import="java.sql.*"%>
<%
   //여기는 게시판 정보 등록 부분
   String dataBaseName = "test";
   String dbAddress = "localhost:3306";
   String accessId = "root";
   String accessPw = "7942";
   String table = "establishprocess";
   String sql = "Insert into " + table
         + "(processName, trainingPeriodStart, trainingPeriodEnd, classRoom, lecturerName, tuition, isApply, centerName, refundSort,isAccepted, detailLectureInfo, completeStandard, processCode) Values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

   Connection connection = null;
   PreparedStatement statement = null;
   
   try {
      String driverName = "com.mysql.jdbc.Driver";
      String dbURL = "jdbc:mysql://" + dbAddress + "/" + dataBaseName;
      Class.forName(driverName);
    
      // 디비 연결
      connection = DriverManager.getConnection(dbURL, accessId, accessPw);
      out.println("asdfasdf");
      statement = connection.prepareStatement(sql);

      String processName = "개설과정";
      int trainingPeriodStart = 20160913;
      int trainingPeriodEnd = 20161121;
      String classRoom = "D330";
      String lecturerName = "홍길동";
      int tuition = 30000;
      int isApply = 1;
      String centerName = "서울";
      String refundSort = "사업주";
      int isAccepted = 1;
      String detailLectureInfo = "1주차 : C++ 2주차 : 자바 3주차 : C#";
      String completeStandart = "출석률80%";
      String processCode = "BA0001";
      
      statement.setString(1, processName);
      statement.setInt(2, trainingPeriodStart);
      statement.setInt(3, trainingPeriodEnd);
      statement.setString(4, classRoom);
      statement.setString(5, lecturerName);
      statement.setInt(6, tuition);
      statement.setInt(7, isApply);
      statement.setString(8, centerName);
      statement.setString(9, refundSort);
      statement.setInt(10, isAccepted);
      statement.setString(11, detailLectureInfo);
      statement.setString(12, completeStandart);
      statement.setString(13, processCode);
      
      // 실행
      statement.executeUpdate();
   } catch (Exception e) {
      out.println("MySql 데이터베이스 " + dataBaseName + "접속에 문제가 있습니다.");

      // 데이터베이스 인젝션 공격 방지를 위해 에러내용 주석 처리
      out.println(e.getMessage());
      e.printStackTrace();
   }
%>