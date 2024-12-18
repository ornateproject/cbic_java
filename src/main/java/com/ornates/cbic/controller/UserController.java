package com.ornates.cbic.controller;

import com.ornates.cbic.DTO.LoginRequestDTO;
import com.ornates.cbic.DTO.PasswordUpdateDTO;
import com.ornates.cbic.dao.pool.JDBCConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Controller
@RequestMapping("/api/users")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Login endpoint
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;

        String query = "SELECT password FROM LoginRequest WHERE email = ?";

        try {
            con = JDBCConnection.getTNConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, loginRequestDTO.getUserEmail());
            resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                String storedPassword = resultSet.getString("password");

                // Match the entered password with the stored hashed password
                if (passwordEncoder.matches(loginRequestDTO.getPassword(), storedPassword)) {
                    return ResponseEntity.ok("Login successful!");
                } else {
                    return ResponseEntity.status(401).body("Invalid email or password.");
                }
            } else {
                return ResponseEntity.status(404).body("User not found.");
            }
        } catch (SQLException e) {
            logger.error("Error during login", e);
            return ResponseEntity.status(500).body("An error occurred while processing the request.");
        } finally {
            closeResources(con, pstmt, resultSet);
        }
    }

    // Update Password endpoint
    @ResponseBody
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    public ResponseEntity<String> updatePassword(@RequestBody PasswordUpdateDTO passwordUpdateDTO) {
        Connection con = null;
        PreparedStatement selectStmt = null;
        PreparedStatement updateStmt = null;
        ResultSet resultSet = null;

        String selectQuery = "SELECT password FROM LoginRequest WHERE email = ?";
        String updateQuery = "UPDATE LoginRequest SET password = ? WHERE email = ?";

        try {
            con = JDBCConnection.getTNConnection();

            selectStmt = con.prepareStatement(selectQuery);
            selectStmt.setString(1, passwordUpdateDTO.getUserEmail());
            resultSet = selectStmt.executeQuery();

            if (resultSet.next()) {
                String storedPassword = resultSet.getString("password");

                // Compare the entered old password with the stored hashed password
                if (!passwordEncoder.matches(passwordUpdateDTO.getOldPassword(), storedPassword)) {
                    return ResponseEntity.status(401).body("Invalid old password.");
                }

                // Encrypt the new password
                String newEncodedPassword = passwordEncoder.encode(passwordUpdateDTO.getNewPassword());

                // Update the password in the database
                updateStmt = con.prepareStatement(updateQuery);
                updateStmt.setString(1, newEncodedPassword);
                updateStmt.setString(2, passwordUpdateDTO.getUserEmail());

                int rowsUpdated = updateStmt.executeUpdate();
                if (rowsUpdated > 0) {
                    return ResponseEntity.ok("Password updated successfully!");
                } else {
                    return ResponseEntity.status(500).body("Failed to update password.");
                }
            } else {
                return ResponseEntity.status(404).body("User not found.");
            }
        } catch (SQLException e) {
            logger.error("Error during password update", e);
            return ResponseEntity.status(500).body("An error occurred while processing the request.");
        } finally {
            closeResources(con, selectStmt, resultSet);
            closeResources(null, updateStmt, null);
        }
    }

    // Utility method to close database resources
    private void closeResources(Connection con, PreparedStatement pstmt, ResultSet resultSet) {
        try {
            if (resultSet != null) resultSet.close();
            if (pstmt != null) pstmt.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            logger.error("Error closing resources", e);
        }
    }
}