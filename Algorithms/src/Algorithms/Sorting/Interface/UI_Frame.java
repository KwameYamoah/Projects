package Algorithms.Sorting.Interface;

import Algorithms.Constants;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class UI_Frame extends JFrame {

    UI_Frame(List<Integer> values) {
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(new UI_Visualizer_Panel(values,"Selection"));
        pack();
        setLocationRelativeTo(null);
        setTitle("Sorting Algorithm Visualization");
    }

    public static void main(String[] args) {
        Random rand = new Random();
        List<Integer> values = new ArrayList<>();
        for (int i = 0; i < Constants.NUMBER_OF_VALUES; i++) {
            values.add(rand.nextInt(Constants.RANGE_OF_VALUES) + 1);
        }
        new UI_Frame(values);
    }
}





