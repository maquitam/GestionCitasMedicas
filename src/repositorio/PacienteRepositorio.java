package repositorio;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import objetos.Paciente;

public class PacienteRepositorio {
    private File file;
    public static final String PATH = "datos\\Pacientes.txt";

    private LoginRepositorio loginRepositorio;

    public PacienteRepositorio() throws IOException {
        file = new File(PATH);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
    }

    public List<Paciente> getPacientes() {
        Scanner s = null;
        List<Paciente> pacientes = new ArrayList<>();
        try {
            s = new Scanner(file);

            while (s.hasNextLine()) {
                String pacienteLine = s.nextLine();
                var paciente = Paciente.fromTxtFormat(pacienteLine);
                pacientes.add(paciente);
            }

            return pacientes;
        } catch (Exception exe) {
            return null;
        }
    }

    public boolean registrarPaciente(Paciente paciente) throws Exception {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(PATH, true))) {
            var content = paciente.toTxtFormat();
            bufferedWriter.write(content);
            bufferedWriter.newLine();
        } catch (Exception exe) {
            throw new Exception(exe.getMessage());
        }

        loginRepositorio = new LoginRepositorio();
        loginRepositorio.registrarUsuario(paciente);

        return true;
    };

    public boolean actualizarBasedeDatos(List<Paciente> pacientes) throws Exception {
        try {
            if (file.exists()) {
                file.delete();
            }

            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(PATH, false))) {
            for (var pacienteTmp : pacientes) {
                String pacienteLine = pacienteTmp.toTxtFormat();
                bufferedWriter.write(pacienteLine);
                bufferedWriter.newLine();
            }
        }
        return true;
        } catch (Exception exe) {
            throw new Exception(exe.getMessage());
        }
    }
}
