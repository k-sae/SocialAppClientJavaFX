package SocialAppClient;

import SocialAppGeneral.Command;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by kemo on 08/11/2016.
 */
public abstract class RequestServerCommand extends Thread {
    private ServerConnection serverConnection;
    private Command command;
    public RequestServerCommand(ServerConnection serverConnection, Command   command)
    {
        this.serverConnection = serverConnection;
        this.command = command;
    }

    @Override
    public void run() {
        super.run();
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(serverConnection.connectionSocket.getOutputStream());
            dataOutputStream.writeUTF(command.toString());
            DataInputStream dataInputStream = new DataInputStream(serverConnection.connectionSocket.getInputStream());
            analyze(dataInputStream.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    abstract void analyze(String s);
}
