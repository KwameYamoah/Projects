package Launcher;

import org.junit.*;

import java.awt.*;

import static org.junit.Assert.*;

public class GameLauncherWindowTest {
    private GameLauncherWindow gameLauncherWindow;
    @Before
    public void setUP(){

        LoginScreen loginScreen = new LoginScreen();
        gameLauncherWindow = new GameLauncherWindow(loginScreen);
    }

    @Test
    public void checkIfWindowContentsIsStored() {
        assert (gameLauncherWindow.hasContents());
    }

    @Test
    public void checkIfLauncherWindowTitleBarHasBeenRemoved() {
        assert (gameLauncherWindow.isUndecorated());
    }

    @Test
    public void checkIfLauncherWindowSizeIsCorrect() {
        assertEquals(gameLauncherWindow.getSize(),new Dimension(GameLauncherWindow.LAUNCHER_WINDOW_WIDTH,
                GameLauncherWindow.LAUNCHER_WINDOW_HEIGHT));
    }

    @Test
    public void checkIfLauncherIsLocatedAtMiddle() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        assert(roundDownToNearestHundred(screenSize.getWidth()) ==
                roundDownToNearestHundred(gameLauncherWindow.getX()*2+gameLauncherWindow.getWidth()));

        assert(roundDownToNearestHundred(screenSize.getHeight()) ==
                roundDownToNearestHundred(gameLauncherWindow.getY()*2 + gameLauncherWindow.getHeight()));
    }


    private int roundDownToNearestHundred(double i){
        int roundedDownNumber =((int)i/100)*100;
        return roundedDownNumber;
    }

    @Test
    public void checkIfLauncherIsVisible() {
        assert(gameLauncherWindow.isVisible());
    }
}