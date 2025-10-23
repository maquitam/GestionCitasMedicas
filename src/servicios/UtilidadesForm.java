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
    };
}
