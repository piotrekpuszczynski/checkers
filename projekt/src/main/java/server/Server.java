package server;

import layout.fields.Field;
import layout.fields.FieldsLayoutFactory;
import layout.fields.LayoutProducer;
import layout.pawns.Pawn;
import layout.pawns.PawnsPutterFactory;
import layout.pawns.PawnsPutterProducer;
import server.winning.WinFactory;
import server.winning.WinProducer;

import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Executors;

import static java.lang.StrictMath.abs;

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

    /**
     * klasa reprezentujaca gre
     */
    static class Game {
        Player currentPlayer;
        ArrayList<Player> opponents = new ArrayList<>();
        ArrayList<Player> tempOpponents = new ArrayList<>();
        private final Color[] colors = {Color.GREEN, Color.RED, Color.BLUE, Color.YELLOW, Color.CYAN, Color.MAGENTA};
        int position = 1;
        boolean changePlayer = false;
        final int random;
        WinFactory winFactory;
        private final List<Field> fields;
        Pawn pawn;

        /**
         * @param random randomowa liczba wybierajaca kolor
         */
        Game(int random, String playersAmount, String boardSize, int pawnsAmount) {
            this.random = random;
            FieldsLayoutFactory fieldsLayout = new LayoutProducer().getFactory(boardSize);
            fieldsLayout.initializeFields(1000, 1000);
            PawnsPutterFactory pawnsPutter = new PawnsPutterProducer().getPutter(playersAmount);
            pawnsPutter.setFields(fieldsLayout.getFields());
            pawnsPutter.putPawns(pawnsAmount);
            fields = fieldsLayout.getFields();
            winFactory = new WinProducer().getWin(playersAmount);
            winFactory.setPawnsAmount(pawnsAmount);
            winFactory.setFields(fieldsLayout.getFields());
        }

        /**
         * gracz
         */
        private class Player implements Runnable {
            private final Socket socket;
            private int playersAmount;
            private final String boardSize;
            private final int pawnsAmount;
            Scanner in;
            PrintWriter out;
            private final Color color;
            int startX = 0, startY = 0, x, y, lastX, lastY;
            boolean flag = false;

            /**
             * @param socket polaczenie z serwerem
             * @param playersAmount liczba graczy
             * @param boardSize rozmiar planszy
             * @param pawnsAmount liczba pionkow
             * @param color kolor gracza
             */
            Player(Socket socket, String playersAmount, String boardSize, int pawnsAmount, Color color) {
                this.socket = socket;
                this.playersAmount = Integer.parseInt(playersAmount);
                this.boardSize = boardSize;
                this.pawnsAmount = pawnsAmount;
                this.color = color;
            }

            /**
             * metoda przyjmuje i wysyla komendy
             */
            @Override
            public void run() {
                System.out.println("Connected: " + socket);
                try {
                    setup();
                    processCommands();
                } catch (Exception e) {
                    System.out.println("Error:" + socket);
                } finally {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Closed: " + socket);
                }
            }

            /**
             * metoda wysyla dane do klienta
             * @throws IOException wyjatek
             */
            private void setup() throws IOException {
                in = new Scanner(socket.getInputStream());
                out = new PrintWriter(socket.getOutputStream(), true);
                out.println(playersAmount);
                out.println(boardSize);
                out.println(pawnsAmount);
                out.println(color.getRed());
                out.println(color.getGreen());
                out.println(color.getBlue());

                if (color.equals(colors[random])) {
                    currentPlayer = this;
                    flag = true;
                }
                else if (flag) opponents.add(this);
                else tempOpponents.add(this);

                if (opponents.size() + tempOpponents.size() == playersAmount - 1 && currentPlayer != null) {
                    opponents.addAll(tempOpponents);
                    currentPlayer.out.println("MESSAGE Your move");
                }
                else {
                    for (Player o: opponents) o.out.println("MESSAGE Waiting for opponents to connect");
                    if (currentPlayer != null) currentPlayer.out.println("MESSAGE Waiting for opponents to connect");
                }
            }

            /**
             * metoda rozpoznajaca komende
             */
            private void processCommands() {
                while (in.hasNextLine()) {
                    String command = in.nextLine();
                    if (command.startsWith("QUIT")) {
                        return;
                    } else if (command.startsWith("REMOVE")) {
                        for (Player o: opponents) o.out.println(command);

                        pawn = fields.get(Integer.parseInt(command.split(" ")[1])).getPawn();
                        fields.get(Integer.parseInt(command.split(" ")[1])).removePawn();

                        startX = Integer.parseInt(command.split(" ")[2]);
                        startY = Integer.parseInt(command.split(" ")[3]);

                        if (command.split(" ")[5].equals("false")) sendAvailableFields(Integer.parseInt(command.split(" ")[4]), "SHOW");
                    } else if (command.startsWith("PUT")) {
                        for (Player o: opponents) o.out.println(command);

                        if ((abs((Integer.parseInt(command.split(" ")[3]) - startY)) >= 2 * Integer.parseInt(command.split(" ")[4]) - 1
                                && abs((Integer.parseInt(command.split(" ")[3]) - startY)) <= 2 * Integer.parseInt(command.split(" ")[4]) + 1)
                                || ((abs((Integer.parseInt(command.split(" ")[2]) - startX)) <= 2 * Integer.parseInt(command.split(" ")[4]) + 1)
                                && abs((Integer.parseInt(command.split(" ")[2]) - startX)) >= 2 * Integer.parseInt(command.split(" ")[4]) - 1)) {

                            startX = Integer.parseInt(command.split(" ")[2]);
                            startY = Integer.parseInt(command.split(" ")[3]);

                            currentPlayer.out.println("MOVESTATUS");
                            sendAvailableFields(Integer.parseInt(command.split(" ")[4]), "CHECK");
                        } else {
                            changePlayer = true;
                            //currentPlayer.out.println("MESSAGE Waiting for opponent to move");
                            //opponents.add(currentPlayer);
                            //currentPlayer = opponents.get(0);
                            //opponents.remove(0);
                            //currentPlayer.out.println("MESSAGE Your move");
                        }

                        fields.get(Integer.parseInt(command.split(" ")[1])).putPawn(pawn);
                        pawn = null;
                    } else if (command.startsWith("MESSAGE")) {
                        currentPlayer.out.println("MESSAGE Waiting for opponent to move");
                        opponents.add(currentPlayer);
                        currentPlayer = opponents.get(0);
                        opponents.remove(0);
                        currentPlayer.out.println("MESSAGE Your move");
                    } else if (command.startsWith("NEXT")) {
                        lastX = startX + 2 * (Integer.parseInt(command.split(" ")[1]) - startX);
                        lastY = startY + 2 * (Integer.parseInt(command.split(" ")[2]) - startY);
                        currentPlayer.out.println("NEXT " + lastX + " " + lastY);
                    }

                    Color won = winFactory.checkWholeBoard();
                    if (won != null) {
                        //for (Player p: opponents) {
                        //    if (won.equals(p.color)) {
                                currentPlayer.out.println("VICTORY " + position);
                                currentPlayer = opponents.get(0);
                                opponents.remove(0);
                                playersAmount--;
                                currentPlayer.out.println("MESSAGE Your move");
                                //opponents.remove(p);
                            //}
                        //}
                        position++;
                    } else if (changePlayer) {
                        currentPlayer.out.println("MESSAGE Waiting for opponent to move");
                        opponents.add(currentPlayer);
                        currentPlayer = opponents.get(0);
                        opponents.remove(0);
                        currentPlayer.out.println("MESSAGE Your move");
                        changePlayer = false;
                    }
                }
            }

            /**
             * @param diameter srednica pola
             * @param command nazwa komendy
             */
            public void sendAvailableFields(int diameter, String command) {

                x = startX - (diameter / 2);
                y = startY - diameter;
                currentPlayer.out.println(command + " " + x + " " + y);

                x = startX + (diameter / 2);
                y = startY - diameter;
                currentPlayer.out.println(command + " " + x + " " + y);

                x = startX + diameter;
                y = startY;
                currentPlayer.out.println(command + " " + x + " " + y);

                x = startX + (diameter / 2);
                y = startY + diameter;
                currentPlayer.out.println(command + " " + x + " " + y);

                x = startX - (diameter / 2);
                y = startY + diameter;
                currentPlayer.out.println(command + " " + x + " " + y);

                x = startX - diameter;
                y = startY;
                currentPlayer.out.println(command + " " + x + " " + y);
            }
        }
    }
}
