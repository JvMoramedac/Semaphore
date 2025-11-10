// Archivo: Aparcamiento.java
package org.proyect2;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Representa el aparcamiento con plazas limitadas, gestionando el acceso
 * mediante un semáforo. Es el núcleo del control de concurrencia.
 */
public class Aparcamiento {

    // El semáforo actúa como guardián de las plazas disponibles.
    // El número inicial de permisos es la capacidad máxima del parking (3).
    private final Semaphore semaphore;
    private final int capacidadMaxima;
    // Contador atómico para llevar el control de plazas ocupadas de forma segura.
    private final AtomicInteger plazasOcupadas;

    /**
     * Constructor que inicializa el Aparcamiento.
     * @param capacidad Capacidad máxima de plazas disponibles.
     */
    public Aparcamiento(int capacidad) {
        this.capacidadMaxima = capacidad;
        // Se inicializa el Semaphore con el número de plazas.
        this.semaphore = new Semaphore(capacidad);
        this.plazasOcupadas = new AtomicInteger(0);
        System.out.println("🚗 Aparcamiento inicializado con " + capacidad + " plazas.");
    }

    /**
     * Intenta que un coche entre al aparcamiento.
     * Llama a semaphore.acquire(), el cual es bloqueante si no hay permisos disponibles.
     * @param nombreCoche Nombre del coche que intenta entrar.
     * @throws InterruptedException Si el hilo es interrumpido mientras espera.
     */
    public void entrar(String nombreCoche) throws InterruptedException {
        // Antes de adquirir, el coche "espera" si no hay plazas libres.
        if (semaphore.tryAcquire() == false) {
            System.out.println(nombreCoche + " está esperando... Plazas ocupadas: " + plazasOcupadas.get());
            // Llama a acquire() de forma bloqueante hasta obtener un permiso.
            semaphore.acquire();
        } else {
            // Ya ha adquirido un permiso en el tryAcquire.
        }

        // Si se llega a este punto, el coche ha adquirido un permiso y ha entrado.
        int ocupadas = plazasOcupadas.incrementAndGet();
        System.out.println(nombreCoche + " ha entrado. Plazas ocupadas: " + ocupadas + "/" + capacidadMaxima);

        // Se verifica la restricción crítica (opcional, para depuración)
        if (ocupadas > capacidadMaxima) {
            System.err.println("¡ERROR CRÍTICO! Exceso de capacidad. Ocupadas: " + ocupadas);
        }
    }

    /**
     * Permite que un coche salga del aparcamiento.
     * Llama a semaphore.release() para liberar un permiso.
     * @param nombreCoche Nombre del coche que sale.
     */
    public void salir(String nombreCoche) {
        // Libera un permiso para que otro coche en espera pueda entrar.
        semaphore.release();
        int ocupadas = plazasOcupadas.decrementAndGet();
        System.out.println( nombreCoche + " ha salido. Plazas ocupadas: " + ocupadas + "/" + capacidadMaxima);
    }
}