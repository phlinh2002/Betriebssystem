package networks;

import java.io.IOException;
import java.util.Scanner;

public class ExampleMain {
    public static final int port = 4444;

    public static void main(String[] args) throws InterruptedException {
        int i = (int) System.currentTimeMillis();
        String aKindOfName = Integer.toString(i);
        System.out.println("Gib bitte ein Long");
        Scanner scan = new Scanner(System.in);
        long message = scan.nextLong();


        System.out.println("I call this peer: " + aKindOfName);


        ExamplePeer peer = new ExamplePeer(aKindOfName, port, true, message); // try as server
        Thread peerThread = new Thread(peer);

        peerThread.start();

        // wait a moment
        Thread.sleep(100);

        // okay - or not
        if(peer.isSetupProblem()) {
            // try as client
            System.out.println("failed to start - other peer was most probably faster - try as client");
            peer = new ExamplePeer(aKindOfName, port, false,message); // try as server
            peerThread = new Thread(peer);
            peerThread.start();
        }

        // wait a moment
        Thread.sleep(1);
        if(peer.isSetupProblem()) {
            System.err.println("there seems to be a problem. Check your logs.");
        }

        peerThread.join();
    }
}
