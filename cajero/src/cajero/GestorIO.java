package cajero;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * La clase GestorIO maneja la entrada y salida de datos desde y hacia la consola.
 * Permite la lectura de cadenas y enteros desde la entrada estándar, y la escritura
 * de mensajes a la salida estándar.
 */
public class GestorIO {

    private static BufferedReader b = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Lee una línea de texto desde la entrada estándar.
     * 
     * @return La cadena de texto leída desde la entrada estándar.
     */
    public String inString() {
        String entrada = null;
        try {
            entrada = b.readLine();
        } catch (Exception e) {
            this.salir();
        }
        return entrada;
    }

    /**
     * Lee un número entero desde la entrada estándar.
     * 
     * @return El número entero leído desde la entrada estándar.
     */
    public int inInt() {
        int entrada = 0;
        try {
            entrada = Integer.parseInt(this.inString());
        } catch (Exception e) {
            this.salir();
        }
        return entrada;
    }

    /**
     * Escribe una cadena de texto a la salida estándar.
     * 
     * @param salida La cadena de texto que se escribirá a la salida estándar.
     */
    public void out(String salida) {
        System.out.println(salida);
    }

    /**
     * Maneja errores de entrada/salida y termina el programa.
     */
    private void salir() {
        System.out.println("ERROR de entrada/salida");
        System.exit(0);
    }
}
