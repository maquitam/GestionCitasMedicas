package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PanelCitas extends JPanel {
    public PanelCitas () {
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        JLabel titulo = new JLabel("Aquí va la vista de citas: ", SwingConstants.CENTER);
        titulo.setFont(new Font("Windings", Font.BOLD, 20));
        add(titulo, BorderLayout.CENTER);
    }
}
