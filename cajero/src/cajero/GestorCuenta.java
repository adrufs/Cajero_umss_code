package cajero;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import cajero.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * La clase GestorCuenta maneja la gestión de cuentas de usuario, 
 * incluyendo la creación de eventos, la gestión de datos de usuarios 
 * y la realización de transacciones financieras.
 * 
 */
public class GestorCuenta {

    private File directorioUsuarios;
    private File directorioLog;
    private Document documentoXML;
    private Element perfilUsuario;
    private Element cuentaActual;

    /**
     * El constructor inicializa los directorios para datos de usuarios y logs.
     */
    public GestorCuenta() {
        directorioUsuarios = new File(System.getProperty("user.dir") + "/src/usuarios/");
        directorioLog = new File(System.getProperty("user.dir") + "/src/logUsuarios/");
        if (!directorioUsuarios.exists()) {
            directorioUsuarios.mkdir();
        }
    }

    /**
     * Crea un evento y lo escribe en el log.
     * 
     * @param evento Evento a ser creado.
     */
    public void crearEvento(Evento evento) {
        try {
            File directorio = new File(directorioLog + "/logs.xml");
            escribirDatosEvento(evento, directorio);
        } catch (IOException ex) {
            Logger.getLogger(GestorCuenta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Escribe los datos del evento en el archivo especificado.
     * 
     * @param evento Datos del evento a ser escritos.
     * @param ruta Ruta del archivo donde se escribirán los datos del evento.
     * @throws IOException Si ocurre un error de entrada o salida.
     */
    private void escribirDatosEvento(Evento evento, File ruta) throws IOException {
        // En caso de que el archivo no exista
        if (!ruta.exists()) {
            generarDocumento(evento, ruta);

        } else {
            anadirEvento(evento, ruta);
        }
    }

    /**
     * Añade un evento al archivo XML existente.
     * 
     * @param evento Evento a ser añadido.
     * @param ruta Ruta del archivo XML.
     */
    private void anadirEvento(Evento evento, File ruta) {
        Document documento = getDocumento(ruta);
        Node eventos = documento.getElementsByTagName("logs").item(0);
        eventos.appendChild(escribirDatosEvento(evento, documento));
        guardarCambiosEnXML(new File(directorioLog + "/logs.xml"), documento);
    }

    /**
     * Genera un nuevo documento XML con los datos del evento.
     * 
     * @param evento Evento a ser añadido.
     * @param ruta Ruta del archivo XML.
     */
    private void generarDocumento(Evento evento, File ruta) {
        Document documento = generarNuevoDocumento();
        Element logs = documento.createElement("logs");
        documento.appendChild(logs);
        logs.appendChild(escribirDatosEvento(evento, documento));
        guardarCambiosEnXML(ruta, documento);
    }

    /**
     * Escribe los datos del evento en un elemento XML.
     * 
     * @param evento Evento cuyos datos serán escritos.
     * @param documento Documento XML donde se escribirán los datos.
     * @return Elemento XML con los datos del evento.
     */
    private Element escribirDatosEvento(Evento evento, Document documento) {
        Element log = documento.createElement("log");

        Element nroCuenta = documento.createElement("nroCuenta");
        nroCuenta.appendChild(documento.createTextNode(evento.getNroCuenta()));
        log.appendChild(nroCuenta);

        Element fecha = documento.createElement("fecha");
        fecha.appendChild(documento.createTextNode(evento.getFecha()));
        log.appendChild(fecha);

        Element descripcion = documento.createElement("descripcion");
        descripcion.appendChild(documento.createTextNode(evento.getDescripcion()));
        log.appendChild(descripcion);

        Element monto = documento.createElement("monto");
        monto.appendChild(documento.createTextNode(evento.getMonto()));
        log.appendChild(monto);

        Element saldo = documento.createElement("saldo");
        saldo.appendChild(documento.createTextNode(evento.getSaldo()));
        log.appendChild(saldo);

        return log;
    }

    /**
     * Añade una cuenta al perfil del usuario actual.
     * 
     * @param divisa Tipo de divisa de la cuenta.
     * @param nroCuenta Número de la cuenta.
     * @param montoInicial Monto inicial de la cuenta.
     */
    public void anadirCuenta(String divisa, String nroCuenta, String montoInicial) {
        Element cuenta = documentoXML.createElement("cuenta");

        Element numeroCuenta = documentoXML.createElement("nroCuenta");
        numeroCuenta.appendChild(documentoXML.createTextNode(nroCuenta));
        cuenta.appendChild(numeroCuenta);

        Element div = documentoXML.createElement("divisa");
        div.appendChild(documentoXML.createTextNode(divisa));
        cuenta.appendChild(div);

        Element monto = documentoXML.createElement("monto");
        monto.appendChild(documentoXML.createTextNode(montoInicial));
        cuenta.appendChild(monto);

        perfilUsuario.appendChild(cuenta);
        guardarCambiosEnXML(new File(directorioUsuarios + "/usuarios.xml"), documentoXML);
    }

    /**
     * Obtiene una lista de eventos asociados a la cuenta actual.
     * 
     * @return Lista de eventos.
     */
    public List<Evento> getEventos() {
        Document documento = getDocumento(new File(directorioLog + "/logs.xml"));
        NodeList logs = documento.getElementsByTagName("log");
        List<Evento> listaLogs = new ArrayList<Evento>();
        for (int i = 0; i < logs.getLength(); i++) {
            Node nodoLog = logs.item(i);
            Element log = (Element) nodoLog;
            String nroCuenta = log.getElementsByTagName("nroCuenta").item(0).getTextContent();
            if (nroCuenta.equals(getNroCuenta())) {
                String fecha = log.getElementsByTagName("fecha").item(0).getTextContent();
                String descripcion = log.getElementsByTagName("descripcion").item(0).getTextContent();
                String monto = log.getElementsByTagName("monto").item(0).getTextContent();
                String saldo = log.getElementsByTagName("saldo").item(0).getTextContent();
                listaLogs.add(new Evento(getNroCuenta(), descripcion, monto, saldo));
            }
        }

        return listaLogs;
    }
    
    /**
     * Crea una cuenta de usuario.
     * 
     * @param usuario Usuario para el que se creará la cuenta.
     */
    public void crearCuenta(Usuario usuario) {

        try {
            File directorio = new File(directorioUsuarios + "/usuarios.xml");
            escribirDatosUsuario(usuario, directorio);
        } catch (IOException ex) {
            Logger.getLogger(GestorCuenta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Escribe los datos del usuario en el archivo especificado.
     * 
     * @param usuario Datos del usuario a ser escritos.
     * @param ruta Ruta del archivo donde se escribirán los datos del usuario.
     * @throws IOException Si ocurre un error de entrada o salida.
     */
    private void escribirDatosUsuario(Usuario usuario, File ruta) throws IOException {
        // En caso de que el archivo no exista
        if (!ruta.exists()) {
            generarDocumento(usuario, ruta);

        } else {
            anadirUsuario(usuario, ruta);
        }
    }

    /**
     * Añade un usuario al archivo XML existente.
     * 
     * @param usuario Usuario a ser añadido.
     * @param ruta Ruta del archivo XML.
     */
    private void anadirUsuario(Usuario usuario, File ruta) {
        Document documento = getDocumento(ruta);
        Node usuarios = documento.getElementsByTagName("usuarios").item(0);
        usuarios.appendChild(escribirDatosUsuario(usuario, documento));
        guardarCambiosEnXML(ruta, documento);
    }

    /**
     * Genera un nuevo documento XML con los datos del usuario.
     * 
     * @param usuario Usuario a ser añadido.
     * @param ruta Ruta del archivo XML.
     */
    private void generarDocumento(Usuario usuario, File ruta) {
        Document documento = generarNuevoDocumento();
        Element usuarios = documento.createElement("usuarios");
        documento.appendChild(usuarios);
        usuarios.appendChild(escribirDatosUsuario(usuario, documento));
        guardarCambiosEnXML(ruta, documento);
    }

    /**
     * Obtiene el documento XML de un archivo especificado.
     * 
     * @param archivoXML Archivo XML del cual se obtendrá el documento.
     * @return Documento XML.
     */
    public Document getDocumento(File archivoXML) {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(archivoXML);
            doc.getDocumentElement().normalize();
            return doc;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Escribe los datos del usuario en un elemento XML.
     * 
     * @param usuario Usuario cuyos datos serán escritos.
     * @param documento Documento XML donde se escribirán los datos.
     * @return Elemento XML con los datos del usuario.
     */
    private Element escribirDatosUsuario(Usuario usuario, Document documento) {
        Element user = documento.createElement("usuario");

        Element nombreUsuario = documento.createElement("nombreUsuario");
        nombreUsuario.appendChild(documento.createTextNode(usuario.getNombreUsuario()));
        user.appendChild(nombreUsuario);

        Element contrasena = documento.createElement("contrasena");
        contrasena.appendChild(documento.createTextNode(usuario.getContrasena()));
        user.appendChild(contrasena);

        Element nombre = documento.createElement("nombre");
        nombre.appendChild(documento.createTextNode(usuario.getNombre()));
        user.appendChild(nombre);

        Element apellido = documento.createElement("apellido");
        apellido.appendChild(documento.createTextNode(usuario.getApellido()));
        user.appendChild(apellido);

        Element fechaNacimiento = documento.createElement("fechaNacimiento");
        fechaNacimiento.appendChild(documento.createTextNode(usuario.getFechaNacimiento()));
        user.appendChild(fechaNacimiento);

        Element saldo = documento.createElement("saldo");
        saldo.appendChild(documento.createTextNode("0"));
        user.appendChild(saldo);

        Element cuentas = documento.createElement("cuentas");
        user.appendChild(cuentas);

        return user;
    }

    /**
     * Genera un nuevo documento XML.
     * 
     * @return Documento XML vacío.
     */
    public Document generarNuevoDocumento() {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder;
        try {
            docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            return doc;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Guarda los cambios en el documento XML en el archivo especificado.
     * 
     * @param archivoXML Archivo XML donde se guardarán los cambios.
     * @param doc Documento XML con los cambios.
     */
    public void guardarCambiosEnXML(File archivoXML, Document doc) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(archivoXML);
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtiene el número de cuenta del usuario actual.
     * 
     * @return Número de cuenta.
     */
    public String getNroCuenta() {
        return cuentaActual.getElementsByTagName("nroCuenta").item(0).getTextContent();
    }

    /**
     * Actualiza la cuenta actual del usuario.
     * 
     * @param cuenta Cuenta a ser actualizada.
     */
    public void setCuentaActual(Element cuenta) {
        this.cuentaActual = cuenta;
    }

    /**
     * Establece el perfil del usuario actual.
     * 
     * @param perfil Perfil del usuario a ser establecido.
     */
    public void setPerfilUsuario(Element perfil) {
        this.perfilUsuario = perfil;
    }

    /**
     * Guarda la cantidad de dinero depositada.
     * 
     * @param cantidad Cantidad de dinero a ser depositada.
     */
    public void guardarDeposito(String cantidad) {
        cuentaActual.getElementsByTagName("monto").item(0).setTextContent(cantidad);
        guardarCambiosEnXML(new File(directorioUsuarios + "/usuarios.xml"), documentoXML);
    }
}
