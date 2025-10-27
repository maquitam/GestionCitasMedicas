package servicios;

import java.util.ArrayList;
import java.util.List;
import objetos.Medico;

public class ControlMedico {

    private List<Medico> listaMedicos;

    public ControlMedico() {
        listaMedicos = new ArrayList<>();
    }

    public boolean agregarMedico(Medico medico) {
        if (medico == null) return false;

        for (Medico m : listaMedicos) {
            if (m.getNumeroDoc().equalsIgnoreCase(medico.getNumeroDoc())) {
                System.out.println(" El médico con ese documento ya existe.");
                return false;
            }
        }

        listaMedicos.add(medico);
        System.out.println(" Médico agregado correctamente.");
        return true;
    }

    public boolean eliminarMedico(String numeroDocumento) {
        for (Medico m : new ArrayList<>(listaMedicos)) {
            if (m.getNumeroDoc().equalsIgnoreCase(numeroDocumento)) {
                listaMedicos.remove(m);
                System.out.println(" Médico eliminado correctamente.");
                return true;
            }
        }
        System.out.println(" No se encontró un médico con ese documento.");
        return false;
    }

    public List<Medico> buscarMedicoPorEspecialidad(String especialidad) {
        List<Medico> resultado = new ArrayList<>();
        if (especialidad == null) return resultado;

        for (Medico m : listaMedicos) {
            try {
                if (m.getEspeciliadad() != null && m.getEspeciliadad().equalsIgnoreCase(especialidad)) {
                    resultado.add(m);
                }
            } catch (Exception e) {
            }
        }
        return resultado;
    }

    public List<Medico> listarMedicos() {
        return new ArrayList<>(listaMedicos);
    }

    public Medico buscarMedicoPorDocumento(String numeroDocumento) {
        for (Medico m : listaMedicos) {
            if (m.getNumeroDoc().equalsIgnoreCase(numeroDocumento)) {
                return m;
            }
        }
        return null;
    }
}
