package org.proyect2;

// Archivo: PrincipalParking.java

/**
 * Punto de entrada de la aplicación.
 * Crea el Aparcamiento y lanza 7 hilos (Coches) para simular la concurrencia.
 */
public class PrincipalParking {

    private static final int CAPACIDAD_PARKING = 3; // El recurso limitado: 3 plazas [cite: 18]
    private static final int NUMERO_COCHES = 7;     // Número de hilos concurrentes [cite: 21]

    public static void main(String[] args) {
        System.out.println("--- SIMULACIÓN DE APARCAMIENTO CONCURRENTE ---");

        // 1. Crear el recurso compartido (Aparcamiento)
        Aparcamiento parking = new Aparcamiento(CAPACIDAD_PARKING);

        // 2. Crear y lanzar los hilos (Coches)
        for (int i = 1; i <= NUMERO_COCHES; i++) {
            String nombre = "Coche-" + i;
            // Instancia el objeto Coche con su nombre y la referencia al parking.
            Coche coche = new Coche(nombre, parking);
            // Crea un Thread usando el objeto Coche (Runnable) y lo lanza.
            new Thread(coche).start();
        }

        System.out.println("7 coches han iniciado la simulación y compiten por 3 plazas.");
    }
}