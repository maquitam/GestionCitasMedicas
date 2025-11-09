package UI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import objetos.*;
import servicios.ControlCita;
import servicios.ServicioMedico;
import servicios.UsuarioSesion;

public class AdminCitas extends JPanel {

    private JComboBox<Especialidad> comboEspecialidad;
    private JComboBox<Medico> comboMedico;
    private JTextField txtFecha;
    private JTextField txtHora;
    private JTextArea txtMotivo;
    private JTable tablaCitas;
    private DefaultTableModel modeloTabla;
    private JButton btnModificar, btnEliminar;

    private final ControlCita controlCita;
    private final ServicioMedico servicioMedico;
    private final String usuario;

    public AdminCitas(AdminView parentView) {
        this.usuario = parentView.USUARIO_AUTENTICADO;
        this.controlCita = new ControlCita();
        this.servicioMedico = new ServicioMedico();

        initComponents();
        cargarEspecialidades();
        cargarCitasPacienteSeleccionado();
    }

    // Interfaz visual
    private void initComponents() {
        setLayout(new BorderLayout(10, 10));

        JPanel panelFormulario = new JPanel(new GridLayout(5, 2, 10, 10));

        comboEspecialidad = new JComboBox<>();
        comboMedico = new JComboBox<>();
        txtFecha = new JTextField(LocalDate.now().toString());
        txtHora = new JTextField("09:00");
        txtMotivo = new JTextArea(3, 20);

        btnModificar = new JButton("Modificar");
        btnEliminar = new JButton("Eliminar");

        panelFormulario.add(new JLabel("Especialidad:"));
        panelFormulario.add(comboEspecialidad);
        panelFormulario.add(new JLabel("Médico:"));
        panelFormulario.add(comboMedico);
        panelFormulario.add(new JLabel("Fecha (AAAA-MM-DD):"));
        panelFormulario.add(txtFecha);
        panelFormulario.add(new JLabel("Hora (HH:MM):"));
        panelFormulario.add(txtHora);
        panelFormulario.add(new JLabel("Motivo:"));
        panelFormulario.add(new JScrollPane(txtMotivo));

        modeloTabla = new DefaultTableModel(new Object[] { "ID", "Médico", "Fecha", "Hora", "Motivo" }, 0);
        tablaCitas = new JTable(modeloTabla);

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);

        add(panelFormulario, BorderLayout.NORTH);
        add(new JScrollPane(tablaCitas), BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        // Eventos
        comboEspecialidad.addActionListener(e -> filtrarMedicosPorEspecialidad());
        btnModificar.addActionListener(e -> modificarCita());
        btnEliminar.addActionListener(e -> eliminarCita());
    }

    // Cargar especialidades (por ahora vacías)
    private void cargarEspecialidades() {
        comboEspecialidad.removeAllItems();
        try {
            repositorio.EspecialidadRepositorio repo = new repositorio.EspecialidadRepositorio();
            for (Especialidad e : repo.getEspecialidades()) {
                comboEspecialidad.addItem(e);
            }

            comboEspecialidad.setRenderer(new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(
                        JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                    super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    if (value instanceof Especialidad esp) {
                        setText(esp.getNombreEspecialidad());
                    }
                    return this;
                }
            });

        } catch (Exception ex) {
            System.err.println("Error cargando especialidades: " + ex.getMessage());
        }
    }

    // Filtrar médicos según especialidad seleccionada
    private void filtrarMedicosPorEspecialidad() {
        comboMedico.removeAllItems();

        Especialidad esp = (Especialidad) comboEspecialidad.getSelectedItem();
        String nombreEsp = (esp != null) ? esp.getNombreEspecialidad() : null;

        try {
            repositorio.MedicoRepositorio repo = new repositorio.MedicoRepositorio();
            for (Medico medico : repo.getMedicos()) {

                // Si no hay especialidad seleccionada, mostrar todos
                if (nombreEsp == null || nombreEsp.isEmpty()) {
                    comboMedico.addItem(medico);
                } else if (medico.getEspeciliadad() != null &&
                        medico.getEspeciliadad().equalsIgnoreCase(nombreEsp)) {
                    comboMedico.addItem(medico);
                }
            }

            comboMedico.setRenderer(new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(
                        JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                    super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    if (value instanceof Medico medico) {
                        setText(medico.getNombres() + " " + medico.getApellidos());
                    }
                    return this;
                }
            });

        } catch (Exception e) {
            System.err.println("Error cargando médicos: " + e.getMessage());
        }
    }

    // CAMBIAR A CITAS DEL PACIENTE SELECCIONADO
    private void cargarCitasPacienteSeleccionado() {
        modeloTabla.setRowCount(0);
        Paciente pacienteActual = (Paciente) UsuarioSesion.getUsuarioActual();
        if (pacienteActual == null) {
            System.err.println("No hay paciente logueado.");
            return;
        }

        List<Cita> citas = controlCita.buscarCitaPorPaciente(pacienteActual);
        for (Cita c : citas) {
            modeloTabla.addRow(new Object[] {
                    c.getIdCita(),
                    c.getMedico().getNombres() + " " + c.getMedico().getApellidos(),
                    c.getFecha(),
                    c.getHora(),
                    c.getMotivo()
            });
        }
    }

    // Modificar cita seleccionada
    private void modificarCita() {
        int fila = tablaCitas.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione una cita primero.");
            return;
        }

        try {
            int id = (int) modeloTabla.getValueAt(fila, 0);
            Medico m = (Medico) comboMedico.getSelectedItem();
            LocalDate fecha = LocalDate.parse(txtFecha.getText());
            LocalTime hora = LocalTime.parse(txtHora.getText());
            String motivo = txtMotivo.getText();

            controlCita.modificarCita(id, m, fecha, hora, motivo);
            JOptionPane.showMessageDialog(this, "Cita modificada correctamente.");
            cargarCitasPacienteSeleccionado();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al modificar: " + ex.getMessage());
        }
    }

    // Eliminar cita seleccionada
    private void eliminarCita() {
        int fila = tablaCitas.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione una cita primero.");
            return;
        }

        int id = (int) modeloTabla.getValueAt(fila, 0);
        controlCita.eliminarCita(id);
        JOptionPane.showMessageDialog(this, "Cita eliminada correctamente.");
        cargarCitasPacienteSeleccionado();
    }
}
