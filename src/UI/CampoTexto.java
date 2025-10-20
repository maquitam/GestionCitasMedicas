package UI;

import java.awt.*;
import javax.swing.*;

public class CampoTexto extends JTextField {
    private final int radio = 15;

    public CampoTexto(int ancho) {
        super(ancho);
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(5,15,5,15));
        setFont(new Font("Segoe UI", Font.PLAIN, 14));
        setBackground(Color.white);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radio, radio);
        
        super.paintComponent(g2);
        g2.dispose();
    }
    
    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2.setColor(new Color(200, 200, 200));
        g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, radio, radio);
        
        g2.dispose();
    }
    
}