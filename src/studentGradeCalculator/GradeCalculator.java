package studentGradeCalculator;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class GradeCalculator {
	 private static final String USERNAME = "Sai";
	    private static final String PASSWORD = "Sai1";

	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(LoginFrame::new);
	    }

	    static class LoginFrame extends JFrame {
	        private JTextField usernameField;
	        private JPasswordField passwordField;

	        LoginFrame() {
	            super("Login");
	            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            setResizable(false);

	            JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
	            panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

	            JLabel usernameLabel = new JLabel("Username:");
	            usernameField = new JTextField(20);
	            panel.add(usernameLabel);
	            panel.add(usernameField);

	            JLabel passwordLabel = new JLabel("Password:");
	            passwordField = new JPasswordField(20);
	            panel.add(passwordLabel);
	            panel.add(passwordField);

	            JButton loginButton = new JButton("Login");
	            loginButton.addActionListener(new LoginListener());
	            panel.add(loginButton);

	            add(panel);
	            pack();
	            setLocationRelativeTo(null);
	            setVisible(true);
	        }

	        class LoginListener implements ActionListener {
	            public void actionPerformed(ActionEvent e) {
	                String usernameInput = usernameField.getText();
	                String passwordInput = new String(passwordField.getPassword());

	                if (usernameInput.equals(USERNAME) && passwordInput.equals(PASSWORD)) {
	                    dispose(); // Close login window
	                    new MainFrame(); // Open grade calculator window
	                } else {
	                    JOptionPane.showMessageDialog(LoginFrame.this, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
	                }
	            }
	        }
	    }

	    static class MainFrame extends JFrame {
	        private JTextField[] subjectFields;
	        private JLabel totalMarksLabel;
	        private JLabel averagePercentageLabel;
	        private JLabel gradeLabel;

	        MainFrame() {
	            super("Grade Calculator");
	            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            setResizable(false);

	            JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
	            panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

	            JLabel titleLabel = new JLabel("Enter marks obtained in each subject (out of 100):");
	            titleLabel.setHorizontalAlignment(JLabel.CENTER);
	            panel.add(titleLabel);

	            int numSubjects = 5; // Assuming 5 subjects
	            subjectFields = new JTextField[numSubjects];
	            for (int i = 0; i < numSubjects; i++) {
	                JLabel subjectLabel = new JLabel("Subject " + (i + 1) + ":");
	                panel.add(subjectLabel);
	                subjectFields[i] = new JTextField(5);
	                panel.add(subjectFields[i]);
	            }

	            JButton calculateButton = new JButton("Calculate");
	            calculateButton.addActionListener(new CalculateListener());
	            panel.add(calculateButton);

	            totalMarksLabel = new JLabel();
	            panel.add(new JLabel("Total Marks:"));
	            panel.add(totalMarksLabel);

	            averagePercentageLabel = new JLabel();
	            panel.add(new JLabel("Average Percentage:"));
	            panel.add(averagePercentageLabel);

	            gradeLabel = new JLabel();
	            panel.add(new JLabel("Grade:"));
	            panel.add(gradeLabel);
	            JButton exitButton = new JButton("Exit");
	            exitButton.addActionListener(e -> System.exit(0));
	            panel.add(exitButton);

	            add(panel);
	            pack();
	            setLocationRelativeTo(null);
	            setVisible(true);
	        }

	        class CalculateListener implements ActionListener {
	            public void actionPerformed(ActionEvent e) {
	                int totalMarks = 0;
	                int numSubjects = subjectFields.length;

	                for (JTextField field : subjectFields) {
	                    try {
	                        int marks = Integer.parseInt(field.getText());
	                        if (marks < 0 || marks > 100) {
	                            JOptionPane.showMessageDialog(MainFrame.this, "Marks should be between 0 and 100", "Error", JOptionPane.ERROR_MESSAGE);
	                            return;
	                        }
	                        totalMarks += marks;
	                    } catch (NumberFormatException ex) {
	                        JOptionPane.showMessageDialog(MainFrame.this, "Please enter valid marks", "Error", JOptionPane.ERROR_MESSAGE);
	                        return;
	                    }
	                }

	                double averagePercentage = (double) totalMarks / numSubjects;
	                String grade;

	                if (averagePercentage >= 90) {
	                    grade = "A+";
	                } else if (averagePercentage >= 80) {
	                    grade = "A";
	                } else if (averagePercentage >= 70) {
	                    grade = "B";
	                } else if (averagePercentage >= 60) {
	                    grade = "C";
	                } else if (averagePercentage >= 50) {
	                    grade = "D";
	                } else {
	                    grade = "F";
	                }

	                totalMarksLabel.setText(Integer.toString(totalMarks));
	                averagePercentageLabel.setText(String.format("%.2f%%", averagePercentage));
	                gradeLabel.setText(grade);
	            }
	        }
	    }
}
