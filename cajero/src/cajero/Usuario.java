package cajero;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * La clase Usuario representa a un usuario del sistema de cajero.
 */
public class Usuario {

    private String nombreUsuario;
    private String nombreTitular;
    private String contrasena;
    private String nroCuenta;
    private String divisa;
    private String monto;

    /**
     * Crea una instancia de Usuario con la información proporcionada.
     *
     * @param nombreUsuario El nombre de usuario.
     * @param nombreTitular El nombre del titular de la cuenta.
     * @param contrasena La contraseña del usuario.
     * @param nroCuenta El número de cuenta del usuario.
     * @param divisa La divisa de la cuenta del usuario.
     * @param montoInicial El monto inicial en la cuenta del usuario.
     */
    public Usuario(String nombreUsuario, String nombreTitular, String contrasena, String nroCuenta, String divisa, String montoInicial) {

        this.nombreUsuario = nombreUsuario;
        this.nombreTitular = nombreTitular;
        this.contrasena = contrasena;
        this.nroCuenta = nroCuenta;
        this.divisa = divisa;
        this.monto = montoInicial;
    }

    /**
     * Obtiene el nombre de usuario.
     *
     * @return El nombre de usuario.
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * Obtiene el nombre del titular de la cuenta.
     *
     * @return El nombre del titular de la cuenta.
     */
    public String getNombreTitular() {
        return nombreTitular;
    }

    /**
     * Obtiene el número de cuenta del usuario.
     *
     * @return El número de cuenta del usuario.
     */
    public String getNumeroCuenta() {
        return nroCuenta;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return La contraseña del usuario.
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * Obtiene el tipo de divisa de la cuenta del usuario.
     *
     * @return El tipo de divisa de la cuenta del usuario.
     */
    public String getTipoDivisa() {
        return divisa.toLowerCase();
    }

    /**
     * Obtiene el monto en la cuenta del usuario.
     *
     * @return El monto en la cuenta del usuario.
     */
    public String getMonto() {
        return monto;
    }

    /**
     * Convierte la contraseña de formato char[] a String.
     *
     * @param contrasena La contraseña en formato char[].
     * @return La contraseña en formato String.
     */
    private String procesarContrasena(char[] contrasena) {
        String contrasenaCadena = "";
        for (int i = 0; i < contrasena.length; i++) {
            contrasenaCadena += "" + contrasena[i];
        }
        return contrasenaCadena;
    }

}
