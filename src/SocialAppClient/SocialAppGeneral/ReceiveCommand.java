package SocialAppClient.SocialAppGeneral;


import SocialAppClient.Connections.TransmissionFailureListener;

import java.io.DataInputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

/**
 * Created by kemo on 25/10/2016.
 */
public abstract class ReceiveCommand extends Thread {


    private Socket remote;
    private volatile boolean run;
    private ArrayList<TransmissionFailureListener> transmissionFailureListeners;
    public ReceiveCommand(Socket remote)
    {
        transmissionFailureListeners = new ArrayList<>();
        this.remote = remote;
        run = true;
    }
    @Override
    public void run() {
        super.run();

            while (run) {
                try {
                    //TODO update 1 #kareem
                    //choose whether to
                    //make this before the while loop
                    //close it each iterate
                    DataInputStream objectInputStream = new DataInputStream(remote.getInputStream()); //open remote stream
                    Command command = Command.fromString(objectInputStream.readUTF()); //generate command from string
                    Analyze(command); //send it to the abstract function Analyze so other team members do there work
                }
                catch (SocketException e)
                {
                    triggerListeners();
                }
                catch (Exception e) {
                    //Export to log
//                    System.out.println("ReadClientData\t" +
                    e.printStackTrace();
                }
            }
    }
    public void kill()
    {
        run = false;
    }
    public abstract void Analyze(Command command);
    public void setTransmissionFailureListener(TransmissionFailureListener transmissionFailureListener)
    {
        transmissionFailureListeners.add(transmissionFailureListener);
    }
    private void triggerListeners()
    {
        //noinspection ForLoopReplaceableByForEach
        for (int i = 0; i < transmissionFailureListeners.size(); i++) {
            transmissionFailureListeners.get(i).onDisconnection();
        }
    }
    public void setRemote(Socket remote) {
        this.remote = remote;
    }
    public Socket getRemote() {
        return remote;
    }
}
