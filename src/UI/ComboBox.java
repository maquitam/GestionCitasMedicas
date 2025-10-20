package UI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;

/**
 * RoundedComboBox - JComboBox con aspecto redondeado y flecha personalizada.
 * Genérico para poder usar cualquier tipo de elementos.
 */
public class ComboBox<E> extends JComboBox<E> {

    private int arc = 20; // radio de curvatura
    private Color bgColor = new Color(240, 240, 240);
    private Color borderColor = new Color(180, 180, 180);

    public ComboBox(E[] items) {
        super(items);
        init();
    }

    public ComboBox() {
        super();
        init();
    }

    private void init() {
        setOpaque(false);
        setFocusable(true);
        setRenderer(createCellRenderer());
        setUI(createCustomUI());
        setUI(createCustomUI());
        setBorder(new EmptyBorder(4,3,4, 0));
    }

    private ListCellRenderer<? super E> createCellRenderer() {
        return new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                JLabel lbl = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                lbl.setOpaque(false);
                lbl.setBorder(new EmptyBorder(1,10 , 1, 0));
                return lbl;
            }
        };
    }

    private BasicComboBoxUI createCustomUI() {
        return new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                return new ArrowButton();
            }

            @Override
            public void installUI(JComponent c) {
                super.installUI(c);
                // quitar borde nativo del arrowButton para que coincida con el estilo
                if (arrowButton != null) {
                    arrowButton.setBorder(null);
                    arrowButton.setContentAreaFilled(false);
                    arrowButton.setFocusPainted(false);
                }
            }

            @Override
            protected ListCellRenderer<Object> createRenderer() {
                // usar el renderer que definimos arriba (pero con cast)
                @SuppressWarnings("unchecked")
                ListCellRenderer<Object> r = (ListCellRenderer<Object>) createCellRenderer();
                return r;
            }
        };
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Pintar fondo redondeado
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        // Fondo
        g2.setColor(bgColor);
        g2.fill(new RoundRectangle2D.Float(0, 0, width - 1, height - 1, arc, arc));

        // Borde
        g2.setColor(borderColor);
        g2.draw(new RoundRectangle2D.Float(0, 0, width - 1, height - 1, arc, arc));

        g2.dispose();

        // Dejar que el super dibuje el texto / iconos encima
        super.paintComponent(g);
    }

    /**
     * Botón de flecha personalizado: transparente y dibuja un triángulo.
     */
    private class ArrowButton extends JButton {
        private final int size = 8;

        ArrowButton() {
            setOpaque(false);
            setFocusable(false);
            setBorder(null);
            setPreferredSize(new Dimension(28, 28));
            setContentAreaFilled(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // opcional: dibujar un pequeño círculo o rect tras la flecha si se desea
            // g2.setColor(new Color(220,220,220,120));
            // g2.fillOval(6, (getHeight()-12)/2, 12, 12);

            // dibujar triángulo
            int x = getWidth() / 2;
            int y = getHeight() / 2;
            Polygon p = new Polygon();
            p.addPoint(x - size/2, y - size/4);
            p.addPoint(x + size/2, y - size/4);
            p.addPoint(x, y + size/2);

            // color de la flecha (podemos usar color oscuro para contraste)
            g2.setColor(new Color(80, 80, 80));
            g2.fill(p);

            g2.dispose();
        }
    }
}