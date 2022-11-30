package Modelo;

import java.util.Arrays;

public class Matriz {
    private boolean celulas[][];
    private int dimensiones;
    private boolean inicializado;

    public Matriz(int dimensiones) {
        this.dimensiones = dimensiones;
        this.inicializado = false;
    }

    public void llenar() {
        this.celulas = new boolean[dimensiones][dimensiones];
        for(int i = 0; i < this.celulas.length; i++) {
            for(int j = 0; j < this.celulas[i].length; j++) {
                this.celulas[i][j] = false;
            }
        }
    }

    public void llenarMitad() {
        for(int i = 0; i < this.celulas.length; i++) {
            for(int j = 0; j < this.celulas[i].length / 2; j++) {
                if ((int) (Math.random() * 4) == 1)
                    celulas[i][j] = true;
                else
                    celulas[i][j] = false;
            }
        }
        inicializado = true;
    }

    public void llenarCompleto(Matriz primerMatriz) {
        this.celulas = primerMatriz.getCelulas();
        for(int i = 0; i < this.celulas.length; i++) {
            for(int j = this.celulas.length / 2; j < this.celulas[i].length; j++) {
                if ((int) (Math.random() * 4) == 1)
                    celulas[i][j] = true;
                else
                    celulas[i][j] = false;
            }
        }
        inicializado = true;
    }

    public void imprimir() {
        for(int i = 0; i < this.celulas.length; i++){
            for(int j = 0; j < this.celulas[i].length; j++) {
                System.out.print(this.celulas[i][j] + "\t");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    public boolean[][] getCelulas() {
        boolean matriz[][] = new boolean[dimensiones][dimensiones];

        for (int i = 0; i < dimensiones; i++) {
            matriz[i] = Arrays.copyOf(this.celulas[i], dimensiones);
        }
        return matriz;
    }

    public boolean getCelula(int posX, int posY) {
        return celulas[posX][posY];
    }

    public void setCelulas(boolean celulas[][]) {
        for (int i = 0; i < dimensiones; i++) {
            this.celulas[i] = Arrays.copyOf(celulas[i], dimensiones);
        }
    }

    public boolean estaInicializada() {
        return inicializado;
    }
}