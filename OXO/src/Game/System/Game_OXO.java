package Game.System;

import Game.HelperClasses.Constants;
import Game.System.GameLogic;
import Game.View.ButtonPanel;
import Game.View.GamePanel;
import Game.View.GameView;

public class Game_OXO {

    public static void main(String[] args) {
        GameLogic.gridSize = 4;


        GameLogic gameLogic = new GameLogic();
        Constants.GRID_LENGTH = GameLogic.grid.length*100;
        Constants.BUTTON_PANEL_WIDTH = Constants.GRID_LENGTH;
        GamePanel panel = new GamePanel(gameLogic.getGrid());
        ButtonPanel bPanel = new ButtonPanel();
        GameView view = new GameView("OXO", panel,bPanel);
        view.repaint();





    }
}
