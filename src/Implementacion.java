import Modelo.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ForkJoinPool;

public class Implementacion extends UnicastRemoteObject implements Controlador {
    private Matriz matrizFinal, matriz;
    private Juego juego;
    private JuegoES juegoExecutor;
    private long inicio;
    private long tiempoTotal;

    protected Implementacion() throws RemoteException {
        super();
    }

    public boolean getCelula(int posX, int posY) {
        if (matrizFinal.estaInicializada())
            return matrizFinal.getCelula(posX, posY);
        else
            return matriz.getCelula(posX, posY);
    }

    public void inicializarJuego(int dimensiones) {
        matriz = new Matriz(dimensiones);
        matriz.llenar();
        matrizFinal = new Matriz(dimensiones);
        matrizFinal.llenar();
    }

    public void llenarJuego() {
        if (matriz.estaInicializada()) {
            matrizFinal.llenarCompleto(matriz);
            juego = new Juego(matrizFinal);
            juegoExecutor = new JuegoES(matrizFinal);
        }
        else
            matriz.llenarMitad();
    }

    public void avanzarSecuencial() {
        inicio = System.currentTimeMillis();
        juego.actualizarJuego();
        tiempoTotal = (System.currentTimeMillis() - inicio);
    }

    public void avanzarJorkJoin() {
        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        inicio = System.currentTimeMillis();
        pool.invoke(new JuegoFJ(matrizFinal));
        tiempoTotal = (System.currentTimeMillis() - inicio);
    }

    public void avanzarExecutor() {
        inicio = System.currentTimeMillis();
        juegoExecutor.actualizarJuego();
        tiempoTotal = (System.currentTimeMillis() - inicio);
    }

    public long getTiempo() {
        return tiempoTotal;
    }

    @Override
    public boolean listo() throws RemoteException {
        return matrizFinal.estaInicializada();
    }
}
