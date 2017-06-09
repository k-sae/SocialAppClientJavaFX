package Connections.Client;

import Connections.TransmissionFailureListener;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by kemo on 24/11/2016.
 */

//singleton class iin order to be easily accessed all over app
public class CommandsExecutor {
    private static CommandsExecutor instance;
    private volatile ArrayList<ConnectionRunnable> commandRequests;
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
    public  void add(ConnectionRunnable request, int index)
    {
        commandRequests.add(index, request);
        start();
    }
    public  void add(ConnectionRunnable request)
    {
        commandRequests.add(request);
        start();

    }
    private synchronized void start()
    {
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
                            ((CommandRequest) commandRequests.get(0)).updateConnectionSocket(updatedSocket);
                        commandRequests.get(0).run();
                        commandRequests.remove(0);
                    } catch (IOException e) {
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
        thread.setName("Connection Thread");
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
