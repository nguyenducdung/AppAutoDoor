package project.dda.autodoor.TCPIp;

import android.os.Handler;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

import project.dda.autodoor.activity.MainActivity;

public class TCPClient implements IConnectionTCP {

//    static final String             SERVER_IP = "192.168.4.1"; //server IP address
    public static final String SERVER_IP = "192.168.7.12"; //server IP address
    static final int                SERVER_PORT = 80;
    static final Integer            mDelay = 10000;
    String                          mServerMessage;// message to send to the server
    OnMessageReceived               mMessageListener = null;// sends message received notifications
    boolean                         mRun = false;
    PrintWriter                     mBufferOut;// used to send messages
    BufferedReader                  mBufferIn;// used to read messages from the server
    static Socket                   mSocket;
    static Timer                    timer;
    /**
     * Constructor of the class. OnMessagedReceived listens for the messages received from server
     */

    public TCPClient(OnMessageReceived listener) {
        mMessageListener = listener;
    }

    @Override
    public void Connect() {
        mRun = true;

        try {
            InetAddress serverAddr = InetAddress.getByName(SERVER_IP);

            Log.d("TCP Client", "C: Connecting...");

            //create a socket to make the connection with the server
            mSocket = new Socket(serverAddr, SERVER_PORT);

            try {
                //sends the message to the server
                mBufferOut = new PrintWriter(new BufferedWriter(new OutputStreamWriter(mSocket.getOutputStream())), true);

                //receives the message which the server sends back
                mBufferIn = new BufferedReader(new InputStreamReader(mSocket.getInputStream()));

                //connected
                MainActivity.IsConnected = true;

                //in this while the client listens for the messages sent by the server
                while (mRun) {
                    mServerMessage = mBufferIn.readLine();
                    if (mServerMessage != null && mMessageListener != null) {
                        //call the method messageReceived from MyActivity class
                        mMessageListener.messageReceived(mServerMessage);
                    }

                }
                Log.d("RESPONSE FROM SERVER", "S: Received Message: '" + mServerMessage + "'");

            } catch (Exception e) {
                Log.e("TCP", "S: Error", e);
            } finally {
                //the socket must be closed. It is not possible to reconnect to this socket
                // after it is closed, which means a new socket instance has to be created.
                //socket.close();
                //socket.setSoTimeout();
            }

        } catch (Exception e) {
            Log.e("TCP", "C: Error", e);
        }
    }

    @Override
    public void Disconnect() {
        mRun = false;

        if (mBufferOut != null) {
            mBufferOut.flush();
            mBufferOut.close();
        }

        mMessageListener = null;
        mBufferIn = null;
        mBufferOut = null;
        mServerMessage = null;
    }

    @Override
    public void Reconnect() {
        if(!mSocket.isConnected())
        {
            Connect();
        }
    }

    @Override
    public void SendMessage(final String mess) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (mBufferOut != null) {
                    Log.d("TCP ", "Sending: " + mess);
                    mBufferOut.println(mess);
                    mBufferOut.flush();
                }

                if(timer != null)
                    timer.cancel();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        //TODO: sau khi gui tin thi doi khoang 10s roi doc socket
        TimeTick();
    }

    @Override
    public void TimeTick() {
        final Handler handler = new Handler();
        timer = new Timer(false);
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        // Do whatever you want
                        if (mSocket != null) {
                            try {
                                mSocket.close();
                                MainActivity.IsConnected = false;

                                Log.d("TCP", "S: Socket Close");
                            } catch (IOException e) {
                                e.printStackTrace();
                                Log.e("TCP", "S: erro", e);
                            }
                        }
                    }
                });
            }
        };
        timer.schedule(timerTask, mDelay);

    }

    //Declare the interface. The method messageReceived(String message) will must be implemented in the Activity
    //class at on AsyncTask doInBackground
    public interface OnMessageReceived {
        public void messageReceived(String message);
    }

}