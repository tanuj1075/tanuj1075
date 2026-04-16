package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 * A Java program to display the first record from the student table (RNo, SName, Per)
 * onto TextFields by clicking a button.
 */
public class StudentApp extends JFrame {
    private JTextField txtRNo, txtSName, txtPer;
    private JButton btnFetch;

    public StudentApp() {
        // UI Setup
        setTitle("Student Record Fetcher");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2, 10, 10));

        // Labels and TextFields
        add(new JLabel("  Roll Number:"));
        txtRNo = new JTextField();
        add(txtRNo);

        add(new JLabel("  Student Name:"));
        txtSName = new JTextField();
        add(txtSName);

        add(new JLabel("  Percentage:"));
        txtPer = new JTextField();
        add(txtPer);

        // Fetch Button
        btnFetch = new JButton("Fetch First Record");
        add(new JLabel("")); // Spacer
        add(btnFetch);

        // Action Listener for Button
        btnFetch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fetchFirstRecord();
            }
        });

        // Initialize Database and Table (Ensures it works out of the box)
        initDB();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Initializes the SQLite database, creates the table if not exists, 
     * and inserts a sample record.
     */
    private void initDB() {
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:student.db";
            
            try (Connection conn = DriverManager.getConnection(url);
                 Statement stmt = conn.createStatement()) {
                
                // 1. Create table
                stmt.execute("CREATE TABLE IF NOT EXISTS student (RNo INTEGER PRIMARY KEY, SName TEXT, Per REAL)");
                
                // 2. Check if empty and insert sample data
                ResultSet rs = stmt.executeQuery("SELECT count(*) FROM student");
                if (rs.next() && rs.getInt(1) == 0) {
                    stmt.execute("INSERT INTO student (RNo, SName, Per) VALUES (101, 'Tanuj', 88.5)");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Startup Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Fetches the first record from the student table and updates the UI.
     */
    private void fetchFirstRecord() {
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:student.db";
            String sql = "SELECT RNo, SName, Per FROM student LIMIT 1";

            try (Connection conn = DriverManager.getConnection(url);
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                if (rs.next()) {
                    txtRNo.setText(String.valueOf(rs.getInt("RNo")));
                    txtSName.setText(rs.getString("SName"));
                    txtPer.setText(String.valueOf(rs.getDouble("Per")));
                } else {
                    JOptionPane.showMessageDialog(this, "The 'student' table is empty!");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Use modern look and feel if available
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // Fallback to default
        }

        // Run UI on Event Dispatch Thread
        SwingUtilities.invokeLater(() -> new StudentApp());
    }
}
