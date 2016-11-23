package SocialAppClient;

import SocialAppGeneral.Command;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by kemo on 08/11/2016.
 */
public abstract class CommandRequest {
    private Socket serverConnection;
    private Command command;
    public CommandRequest(Socket serverConnection, Command   command)
    {
        this.serverConnection = serverConnection;
        this.command = command;
    }
    public void run() {
        try {
            //send command to server
            DataOutputStream dataOutputStream = new DataOutputStream(serverConnection.getOutputStream());
            dataOutputStream.writeUTF(command.toString());
            //receive server command
            DataInputStream dataInputStream = new DataInputStream(serverConnection.getInputStream());
            //read string from server
            String s = dataInputStream.readUTF();
            //start these function in another thread inorder to prevent time consuming time
            Thread thread = new Thread()
            {
                @Override
                public void run() {
                    super.run();
                    analyze(Command.fromString(s));
                }
            };
          thread.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    abstract void  analyze(Command Command);
}
