package Application.Socket;

import Application.Game;
import Application.GridState;
import org.junit.Before;
import org.junit.Test;


import java.net.Socket;
import java.util.Random;

import static org.junit.Assert.*;

public class GameServiceTest {

    private GameService gameService;
    private Game game;


    @Before
    public void setUp() {
        game = new Game();
        gameService = new GameService();


    }

    @Test
    public void turnToColor() {

        Random random = new Random();
        String[] strings = {"Red", "Blue", "Green", "Pink", "Yellow"};
        int randomIndex = random.nextInt(5) + 1;
        assertEquals(gameService.color(randomIndex), strings[randomIndex - 1]);


    }

    @Test
    public void execute() {
        Player player = new Player("Red", GridState.OCCUPIED1, new Socket());
        assertEquals(gameService.execute(game, Move.parse("f 1.1"), player), "You have used a freedom card");
    }


}