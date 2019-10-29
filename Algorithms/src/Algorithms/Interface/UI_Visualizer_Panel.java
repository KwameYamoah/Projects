package Algorithms.Interface;

import Algorithms.Constants;
import Algorithms.Sorting.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UI_Visualizer_Panel extends JPanel implements ActionListener {

    private SortStage sortStage;
    private Timer timer;
    private List<Integer> values;
    private SortingAlgorithm sort;

    UI_Visualizer_Panel(List<Integer> values, String sortType) {
        this.values = values;
        switch(sortType){
            case "Insertion" :
                sort = new InsertionSort();
                break;
            case "Selection" :
                sort = new SelectionSort();
                break;
            case "BubbleSort" :
                sort = new BubbleSort();
                break;
            default:
                sort = null;
                break;
        }

        if(sort != null) {
            if (sort instanceof BubbleSort) {
                timer = new Timer(20, this);
            } else if (sort instanceof SelectionSort) {
                timer = new Timer(200, this);
            } else if (sort instanceof InsertionSort) {
                timer = new Timer(200, this);
            }
            timer.start();
            sortStage = new SortStage();
            sortStage.setAscendingOrder(false);

        }
        else{
            System.out.println("Invalid sort provided");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < values.size(); i++) {
            g.setColor(Color.BLACK);
            if (sortStage != null && !sortStage.isSorted()) {
                if (i == sortStage.getTargetA() || i == sortStage.getTargetB()) {
                    g.setColor(Color.red);
                }
            }
            g.fillRect(i * (Constants.FRAME_WIDTH / Constants.NUMBER_OF_VALUES), 0, (Constants.FRAME_WIDTH / Constants.NUMBER_OF_VALUES), values.get(i));
            g.setColor(Color.WHITE);
            g.drawLine(i * (Constants.FRAME_WIDTH / Constants.NUMBER_OF_VALUES), 0, i * (Constants.FRAME_WIDTH / Constants.NUMBER_OF_VALUES), i * values.get(i));
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        sortStage = sort.nextStepSort(sortStage, values);
        if (sortStage.isSorted()) {
            System.out.println("Sorted");
            timer.stop();
        }
        repaint();
    }
}
