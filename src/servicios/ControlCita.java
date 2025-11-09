package servicios;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import objetos.Cita;
import objetos.Medico;
import objetos.Paciente;

import repositorio.LoginRepositorio;
import repositorio.CitasRepositorio;


public class ControlCita {

    CitasRepositorio citasRepositorio;
    LoginRepositorio loginRepositorio;
    private final List<Cita> listaCitas;

    public ControlCita() {

        try {
            citasRepositorio = new CitasRepositorio();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.listaCitas = new ArrayList<>();
    }
    //Agendar una nueva cita
    
    public List<Cita> getCitas() throws Exception, IOException{
        return citasRepositorio.getCitas();
    }

    public boolean agendarCita(Cita nuevaCita) {
        if (!validarDisponibilidad(nuevaCita.getIdMedico(), nuevaCita.getFecha(), nuevaCita.getHora())) {
            System.out.println("El médico ya tiene una cita en esa fecha y hora.");
            return false;
        }

        nuevaCita.setIdCita(generarIdCita());
        listaCitas.add(nuevaCita);
        System.out.println("Cita agendada correctamente: " + nuevaCita);
        return true;
    }

    
    //Validar disponibilidad del médico

    public boolean validarDisponibilidad(String idMedico, LocalDate fecha, LocalTime hora) {
        for (Cita c : listaCitas) {
            if (c.getIdMedico() != null
                    && c.getIdMedico().equals(idMedico)
                    && c.getFecha() != null && c.getFecha().equals(fecha)
                    && c.getHora() != null && c.getHora().equals(hora)) {
                return false; // El médico ya tiene cita a esa hora
            }
        }
        return true;
    }

    
    //Modificar cita

    public boolean modificarCita(int idCita, String nuevoIdMedico, LocalDate nuevaFecha, LocalTime nuevaHora, String nuevoMotivo) {
        for (Cita c : listaCitas) {
            if (c.getIdCita() == idCita) {
                // Validar disponibilidad (excepto si es el mismo horario)
                if (!(c.getIdMedico().equals(nuevoIdMedico)
                        && c.getFecha().equals(nuevaFecha)
                        && c.getHora().equals(nuevaHora))) {

                    if (!validarDisponibilidad(nuevoIdMedico, nuevaFecha, nuevaHora)) {
                        System.out.println("El médico ya tiene una cita en esa fecha y hora.");
                        return false;
                    }
                }

                c.setIdMedico(nuevoIdMedico);
                c.setFecha(nuevaFecha);
                c.setHora(nuevaHora);
                c.setMotivo(nuevoMotivo);
                System.out.println("Cita modificada correctamente: " + c);
                return true;
            }
        }
        System.out.println("No se encontró una cita con el ID " + idCita);
        return false;
    }

    
    //Cancelar cita
    
    public boolean eliminarCita(int idEliminar) {
        boolean eliminada = listaCitas.removeIf(c -> c.getIdCita() == idEliminar);
        if (eliminada) {
            System.out.println("Cita cancelada correctamente.");
        } else {
            System.out.println("No se encontró la cita con el ID " + idEliminar);
        }
        return eliminada;
    }

    
    //Buscar citas por paciente

    public List<Cita> buscarCitaPorPaciente(String idPaciente) {
        List<Cita> resultado = new ArrayList<>();
        for (Cita c : listaCitas) {
            if (c.getIdPaciente() != null && c.getIdPaciente().equals(idPaciente)) {
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
