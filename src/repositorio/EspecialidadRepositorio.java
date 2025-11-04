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
    
    private LoginRepositorio loginRepositorio;

    public EspecialidadRepositorio() throws IOException {
        file = new File(PATH);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
    }

    public List<Especialidad> getEspecialidades() {
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
            
    } catch (Exception exe) {
        return null;
    }        
}

    public String[] getEspecialidadesList() {
        Scanner s = null;
        String[] especialidades = new String[getEspecialidades().size()];
        try {
            s = new Scanner(file);
            int i = 0;
            while (s.hasNextLine()) {
                String especialidadLine = s.nextLine();
                String especialidad = Especialidad.listaEspecialidades(especialidadLine);
                especialidades[i] = especialidad;
                i++;
                /*for (int i = 0; i <= getEspecialidades().size() + 1 ; i++){
                    especialidades[i] = especialidad;
                }*/
            }
    
            return especialidades;
            
    } catch (Exception exe) {
        return null;
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

public boolean registrarEspecialidad(Especialidad especialidad) throws Exception {
    validacionEspecialiad(especialidad);
    try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(PATH, true))) {  
            
            var content = especialidad.toTxtFormat();
            bufferedWriter.write(content);
            bufferedWriter.newLine();

        } catch (Exception exe) {
           throw new Exception(exe.getMessage());
        }
        return true;
    };


    public boolean actualizarBasedeDatos(List<Especialidad> especialidades) throws Exception {
        try {
            if (file.exists()) {
                file.delete();
            }

            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
        

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(PATH, false))) {
            for (var especialidadTmp : especialidades) {
                    String especialidadLine = especialidadTmp.toTxtFormat();
                    bufferedWriter.write(especialidadLine);
                    bufferedWriter.newLine();
            }
        }
        return true;
        
    } catch (Exception exe) {
            throw new Exception(exe.getMessage());
    }
    }
}
