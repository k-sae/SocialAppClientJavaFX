package SocialAppClient;

import SocialAppGeneral.Command;
import SocialAppGeneral.Connection;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by kemo on 28/10/2016.
 */
public class ServerConnection implements Connection{
    private int port;
    private String serverName;
    private Socket connectionSocket;
    //if no parameters passed set default connection
    //TODO #kareem
    //after creating users levels accept user of type registeredUser or login user
    public ServerConnection ()
    {   //default connection @LIP-LIP port 6060
        this(new String[]{"LIP-LIP", "6060"});
    }
    public ServerConnection(String[] args)
    {
        serverName = args[0];
        port = Integer.parseInt(args[1]);
        startConnection();
    }
    @Override
    public void startConnection() {
        //here i will check for user info and choose weather to continue the connection or to end it
        try {
            connectionSocket = new Socket(serverName, port);
            //TODO #kareem
            //Check for input info if the server returns true start receiving in another thread
            ReceiveServerCommand receiveServerCommand = new ReceiveServerCommand(connectionSocket, this); // do this after passing the if condition later
            receiveServerCommand.start();
        }
        catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            //TODO #Lastly
            //export it to log file
            System.out.println("StartConnection\t"+ e.getMessage());
        }

    }
    //i have to find a better way to do this :\
    @Override
    public void sendData(Command data) {
        try {
            ObjectOutputStream objectOutputStream= new ObjectOutputStream(connectionSocket.getOutputStream());
            objectOutputStream.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            //TODO #Lastly
            //export to Log file
            System.out.println("send data\t"+ e.getMessage());
        }

    }
}
