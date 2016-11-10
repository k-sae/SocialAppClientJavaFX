package SocialAppClient;

import SocialAppGeneral.Command;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by kemo on 08/11/2016.
 */
public abstract class RequestServerCommand extends Thread {
    private Socket serverConnection;
    private Command command;
    public RequestServerCommand(Socket serverConnection, Command   command)
    {
        this.serverConnection = serverConnection;
        this.command = command;
    }

    @Override
    public void run() {
        super.run();
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(serverConnection.getOutputStream());
            dataOutputStream.writeUTF(command.toString());
            DataInputStream dataInputStream = new DataInputStream(serverConnection.getInputStream());
            analyze(Command.fromString(dataInputStream.readUTF()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    abstract void  analyze(Command Command);
}
