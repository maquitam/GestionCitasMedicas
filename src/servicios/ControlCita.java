package servicios;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import objetos.Cita;
import objetos.Medico;
import objetos.Paciente;

public class ControlCita {

    private final List<Cita> listaCitas;

    public ControlCita() {
        this.listaCitas = new ArrayList<>();
    }

    
    //Agendar una nueva cita
    
    public boolean agendarCita(Cita nuevaCita) {
        if (!validarDisponibilidad(nuevaCita.getMedico(), nuevaCita.getFecha(), nuevaCita.getHora())) {
            System.out.println("❌ El médico ya tiene una cita en esa fecha y hora.");
            return false;
        }

        nuevaCita.setIdCita(generarIdCita());
        listaCitas.add(nuevaCita);
        System.out.println("✅ Cita agendada correctamente: " + nuevaCita);
        return true;
    }

    
    //Validar disponibilidad del médico
    
    public boolean validarDisponibilidad(Medico medico, LocalDate fecha, LocalTime hora) {
        for (Cita c : listaCitas) {
            if (c.getMedico() != null
                    && c.getMedico().equals(medico)
                    && c.getFecha() != null && c.getFecha().equals(fecha)
                    && c.getHora() != null && c.getHora().equals(hora)) {
                return false; // El médico ya tiene cita a esa hora
            }
        }
        return true;
    }

    
    //Modificar cita
    
    public boolean modificarCita(int idCita, Medico nuevoMedico, LocalDate nuevaFecha, LocalTime nuevaHora, String nuevoMotivo) {
        for (Cita c : listaCitas) {
            if (c.getIdCita() == idCita) {
                // Validar disponibilidad (excepto si es el mismo horario)
                if (!(c.getMedico().equals(nuevoMedico)
                        && c.getFecha().equals(nuevaFecha)
                        && c.getHora().equals(nuevaHora))) {

                    if (!validarDisponibilidad(nuevoMedico, nuevaFecha, nuevaHora)) {
                        System.out.println("❌ El médico ya tiene una cita en esa fecha y hora.");
                        return false;
                    }
                }

                c.setMedico(nuevoMedico);
                c.setFecha(nuevaFecha);
                c.setHora(nuevaHora);
                c.setMotivo(nuevoMotivo);
                System.out.println("✅ Cita modificada correctamente: " + c);
                return true;
            }
        }
        System.out.println("⚠️ No se encontró una cita con el ID " + idCita);
        return false;
    }

    
    //Cancelar cita
    
    public boolean eliminarCita(int idEliminar) {
        boolean eliminada = listaCitas.removeIf(c -> c.getIdCita() == idEliminar);
        if (eliminada) {
            System.out.println("🗑️ Cita cancelada correctamente.");
        } else {
            System.out.println("⚠️ No se encontró la cita con el ID " + idEliminar);
        }
        return eliminada;
    }

    
    //Buscar citas por paciente
    
    public List<Cita> buscarCitaPorPaciente(Paciente paciente) {
        List<Cita> resultado = new ArrayList<>();
        for (Cita c : listaCitas) {
            if (c.getPaciente() != null && c.getPaciente().equals(paciente)) {
                resultado.add(c);
            }
        }
        return resultado;
    }

    
    //Listar todas las citas
    
    public List<Cita> listarCitas() {
        return new ArrayList<>(listaCitas);
    }

    
    //Generar ID automático
    
    public int generarIdCita() {
        return listaCitas.size() + 1;
    }

    
    //Buscar cita por ID
    
    public Cita buscarPorId(int idCita) {
        for (Cita c : listaCitas) {
            if (c.getIdCita() == idCita) {
                return c;
            }
        }
        return null;
    }
}
