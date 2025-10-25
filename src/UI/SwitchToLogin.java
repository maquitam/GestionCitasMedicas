package UI;

import javax.swing.*;
import java.awt.*;

public class SwitchToLogin extends JPanel {   
    private LoginView loginFrame;

    public SwitchToLogin(LoginView loginFrame) {
        this.loginFrame = loginFrame;
        setBackground(new Color(35,94,40));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,20,10,20);
        gbc.fill = GridBagConstraints.HORIZONTAL;


        // Titulo
        JLabel registerTitle = new JLabel("¿Ya tienes una cuenta?");
        registerTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        registerTitle.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        add(registerTitle, gbc);

        
        Boton registerButton = new Boton("Iniciar Sesión");
        registerButton.setBackground(new Color(44,119,50));

        gbc.gridy = 6;
        add(registerButton, gbc);
        
        // ---- EVENTOS ----
        registerButton.addActionListener(e -> {
            loginFrame.mostrarLoginPanel();
            loginFrame.mostrarCrearCuenta();
        });

    }
}
