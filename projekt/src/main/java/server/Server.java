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
                pool.execute(new Capitalizer(listener.accept(), playersAmount, boardSize, pawnsAmount));
            }
        }
    }
    private static class Capitalizer implements Runnable {
        private final Socket socket;
        private final String playersAmount;
        private final String boardSize;
        private final int pawnsAmount;

        Capitalizer(Socket socket, String playersAmount, String boardSize, int pawnsAmount) {
            this.socket = socket;
            this.playersAmount = playersAmount;
            this.boardSize = boardSize;
            this.pawnsAmount = pawnsAmount;
        }

        @Override
        public void run() {
            System.out.println("Connected: " + socket);
            try {
                var in = new Scanner(socket.getInputStream());
                var out = new PrintWriter(socket.getOutputStream(), true);
                out.println(playersAmount);
                out.println(boardSize);
                out.println(pawnsAmount);
                while(in.hasNext()) {
                    System.out.println(in.nextLine());
                }

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
    }
}
