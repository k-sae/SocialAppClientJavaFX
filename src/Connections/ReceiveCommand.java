package Connections;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
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

    public ReceiveCommand(Socket remote) {
        transmissionFailureListeners = new ArrayList<>();
        this.remote = remote;
        run = true;
    }

    @Override
    public void run() {
        super.run();

        while (run) {
            try {
                DataInputStream objectInputStream = new DataInputStream(remote.getInputStream()); //open remote stream
                Command command = Command.fromString(objectInputStream.readUTF()); //generate command from string
                Analyze(command); //send it to the abstract function Analyze so other team members to do there work
            }catch (EOFException e)
            {
                break;
            }
            catch (SocketException e) {
                triggerListeners();
            } catch (Exception e) {
                //Export to log
//                    System.out.println("ReadClientData\t" +
                e.printStackTrace();
            }
        }
    }

    public void kill() {
        run = false;
    }

    public abstract void Analyze(Command command);

    public void setTransmissionFailureListener(TransmissionFailureListener transmissionFailureListener) {
        transmissionFailureListeners.add(transmissionFailureListener);
    }

    private void triggerListeners() {
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

    public void sendCommand(Command command) {
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(remote.getOutputStream());
            dataOutputStream.writeUTF(command.toString());
        } catch (
                IOException e) {
            //For debugging
            System.out.println("User Disconnected");
        } catch (
                Exception e) {
            //TODO
            //Export to Log
            System.out.println("E: send Data\t" + e.getMessage());
        }

    }
}
