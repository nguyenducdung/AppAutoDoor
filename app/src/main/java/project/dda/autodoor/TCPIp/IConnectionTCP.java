package project.dda.autodoor.TCPIp;

/**
 * Created by Duy Anh on 12/12/2018
 **/
public interface IConnectionTCP {
    void Connect();
    void Disconnect();
    void Reconnect();
    void SendMessage(String mess);
    void TimeTick();

}
