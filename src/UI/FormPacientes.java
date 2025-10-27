package UI;

import javax.swing.*;

import servicios.ServicioPaciente;
import servicios.UtilidadesForm;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class FormPacientes extends JPanel {
    protected CampoTexto primerNombre, segundoNombre, primerApellido, segundoApellido, documento, telefono, direccion, fechaNacimiento, correo;
    protected ComboBox<String> tiposDocumentos, sexo, grupo, rh;
    protected JPasswordField contrasenna;
    protected JCheckBox mostrarContrasenna;
    protected JLabel contrasennaLabel;
    
    public FormPacientes(String titulo) {
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

        // Primer Nombre
        JLabel primerNombreLabel = new JLabel("Primer Nombre");
        gbc.gridx = 0;
        add(primerNombreLabel, gbc);

        // Segundo Nombre
        JLabel segundoNombreLabel = new JLabel("Segundo Nombre");
        gbc.gridx = 1;
        add(segundoNombreLabel, gbc);

        gbc.gridy = 2;
        gbc.weightx = 0.5;
        gbc.insets = new Insets(10,20,10,20);

        primerNombre = new CampoTexto(20);
        gbc.gridx = 0;
        add(primerNombre, gbc);

        segundoNombre = new CampoTexto(20);
        gbc.gridx = 1;
        add(segundoNombre, gbc);

        gbc.insets = new Insets(10,20,1,20);

        // Apellidos
        JLabel primerApellidoLabel = new JLabel("Primer Apellido");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(primerApellidoLabel, gbc);

        JLabel segundoApellidoLabel = new JLabel("Segundo Apellido");
        gbc.gridx = 1;
        add(segundoApellidoLabel, gbc);
        
        gbc.gridy = 4;
        gbc.weightx = 0.5;
        gbc.insets = new Insets(10,20,10,20);
        
        primerApellido = new CampoTexto(20);
        gbc.gridx = 0;
        add(primerApellido, gbc);

        segundoApellido = new CampoTexto(20);
        gbc.gridx = 1;
        add(segundoApellido, gbc);

        // Documento y Drop List
        gbc.insets = new Insets(10,20,1,20);
        // Numero de Documento
        JLabel documentoLabel = new JLabel("Documento");
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(documentoLabel, gbc);
        
        gbc.insets = new Insets(10,20,10,20);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(getBackground());
        GridBagConstraints inner = new GridBagConstraints();
        inner.insets = new Insets(0, 0, 0, 5);
        inner.fill = GridBagConstraints.HORIZONTAL;
        inner.gridx = 0;
        inner.weightx = 0.9;
        documento = new CampoTexto(10);
        panel.add(documento, inner);

        inner.weightx = 0.1;
        inner.gridx = 1;
        String[] items = {"CC", "TI", "RC", "CE", "PA"};
        tiposDocumentos = new ComboBox<>(items);
        panel.add(tiposDocumentos, inner);

        gbc.gridx = 0;
        gbc.gridy = 6;
        add(panel, gbc);
        
        gbc.insets = new Insets(10,20,1,20);

        // FECHA DE NACIMIENTO
        JLabel fechaNacimientoLabel = new JLabel("Fecha Nacimiento");
        gbc.gridx = 1;
        gbc. gridy = 5;
        add(fechaNacimientoLabel, gbc);

        gbc.insets = new Insets(10,20,10,20);

        fechaNacimiento = new CampoTexto(20);
        gbc.gridy = 6;
        add(fechaNacimiento, gbc);

        gbc.insets = new Insets(10,20,1,20);
        // SEXO
        JLabel sexoLabel = new JLabel("Sexo");
        gbc.gridx = 0;
        gbc.gridy = 7;
        add(sexoLabel, gbc);

        gbc.insets = new Insets(10,20,10,20);

        String[] sexos = {" ","FEMENINO", "MASCULINO"};
        sexo = new ComboBox<>(sexos);
        gbc.gridy = 8;
        add(sexo, gbc);

        gbc.insets = new Insets(10,20,1,20);
        
        // GRUPO SANGUINEO
        JLabel grupoSanguineoLabel = new JLabel("Grupo Sanguíneo");
        gbc.gridx = 1;
        gbc.gridy = 7;
        add(grupoSanguineoLabel, gbc);

        JPanel grupoSanguineoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        grupoSanguineoPanel.setBackground(getBackground());

        gbc.insets = new Insets(10,20,10,20);

        String[] grupos = {" ","A", "B", "AB", "O"};
        grupo = new ComboBox<>(grupos);

        JLabel factorRhLabel = new JLabel("RH:");
        String[] factoresRh = {" ","+", "-"};
        rh = new ComboBox<>(factoresRh);

        grupoSanguineoPanel.add(grupo);
        grupoSanguineoPanel.add(factorRhLabel);
        grupoSanguineoPanel.add(rh);

        gbc.gridy = 8;
        gbc.insets = new Insets(10,20,10,20);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(grupoSanguineoPanel, gbc);

        gbc.insets = new Insets(10,20,1,20);

        // TELEFONO
        JLabel telefonoLabel = new JLabel("Telefono");
        gbc.gridx = 0;
        gbc.gridy = 10;
        add(telefonoLabel,gbc);

        gbc.insets = new Insets(10,20,10,20);
        telefono = new CampoTexto(20);
        gbc.gridy = 11;
        add(telefono, gbc);
        
        gbc.insets = new Insets(10,20,1,20);

        // Direccion
        JLabel direccionLabel = new JLabel("Dirección");
        gbc.gridx = 1;
        gbc.gridy = 10;
        add(direccionLabel,gbc);

        gbc.insets = new Insets(10,20,10,20);
        direccion = new CampoTexto(20);
        gbc.gridy = 11;
        add(direccion, gbc);

        gbc.insets = new Insets(10,20,1,20);

        // Correo Electronico
        JLabel correoLabel = new JLabel("Correo Electrónico");
        gbc.gridx = 0;
        gbc.gridy = 12;
        add(correoLabel,gbc);


        gbc.insets = new Insets(10,20,10,20);

        correo = new CampoTexto(20);
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridy = 13;
        add(correo, gbc);

        gbc.insets = new Insets(10,20,1,20);

        // Campo de Contraseña
        contrasennaLabel = new JLabel("Contraseña");
        gbc.gridy = 12;
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(contrasennaLabel, gbc);

        gbc.insets = new Insets(10,20,10,20);

        contrasenna = new CampoContraseña(20);
        contrasenna.setPreferredSize(primerNombre.getPreferredSize());
        gbc.gridy = 13;

        add(contrasenna, gbc);

        gbc.insets = new Insets(0,20,10,20);

        mostrarContrasenna = new JCheckBox("Mostrar Contraseña");
        mostrarContrasenna.setBackground(getBackground());

        gbc.gridy = 14;
        add(mostrarContrasenna, gbc);

        mostrarContrasenna.addActionListener(_->{
            boolean showing = contrasenna.getEchoChar() != 0;
            if (showing) {
                contrasenna.setEchoChar((char) 0); // Mostrar texto
                mostrarContrasenna.setText("Ocultar Constraseña");
            } else {
                contrasenna.setEchoChar('•'); // Ocultar texto
                mostrarContrasenna.setText("Mostrar Constraseña");
            }
        });

    }

    protected void cargarPaciente(Map<String, String> datos) {
        primerNombre.setText(datos.get("primerNombre"));
        segundoNombre.setText(datos.get("segundoNombre"));
        primerApellido.setText(datos.get("primerApellido"));
        segundoApellido.setText(datos.get("segundoApellido"));
        tiposDocumentos.setSelectedItem(datos.get("tipoDocumento"));
        documento.setText(datos.get("documento"));
        sexo.setSelectedItem(datos.get("sexo"));
        grupo.setSelectedItem(datos.get("grupo"));
        rh.setSelectedItem(datos.get("rh"));
        telefono.setText(datos.get("telefono"));
        direccion.setText(datos.get("direccion"));
        correo.setText(datos.get("correo"));
        contrasenna.setText(datos.get("contrasenna"));
        fechaNacimiento.setText(datos.get("fechaNacimiento"));
    }

    protected Map<String, String> cargarDatos() {
        Map<String, String> datos = new HashMap<>();

        datos.put("primerNombre", primerNombre.getText());
        datos.put("segundoNombre", segundoNombre.getText());
        datos.put("primerApellido", primerApellido.getText());
        datos.put("segundoApellido", segundoApellido.getText());
        datos.put("documento", documento.getText());
        datos.put("tipoDocumento", (String) tiposDocumentos.getSelectedItem());
        datos.put("sexo", (String) sexo.getSelectedItem());
        datos.put("grupo", (String) grupo.getSelectedItem());
        datos.put("rh", (String) rh.getSelectedItem());
        datos.put("telefono", telefono.getText());
        datos.put("direccion", direccion.getText());
        datos.put("correo", correo.getText());
        datos.put("contrasenna", new String(contrasenna.getPassword()));
        datos.put("fechaNacimiento", fechaNacimiento.getText());
        datos.put("perfil", "Paciente");

        return datos;
    };

    protected void setCampos() {
        primerNombre.setText("María");
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
    }

    protected boolean guardarPaciente() {
        ServicioPaciente  servicioPaciente = new ServicioPaciente();
            var datos = cargarDatos();
            boolean valid;
            try {
                valid = servicioPaciente.crearPaciente(datos);
                if (valid) {
                    JOptionPane.showMessageDialog(null, "Usuario Creado Exitosamente", "", JOptionPane.INFORMATION_MESSAGE);
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
