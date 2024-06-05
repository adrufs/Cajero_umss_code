package cajero;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * La clase Hora proporciona métodos para obtener la hora actual formateada.
 */
public class Hora {

    /**
     * Obtiene la hora actual formateada.
     *
     * @return Una cadena con la hora actual formateada en el patrón "yyyy/MM/dd".
     */
    public static String hora() {
        LocalTime now = LocalTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String horaFormateada = now.format(formato);
        return horaFormateada;
    }
}
