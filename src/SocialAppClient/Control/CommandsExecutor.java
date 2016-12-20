package SocialAppClient.Control;

import java.util.ArrayList;

/**
 * Created by kemo on 24/11/2016.
 */

//singleton class iin order to be easily accessed all over app
public class CommandsExecutor {
    private static CommandsExecutor instance;
    private volatile ArrayList<CommandRequest> commandRequests;
    private volatile boolean isRunning;
    //private constructor to prevent any one from creating object from it
    private CommandsExecutor()
    {
        commandRequests = new ArrayList<>();
        isRunning = false;
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
                    commandRequests.get(0).run();
                    commandRequests.remove(0);
                }
                isRunning = false;
            }
        };
        thread.start();
    }
}
