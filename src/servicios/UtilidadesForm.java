package servicios;

import javax.swing.*;
import java.awt.*;

public abstract class UtilidadesForm {

    public static void limpiarCampos(JPanel panel) {
        for (Component comp : panel.getComponents()) {
            if (comp instanceof JTextField) {
                ((JTextField) comp).setText("");
            } else if (comp instanceof JPasswordField) {
                ((JPasswordField) comp).setText("");
            } else if (comp instanceof JPanel) {
                limpiarCampos((JPanel) comp);
            }

        } 
    }

    public static boolean validarCampoTexto(JTextField campoTexto, JLabel nombreCampo) throws Exception {
        String valor = campoTexto.getText();

        if (!valor.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+")) {
            nombreCampo.setText("El campo sólo debe contener letras.");
            return false;
        } 

        nombreCampo.setText(" ");
        nombreCampo.setForeground(Color.RED);
        return true;
    }

    public static boolean validarContrasenna(JTextField campoTexto, JLabel nombreCampo) throws Exception {
        String valor = campoTexto.getText();

        if (valor.matches("[0-9]{4}")) {
            return true;
        }
        nombreCampo.setText("Ingrese una contraseña válida de 4 dígitos.");
        nombreCampo.setForeground(Color.RED);
        return false;
    }

    public static boolean validarDocumento(JTextField campoTexto, JLabel nombreCampo) throws Exception {
        String valor = campoTexto.getText();

        if (valor.matches("[0-9]{8}") || valor.matches("[0-9]{10}")) {
            nombreCampo.setText(" ");
            return true;
        }

        nombreCampo.setText("Ingrese una documento válido.");
        nombreCampo.setForeground(Color.RED);
        return false;
    }

    public static boolean validarTelefono(JTextField campoTexto, JLabel nombreCampo) throws Exception {
        String valor = campoTexto.getText();

        if (valor.matches("[0-9]{10}")) {
            nombreCampo.setText(" ");
            return true;
        }

        nombreCampo.setText("Ingrese una número válido.");
        nombreCampo.setForeground(Color.RED);
        return false;
    }

    public static boolean validarCorreo(JTextField campoTexto, JLabel nombreCampo) throws Exception {
        String valor = campoTexto.getText();

        if (valor.matches("(?=.*\\d)(?=.*@)(?=.*\\.com).*")) {
            nombreCampo.setText(" ");
            return true;
        }

        nombreCampo.setText("Ingrese una correo válido.");
        nombreCampo.setForeground(Color.RED);
        return false;
    }
}