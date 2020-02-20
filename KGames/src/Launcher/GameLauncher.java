package Launcher;

public class GameLauncher {
    private static LauncherWindow launcherWindow;

    public static void main(String[] args) {
        initialiseGameLauncher();
    }

    private static void initialiseGameLauncher() {
        launcherWindow = new LauncherWindow(getGameWindowContents());
    }

    private static LoginScreen getGameWindowContents() {
        return new LoginScreen();
    }
}
