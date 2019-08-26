package Application.Socket;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class CellTest {

    @Test
    public void parse() {

        Random random = new Random();
        String row = (random.nextInt(10) + 1) + "";
        String column = (random.nextInt(10) + 1) + "";

        String parseString = row + "." + column;
        Cell testCell = Cell.parse(parseString);
        assertEquals(parseString, testCell.row + "." + testCell.column);
    }
}