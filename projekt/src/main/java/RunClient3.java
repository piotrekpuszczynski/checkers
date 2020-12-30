import server.Client;

public class RunClient3 {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println("Pass the server IP as the sole command line argument");
            return;
        }
        new Client(args[0]);
    }
}
