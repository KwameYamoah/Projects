package Launcher;

import javax.swing.*;
import java.awt.*;

public class GameLauncherWindow extends JFrame {
    private LoginScreen gameLauncherWindowContents;
    public static final int LAUNCHER_WINDOW_WIDTH = 800;
    public static final int LAUNCHER_WINDOW_HEIGHT = 600;


    public GameLauncherWindow(LoginScreen gameLauncherWindowContents){
        storeWindowContents(gameLauncherWindowContents);
        initialiseLauncher();
    }

    private void storeWindowContents(LoginScreen launcherWindowContents) {
        this.gameLauncherWindowContents = launcherWindowContents;
    }

    private  void initialiseLauncher() {
        setDefaultLauncherWindowValues();
        addLoginScreen();
    }

    private  void setDefaultLauncherWindowValues(){
        setUndecorated(true);
        setSize(LAUNCHER_WINDOW_WIDTH, LAUNCHER_WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        setVisible(true);
        getRootPane().setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GRAY));

    }

    private  void addLoginScreen() {
        add(gameLauncherWindowContents);
    }

    public boolean hasContents() {
        return gameLauncherWindowContents != null;
    }
}
