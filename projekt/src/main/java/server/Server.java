package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Executors;

public class Server {

    public void runServer(String playersAmount, String boardSize, int pawnsAmount) throws IOException {
        try (var listener = new ServerSocket(58989)) {
            System.out.println("Server is Running...");
            var pool = Executors.newFixedThreadPool(200);
            while (true) {
                Game game = new Game();
                pool.execute(game.new Player(listener.accept(), playersAmount, boardSize, pawnsAmount, '0'));
                pool.execute(game.new Player(listener.accept(), playersAmount, boardSize, pawnsAmount, '1'));
            }
        }
    }

    static class Game {
        Player currentPlayer;

        public synchronized void move(int location, Player player) {
            if (player != currentPlayer) {
                throw new IllegalStateException("Not your turn");
            } else if (player.opponent == null) {
                throw new IllegalStateException("You don't have an opponent yet");
            }
            currentPlayer = currentPlayer.opponent;
        }

        private class Player implements Runnable {
            private final Socket socket;
            private final String playersAmount;
            private final String boardSize;
            private final int pawnsAmount;
            Scanner in;
            PrintWriter out;
            Player opponent;
            char mark;

            Player(Socket socket, String playersAmount, String boardSize, int pawnsAmount, char mark) {
                this.socket = socket;
                this.playersAmount = playersAmount;
                this.boardSize = boardSize;
                this.pawnsAmount = pawnsAmount;
                this.mark = mark;
            }

            @Override
            public void run() {
                System.out.println("Connected: " + socket);
                try {
                    setup();
                    processCommands();
                } catch (Exception e) {
                    System.out.println("Error:" + socket);
                } finally {
                    if (opponent != null && opponent.out != null) {
                        opponent.out.println("OTHER PLAYER LEFT");
                    }
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Closed: " + socket);
                }
            }

            private void setup() throws IOException {
                in = new Scanner(socket.getInputStream());
                out = new PrintWriter(socket.getOutputStream(), true);
                out.println(playersAmount);
                out.println(boardSize);
                out.println(pawnsAmount);
                if (mark == '0') {
                    currentPlayer = this;
                    out.println("MESSAGE Waiting for opponent to connect");
                } else {
                    opponent = currentPlayer;
                    opponent.opponent = this;
                    opponent.out.println("MESSAGE Your move");
                }
            }

            private void processCommands() {
                while (in.hasNextLine()) {
                    var command = in.nextLine();
                    if (command.startsWith("QUIT")) {
                        return;
                    } else if (command.startsWith("MOVE")) {
                        processCommand(Integer.parseInt(command.substring(5)));
                    } else if (command.startsWith("REMOVE")) {
                        opponent.out.println(command);
                    } else if (command.startsWith("PUT")) {
                        opponent.out.println(command);
                    }
                }
            }

            private void processCommand(int location) {
                try {
                    move(location, this);
                    out.println("VALID_MOVE");
                    opponent.out.println("OPPONENT_MOVED " + location);
                    //if (hasWinner()) {
                    //    output.println("VICTORY");
                    //    opponent.output.println("DEFEAT");
                    //} else if (boardFilledUp()) {
                    //    output.println("TIE");
                    //    opponent.output.println("TIE");
                    //}
                } catch (IllegalStateException e) {
                    out.println("MESSAGE " + e.getMessage());
                }
            }
        }
    }
}
