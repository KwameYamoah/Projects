package Launcher;

public class GameLauncher {
    private static GameLauncherWindow gameLauncherWindow;

    public static void main(String[] args) {
        initialiseGameLauncher();
    }

    private static void initialiseGameLauncher() {
        gameLauncherWindow = new GameLauncherWindow(getGameWindowContents());
    }

    private static LoginScreen getGameWindowContents() {
        return new LoginScreen();
    }

}
