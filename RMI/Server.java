import java.rmi.Naming;
public class Server {
    public static void main(String[] args) {
        try {
            CalculatorImplement obj = new CalculatorImplement();
            Naming.rebind("rmi://Localhost/CalculatorService", obj);
            System.out.println("Calculator Service is running...");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}   