package servicios;

import java.util.ArrayList;
import java.util.List;
import objetos.Medico;

public class ControlMedico {

    // Lista que simula la base de datos de médicos
    private List<Medico> listaMedicos;

    // Constructor
    public ControlMedico() {
        listaMedicos = new ArrayList<>();
    }

    // Método para agregar un médico
    public boolean agregarMedico(Medico medico) {
        // Evitar duplicados por código o nombre
        for (Medico m : listaMedicos) {
            if (m.getCodigo().equals(medico.getCodigo())) {
                System.out.println("⚠️ El médico con ese código ya existe.");
                return false;
            }
        }
        listaMedicos.add(medico);
        System.out.println("✅ Médico agregado correctamente.");
        return true;
    }

    // Método para eliminar un médico por su código
    public boolean eliminarMedico(String codigo) {
        for (Medico m : listaMedicos) {
            if (m.getCodigo().equals(codigo)) {
                listaMedicos.remove(m);
                System.out.println("🗑️ Médico eliminado correctamente.");
                return true;
            }
        }
        System.out.println("❌ No se encontró un médico con ese código.");
        return false;
    }

    // Método para buscar médicos por especialidad
    public List<Medico> buscarMedicoPorEspecialidad(String especialidad) {
        List<Medico> resultado = new ArrayList<>();
        for (Medico m : listaMedicos) {
            if (m.getEspecialidad().equalsIgnoreCase(especialidad)) {
                resultado.add(m);
            }
        }
        return resultado;
    }

    // Método para listar todos los médicos
    public List<Medico> listarMedicos() {
        return listaMedicos;
    }

    // Método opcional: buscar por nombre o código
    public Medico buscarMedico(String codigo) {
        for (Medico m : listaMedicos) {
            if (m.getCodigo().equals(codigo)) {
                return m;
            }
        }
        return null;
    }
}