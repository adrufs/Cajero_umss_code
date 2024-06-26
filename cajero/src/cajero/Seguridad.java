package cajero;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * La clase Seguridad proporciona métodos para cifrar y descifrar datos utilizando el algoritmo AES.
 */
public class Seguridad {

    private static final String ALGORITMO = "AES";
    private static final String CLAVE_SECRETA = "miclavesecreta12";

    /**
     * Obtiene la clave secreta para cifrar y descifrar los datos.
     *
     * @return Una instancia de SecretKey que representa la clave secreta.
     */
    private static SecretKey getSecretKey() {
        byte[] keyBytes = CLAVE_SECRETA.getBytes();
        return new SecretKeySpec(keyBytes, ALGORITMO);
    }

    /**
     * Cifra los datos utilizando la clave secreta.
     *
     * @param data Los datos a cifrar.
     * @return Una cadena cifrada en Base64.
     */
    public static String cifrar(String data) {
        byte[] encryptedBytes = null;
        try {
            SecretKey secretKey = getSecretKey();
            Cipher cipher = Cipher.getInstance(ALGORITMO);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            encryptedBytes = cipher.doFinal(data.getBytes());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    /**
     * Descifra los datos cifrados utilizando la clave secreta.
     *
     * @param encryptedData Los datos cifrados en Base64.
     * @return Una cadena con los datos descifrados.
     */
    public static String descifrar(String encryptedData) {
        byte[] decryptedBytes = null;
        try {
            SecretKey secretKey = getSecretKey();
            Cipher cipher = Cipher.getInstance(ALGORITMO);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decodedBytes = Base64.getDecoder().decode(encryptedData);
            decryptedBytes = cipher.doFinal(decodedBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(decryptedBytes);
    }
}
