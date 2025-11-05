package UI;

import javax.swing.*;

import servicios.ServicioEspecialidad;
import servicios.UtilidadesForm;

import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FormEspecialidades extends JPanel {    
    protected CampoTexto nombreEspecialidad, identificador,estado, descripcion;

    
    public FormEspecialidades(String titulo) {
        setLayout(new GridBagLayout());

        crearFormularioBase(titulo);

    }

    protected void crearFormularioBase(String titulo) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,20,10,20);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel tituloLabel = new JLabel(titulo);
        tituloLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        tituloLabel.setForeground(new Color(35,94,40));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 1.0;
        gbc.gridwidth = 2;
        add(tituloLabel, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.insets = new Insets(10,20,1,20);

        // Nombre Especialidad
        JLabel nombreEspecialidadLabel = new JLabel("Nombre Especialidad");
        gbc.gridx = 0;
        add(nombreEspecialidadLabel, gbc);

        gbc.gridy = 2;
        gbc.weightx = 0.5;
        gbc.insets = new Insets(10,20,10,20);

        nombreEspecialidad = new CampoTexto(20);
        gbc.gridx = 0;
        add(nombreEspecialidad, gbc);

        gbc.insets = new Insets(10,20,1,20);

        // Descripción
        JLabel descripcionLabel = new JLabel("Descripción");
        gbc.gridx = 0;
        gbc.gridy = 3;

        add(descripcionLabel, gbc);
        
        gbc.weightx = 0.5;
        gbc.insets = new Insets(10,20,10,20);
        
        descripcion = new CampoTexto(20);
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(descripcion, gbc);
        gbc.insets = new Insets(10,20,1,20);


    }

    protected void cargarEspecialidad(Map<String, String> datos) {
        nombreEspecialidad.setText(datos.get("nombreEspecialidad"));
        descripcion.setText(datos.get("descripcion"));

    }

    protected Map<String, String> cargarDatos() {
        Map<String, String> datos = new HashMap<>();

        datos.put("nombreEspecialidad", nombreEspecialidad.getText());
        datos.put("identificador", "0"); 
        datos.put("estado", "true");
        datos.put("descripcion", descripcion.getText());

        return datos;
    };


    protected boolean guardarEspecialidad() throws Exception, IOException {
        ServicioEspecialidad servicioEspecialidad = new ServicioEspecialidad();
            var datos = cargarDatos();
            boolean valid;
            try {
                valid = servicioEspecialidad.crearEspecialidad(datos);
                if (valid) {
                    JOptionPane.showMessageDialog(null, "Especialidad creada exitosamente", "", JOptionPane.INFORMATION_MESSAGE);
                    UtilidadesForm.limpiarCampos(this);
                    return true;
                }  else {
                    JOptionPane.showMessageDialog(null, "¡Ups! Parece que esta especialidad ya existe", "", JOptionPane.INFORMATION_MESSAGE);
                    UtilidadesForm.limpiarCampos(this);

                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "", JOptionPane.INFORMATION_MESSAGE);
                ex.printStackTrace();
            }
        return false;
    };
}

