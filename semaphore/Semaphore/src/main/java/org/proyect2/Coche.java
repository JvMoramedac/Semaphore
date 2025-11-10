package org.proyect2;
// Archivo: Coche.java


import java.util.Random;

/**
 * Representa un coche como un hilo independiente (Runnable).
 * Cada coche intenta entrar, permanece aparcado un tiempo aleatorio y luego sale.
 */
public class Coche implements Runnable {

    // Referencia al recurso compartido: el Aparcamiento.
    private final Aparcamiento aparcamiento;
    private final String nombreCoche;
    // Generador de números aleatorios para simular el tiempo de aparcamiento.
    private static final Random random = new Random();

    /**
     * Constructor de Coche.
     * @param nombreCoche Nombre identificativo del coche (ej: "Coche-1").
     * @param aparcamiento Referencia al objeto Aparcamiento compartido.
     */
    public Coche(String nombreCoche, Aparcamiento aparcamiento) {
        this.nombreCoche = nombreCoche;
        this.aparcamiento = aparcamiento;
    }

    /**
     * Define la secuencia de ejecución del hilo Coche.
     * Secuencia: llamar a entrar(), dormir tiempo aleatorio (1-4 seg), llamar a salir().
     */
    @Override
    public void run() {
        try {
            // 1. Intentar entrar al aparcamiento (bloqueante si no hay plazas)
            aparcamiento.entrar(nombreCoche);

            // 2. Permanecer aparcado un tiempo aleatorio (1-4 segundos)
            int tiempoAparcado = 1000 + random.nextInt(3000); // 1000ms a 3999ms (1 a <4 seg)
            System.out.println(nombreCoche + " aparca por " + (tiempoAparcado / 1000.0) + " segundos.");
            Thread.sleep(tiempoAparcado);

            // 3. Salir del aparcamiento (libera el permiso)
            aparcamiento.salir(nombreCoche);

        } catch (InterruptedException e) {
            // Gestión de la excepción InterruptedException si el hilo es interrumpido
            Thread.currentThread().interrupt();
            System.out.println( nombreCoche + " ha sido interrumpido.");
        }
    }
}