package UI;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import repositorio.LoginRepositorio;
import objetos.Login;
import servicios.ServicioLogin;


public class LoginPanel extends JPanel {
    private LoginView loginFrame;
    ServicioLogin servicioLogin;

    public LoginPanel(LoginView loginFrame) {
        this.loginFrame = loginFrame;
        
        setBackground(Color.WHITE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,20,10,20);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Título
        JLabel titulo = new JLabel("¡Bienvenid@!");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titulo.setForeground(new Color(35,94,40));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0,20,30,20);
        add(titulo, gbc);
        
        // Subtítulo
        gbc.gridx = 0;
        JLabel subtitulo = new JLabel("Inicia sesión para continuar");
        subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitulo.setForeground(Color.GRAY);
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 20, 40, 20);
        add(subtitulo, gbc);

        gbc.insets = new Insets(8, 20, 8, 20);
        gbc.gridwidth = 1;

        // Campo de usuario
        JLabel labelUsuario = new JLabel("Usuario");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        add(labelUsuario,gbc);

        CampoTexto textUsuario = new CampoTexto(20);
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(textUsuario,gbc);
        gbc.gridwidth = 1;
        
        // Campo de Contraseña
        JLabel labelContraseña = new JLabel("Contraseña");
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        add(labelContraseña, gbc);
        
        CampoContraseña campoContraseña = new CampoContraseña(20);
        campoContraseña.setPreferredSize(textUsuario.getPreferredSize());

        ImageIcon closedEye = new ImageIcon(getClass().getResource("/img/closed_eye.png"));
        Image imgclosedEye = closedEye.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon closedEyeEscalado = new ImageIcon(imgclosedEye);

        ImageIcon openedEye = new ImageIcon(getClass().getResource("/img/opened_eye.png"));
        Image imgOpenedEye = openedEye.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon openedEyeEscalado = new ImageIcon(imgOpenedEye);
        
        JButton boton = new JButton(closedEyeEscalado);

        boton.setBorderPainted(false);
        boton.setContentAreaFilled(false);
        boton.setFocusPainted(false);
        boton.setMargin(new Insets(0, 0, 0, 0));
        boton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));


        boton.addActionListener (e -> {
            boolean showing = campoContraseña.getEchoChar() != 0;
            if (showing) {
                campoContraseña.setEchoChar((char) 0); // Mostrar texto
                boton.setIcon(openedEyeEscalado);
            } else {
                campoContraseña.setEchoChar('•'); // Ocultar texto
                boton.setIcon(closedEyeEscalado);
            }
        });

        gbc.gridy = 5;
        gbc.insets = new Insets(8, 20, 8, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        add(campoContraseña, gbc);
        gbc.gridx = 2;
        gbc.insets = new Insets(0, 0, 0, 0);
        add(boton, gbc);

        // Boton Login
        Boton loginBoton = new Boton("Iniciar Sesión");
        gbc.gridy = 6;
        gbc.insets = new Insets(30, 20, 8, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        add(loginBoton, gbc);

        
        loginBoton.addActionListener(e -> {
            
            servicioLogin = new ServicioLogin();

            var usuario = textUsuario.getText();
            var contraseña = new String(campoContraseña.getPassword());
            var login = new Login(usuario,contraseña);

            var valid = servicioLogin.validarUsuarioyContraseña(usuario, contraseña);

            if (valid) {
                JOptionPane.showMessageDialog(loginBoton, "Inició sesión correctamente");
            } else {
                JOptionPane.showMessageDialog(loginBoton, "Usuario o Contraseña Incorrectos");
            }

        });

    }
}
