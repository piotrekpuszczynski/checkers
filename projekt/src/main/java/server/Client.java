package server;

import general.Facade;
import general.GameWindow;

import javax.swing.*;
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
    private final Facade facade;
    private boolean turn = false;

    /**
     * @param serverAddress adres serwera
     * @throws Exception wyjatek
     */
    public Client(String serverAddress) throws Exception {
        facade = new Facade();
        facade.setClient(this);

        socket = new Socket(serverAddress, 58989);
        in = new Scanner(socket.getInputStream());
        out = new PrintWriter(socket.getOutputStream(), true);

        String playersAmount = in.nextLine();
        String boardSize = in.nextLine();
        int pawnsAmount = Integer.parseInt(in.nextLine());
        color = new Color(Integer.parseInt(in.nextLine()), Integer.parseInt(in.nextLine()), Integer.parseInt(in.nextLine()));

        new GameWindow(playersAmount, boardSize, pawnsAmount, facade);
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
                    facade.setPawn(facade.getFields().get(Integer.parseInt(response.split(" ")[1])).getPawn());
                    facade.getFields().get(Integer.parseInt(response.split(" ")[1])).removePawn();
                } else if (response.startsWith("PUT")) {
                    facade.getFields().get(Integer.parseInt(response.split(" ")[1])).putPawn(facade.getPawn());
                    facade.setPawn(null);
                } else if (response.startsWith("MESSAGE")) {
                    facade.getGameWindow().changeState(response);
                    if (response.split(" ")[1].equals("Your")) turn = true;
                    else if (response.split(" ")[1].equals("Waiting")) turn = false;
                } else if (response.startsWith("SHOW")) {
                    for (int i = 0; i < facade.getFields().size(); i++) {
                        if (facade.getFields().get(i).getX() <= Integer.parseInt(response.split(" ")[1]) + 1 &&
                                facade.getFields().get(i).getX() >= Integer.parseInt(response.split(" ")[1]) - 1 &&
                                facade.getFields().get(i).getY() == Integer.parseInt(response.split(" ")[2])) {
                            facade.getFields().get(i).setAvailabilityTrue();
                            if (facade.getFields().get(i).getPawn() != null) {
                                send("NEXT " + facade.getFields().get(i).getX() + " " + facade.getFields().get(i).getY());
                            }
                        }
                    }
                } else if (response.startsWith("CHECK")) {
                    for (int i = 0; i < facade.getFields().size(); i++) {
                        if (facade.getFields().get(i).getX() <= Integer.parseInt(response.split(" ")[1]) + 1 &&
                                facade.getFields().get(i).getX() >= Integer.parseInt(response.split(" ")[1]) - 1 &&
                                facade.getFields().get(i).getY() == Integer.parseInt(response.split(" ")[2])) {
                            if (facade.getFields().get(i).getPawn() != null) {
                                send("NEXT " + facade.getFields().get(i).getX() + " " + facade.getFields().get(i).getY()
                                        + " " + facade.getFields().get(i).getDiameter());
                            }
                        }
                    }
                } else if (response.startsWith("NEXT")) {
                    for (int i = 0; i < facade.getFields().size(); i++) {
                        if (facade.getFields().get(i).getX() <= Integer.parseInt(response.split(" ")[1]) + 1 &&
                                facade.getFields().get(i).getX() >= Integer.parseInt(response.split(" ")[1]) - 1 &&
                                facade.getFields().get(i).getY() == Integer.parseInt(response.split(" ")[2])) {
                            facade.getFields().get(i).setAvailabilityTrue();
                        }
                    }

                } else if (response.startsWith("MOVESTATUS")) {
                    facade.getMouse().nextMove = true;
                } else if (response.startsWith("VICTORY")) {
                    JOptionPane.showMessageDialog(facade.getGameWindow(), "Position: " + response.split(" ")[1]);
                    System.exit(0);
                }
                facade.getBoardPanel().repaint();
            }
            out.println("QUIT");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            socket.close();
        }
    }
}
