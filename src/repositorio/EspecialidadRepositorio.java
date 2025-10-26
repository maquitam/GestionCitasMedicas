package repositorio;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
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

public boolean registrarEspecialidad(Especialidad especialidad) throws Exception {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(PATH, true))) {
            var content = especialidad.toTxtFormat();
            bufferedWriter.write(content);
            bufferedWriter.newLine();
        } catch (Exception exe) {
            throw new Exception(exe.getMessage());
        }
        loginRepositorio = new LoginRepositorio();
        loginRepositorio.registrarEspecialidad(especialidad);

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
