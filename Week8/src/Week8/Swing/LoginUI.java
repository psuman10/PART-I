package Week8.Swing;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import Week8.Utils.Login;

public class LoginUI {
    JFrame frame = new JFrame("USER LOGIN");

    // INITIALIZING REQUIRED COMPONENTS AND THEIR OBJ INSTANCES
    JLabel lUser, lPass;
    JTextField tfUser;
    JPasswordField tfPass;
    JButton btnLogin, btnSignUp;

    // JOptionPane alertBox;
    public LoginUI() {
        // DEFINING OBJ OF THE COMPONENTS
        lUser = new JLabel("Username: ");
        frame.add(lUser);
        lUser.setBounds(125, 100, 100, 30);
        tfUser = new JTextField();
        frame.add(tfUser);
        tfUser.setBounds(225, 100, 250, 30);
        lPass = new JLabel("Password: ");
        frame.add(lPass);
        lPass.setBounds(125, 160, 100, 30);
        tfPass = new JPasswordField();
        frame.add(tfPass);
        tfPass.setBounds(225, 160, 250, 30);
        btnLogin = new JButton("Login");
        frame.add(btnLogin);
        btnLogin.setBounds(170, 220, 120, 30);
        btnSignUp = new JButton("Sign Up");
        frame.add(btnSignUp);
        btnSignUp.setBounds(310, 220, 120, 30);

        // ACTION LISTENING AND EXECUTING

        btnSignUp.addActionListener(e -> {
            new RegisterUI();
            frame.dispose();
        });

        btnLogin.addActionListener(e -> {
            // LOGIC
            // char passWord[] = tfPass.getPassword();
            String userName = tfUser.getText();
            char passWord[] = tfPass.getPassword();
            String password = "";
            for (char c : passWord) {
                password += c;
            }    
            boolean result = userLogin(userName, password);
            if (result) {
                JOptionPane.showMessageDialog(frame, "WELCOME");
                new DevicesTable();
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(frame, "LOGIN FAILURE");
            }
        });

        // SETTINGS OF THE FRAME
        frame.setLayout(null);
        frame.setSize(600, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public boolean userLogin(String user, String password) {
        Login lg = new Login();
        return lg.checkLogin(user, password);
    }

}
