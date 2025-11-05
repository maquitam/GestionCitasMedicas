package repositorio;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import excepciones.ExcepcionesEspecialidad;
import objetos.Especialidad;


public class EspecialidadRepositorio {
    private File file;
    public static final String PATH = "datos\\Especialidades.txt";
    
    public EspecialidadRepositorio() throws IOException, Exception {
       try {file = new File(PATH);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        }catch(IOException e){
            throw new ExcepcionesEspecialidad("Error creando el archivo Especialidades.txt: " + e.getMessage());
        }
    }

    public List<Especialidad> getEspecialidades() throws IOException, Exception {
        Scanner s = null;
        List<Especialidad> especialidades = new ArrayList<>();
        try {
            s = new Scanner(file);
            while (s.hasNextLine()) {
                String especialidadLine = s.nextLine();
                var especialidad = Especialidad.fromTxtFormat(especialidadLine);
                especialidades.add(especialidad);
            }
            return especialidades;
            
    } catch (IOException e) {
            throw new ExcepcionesEspecialidad("A ocurrido un error leyendo el archivo Especialidades.txt: " + e.getMessage());
    }  
}

    public String[] getEspecialidadesList() throws IOException, Exception {
        Scanner s = null;
        try {String[] especialidades = new String[getEspecialidades().size()];
            s = new Scanner(file);
            int i = 0;
            while (s.hasNextLine()) {
                String especialidadLine = s.nextLine();
                String especialidad = Especialidad.listaEspecialidades(especialidadLine);
                especialidades[i] = especialidad;
                i++;
            }
            return especialidades;
            
        } catch (IOException e) {
            throw new ExcepcionesEspecialidad("A ocurrido un error leyendo el archivo Especialidades.txt: " + e.getMessage());
            }
}  

public void validacionEspecialiad(Especialidad especialidad) throws Exception{
    if(especialidad.getNombreEspecialidad().isEmpty() || especialidad.getNombreEspecialidad().isBlank()){
                throw new ExcepcionesEspecialidad("Nombre de especialidad NO válido.");
            }
    if(especialidad.getDescripcion().isEmpty() || especialidad.getDescripcion().isBlank()){
                throw new ExcepcionesEspecialidad("Descripción de especialidad NO válida.");
            }    
}

public boolean registrarEspecialidad(Especialidad especialidad) throws IOException, Exception {
    validacionEspecialiad(especialidad);
    try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(PATH, true))) {  
            var content = especialidad.toTxtFormat();
            bufferedWriter.write(content);
            bufferedWriter.newLine();
            return true;
        } catch (Exception e) {
            throw new ExcepcionesEspecialidad("Error registrando especialidad: " + e.getMessage());
            }   
    };


    public boolean actualizarBasedeDatos(List<Especialidad> especialidades) throws Exception, IOException {
        try {
            if (file.exists()) {
                file.delete();
                }

            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
                }
        
            try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(PATH, false))){
            for (var especialidadTmp : especialidades) {
                    String especialidadLine = especialidadTmp.toTxtFormat();
                    bufferedWriter.write(especialidadLine);
                    bufferedWriter.newLine();
                    }
                }
                return true;
            }catch (IOException e) {
                throw new ExcepcionesEspecialidad("Error actualizando base de datos: " + e.getMessage());
                }
    }
}
