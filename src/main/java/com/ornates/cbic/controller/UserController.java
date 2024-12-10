package com.ornates.cbic.controller;

import com.ornates.cbic.DTO.LoginRequestDTO;
import com.ornates.cbic.DTO.PasswordUpdateDTO;
import com.ornates.cbic.DTO.UserRegistrationDTO;
import com.ornates.cbic.dao.pool.JDBCConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
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

    // Registration API
    @PostMapping("/register")
    @ResponseBody
    //http://localhost:8080/cbicApi/api/users/register
    public ResponseEntity<String> register(@RequestBody UserRegistrationDTO userRegistrationDTO) {
        Connection con = null;
        PreparedStatement pstmt = null;

        String query = "INSERT INTO LoginRequest (email, password) VALUES (?, ?)";
        try {
            con = JDBCConnection.getTNConnection();
            pstmt = con.prepareStatement(query);

            // Hash the password before storing it
            String hashedPassword = BCrypt.hashpw(userRegistrationDTO.getPassword(), BCrypt.gensalt());
            pstmt.setString(1, userRegistrationDTO.getUserEmail());
            pstmt.setString(2, hashedPassword);

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                return ResponseEntity.ok("User registered successfully!");
            } else {
                return ResponseEntity.status(500).body("Failed to register user.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("An error occurred while processing the request.");
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Login API
    @PostMapping("/login")
    @ResponseBody
    // http://localhost:8080/cbicApi/api/users/login
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
                String hashedPassword = resultSet.getString("password");
                // Verify password
                if (BCrypt.checkpw(loginRequestDTO.getPassword(), hashedPassword)) {
                    return ResponseEntity.ok("Login successful!");
                } else {
                    return ResponseEntity.status(401).body("Invalid email or password.");
                }
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

    // Update Password API
    @PutMapping("/updatePassword")
    @ResponseBody
    // http://localhost:8080/cbicApi/api/users/updatePassword
    public ResponseEntity<String> updatePassword(@RequestBody PasswordUpdateDTO passwordUpdateDTO) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;

        String selectQuery = "SELECT password FROM LoginRequest WHERE email = ?";
        String updateQuery = "UPDATE LoginRequest SET password = ? WHERE email = ?";

        try {
            con = JDBCConnection.getTNConnection();

            // Check if old password matches
            pstmt = con.prepareStatement(selectQuery);
            pstmt.setString(1, passwordUpdateDTO.getUserEmail());
            resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                String hashedPassword = resultSet.getString("password");
                if (BCrypt.checkpw(passwordUpdateDTO.getOldPassword(), hashedPassword)) {
                    // Update to the new password
                    pstmt.close();
                    pstmt = con.prepareStatement(updateQuery);
                    String newHashedPassword = BCrypt.hashpw(passwordUpdateDTO.getNewPassword(), BCrypt.gensalt());
                    pstmt.setString(1, newHashedPassword);
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
            } else {
                return ResponseEntity.status(401).body("User not found.");
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
