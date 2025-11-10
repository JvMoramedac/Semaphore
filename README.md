# 🚗 Control de Acceso Concurrente a un Aparcamiento con *Semaphore*

## 🧩 Descripción del Proyecto
Este proyecto simula un **aparcamiento con plazas limitadas (3)** donde **7 coches** intentan entrar de forma simultánea.  
El objetivo es controlar el acceso concurrente a un recurso compartido (las plazas de aparcamiento) utilizando **`java.util.concurrent.Semaphore`**.  
Cada coche se comporta como un **hilo independiente**, que:
1. Intenta entrar 🅿️  
2. Permanece aparcado un tiempo aleatorio entre 1 y 4 segundos ⏱️  
3. Sale liberando la plaza para otro vehículo 🚙  

## 🧠 Objetivos de Aprendizaje
- Comprender el uso de **Semáforos** para gestionar recursos limitados.  
- Sincronizar correctamente **múltiples hilos** sin condiciones de carrera.  
- Aplicar **buenas prácticas** de diseño modular y documentación con JavaDoc.  
- Observar la **concurrencia en acción** de forma visual mediante mensajes por consola.

## 🧱 Arquitectura del Sistema
El sistema está compuesto por tres clases principales:

| Clase | Rol | Descripción |
|-------|-----|-------------|
| 🅿️ `Aparcamiento` | Núcleo del sistema | Gestiona el `Semaphore` y controla las plazas mediante `entrar()` y `salir()`. |
| 🚗 `Coche` | Hilo individual | Representa cada coche. Implementa `Runnable` y define el comportamiento del hilo. |
| 🧭 `PrincipalParking` | Punto de entrada | Crea el aparcamiento, instancia los coches y lanza los hilos concurrentemente. |

