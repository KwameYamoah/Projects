package backup.Game.View;

import backup.Game.HelperClasses.Constants;
import backup.Game.System.GameLogic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonPanel extends JPanel {
    public static JLabel label;
    public static JLabel score;
    public ButtonPanel(){
        setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        JButton o = new JButton("O");
        o.setPreferredSize(new Dimension(Constants.BUTTON_PANEL_WIDTH/2, Constants.BUTTON_PANEL_HEIGHT/2));
        o.addActionListener(new ButtonHandler(0));
        JButton x = new JButton("X");
        x.setPreferredSize(new Dimension(Constants.BUTTON_PANEL_WIDTH/2+1, Constants.BUTTON_PANEL_HEIGHT/2));
        x.addActionListener(new ButtonHandler(1));
        add(o,BorderLayout.NORTH);
        add(x,BorderLayout.NORTH);
        label = new JLabel("Player 1's turn",SwingConstants.CENTER);
        label.setPreferredSize(new Dimension(Constants.BUTTON_PANEL_WIDTH, Constants.BUTTON_PANEL_HEIGHT/4));
        label.setBorder(BorderFactory.createLineBorder(Color.black));
        add(label,BorderLayout.CENTER);

        String s =  "Player 1 : 0\t\t\t\t\tPlayer 2 : 0";
        s = s.replaceAll("\t", "    ");
        score = new JLabel(s,SwingConstants.CENTER);
        score.setPreferredSize(new Dimension(Constants.BUTTON_PANEL_WIDTH, Constants.BUTTON_PANEL_HEIGHT/5));
        add(score,BorderLayout.SOUTH);
    }




    @Override
    public Dimension getPreferredSize() {
        return new Dimension(Constants.BUTTON_PANEL_WIDTH, Constants.BUTTON_PANEL_HEIGHT);
    }

    class ButtonHandler implements ActionListener {
        int move;
        ButtonHandler(int move){
            this.move = move;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if(move==0) GameLogic.move = 'O';
            if(move==1) GameLogic.move = 'X';


        }
    }
}
