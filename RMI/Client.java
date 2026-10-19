import java.rmi.Naming;

public class Client {
    public static void main(String[] args) {
        try {
            Calculator obj = (Calculator) Naming.lookup("rmi://Localhost/CalculatorService");
            System.out.println("Addition: " + obj.add(10, 20));
            System.out.println("Subtraction: " + obj.subtract(20, 10)); 
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}

