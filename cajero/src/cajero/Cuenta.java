
package cajero;
/**
 * La clase {@code Cuenta} representa una cuenta bancaria con su número, divisa y monto asociado.
 * Cuenta cuenta = new Cuenta("123456", "USD", "1000.00");
 * System.out.println(cuenta.getNroCuenta());  // Salida: 123456
 * System.out.println(cuenta.getDivisa());     // Salida: USD
 * System.out.println(cuenta.getMonto());      // Salida: 1000.00
 * System.out.println(cuenta.toString());      // Salida: 123456(USD)
 */
public class Cuenta {

    private String nroCuenta;
    private String divisa;
    private String monto;

    /**
     * Constructor para crear una nueva cuenta.
     * 
     * @param nroCuenta el número de la cuenta
     * @param divisa la divisa de la cuenta (por ejemplo, USD, EUR)
     * @param monto el monto inicial de la cuenta
     */
    public Cuenta(String nroCuenta, String divisa, String monto) {
        this.nroCuenta = nroCuenta;
        this.divisa = divisa;
        this.monto = monto;
    }

    /**
     * Obtiene el número de la cuenta.
     * 
     * @return el número de la cuenta
     */
    public String getNroCuenta() {
        return nroCuenta;
    }

    /**
     * Obtiene la divisa de la cuenta.
     * 
     * @return la divisa de la cuenta
     */
    public String getDivisa() {
        return divisa;
    }

    /**
     * Obtiene el monto de la cuenta.
     * 
     * @return el monto de la cuenta
     */
    public String getMonto() {
        return monto;
    }
    
    /**
     * Devuelve una representación en cadena de la cuenta.
     * 
     * @return una cadena que contiene el número de la cuenta y la divisa
     */
    @Override
    public String toString(){
        return nroCuenta + "(" + getDivisa() + ")";
    }
}
