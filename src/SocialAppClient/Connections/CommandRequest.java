package SocialAppClient.Connections;

import SocialAppClient.SocialAppGeneral.Command;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

/**
 * Created by kemo on 08/11/2016.
 */
public abstract class CommandRequest {
    private  Socket socket;
    private Command command;
    public CommandRequest(Socket socket, Command   command)
    {
       this.socket = socket;
        this.command = command;
    }
    void run() throws SocketException {
        try {
            //send command to server
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeUTF(command.toString());
            //receive server command
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            //read string from server
            final String s = dataInputStream.readUTF();
            //start these function in another thread inorder to prevent time consuming
            Thread thread = new Thread()
            {
                @Override
                public void run() {
                    super.run();
                    analyze(Command.fromString(s));
                }
            };
          thread.start();

        } catch (SocketException e) {
            //TODO #kareem
            //notify for user dc
            throw e;
        }catch (SocketTimeoutException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        catch (NullPointerException ignored)
        {

        }
    }
    public void updateConnectionSocket(Socket socket)
    {
        this.socket = socket;
    }
    public abstract void  analyze(Command cmd);
}
