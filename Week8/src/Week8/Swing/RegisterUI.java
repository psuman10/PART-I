package Week8.Swing;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import Week8.Utils.Registration;

import javax.swing.ButtonGroup;
import javax.swing.JButton;

public class RegisterUI {
    int baseX = 125;
    int baseY = 100;
    int baseWidth = 100;
    int baseHeight = 30;
    int baseInputWidth = baseWidth + 150;
    int baseInputHeight = baseHeight;
    /**
     *
     */

    JFrame frame = new JFrame("REGISTER USER");
    JLabel lblFirstName, lblLastName, lblEmail, lblPhone, lblGender, lblUserName, lblPassword;
    JTextField tfFirstName, tfLastName, tfEmail, tfPhone, tfUserName, tfPassword;
    JRadioButton rbGenderMale, rbGenderFemale;
    JButton btnSave, btnBackToLogin;

    /**
     * Create the frame.
     */
    public RegisterUI() {

        Registration rg = new Registration();

        // DEFINING OBJ OF THE COMPONENTS
        lblFirstName = new JLabel("First Name: ");
        frame.add(lblFirstName);
        lblFirstName.setBounds(baseX, (int) (baseY * 1), baseWidth, baseHeight);
        tfFirstName = new JTextField();
        frame.add(tfFirstName);
        tfFirstName.setBounds(baseX + 125, (int) (baseY * 1), baseInputWidth, baseInputHeight);

        lblLastName = new JLabel("Last Name: ");
        frame.add(lblLastName);
        lblLastName.setBounds(baseX, (int) (baseY * 1.5), baseWidth, baseHeight);
        tfLastName = new JTextField();
        frame.add(tfLastName);
        tfLastName.setBounds(baseX + 125, (int) (baseY * 1.5), baseInputWidth, baseInputHeight);

        lblEmail = new JLabel("Email: ");
        frame.add(lblEmail);
        lblEmail.setBounds(baseX, (int) (baseY * 2), baseWidth, baseHeight);
        tfEmail = new JTextField();
        frame.add(tfEmail);
        tfEmail.setBounds(baseX + 125, (int) (baseY * 2), baseInputWidth, baseInputHeight);

        lblPhone = new JLabel("Phone: ");
        frame.add(lblPhone);
        lblPhone.setBounds(baseX, (int) (baseY * 2.5), baseWidth, baseHeight);
        tfPhone = new JTextField();
        frame.add(tfPhone);
        tfPhone.setBounds(baseX + 125, (int) (baseY * 2.5), baseInputWidth, baseInputHeight);

        lblGender = new JLabel("Gender: ");
        frame.add(lblGender);
        lblGender.setBounds(baseX, (int) (baseY * 3), baseWidth, baseHeight);
        rbGenderMale = new JRadioButton("Male");
        rbGenderMale.setActionCommand("Male");
        frame.add(rbGenderMale);
        rbGenderMale.setBounds(baseX + 125, (int) (baseY * 3), baseWidth, baseHeight);
        rbGenderFemale = new JRadioButton("Female");
        rbGenderFemale.setActionCommand("Female");
        frame.add(rbGenderFemale);
        rbGenderFemale.setBounds(baseX + 225, (int) (baseY * 3), baseWidth, baseHeight);

        ButtonGroup groupGender = new ButtonGroup();
        groupGender.add(rbGenderMale);
        groupGender.add(rbGenderFemale);

        lblUserName = new JLabel("Username: ");
        frame.add(lblUserName);
        lblUserName.setBounds(baseX, (int) (baseY * 3.5), baseWidth, baseHeight);
        tfUserName = new JTextField();
        frame.add(tfUserName);
        tfUserName.setBounds(baseX + 125, (int) (baseY * 3.5), baseInputWidth, baseInputHeight);

        lblPassword = new JLabel("Password: ");
        frame.add(lblPassword);
        lblPassword.setBounds(baseX, (int) (baseY * 4), baseWidth, baseHeight);
        tfPassword = new JPasswordField();
        frame.add(tfPassword);
        tfPassword.setBounds(baseX + 125, (int) (baseY * 4), baseInputWidth, baseInputHeight);

        btnSave = new JButton("Save");
        frame.add(btnSave);
        btnSave.setBounds(baseX + 70, (int) (baseY * 4.5), baseWidth + 20, baseHeight);
        btnBackToLogin = new JButton("Back to Login");
        frame.add(btnBackToLogin);
        btnBackToLogin.setBounds(baseX + 210, (int) (baseY * 4.5), baseWidth + 20, baseHeight);

        btnBackToLogin.addActionListener(e -> {
            new LoginUI();
            frame.dispose();
        });

        btnSave.addActionListener(e -> {
            String firstName = tfFirstName.getText();
            String lastName = tfLastName.getText();
            String fullName = firstName + " " + lastName;
            String email = tfEmail.getText();
            String phone = tfPhone.getText();
            String userName = tfUserName.getText();
            String password = tfPassword.getText();
            String gender = "";
            try {
                gender = groupGender.getSelection().getActionCommand();
            } catch (Exception exc) {
                exc.printStackTrace();
            }

            if (firstName.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "First name is required.");
            } else if (lastName.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Last name is required.");
            } else if (email.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Email is required.");
            } else if (phone.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Phone is required.");
            } else if (gender.isEmpty() || gender.equals(null)) {
                JOptionPane.showMessageDialog(frame, "Gender is required.");
            } else if (userName.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Username is required.");
            } else if (password.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Password is required.");
            } else {
                String userDetail[] = { fullName, email, phone, gender, userName, password };
                String message = rg.registerUser(userDetail);
                JOptionPane.showMessageDialog(frame, message);
                if (message.startsWith("Success")) {
                    new LoginUI();
                    frame.dispose();
                }
            }
        });

        // SETTINGS OF THE FRAME
        frame.setLayout(null);
        frame.setSize(600, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}