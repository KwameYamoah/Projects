package Launcher;

import javax.swing.*;
import java.awt.*;

public class LoginScreen extends JPanel {
    private static final int LOGIN_SCREEN_WIDTH = 400;
    private static final int LOGIN_SCREEN_HEIGHT = 600;
    private static final int FIELD_LENGTH = 20;
    private JLabel userNameLabel;
    private JTextField userNameField;
    private JLabel passwordLabel;
    private JTextField passwordField;
    private JButton signInButton;
    private JButton exitButton;
    private static Insets WEST_INSETS = new Insets(0,5,5,0);
    private static Insets EAST_INSETS = new Insets(0,5,5,0);

    public LoginScreen(){
        setDefaultLoginScreenValues();
        createLoginLabels();
        createLoginFields();
        createLoginButtons();
        placeLoginScreenComponents();
    }

    private void setDefaultLoginScreenValues() {
        setMaximumSize(new Dimension(LOGIN_SCREEN_WIDTH,LOGIN_SCREEN_HEIGHT));
        setMinimumSize(new Dimension(LOGIN_SCREEN_WIDTH,LOGIN_SCREEN_HEIGHT));
        setLayout(new GridBagLayout());

    }


    private void createLoginLabels() {
        userNameLabel = new JLabel("Username");
        passwordLabel = new JLabel("Password");
    }

    private void createLoginFields() {
        userNameField = new JTextField(FIELD_LENGTH);
        passwordField = new JTextField(FIELD_LENGTH);
    }

    private void createLoginButtons() {
        signInButton = new JButton("Sign in");
        exitButton = new JButton("Exit");
    }

    private void placeLoginScreenComponents() {
        placeUsernameLabel();
        placeUsernameField();
        placePasswordLabel();
        placePasswordField();
        placeSignInButton();
        placeExitButton();
    }

    private void placeUsernameLabel(){
        GridBagConstraints constraints = createGridBagConstraints(0,0, GridBagConstraints.NONE);
        add(userNameLabel, constraints);
    }
    private void placeUsernameField(){
        GridBagConstraints constraints = createGridBagConstraints(1,0, GridBagConstraints.HORIZONTAL);
        add(userNameField, constraints);

    }
    private void placePasswordLabel(){
        GridBagConstraints constraints = createGridBagConstraints(0,1, GridBagConstraints.NONE);
        add(passwordLabel, constraints);
    }
    private void placePasswordField(){
        GridBagConstraints constraints = createGridBagConstraints(1,1, GridBagConstraints.HORIZONTAL);
        add(passwordField, constraints);
    }
    private void placeSignInButton(){
        GridBagConstraints constraints = createGridBagConstraints(0,2, GridBagConstraints.NONE);
        add(signInButton, constraints);
    }

    private void placeExitButton() {
        GridBagConstraints constraints = createGridBagConstraints(1,2, GridBagConstraints.NONE);
        add(exitButton, constraints);
    }

    public GridBagConstraints createGridBagConstraints(int x, int y, int fill){
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.anchor = (x == 0) ? GridBagConstraints.WEST : GridBagConstraints.EAST;
        constraints.fill = fill;
        constraints.insets = (x == 0) ? WEST_INSETS : EAST_INSETS;
        return  constraints;
    }


    @Override
    public void setPreferredSize(Dimension preferredSize) {
        super.setPreferredSize(new Dimension(LOGIN_SCREEN_WIDTH, LOGIN_SCREEN_HEIGHT));
    }
}
