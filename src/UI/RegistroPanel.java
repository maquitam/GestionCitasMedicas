package UI;

import javax.swing.*;
import java.awt.*;

public class RegistroPanel extends JPanel {
    private Login loginFrame;

    public RegistroPanel(Login loginFrame) {
        this.loginFrame = loginFrame;
        setBackground(Color.WHITE);
        setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,20,10,20);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Título - Registro Usuario
        JLabel registerTitle = new JLabel("Registro de Usuario");
        registerTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
        registerTitle.setForeground(new Color(35,94,40));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 1.0;
        gbc.gridwidth = 2;
        add(registerTitle, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.insets = new Insets(10,20,1,20);

        // Primer Nombre
        JLabel nameLabel = new JLabel("Primer Nombre");
        gbc.gridx = 0;
        add(nameLabel, gbc);

        // Segundo Nombre
        JLabel secondNameLabel = new JLabel("Segundo Nombre");
        gbc.gridx = 1;
        add(secondNameLabel, gbc);

        gbc.gridy = 2;
        gbc.weightx = 0.5;
        gbc.insets = new Insets(10,20,10,20);

        CampoTexto nameField = new CampoTexto(20);
        gbc.gridx = 0;
        add(nameField, gbc);

        CampoTexto secondNameField = new CampoTexto(20);
        gbc.gridx = 1;
        add(secondNameField, gbc);

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
        
        CampoTexto primerApellidoField = new CampoTexto(20);
        gbc.gridx = 0;
        add(primerApellidoField, gbc);

        CampoTexto segundoApellidoField = new CampoTexto(20);
        gbc.gridx = 1;
        add(segundoApellidoField, gbc);

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
        CampoTexto documentoField = new CampoTexto(10);
        panel.add(documentoField, inner);

        inner.weightx = 0.1;
        inner.gridx = 1;
        String[] items = {"CC", "TI", "RC", "CE", "PA"};
        ComboBox<String> tiposDocumentos = new ComboBox<>(items);
        panel.add(tiposDocumentos, inner);

        gbc.gridx = 0;
        gbc.gridy = 6;
        add(panel, gbc);
        
        gbc.insets = new Insets(10,20,1,20);
        // Telefono
        JLabel telefonoLabel = new JLabel("Telefono");
        gbc.gridx = 1;
        gbc.gridy = 5;
        add(telefonoLabel,gbc);

        gbc.insets = new Insets(10,20,10,20);
        CampoTexto telefonoField = new CampoTexto(20);
        gbc.gridy = 6;
        add(telefonoField, gbc);
        
        gbc.insets = new Insets(10,20,1,20);
        // Direccion
        JLabel direccionLabel = new JLabel("Dirección");
        gbc.gridx = 1;
        gbc.gridy = 7;
        add(direccionLabel,gbc);

        gbc.insets = new Insets(10,20,10,20);
        CampoTexto direccionField = new CampoTexto(20);
        gbc.gridy = 8;
        add(direccionField, gbc);

        gbc.insets = new Insets(10,20,1,20);
        // Correo Electronico
        JLabel correoLabel = new JLabel("Correo Electrónico");
        gbc.gridx = 0;
        gbc.gridy = 7;
        add(correoLabel,gbc);

        gbc.insets = new Insets(10,20,10,20);
        CampoTexto correoField = new CampoTexto(20);
        gbc.gridy = 8;
        add(correoField, gbc);


        // Boton Enviar
        Boton enviarBoton = new Boton("Crear Cuenta");
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridwidth = 2;
        add(enviarBoton, gbc);
    }
}
