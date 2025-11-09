package servicios;

import javax.swing.*;

import UI.PanelRegistro;

import java.awt.*;
import java.util.List;

public abstract class UtilidadesForm {
    private static BotonActualizable botonActualizable;

    public interface BotonActualizable {
        void actualizarEstadoBoton(boolean habilitado);
    }

    public static void setBotonActualizable(BotonActualizable boton) {
        botonActualizable = boton;
    }

    public static void actualizarBoton(List<Boolean> camposValidos) {
        if (botonActualizable != null) {
            boolean todosValidos = camposValidos.stream().allMatch(v -> v);
            botonActualizable.actualizarEstadoBoton(todosValidos);
        }
    };

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
            nombreCampo.setForeground(Color.RED);
            nombreCampo.setText("El campo sólo debe contener letras.");
            return false;
        } 

        nombreCampo.setText(" ");
        return true;
    }

    public static boolean validarContrasenna(JTextField campoTexto, JLabel nombreCampo) throws Exception {
        String valor = campoTexto.getText();

        if (valor.matches("[0-9]{4}")) {
            nombreCampo.setText(" ");
            return true;
        }
        nombreCampo.setForeground(Color.RED);
        nombreCampo.setText("Ingrese una contraseña válida de 4 dígitos.");
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

        if (valor.matches("(?=.*)(?=.*@)(?=.*\\.com).*")) {
            nombreCampo.setText(" ");
            return true;
        }

        nombreCampo.setText("Ingrese una correo válido.");
        nombreCampo.setForeground(Color.RED);
        return false;
    }

    public static boolean validarFechaNacimiento(JTextField campoTexto, JLabel nombreCampo) {
        String texto = campoTexto.getText();

        if (texto.matches("^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-\\d{4}$")) {
            nombreCampo.setText(" ");
            return true;
        }

        nombreCampo.setForeground(Color.RED);
        nombreCampo.setText("Formato de fecha inválido (DD-MM-AAAA).");
        return false;
    }

    public static boolean validarSexo(JComboBox<String> lista, JLabel nombreCampo) {
        String opcion = String.valueOf(lista.getSelectedItem());

        if (opcion.isBlank()) {
            nombreCampo.setForeground(Color.RED);
            nombreCampo.setText("Seleccione una opción válida");
            return false;
        }

        nombreCampo.setText(" ");
        return true;
    }

    public static boolean validarGrupoSanguineo(JComboBox<String> grupo, JComboBox<String> rh, JLabel nombreCampo) {
        String valor_grupo = String.valueOf(grupo.getSelectedItem());
        String valor_rh = String.valueOf(rh.getSelectedItem());

        var mensaje = " ";
        nombreCampo.setForeground(Color.RED);

        if (valor_grupo.isBlank() && valor_rh.isBlank()) {
            mensaje = "Seleccione un grupo y rh válido.";
        }

        if (!valor_grupo.isBlank() && valor_rh.isBlank()) {
            mensaje = "Seleccione un rh válido.";
        }

        if (valor_grupo.isBlank() && !valor_rh.isBlank()) {
            mensaje = "Seleccione un grupo válido.";
        }

        nombreCampo.setText(mensaje);
        
        if (!mensaje.isBlank()) {
            return false;
        }
        
        return true;
    }

    public static boolean validarDireccion(JTextField direccion, JLabel nombreCampo) {
        String valor = direccion.getText();

        if (valor.isBlank()) {
            nombreCampo.setForeground(Color.RED);
            nombreCampo.setText("Escriba una dirección válida.");
            return false;
        }

        nombreCampo.setText(" ");
        return true;
    }

}