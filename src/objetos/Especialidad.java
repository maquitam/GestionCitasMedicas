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

    // setters
    public void setNombreEspecialidad(String nombreEspecialidad) {
        this.nombreEspecialidad = nombreEspecialidad;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // getters
    public String getNombreEspecialidad() {
        return nombreEspecialidad;
    }

    public int getIdentificador() {
        return identificador;
    }

    public boolean getEstado() {
        return estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    // boolean to formated string
    public String getEstadoFormated() {
        var estado = getEstado();
        switch (estado) {
            case true:
                return "Activa";
            default:
                return "Inactiva";
        }
    };

    // int to formated string
    public String getIdentificadorFormated() {
        return String.valueOf(getIdentificador());
    }

    public String toTxtFormat() {
        return nombreEspecialidad + "|" + identificador + "|" + estado + "|" + descripcion;
    }

    public static Especialidad fromTxtFormat(String lineaEspecialidad) {
        String[] parts = lineaEspecialidad.split("\\|");
        if (parts.length != 4)
            throw new IllegalArgumentException("Fórmato de linea no valido.");
        Especialidad especialidad = new Especialidad(parts[0], Integer.parseInt(parts[1]),
                Boolean.parseBoolean(parts[2]), parts[3]);
        return especialidad;
    }

    public static String listaEspecialidades(String lineaEspecialidad) {
        String[] parts = lineaEspecialidad.split("\\|");
        if (parts.length != 4)
            throw new IllegalArgumentException("Fórmato de linea no valido.");
        String especialidad = parts[0];
        return especialidad;
    }

}
