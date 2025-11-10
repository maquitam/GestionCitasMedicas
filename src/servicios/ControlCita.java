package servicios;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import objetos.Cita;
import objetos.Medico;
import objetos.Paciente;
import repositorio.CitasRepositorio;

public class ControlCita {

    private final List<Cita> listaCitas;
    private final CitasRepositorio citasRepositorio;

    // Constructor: carga citas desde archivo
    public ControlCita() {
        this.citasRepositorio = new CitasRepositorio();
        this.listaCitas = citasRepositorio.listarCitas();
    }

    // Agendar nueva cita
    public boolean agendarCita(Cita nuevaCita) {
        if (!validarDisponibilidad(nuevaCita.getMedico(), nuevaCita.getFecha(), nuevaCita.getHora())) {
            System.out.println("El médico ya tiene una cita en esa fecha y hora.");
            return false;
        }

        nuevaCita.setIdCita(generarIdCita());
        listaCitas.add(nuevaCita);

        boolean guardado = citasRepositorio.registrarCita(nuevaCita);
        if (guardado) {
            System.out.println("Cita agendada y guardada: " + nuevaCita);
            return true;
        } else {
            System.err.println("Error guardando cita en archivo.");
            return false;
        }
    }

    // Validar disponibilidad del médico
    public boolean validarDisponibilidad(Medico medico, LocalDate fecha, LocalTime hora) {
        for (Cita c : listaCitas) {
            if (c.getMedico() != null
                    && c.getMedico().equals(medico)
                    && c.getFecha() != null && c.getFecha().equals(fecha)
                    && c.getHora() != null && c.getHora().equals(hora)) {
                return false;
            }
        }
        return true;
    }

    // Modificar cita existente
    public boolean modificarCita(int idCita, Medico nuevoMedico, LocalDate nuevaFecha, LocalTime nuevaHora,
            String nuevoMotivo) {
        for (Cita c : listaCitas) {
            if (c.getIdCita() == idCita) {
                if (!(c.getMedico().equals(nuevoMedico)
                        && c.getFecha().equals(nuevaFecha)
                        && c.getHora().equals(nuevaHora))) {

                    if (!validarDisponibilidad(nuevoMedico, nuevaFecha, nuevaHora)) {
                        System.out.println("El médico ya tiene una cita en esa fecha y hora.");
                        return false;
                    }
                }

                c.setMedico(nuevoMedico);
                c.setFecha(nuevaFecha);
                c.setHora(nuevaHora);
                c.setMotivo(nuevoMotivo);
                citasRepositorio.actualizarBasedeDatos(listaCitas);
                System.out.println("Cita modificada: " + c);
                return true;
            }
        }
        System.out.println("No se encontró la cita con ID " + idCita);
        return false;
    }

    // Eliminar cita
    public boolean eliminarCita(int idEliminar) {
        boolean eliminada = listaCitas.removeIf(c -> c.getIdCita() == idEliminar);
        if (eliminada) {
            citasRepositorio.actualizarBasedeDatos(listaCitas);
            System.out.println("Cita eliminada.");
        } else {
            System.out.println("No se encontró la cita con ID " + idEliminar);
        }
        return eliminada;
    }

    // Buscar citas por paciente
    public List<Cita> buscarCitaPorPaciente(Paciente paciente) {
        List<Cita> resultado = new ArrayList<>();
        if (paciente == null)
            return resultado;

        for (Cita c : listaCitas) {
            if (c.getPaciente() != null &&
                    c.getPaciente().getId() == paciente.getId()) {
                resultado.add(c);
            }
        }
        return resultado;
    }

    public List<Cita> mostrarTodasLasCitas() {
        List<Cita> resultado = new ArrayList<>();
        for (Cita c : listaCitas) {
            resultado.add(c);
        }
        return resultado;
    }

    // Listar todas las citas
    public List<Cita> listarCitas() {
        return new ArrayList<>(listaCitas);
    }

    // Generar ID automático
    public int generarIdCita() {
        int maxiId = 0;
        for (Cita c : listaCitas) {
            if (c.getIdCita() > maxiId) {
                maxiId = c.getIdCita();
            }
        }
        return maxiId + 1;
    }

    // Buscar cita por ID
    public Cita buscarPorId(int idCita) {
        for (Cita c : listaCitas) {
            if (c.getIdCita() == idCita) {
                return c;
            }
        }
        return null;
    }
}