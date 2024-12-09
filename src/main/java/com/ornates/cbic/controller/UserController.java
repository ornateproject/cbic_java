package com.ornates.cbic.controller;

import com.ornates.cbic.DTO.LoginRequestDTO;
import com.ornates.cbic.DTO.PasswordUpdateDTO;
import com.ornates.cbic.dao.pool.JDBCConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 *
 */
@Controller
@RequestMapping("/api/users")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    //http://localhost:8080/cbicApi/api/users/login?userEmail=""&password=""
    // Login endpoint
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        String query = "SELECT email, password FROM LoginRequest WHERE email = ? AND password = ?";

        try {
            // Get database connection
            con = JDBCConnection.getTNConnection();
            // Prepare the statement
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, loginRequestDTO.getUserEmail());
            pstmt.setString(2, loginRequestDTO.getPassword());
            // Execute query
            resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                return ResponseEntity.ok("Login successful!");
            } else {
                return ResponseEntity.status(401).body("Invalid email or password.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("An error occurred while processing the request.");
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //http://localhost:8080/cbicApi/api/users/updatePassword?userEmail=""&oldPassword=""&newPassword=""
    // Change Password endpoint
    @ResponseBody
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    public ResponseEntity<String> updatePassword(@RequestBody PasswordUpdateDTO passwordUpdateDTO) {

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;

        String selectQuery = "SELECT email, password FROM LoginRequest WHERE email = ? AND password = ?";
        String updateQuery = "UPDATE LoginRequest SET password = ? WHERE email = ?";

        try {
            // Get the database connection
            con = JDBCConnection.getTNConnection();

            // Check if old password is correct
            pstmt = con.prepareStatement(selectQuery);
            pstmt.setString(1, passwordUpdateDTO.getUserEmail());
            pstmt.setString(2, passwordUpdateDTO.getOldPassword());
            resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                // Old password matches, update to the new password
                pstmt.close(); // Close the previous PreparedStatement
                pstmt = con.prepareStatement(updateQuery);
                pstmt.setString(1, passwordUpdateDTO.getNewPassword());
                pstmt.setString(2, passwordUpdateDTO.getUserEmail());
                int rowsUpdated = pstmt.executeUpdate();

                if (rowsUpdated > 0) {
                    return ResponseEntity.ok("Password updated successfully!");
                } else {
                    return ResponseEntity.status(500).body("Failed to update password.");
                }
            } else {
                return ResponseEntity.status(401).body("Invalid old password.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("An error occurred while processing the request.");
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
