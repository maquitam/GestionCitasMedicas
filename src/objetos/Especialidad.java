package objetos;

public class Especialidad {

String nombreEspecialidad, descripcion;
int identificador;
boolean estado;


public Especialidad(String nombreEspecialidad, int identificador, boolean estado, String descripcion) {
    this.nombreEspecialidad = nombreEspecialidad;
    this.identificador = identificador;
    this.estado = estado;
    this.descripcion = descripcion;
}

/* 
public void actualizarEspecialidad(String nombreEspecialidad, String identificador, boolean estado){
    this.nombreEspecialidad = nombreEspecialidad;
    this.identificador = identificador;
    this.estado = estado;
}

public String consultarEspecialidad(){
    return "Nombre: " + nombreEspecialidad +"\nID: " + identificador + "\nEstado: "+ estado;
}*/

//setters
public void setNombreEspecialidad(String nombreEspecialidad) {this.nombreEspecialidad = nombreEspecialidad;}
public void setIdentificador(int identificador) {this.identificador = identificador;}
public void setEstado(boolean estado) {this.estado = estado;}
public void setDescripcion(String descripcion) {this.descripcion = descripcion;}

//getters
public String getNombreEspecialidad() {return nombreEspecialidad;}
public int getIdentificador() {return identificador;}
public boolean getEstado() {return estado;}
public String getDescripcion() {return descripcion;}

public String getEstadoFormated() {
    var estado = getEstado();
    switch (estado) {
        case true:
            return "Activa";
        default:
            return "Inactiva";
    }
};

public String getIdentificadorFormated() {
    var idFormated = String.valueOf(getIdentificador());
    return idFormated;
    }

public String toTxtFormat() {
    return identificador + "|" + nombreEspecialidad + "|" + estado;
    }

 public static Especialidad fromTxtFormat(String lineaEspecialidad) {
    String[] parts = lineaEspecialidad.split("\\|");
  if (parts.length != 3) throw new IllegalArgumentException("Fórmato de linea no valido.");
  Especialidad especialidad = new Especialidad(parts[0], Integer.parseInt(parts[1]), Boolean.parseBoolean(parts[2]), parts[3]);
    return especialidad;
    }


}
