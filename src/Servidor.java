import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {
    public static void main(String[] args) {
        String ip = "192.168.3.25";
        int puerto = 8080;

        try {
            System.setProperty("java.rmi.server.hostname", ip);
            Registry registry= LocateRegistry.createRegistry(puerto);
            registry.rebind("JuegoDeLaVida", new Implementacion());
            System.out.println("Servidor iniciado con la IP: " + ip);
        } catch (RemoteException e) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}