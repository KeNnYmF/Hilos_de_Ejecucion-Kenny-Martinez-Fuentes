import java.util.ArrayList;
import java.util.List;

// Esta clase realiza la búsqueda de números primos de manera secuencial

public class BusquedaSecuencial {
    private int limite; // Límite superior hasta donde se buscarán los números primos

    // Constructor que recibe el valor máximo hasta donde se buscarán los primos
    public BusquedaSecuencial(int limite) {
        this.limite = limite;
    }

    // Método principal que devuelve una lista con todos los números primos encontrados
    public List<Integer> buscarPrimos() {
        List<Integer> primos = new ArrayList<>();
        for (int i = 2; i <= limite; i++) {
            if (esPrimo(i)) {
                primos.add(i); // Se agrega a la lista si el número es primo
            }
        }
        return primos; // Se retorna la lista completa de primos encontrados
    }

    // Método auxiliar que determina si un número dado es primo
    private boolean esPrimo(int numero) {
        if (numero < 2) return false; // Los números menores a 2 no son primos
        for (int i = 2; i <= Math.sqrt(numero); i++) {
            if (numero % i == 0) return false; // Si tiene algún divisor, no es primo
        }
        return true;
    }
}
