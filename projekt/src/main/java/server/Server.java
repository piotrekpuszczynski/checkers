package server;

import java.awt.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Random;
import java.util.concurrent.Executors;

/**
 * klasa serwera
 */
public class Server {

    /**
     * @param playersAmount liczba graczy
     * @param boardSize rozmiar planszy
     * @param pawnsAmount liczba pionkow
     * @throws IOException wyjatek
     */
    public void runServer(String playersAmount, String boardSize, int pawnsAmount) throws IOException {
        try (var listener = new ServerSocket(58989)) {
            System.out.println("Server is Running...");
            var pool = Executors.newFixedThreadPool(Integer.parseInt(playersAmount));
            while (true) {
                Game game = new Game(new Random().nextInt(Integer.parseInt(playersAmount)), playersAmount, boardSize, pawnsAmount);
                switch (playersAmount) {
                    case "6":
                        pool.execute(game.new Player(listener.accept(), playersAmount, boardSize, pawnsAmount, Color.MAGENTA));
                        pool.execute(game.new Player(listener.accept(), playersAmount, boardSize, pawnsAmount, Color.CYAN));
                    case "4":
                        pool.execute(game.new Player(listener.accept(), playersAmount, boardSize, pawnsAmount, Color.YELLOW));
                    case "3":
                        pool.execute(game.new Player(listener.accept(), playersAmount, boardSize, pawnsAmount, Color.BLUE));
                    case "2":
                        pool.execute(game.new Player(listener.accept(), playersAmount, boardSize, pawnsAmount, Color.GREEN));
                        pool.execute(game.new Player(listener.accept(), playersAmount, boardSize, pawnsAmount, Color.RED));
                }
            }
        }
    }
}
