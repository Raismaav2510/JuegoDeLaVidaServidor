import Modelo.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ForkJoinPool;

public class Implementacion extends UnicastRemoteObject implements Controlador {
    private Matriz matriz;
    private Juego juego;
    private JuegoES juegoExecutor;
    private long inicio;
    private long tiempoTotal;

    protected Implementacion() throws RemoteException {
    }

    public boolean getCelula(int posX, int posY) {
        return matriz.getCelula(posX, posY);
    }

    public void inicializarJuego(int dimensiones) {
        matriz.llenarAleatorio();
        this.matriz = new Matriz(dimensiones);
        this.juego = new Juego(matriz);
        this.juegoExecutor = new JuegoES(matriz);
    }

    public void avanzarSecuencial() {
        this.inicio = System.currentTimeMillis();
        juego.actualizarJuego();
        this.tiempoTotal = (System.currentTimeMillis() - inicio);
    }

    public void avanzarJorkJoin() {
        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        this.inicio = System.currentTimeMillis();
        pool.invoke(new JuegoFJ(matriz));
        this.tiempoTotal = (System.currentTimeMillis() - inicio);
    }

    public void avanzarExecutor() {
        this.inicio = System.currentTimeMillis();
        juegoExecutor.actualizarJuego();
        this.tiempoTotal = (System.currentTimeMillis() - inicio);
    }

    public long getTiempo() {
        return tiempoTotal;
    }
}
