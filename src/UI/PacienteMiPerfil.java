package UI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.*;

public class PacienteMiPerfil extends JPanel {
    PacienteView pacienteView;

    PacienteMiPerfil(PacienteView pacienteView) {
        this.pacienteView = pacienteView;
        setLayout(new BorderLayout());

        crearFormulario();

        setVisible(true);
    }

    public void crearFormulario() {
        JPanel miPerfilPanel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;

        FormPacientes formPacientes = new FormPacientes("Mi Perfil");
        
        formPacientes.contrasenna.setVisible(false);
        formPacientes.mostrarContrasenna.setVisible(false);
        formPacientes.contrasennaLabel.setVisible(false);

        desahibilitarCampos(formPacientes);
        
        miPerfilPanel.add(formPacientes, gbc);
        add(miPerfilPanel, BorderLayout.CENTER);
    }

    public void desahibilitarCampos(JPanel panel) {
        for (Component comp : panel.getComponents()) {
            if (comp instanceof JTextField) {
                ((JTextField) comp).setEnabled(false);
            }
            
            else if (comp instanceof JComboBox) {                
                ((JComboBox) comp).setEnabled(false);
            } 
            
            else if (comp instanceof JPanel) {
                desahibilitarCampos((JPanel) comp);
            }
        }
    panel.revalidate();
    panel.repaint();
    }
}
