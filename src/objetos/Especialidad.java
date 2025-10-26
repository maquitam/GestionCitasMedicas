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


//setters
public void setNombreEspecialidad(String nombreEspecialidad) {this.nombreEspecialidad = nombreEspecialidad;}
public void setIdentificador(String identificador) {this.identificador = identificador;}
public void setEstado(boolean estado) {this.estado = estado;}

//getters
public String getNombreEspecialidad() {return nombreEspecialidad;}
public String getIdentificador() {return identificador;}
public boolean getEstado() {return estado;}

public String getEstadoFormated() {
    var estado = getEstado();
    switch (estado) {
        case true:
            return "Activa";
        default:
            return "Inactiva";
    }
};

public String toTxtFormat() {
    return identificador + "|" + nombreEspecialidad + "|" + estado;
}

 public static Especialidad fromTxtFormat(String lineaEspecialidad) {
    String[] parts = lineaEspecialidad.split("\\|");
  if (parts.length != 3) throw new IllegalArgumentException("Fórmato de linea no valido.");
  Especialidad especialidad = new Especialidad(parts[0], (parts[1]), Boolean.parseBoolean(parts[2]));
    return especialidad;
 }


}
