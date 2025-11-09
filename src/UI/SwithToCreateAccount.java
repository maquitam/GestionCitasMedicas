package UI;

import javax.swing.*;
import java.awt.*;

public class SwithToCreateAccount extends JPanel {
    private LoginView loginFrame;

    public SwithToCreateAccount(LoginView loginFrame) {
        this.loginFrame = loginFrame;
        setPreferredSize(new Dimension(300, getHeight()));
        setBackground(new Color(35, 94, 40));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Titulo
        JLabel registerTitle = new JLabel("¿No tienes una cuenta?");
        registerTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        registerTitle.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        add(registerTitle, gbc);

        // Boton Registrarse
        var boton = BotonCrearCuenta();

        gbc.gridy = 6;
        add(boton, gbc);

    }

    public Boton BotonCrearCuenta() {
        Boton registerButton = new Boton("Crear Cuenta");
        registerButton.setBackground(new Color(44, 119, 50));

        registerButton.addActionListener(a -> {
            loginFrame.mostrarRegistroPanel();
            loginFrame.mostrarIniciarSesion();
        });

        return registerButton;
    }
}