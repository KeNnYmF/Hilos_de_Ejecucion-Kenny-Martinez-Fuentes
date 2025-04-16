import java.util.ArrayList;
import java.util.List;

// Esta clase extiende de Thread para realizar la búsqueda de números primo en paralelo

public class BusquedaParalela extends Thread {
    private int inicio; // Límite inferior del rango a evaluar
    private int fin;    // Límite superior del rango a evaluar
    private String nombre; // Nombre del hilo para identificarlo en la salida
    private List<Integer> primos; // Lista donde se almacenarán los números primos encontrados

    // Constructor que recibe los límites de búsqueda y un nombre identificador del hilo
    public BusquedaParalela(int inicio, int fin, String nombre) {
        this.inicio = inicio;
        this.fin = fin;
        this.nombre = nombre;
        this.primos = new ArrayList<>();
    }

    // Método que se ejecuta al iniciar el hilo
    // Aquí se realiza la búsqueda de números primos en el rango asignado
    @Override
    public void run() {
        System.out.println(nombre + " buscando primos en rango: " + inicio + " - " + fin);
        for (int i = inicio; i <= fin; i++) {
            if (esPrimo(i)) {
                primos.add(i); // Se guarda el número si cumple la condición de primo
            }
        }
        System.out.println(nombre + " encontró " + primos.size() + " primos.");
    }

    // Método auxiliar que verifica si un número es primo
    private boolean esPrimo(int numero) {
        if (numero < 2) return false;
        for (int i = 2; i <= Math.sqrt(numero); i++) {
            if (numero % i == 0) return false; // Si el número tiene algún divisor, no es primo
        }
        return true;
    }

    // Método para obtener la lista de primos encontrados por este hilo
    public List<Integer> getPrimos() {
        return primos;
    }
}
