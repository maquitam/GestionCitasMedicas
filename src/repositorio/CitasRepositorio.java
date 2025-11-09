package repositorio;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import excepciones.ExcepcionesEspecialidad;
import objetos.Cita;
import objetos.Especialidad;

public class CitasRepositorio {
    private File file;
    public static final String PATH = "datos\\Citas.txt";

    private LoginRepositorio loginRepositorio;

    public CitasRepositorio() throws IOException {
        file = new File(PATH);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
    }

    public List<Cita> getCitas() {
        Scanner s = null;
        List<Cita> citas = new ArrayList<>();
        try {
            s = new Scanner(file);

            while (s.hasNextLine()) {
                String citaLine = s.nextLine();
                var cita = Cita.fromTxtFormat(citaLine);
                citas.add(cita);
            }

            return citas;

        } catch (Exception exe) {
            return null;
        }        
    }

    public String[] getCitasList() throws IOException, Exception {
        Scanner s = null;
        try {String[] citas = new String[getCitas().size()];
            s = new Scanner(file);
            int i = 0;
            while (s.hasNextLine()) {
                String citaLine = s.nextLine();
                String cita = Cita.listaCitas(citaLine);
                citas[i] = cita;
                i++;
            }
            return citas;

        } catch (IOException e) {
            throw new ExcepcionesEspecialidad("A ocurrido un error leyendo el archivo Especialidades.txt: " + e.getMessage());
            }

    public boolean registrarCita(Cita cita) throws Exception {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(PATH, true))) {
            var content = cita.toTxtFormat();
            bufferedWriter.write(content);
            bufferedWriter.newLine();
        } catch (Exception exe) {
            throw new Exception(exe.getMessage());
        }

        return true;
    }

    public boolean actualizarBasedeDatos(List<Cita> citas) throws Exception {
        try {
            if (file.exists()) {
                file.delete();
            }

            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
        

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(PATH, false))) {
            for (var citaTmp : citas) {
                    String citaLine = citaTmp.toTxtFormat();
                    bufferedWriter.write(citaLine);
                    bufferedWriter.newLine();
            }
        }
        return true;
        
    } catch (Exception exe) {
            throw new Exception(exe.getMessage());
    }
    }
}