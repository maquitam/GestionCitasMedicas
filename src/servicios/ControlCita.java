package servicios;
import java.util.List;
import java.util.ArrayList;
import objetos.Cita;
public class ControlCita {
    private List<Cita> listaCitas;

    public ControlCita() {
        this.listaCitas = new ArrayList<>();
    }

    public void agendarCita(Cita nuevaCita) {
        for (Cita citaExistente: listaCitas) {
            if(!nuevaCita.validarDisponibilidad(citaExistente)){
                System.out.println("Ya existe una cita con esas caracteristicas");
                return;
            }
        }
        listaCitas.add(nuevaCita);
        nuevaCita.crearCita();
    }
    public void cancelarCita(int idEliminar){
        boolean eliminada = listaCitas.removeIf(c -> c.getIdCita() == idEliminar);
        if (eliminada) {
            System.out.println("Cita cancelada con éxito.");
        } else {
            System.out.println("No se encontró una cita con el ID especificado.");
        }
    }

    public void listarCitas(){
        if (listaCitas.isEmpty()){
            System.out.println("Lista vacia");
        }
        else{
            for (Cita c: listaCitas) {
                System.out.println(c);
            }
        }
    }

    public void buscarCitaPorPaciente(long documentoPaciente){
        boolean encontrado = false;
        for (Cita c: listaCitas) {
            if (c.getPaciente().getDocumento() == documentoPaciente){
                System.out.println(c);
                encontrado = true;
            }
        }
        if (!encontrado){
        System.out.println("El paciente con el documento: "+ documentoPaciente +" no tene citas registradas.");       
        }
    }
}
