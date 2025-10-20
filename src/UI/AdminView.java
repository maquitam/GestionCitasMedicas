package UI;

import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
import javax.swing.*;
import java.awt.*;

public class AdminView extends JFrame {
    public AdminView() {
        setTitle("Vista Administrador");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());
        
        initComponents();
        setVisible(true);
    
    }

    private void initComponents() {
        crearMenuLateral();
    }

    private void crearMenuLateral() {
        JPanel menuLateral = new JPanel(new GridBagLayout());
        setPreferredSize(new Dimension(100,getHeight()));
        setBackground(new Color(35,94,40));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Botones Laterales
        gbc.gridy = 0;
        gbc.gridx = 0;
        Boton medicos = new Boton("Médicos");
        menuLateral.add(medicos, gbc);
        gbc.gridy = 1;
        Boton pacientes = new Boton("Pacientes");
        menuLateral.add(pacientes, gbc);
        gbc.gridy = 2;
        Boton citas = new Boton("Citas");
        gbc.gridy = 3;
        menuLateral.add(citas, gbc);
        Boton especialidades = new Boton("Especialidades");
        gbc.gridy = 4;
        menuLateral.add(especialidades, gbc);
        Boton cerrarSesion = new Boton("Cerrar Sesion");
        gbc.gridy = 5;
        menuLateral.add(cerrarSesion, gbc);


        medicos.setRedondeado(0);
        pacientes.setRedondeado(0);
        citas.setRedondeado(0);
        especialidades.setRedondeado(0);
        cerrarSesion.setRedondeado(0);


        add(menuLateral);
    }

    public static void main(String[] args) {
        AdminView adminView = new AdminView();
    }
}
