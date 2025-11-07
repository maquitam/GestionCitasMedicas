package interfaces;

import javax.swing.JLabel;
import javax.swing.JTextField;

import excepciones.*;

public interface ValidacionFormularioService {
    void validarCampoTexto(JTextField campoTexto, JLabel nombreCampo) throws ValidacionMedicoException;
    void validarNumero(String valor, String nombreCampo) throws ValidacionMedicoException;
    void validarEmail(String valor) throws ValidacionMedicoException;
    void validarTelefono(String valor) throws ValidacionMedicoException;
}
