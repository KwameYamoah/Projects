package Launcher;

import javax.swing.*;
import java.awt.*;

public class LauncherWindow extends JFrame {
    private static JFrame  launcherWindow;
    private LoginScreen launcherWindowContents;
    private static final int LAUNCHER_WINDOW_WIDTH = 800;
    private static final int LAUNCHER_WINDOW_HEIGHT = 600;


    public LauncherWindow(LoginScreen launcherWindowContents){
        storeWindowContents(launcherWindowContents);
        initialiseLauncher();
    }

    private void storeWindowContents(LoginScreen launcherWindowContents) {
        this.launcherWindowContents = launcherWindowContents;
    }

    private  void initialiseLauncher() {
        createGameWindow();
        createLoginScreen();
        setDefaultLauncherWindowValues();
    }

    private  void createGameWindow() {
        launcherWindow = new JFrame();
    }

    private  void createLoginScreen() {
        launcherWindow.add(launcherWindowContents);
    }

    private  void setDefaultLauncherWindowValues(){
        launcherWindow.setUndecorated(true);
        launcherWindow.setSize(LAUNCHER_WINDOW_WIDTH, LAUNCHER_WINDOW_HEIGHT);
        launcherWindow.setLocationRelativeTo(null);
        launcherWindow.setVisible(true);
        launcherWindow.getRootPane().setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GRAY));

    }
}
