// ...existing code...
package repositorio;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import objetos.Medico;

public class MedicoRepositorio {
    private File file;
    public static final String PATH = "datos\\Medicos.txt";
    
    private LoginRepositorio loginRepositorio;

    public MedicoRepositorio() throws IOException {
        file = new File(PATH);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
    }

    public List<Medico> getMedicos() {
        Scanner s = null;
        List<Medico> medicos = new ArrayList<>();
        try {
            s = new Scanner(file);
            while (s.hasNextLine()) {
                String medicoLine = s.nextLine();
                try {
                    var medico = Medico.fromTxtFormat(medicoLine);
                    if (medico != null) medicos.add(medico);
                } catch (Exception e) {
                    // línea inválida: ignorar
                }
            }
        } catch (Exception exe) {
            // opcional: registrar log
        } finally {
            if (s != null) s.close();
        }
        return medicos;
    }

    public boolean registrarMedico(Medico medico) throws Exception {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(PATH, true))) {
            var content = medico.toTxtFormat();
            bufferedWriter.write(content);
            bufferedWriter.newLine();
        } catch (Exception exe) {
            throw new Exception(exe.getMessage());
        }
        loginRepositorio = new LoginRepositorio();
        loginRepositorio.registrarUsuario(medico);

        return true;
    }

    public boolean actualizarBasedeDatos(List<Medico> medicos) throws Exception {
        try {
            if (file.exists()) {
                file.delete();
            }

            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
        
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(PATH, false))) {
                for (var medicoTmp : medicos) {
                    String medicoLine = medicoTmp.toTxtFormat();
                    bufferedWriter.write(medicoLine);
                    bufferedWriter.newLine();
                }
            }
            return true;
        
        } catch (Exception exe) {
            throw new Exception(exe.getMessage());
        }
    }
}