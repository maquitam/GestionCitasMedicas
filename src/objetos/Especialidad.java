package objetos;

public class Especialidad {

String nombreEspecialidad;
String identificador;
boolean estado;

// Registrar Especialidad()
public Especialidad(String nombreEspecialidad, String identificador, boolean estado){
    this.nombreEspecialidad = nombreEspecialidad;
    this.identificador = identificador;
    this.estado = estado;
}


public void actualizarEspecialidad(String nombreEspecialidad, String identificador, boolean estado){
    this.nombreEspecialidad = nombreEspecialidad;
    this.identificador = identificador;
    this.estado = estado;
}

public String consultarEspecialidad(){
    return "Nombre: " + nombreEspecialidad +"\nID: " + identificador + "\nEstado: "+ estado;
}


}
