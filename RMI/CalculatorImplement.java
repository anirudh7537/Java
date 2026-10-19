import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class CalculatorImplement extends UnicastRemoteObject implements Calculator {
    protected CalculatorImplement() throws RemoteException {
        super();
    }

    
    public int add(int a, int b){
        return a + b;
    }

    
    public int subtract(int a, int b)  {
        return a - b;
    }
}