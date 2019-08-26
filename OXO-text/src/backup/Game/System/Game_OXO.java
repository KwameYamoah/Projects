package backup.Game.System;

import backup.Game.HelperClasses.Constants;
import backup.Game.View.ButtonPanel;
import backup.Game.View.GamePanel;
import backup.Game.View.GameView;

public class Game_OXO {

    public static void main(String[] args) {
        GameLogic.gridSize = 6;


        GameLogic gameLogic = new GameLogic();
        Constants.GRID_LENGTH = GameLogic.grid.length*100;
        Constants.BUTTON_PANEL_WIDTH = Constants.GRID_LENGTH;
        GamePanel panel = new GamePanel(gameLogic.getGrid());
        ButtonPanel bPanel = new ButtonPanel();
        GameView view = new GameView("OXO", panel,bPanel);
        view.repaint();





    }
}
