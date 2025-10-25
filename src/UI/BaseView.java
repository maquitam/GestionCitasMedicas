package UI;

import javax.swing.*;
import java.awt.*;

public abstract class BaseView extends JFrame {
    protected JPanel menuLateral;
    protected JPanel panelDerecho;
    protected CardLayout cardLayout = new CardLayout();
    protected String USUARIO_AUTENTICADO;

    public BaseView(String usuario, String titulo) {
        setTitle(titulo);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setResizable(true);
        setLayout(new BorderLayout());

        setMinimumSize(new Dimension(700,400));
        
        initComponents();
        setVisible(true);
    }

    protected abstract void crearMenuLateral();
    protected abstract void crearPanelDerecho();

    protected void initComponents() {
        crearPanelDerecho();
        crearMenuLateral();

        add(menuLateral, BorderLayout.WEST);
        add(panelDerecho, BorderLayout.CENTER);
    }

    protected JPanel mostrarBienvenida(String mensaje) {
        JPanel bienvenidaPanel = new JPanel();
        bienvenidaPanel.setBackground(Color.WHITE);
        bienvenidaPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,20,10,20);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Mensaje de Bienvenida
        JLabel tituloBienvenida = new JLabel(mensaje);
        tituloBienvenida.setFont(new Font("Segoe UI", Font.BOLD, 28));
        tituloBienvenida.setForeground(new Color(35,94,40));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 1.0;
        gbc.gridwidth = 2;
        bienvenidaPanel.add(tituloBienvenida, gbc);

        return bienvenidaPanel;
    }

}
