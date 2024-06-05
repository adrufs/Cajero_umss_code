package cajero;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * La clase {@code Evento} representa un evento relacionado con una cuenta bancaria, 
 * incluyendo detalles como el número de cuenta, una descripción del evento, el monto 
 * involucrado y el saldo después del evento.
 * Evento evento = new Evento("123456", "Depósito", "500.00", "1500.00");
 * System.out.println(evento.getNroCuenta());  // Salida: 123456
 * System.out.println(evento.getDescripcion()); // Salida: Depósito
 * System.out.println(evento.getMonto());      // Salida: 500.00
 * System.out.println(evento.getSaldo());      // Salida: 1500.00
 * System.out.println(evento.getFecha());      // Salida: 2024/06/05 (o la fecha actual)
 */
public class Evento {

    private String nroCuenta;
    private String descripcion;
    private String monto;
    private String saldo;

    /**
     * Constructor para crear un nuevo evento.
     * 
     * @param nroCuenta el número de la cuenta asociada al evento
     * @param descripcion una descripción del evento
     * @param monto el monto asociado al evento
     * @param saldo el saldo de la cuenta después del evento
     */
    public Evento(String nroCuenta, String descripcion, String monto, String saldo) {
        this.nroCuenta = nroCuenta;
        this.descripcion = descripcion;
        this.monto = monto;
        this.saldo = saldo;
    }

    /**
     * Obtiene el número de la cuenta asociada al evento.
     * 
     * @return el número de la cuenta
     */
    public String getNroCuenta() {
        return nroCuenta;
    }

    /**
     * Obtiene la descripción del evento.
     * 
     * @return la descripción del evento
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Obtiene el monto asociado al evento.
     * 
     * @return el monto del evento
     */
    public String getMonto() {
        return monto;
    }

    /**
     * Obtiene el saldo de la cuenta después del evento.
     * 
     * @return el saldo de la cuenta
     */
    public String getSaldo() {
        return saldo;
    }

    /**
     * Obtiene la fecha actual en formato yyyy/MM/dd.
     * 
     * @return la fecha actual como una cadena en formato yyyy/MM/dd
     */
    public String getFecha() {
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String fechaFormateada = fechaActual.format(formato);
        return fechaFormateada;
    }
}
