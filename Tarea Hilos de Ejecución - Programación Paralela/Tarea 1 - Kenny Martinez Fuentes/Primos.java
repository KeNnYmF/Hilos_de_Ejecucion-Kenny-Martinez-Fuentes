import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Primos {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        // Se solicita al usuario un número N para buscar los números primos desde 1 hasta N
        System.out.print("Ingrese el valor N: ");
        int N = scanner.nextInt();

        // Se empieza a medir el tiempo para la búsqueda secuencial
        long inicioSecuencial = System.currentTimeMillis();
        List<Integer> primosSecuencial = new ArrayList<>();

        // Búsqueda secuencial de números primos desde 1 hasta N
        for (int i = 1; i <= N; i++) {
            if (esPrimo(i)) {
                primosSecuencial.add(i);
            }
        }

        long finSecuencial = System.currentTimeMillis(); // Fin de la búsqueda secuencial

        System.out.println("Primos encontrados de forma secuencial: " + primosSecuencial.size());
        System.out.println("Tiempo secuencial: " + (finSecuencial - inicioSecuencial) + " ms");

        // Ahora se realiza la búsqueda de forma paralela usando varios hilos
        System.out.print("Ingrese la cantidad de hilos: ");
        int hilos = scanner.nextInt();

        int rango = N / hilos; // Se divide el rango total para cada hilo
        BusquedaParalela[] tareas = new BusquedaParalela[hilos];

        long inicioParalelo = System.currentTimeMillis();

        // Se crean y ejecutan los hilos con sus respectivos rangos de búsqueda
        for (int i = 0; i < hilos; i++) {
            int inicio = i * rango + 1;
            int fin = (i == hilos - 1) ? N : (i + 1) * rango;
            tareas[i] = new BusquedaParalela(inicio, fin, "Hilo-" + (i + 1));
            tareas[i].start();
        }

        // Lista para juntar todos los primos encontrados por los hilos
        List<Integer> primosTotales = new ArrayList<>();

        // Se espera que cada hilo termine y se recopilan sus resultados
        for (int i = 0; i < hilos; i++) {
            tareas[i].join();
            primosTotales.addAll(tareas[i].getPrimos());
        }

        long finParalelo = System.currentTimeMillis();
        
        System.out.println("Primos encontrados de forma paralela: " + primosTotales.size());
        System.out.println("Tiempo paralelo: " + (finParalelo - inicioParalelo) + " ms");
        System.out.println("Los números primos de " + N + " son: " + primosTotales);
        scanner.close();
    }

    // Función que verifica si un número es primo
    public static boolean esPrimo(int numero) {
        if (numero < 2) return false;
        for (int i = 2; i <= Math.sqrt(numero); i++) {
            if (numero % i == 0) return false;
        }
        return true;
    }
}
