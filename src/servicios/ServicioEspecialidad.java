package servicios;

import java.io.IOException;
import java.util.Map;
import objetos.Especialidad;
import objetos.Paciente;

import java.util.HashMap;
import java.util.List;

import repositorio.LoginRepositorio;
import repositorio.EspecialidadRepositorio;


public class ServicioEspecialidad {
        
    EspecialidadRepositorio especialidadRepositorio;
    LoginRepositorio loginRepositorio;

    public ServicioEspecialidad() {
   
        try {
            especialidadRepositorio = new EspecialidadRepositorio();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Especialidad> getEspecialidades() {
        return especialidadRepositorio.getEspecialidades();
    }

    public int generarId() {
        var especialidades = getEspecialidades();
        
        if (especialidades == null) {
            return 0;
        }
        
        return especialidades.size();
    }

    public boolean crearEspecialidad(Map<String, String> datos) throws Exception {
        try {
            if (buscarPorNombre(datos.get("nombreEspecialidad")) != -1) {
                return false;
            }

            var especialidad = new Especialidad(datos.get("nombreEspecialidad"), generarId(),true, datos.get("descripcion"));
        
        return especialidadRepositorio.registrarEspecialidad(especialidad);

        } catch (Exception e) {
            return false;
        }
        
    }

    public Map<String, String> cargarEspecialidad(String nombre) {
        Especialidad especialidad = obtenerEspecialidad(nombre);

        Map<String, String> datos = new HashMap<>();

        datos.put("nombreEspecialidad", especialidad.getNombreEspecialidad());
        datos.put("identificador", especialidad.getIdentificadorFormated());
        datos.put("estado", especialidad.getEstadoFormated());
        datos.put("descripcion", especialidad.getDescripcion());
       
        return datos;
    }

    public Especialidad obtenerEspecialidad(String nombre) {
        var especialidades = getEspecialidades();
        var indice = buscarPorNombre(nombre);
        return especialidades.get(indice);
    };

    public int buscarPorNombre(String nombre) {
        var especialidades = getEspecialidades();

        if (especialidades == null) {return -1;}

        for (var temporal : especialidades) {
            if (temporal.getNombreEspecialidad().equals(nombre)) {
                return especialidades.indexOf(temporal);
            }
        }

        return -1;
    }

    public boolean actualizarEspecialidad(Map<String, String> datos) throws Exception {
        var especialidades = getEspecialidades();
        var indice = buscarPorNombre(datos.get("nombreEspecialidad"));
        var especialidad = especialidades.get(indice);

        if (especialidad.getEstado()) {
            especialidad.setEstado(false);
            } else {
                especialidad.setEstado(true);
    }

        especialidadRepositorio.actualizarBasedeDatos(especialidades);

        return true;
    };
    

    
}

