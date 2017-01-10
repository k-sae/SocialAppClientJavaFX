package SocialAppClient.Connections;

import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

/**
 * Created by kemo on 24/11/2016.
 */

//singleton class iin order to be easily accessed all over app
public class CommandsExecutor {
    private static CommandsExecutor instance;
    private volatile ArrayList<CommandRequest> commandRequests;
    private volatile boolean isRunning;
    private ArrayList<TransmissionFailureListener> transmissionFailureListeners;
    private Socket updatedSocket;
    //private constructor to prevent any one from creating object from it
    private CommandsExecutor()
    {
        commandRequests = new ArrayList<>();
        isRunning = false;
        transmissionFailureListeners = new ArrayList<>();
    }
    //get single instance for this class
    public static CommandsExecutor getInstance()
    {
        if(instance == null) instance = new CommandsExecutor();
        return instance;
    }
    public synchronized void add(CommandRequest request)
    {
        commandRequests.add(request);
        if(!isRunning) startExecuting();
    }
    private void startExecuting()
    {
        isRunning = true;
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                //loop till no items left in the list
                while(commandRequests.size() != 0)
                {
                    try {
                        if (updatedSocket != null)
                            commandRequests.get(0).updateConnectionSocket(updatedSocket);
                        commandRequests.get(0).run();
                        commandRequests.remove(0);
                    } catch (SocketException e) {
                        //noinspection ForLoopReplaceableByForEach
                        for (int i = 0; i < transmissionFailureListeners.size(); i++) {
                            transmissionFailureListeners.get(i).onDisconnection();
                        }
                    }
                }
                isRunning = false;
                updatedSocket = null;
            }
        };
        thread.start();
    }
    public void setOnTransmissionFailure(TransmissionFailureListener transmissionFailureListener)
    {
        transmissionFailureListeners.add(transmissionFailureListener);
    }
    public void updateSocket(Socket socket)
    {
        updatedSocket = socket;
    }
}
