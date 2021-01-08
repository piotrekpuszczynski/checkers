package server;

import frames.GameWindow;

import java.awt.*;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * klasa reprezentujaca klienta
 */
public class Client {
    Scanner in;
    PrintWriter out;
    Socket socket;
    Color color;
    GameWindow gameWindow;
    private boolean turn = false;

    /**
     * @param serverAddress adres serwera
     * @throws Exception wyjatek
     */
    public Client(String serverAddress) throws Exception {

        socket = new Socket(serverAddress, 58989);
        in = new Scanner(socket.getInputStream());
        out = new PrintWriter(socket.getOutputStream(), true);

        String playersAmount = in.nextLine();
        String boardSize = in.nextLine();
        int pawnsAmount = Integer.parseInt(in.nextLine());
        color = new Color(Integer.parseInt(in.nextLine()), Integer.parseInt(in.nextLine()), Integer.parseInt(in.nextLine()));

        gameWindow = new GameWindow(playersAmount, boardSize, pawnsAmount, this);
        play();
    }

    /**
     * @return zwraca kolor gracza
     */
    public Color getColor() { return this.color; }

    /**
     * @param command komenda wysylana do serwera
     */
    public void send(String command) { out.println(command); }

    /**
     * @return zwraca czy klient ma przeprowadzic w danym momencie swoj ruch
     */
    public boolean getTurn() { return this.turn; }

    /**
     * metoda rozpoznaje komende i wykonuje odpowiednie kroki
     * @throws Exception wyjatek
     */
    public void play() throws Exception {
        try {
            while (in.hasNextLine()) {
                String response = in.nextLine();
                System.out.println(response);
                if (response.startsWith("REMOVE")) {
                    gameWindow.getBoard().getMouse().setPawn(gameWindow.getBoard().getMouse().getField(Integer.parseInt(response.split(" ")[1])).getPawn());
                    gameWindow.getBoard().getMouse().getField(Integer.parseInt(response.split(" ")[1])).removePawn();
                } else if (response.startsWith("PUT")) {
                    gameWindow.getBoard().getMouse().getField(Integer.parseInt(response.split(" ")[1])).putPawn(gameWindow.getBoard().getMouse().getPawn());
                    gameWindow.getBoard().getMouse().setPawn(null);
                } else if (response.startsWith("MESSAGE")) {
                    gameWindow.changeState(response);
                    if (response.split(" ")[1].equals("Your")) turn = true;
                    else if (response.split(" ")[1].equals("Waiting")) turn = false;
                } else if (response.startsWith("SHOW")) {
                    for (int i = 0; i < gameWindow.getBoard().getAllFields().size(); i++) {
                        if (gameWindow.getBoard().getAllFields().get(i).getX() <= Integer.parseInt(response.split(" ")[1]) + 1 &&
                                gameWindow.getBoard().getAllFields().get(i).getX() >= Integer.parseInt(response.split(" ")[1]) - 1 &&
                                gameWindow.getBoard().getAllFields().get(i).getY() == Integer.parseInt(response.split(" ")[2])) {
                            gameWindow.getBoard().getAllFields().get(i).setAvailabilityTrue();
                            if (gameWindow.getBoard().getAllFields().get(i).getPawn() != null) {
                                send("NEXT " + gameWindow.getBoard().getAllFields().get(i).getX() + " " + gameWindow.getBoard().getAllFields().get(i).getY());
                            }
                        }
                    }
                } else if (response.startsWith("CHECK")) {
                    for (int i = 0; i < gameWindow.getBoard().getAllFields().size(); i++) {
                        if (gameWindow.getBoard().getAllFields().get(i).getX() <= Integer.parseInt(response.split(" ")[1]) + 1 &&
                                gameWindow.getBoard().getAllFields().get(i).getX() >= Integer.parseInt(response.split(" ")[1]) - 1 &&
                                gameWindow.getBoard().getAllFields().get(i).getY() == Integer.parseInt(response.split(" ")[2])) {
                            if (gameWindow.getBoard().getAllFields().get(i).getPawn() != null) {
                                send("NEXT " + gameWindow.getBoard().getAllFields().get(i).getX() + " " + gameWindow.getBoard().getAllFields().get(i).getY()
                                        + " " + gameWindow.getBoard().getAllFields().get(i).getDiameter());
                            }
                        }
                    }
                } else if (response.startsWith("NEXT")) {
                    for (int i = 0; i < gameWindow.getBoard().getAllFields().size(); i++) {
                        if (gameWindow.getBoard().getAllFields().get(i).getX() <= Integer.parseInt(response.split(" ")[1]) + 1 &&
                                gameWindow.getBoard().getAllFields().get(i).getX() >= Integer.parseInt(response.split(" ")[1]) - 1 &&
                                gameWindow.getBoard().getAllFields().get(i).getY() == Integer.parseInt(response.split(" ")[2])) {
                            gameWindow.getBoard().getAllFields().get(i).setAvailabilityTrue();
                        }
                    }

                } else if (response.startsWith("MOVESTATUS")) {
                    gameWindow.getBoard().getMouse().nextMove = true;
                }
                gameWindow.getBoard().getMouse().panel.repaint();
            }
            out.println("QUIT");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            socket.close();
        }
    }

    /**
     * @return sprawdza czy pionek ma mozliwosc zrobienia ruchu
     */
    public boolean checkAvailability() {
        for (int i = 0; i < gameWindow.getBoard().getAllFields().size(); i++) {
            if (gameWindow.getBoard().getAllFields().get(i).getAvailability()) return true;
        }
        return false;
    }
}
