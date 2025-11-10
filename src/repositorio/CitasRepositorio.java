package repositorio;

import java.io.*;
import java.util.*;

import excepciones.ExcepcionesEspecialidad;
import objetos.Cita;
import objetos.Especialidad;
import objetos.Medico;
import objetos.Paciente;

public class CitasRepositorio {

    // Maneja operaciones de lectura y escritura de citas.txt

    // funciones Registra, lista, elimina busca
    // formato idcita|idpaciente|idmedico|fecha|hora|motivo

    private static final String PATH = "datos\\Citas.txt";
    private File archivo;

    // contructor crear el archivo si no existe
    public CitasRepositorio() {
        archivo = new File(PATH);
        try {
            if (!archivo.exists()) {
                archivo.getParentFile().mkdirs();
                archivo.createNewFile();
            }
        } catch (IOException e) {
            System.err.println("Error creando archivo Citas " + e);
        }
    }

    // Guardamos en archivo citas
    // true guardo false hubo un error
    public boolean registrarCita(Cita cita) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(archivo, true))) {
            bufferedWriter.write(cita.exportarArchivoCitas());
            //bufferedWriter.newLine();

            return true;
        } catch (IOException e) {
            System.err.println("Error guardando Citas " + e);
            return false;
        }
    }

    // listar todas
    public List<Cita> listarCitas() {
        List<Cita> citas = new ArrayList<>();

        try (Scanner s = new Scanner(archivo)) {

            // Repos para resolver IDs a objetos completos
            repositorio.MedicoRepositorio medicoRepo = new repositorio.MedicoRepositorio();
            repositorio.PacienteRepositorio pacienteRepo = new repositorio.PacienteRepositorio();

            var medicos = medicoRepo.getMedicos();
            var pacientes = pacienteRepo.getPacientes();

            while (s.hasNextLine()) {
                String linea = s.nextLine().trim();
                if (linea.isEmpty())
                    continue;

                Cita cita = Cita.cargarDesdeArchivo(linea);
                if (cita == null)
                    continue;

                // Resolver médico
                if (cita.getMedico() != null) {
                    int idMedico = cita.getMedico().getId();
                    for (Medico m : medicos) {
                        if (m.getId() == idMedico) {
                            cita.setMedico(m);
                            break;
                        }
                    }
                }

                // Resolver paciente
                if (cita.getPaciente() != null) {
                    int idPaciente = cita.getPaciente().getId();
                    for (Paciente p : pacientes) {
                        if (p.getId() == idPaciente) {
                            cita.setPaciente(p);
                            break;
                        }
                    }
                }

                citas.add(cita);
            }

        } catch (Exception e) {
            System.err.println("Error leyendo Citas: " + e.getMessage());
        }

        return citas;
    }

    // actualizar archivo con una lista de citas
    public boolean actualizarBasedeDatos(List<Cita> citas) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, false))) {

            for (Cita c : citas) {
                writer.write(c.exportarArchivoCitas());
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            System.err.println("Error leyendo Citas " + e);
            return false;
        }
    }

    // eliminar cita con ID
    public boolean eliminarCita(int idCita) {

        List<Cita> citas = listarCitas();
        boolean eliminado = citas.removeIf(c -> c.getIdCita() == idCita);

        if (eliminado) {
            return actualizarBasedeDatos(citas);
        } else {
            System.out.println("Error eliminando Cita ID " + idCita);
            return false;
        }
    }

    // Buscar cita por paciente
    public List<Cita> buscarCitaPorPaciente(int idPaciente) {

        List<Cita> todas = listarCitas();
        List<Cita> resultado = new ArrayList<>();

        for (Cita c : todas) {
            if (c.getPaciente() != null && c.getPaciente().getId() == idPaciente) {
                resultado.add(c);
            }
        }
        return resultado;
    }
}
