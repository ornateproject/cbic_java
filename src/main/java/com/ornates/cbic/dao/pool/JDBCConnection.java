package com.ornates.cbic.dao.pool;

import java.sql.*;

public class JDBCConnection {

	private static JDBCConnection jdbc;

	public JDBCConnection() {
	}


	public static JDBCConnection getInstance() {
		if (jdbc == null) {
			jdbc = new JDBCConnection();
		}
		return jdbc;
	}



	public static Connection getTNConnection(){


    	String url="jdbc:mysql://localhost:3306/cbecddm"; 		//local for CGST & Custom
		//	String url="jdbc:mysql://103.205.64.103:3306/cbic"; 		//TEST
//		String url="jdbc:postgresql://10.25.121.245:5432/TNPCB" ;//TesT
//		String url="jdbc:postgresql://10.248.110.103:5432/TNPCB"; //Replica Live
//      String url="jdbc:postgresql://dbsrv:5432/TNPCB" ;// Live


		String userName="root";
		String password="Rakesh@500";
   

//    	String userName="cbic_user";
//		String password="CbiC@!321#$";
    	
    	
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url,userName, password);
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
			System.out.println("--ex-- " + ex);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("--e-- " + e);
		}

		return con;

	}
}
