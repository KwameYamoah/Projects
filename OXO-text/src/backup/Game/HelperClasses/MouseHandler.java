package backup.Game.HelperClasses;

import backup.Game.System.GameLogic;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandler extends MouseAdapter {
    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        System.out.println("("+e.getX()+","+e.getY()+")");
        if(findPoint(Constants.GRID_LENGTH, Constants.GRID_LENGTH,e.getX(),e.getY())) GameLogic.cellClicked(e.getX(),e.getY());

    }


    private boolean findPoint( int y1, int x1, int x, int y)
    {
        return x > 0 && x < x1 && y < y1 && y > 0;
    }
}

