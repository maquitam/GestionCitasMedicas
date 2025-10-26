package UI;

import javax.swing.*;

import servicios.ServicioEspecialidad;
import servicios.UtilidadesForm;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class FormEspecialidades extends JPanel {      
    protected CampoTexto nombreEspecialidad, identificador,estado, descripcion;

    
    public FormEspecialidades(String titulo) {
        setLayout(new GridBagLayout());

        crearFormularioBase(titulo);
        //setCampos();
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

        // Identificador
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


/*  Estado
        JLabel estadoLabel = new JLabel("Estado");
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(estadoLabel, gbc);

        gbc.weightx = 0.5;
        gbc.insets = new Insets(10,20,10,20);
        
        estado = new CampoTexto(20);
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(estado, gbc);
        gbc.insets = new Insets(10,20,1,20);

          Descripción
        JLabel descripcionLabel = new JLabel("Descripción");
        gbc.gridx = 0;
        gbc.gridy = 7;
        add(descripcionLabel, gbc);
        
        gbc.insets = new Insets(10,20,10,20);

        descripcion = new CampoTexto(20);
        gbc.gridx = 0;
        gbc.gridy = 8;

        add(estado, gbc);
        gbc.insets = new Insets(10,20,1,20);*/

        
        
        
        
        
       /*  JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(getBackground());
        GridBagConstraints inner = new GridBagConstraints();
        inner.insets = new Insets(0, 0, 0, 5);
        inner.fill = GridBagConstraints.HORIZONTAL;
        inner.gridx = 0;
        inner.weightx = 0.9;
        descripcion = new CampoTexto(10);
        panel.add(descripcion, inner);

        inner.weightx = 0.1;
        inner.gridx = 1;
        String[] items = {"CC", "TI", "RC", "CE", "PA"};
        tiposDocumentos = new ComboBox<>(items);
        panel.add(tiposDocumentos, inner);

        gbc.gridx = 0;
        gbc.gridy = 10;
        add(panel, gbc);
        
        gbc.insets = new Insets(10,20,1,20);*/
      
    }

    protected void cargarEspecialidad(Map<String, String> datos) {
        nombreEspecialidad.setText(datos.get("nombreEspecialidad"));
        identificador.setText(datos.get("identificador"));
        estado.setText(datos.get("estado"));
        descripcion.setText(datos.get("descripción"));

    }

    protected Map<String, String> cargarDatos() {
        Map<String, String> datos = new HashMap<>();

        datos.put("nombreEspecialidad", nombreEspecialidad.getText());
        datos.put("identificador", identificador.getText());
        datos.put("estado", estado.getText());
        datos.put("descripción", descripcion.getText());

        return datos;
    };

    /*protected void setCampos() {
        nombre.setText("María");
        segundoNombre.setText("Camila");
        primerApellido.setText("Parra");
        segundoApellido.setText("Morales");
        documento.setText("1090377346");
        tiposDocumentos.setSelectedItem("CC");
        sexo.setSelectedItem("FEMENINO");
        grupo.setSelectedItem("A");
        rh.setSelectedItem("+");
        telefono.setText("3022480598");
        direccion.setText("Sabaneta");
        correo.setText("mcparram1611@gmail.com");
        contrasenna.setText("123");
        fechaNacimiento.setText("16-11-2004");
    }*/

    protected boolean guardarEspecialidad() {
        ServicioEspecialidad  servicioEspecialidad = new ServicioEspecialidad();
            var datosMap = cargarDatos();
            String datos = String.format("%s|%s|%s|%s", 
                datosMap.get("nombre"),
                datosMap.get("identificador"),
                datosMap.get("descripcion"),
                "activo");
            boolean valid;
            try {
                valid = servicioEspecialidad.crearEspecialidad(datos);
                if (valid) {
                    JOptionPane.showMessageDialog(null, "Especialidad Creada Exitosamente", "", JOptionPane.INFORMATION_MESSAGE);
                    UtilidadesForm.limpiarCampos(this);
                    return true;
                }
            JOptionPane.showMessageDialog(null, "¡Ups! Parece que este documento ya tiene una cuenta asociada", "", JOptionPane.INFORMATION_MESSAGE);
            UtilidadesForm.limpiarCampos(this);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        return false;
    };
}

