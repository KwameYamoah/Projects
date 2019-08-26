package Application.Socket;

import org.junit.Test;

import static org.junit.Assert.*;

public class MoveTest {

    @Test
    public void parse() {
        Move move = Move.parse("n 5.4");
        assertEquals(move.cardType, MoveType.NO_CARD);
        assertEquals(move.cell[0], new Cell(5, 4));
        Move move2 = Move.parse("d 3.4 1.2 ");
        assertEquals(move2.cell[0], new Cell(3, 4));
        assertEquals(move2.cell[1], new Cell(1, 2));
    }

}