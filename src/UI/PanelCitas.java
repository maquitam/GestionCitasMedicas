package UI;

import UI.Boton;
import objetos.Cita;
import objetos.Medico;
import objetos.Paciente;
import servicios.ControlCita;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.awt.event.MouseAdapter;

public class PanelCitas extends JPanel{
    private AdminView adminView;
    private ControlCita controlCita;

    private JTable tablaCitas;
    private DefaultTableModel modeloTabla;
    private JTextField txtDocumento, txtPaciente, txtMedico, txtFecha, txtHora, txtMotivo;

    private Boton agendarCitaBoton, modificarCitaBoton, cancelarCitaBoton, listarCitaBoton, buscarCitaPorPacienteBoton;
    

    public PanelCitas (AdminView adminView) {
        this.adminView = adminView;
        this.controlCita = new ControlCita();

        setLayout(new BorderLayout(10,10));
        setBackground(Color.WHITE);
        
        //Titulo Principal
        JLabel tituloLabel = new JLabel("Gestión de Citas Médicas", SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        tituloLabel.setForeground(new Color(35,94,40));
        add(tituloLabel, BorderLayout.NORTH);
        
        //Panel Superior - Formulario de Citas
        JPanel panelSuperior = crearPanelFormulario();
        add(panelSuperior, BorderLayout.NORTH);

        //Panel Central - Tabla de Citas
        JPanel panelCentral = crearPanelTabla();
        add(panelCentral, BorderLayout.CENTER);

        //Panel Inferior - Botones de Acción
        JPanel panelInferior = crearPanelBusqueda();
        add(panelInferior, BorderLayout.SOUTH);
    }
    //Formulario de datos
    private JPanel crearPanelFormulario() {
        JPanel panel = new JPanel(new GridLayout(3, 4, 10, 5));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createTitledBorder("Datos De La Cita"));

        txtDocumento = new JTextField();
        txtPaciente = new JTextField();
        txtMedico = new JTextField();
        txtMotivo = new JTextField();
        txtFecha = new JTextField();
        txtHora = new JTextField();

        //Etiquetas y campos de texto
        panel.add(new JLabel("Documento Del Paciente:"));
        panel.add(txtDocumento);
        panel.add(new JLabel("Nombre Del Paciente:"));
        panel.add(txtPaciente);
        panel.add(new JLabel("Nombre Del Médico:"));
        panel.add(txtMedico);
        panel.add(new JLabel("Motivo:"));
        panel.add(txtMotivo);
        panel.add(new JLabel("DD/MM/YYYY:"));
        panel.add(txtFecha);
        panel.add(new JLabel("HH:MM:"));
        panel.add(txtHora);

        //Botones
        agendarCitaBoton = new Boton("Agendar Cita");
        modificarCitaBoton = new Boton("Modificar Cita");
        cancelarCitaBoton = new Boton("Cancelar Cita");

        //Acciones de los botones
        agendarCitaBoton.addActionListener(e -> agendarCita());
        modificarCitaBoton.addActionListener(e -> modificarCita());
        cancelarCitaBoton.addActionListener(e -> cancelarCita());
        
        panel.add(agendarCitaBoton);
        panel.add(modificarCitaBoton);
        panel.add(cancelarCitaBoton);
        return panel;
    }

    //Tabla de citas
    private JPanel crearPanelTabla() {
        JPanel panel = new JPanel(new GridLayout(3, 4, 10, 5));
        panel.setBackground(Color.WHITE);
        return panel;
    }

    //Busqueda de citas
    private JPanel crearPanelBusqueda() {
            JPanel panel = new JPanel(new GridLayout(3, 4, 10, 5));
            panel.setBackground(Color.WHITE);
            return panel;
        }    
}
