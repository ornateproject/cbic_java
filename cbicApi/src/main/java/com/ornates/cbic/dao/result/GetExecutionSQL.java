package com.ornates.cbic.dao.result;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ornates.cbic.dao.pool.JDBCConnection;

public class GetExecutionSQL {


	public static ResultSet getResult(String query){
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			con= JDBCConnection.getTNConnection();
			ps=con.prepareStatement(query);
			rs=ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;					
	}

}
