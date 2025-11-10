package repositorio;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import objetos.Especialidad;
import objetos.Login;
import objetos.Usuario;

public class LoginRepositorio {
    private File file;
    public static final String PATH = "datos\\Login.txt";

    public LoginRepositorio() throws IOException {
        file = new File(PATH);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();

            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(PATH, true))) {
                var admin = "admin" + "|" + "admin";
                bufferedWriter.write(admin);
                bufferedWriter.newLine();
            } catch (Exception exe) {
                exe.getMessage();
            }

        }
    }

    public List<Login> getLogins() {
        Scanner s = null;
        List<Login> logins = new ArrayList<>();
        try {
            s = new Scanner(file);

            while (s.hasNextLine()) {
                String loginLine = s.nextLine();
                var login = new Login(loginLine.split("\\|")[0], loginLine.split("\\|")[1]);
                logins.add(login);
            }

            return logins;

        } catch (Exception exe) {
            return null;
        }
    }

    public boolean registrarUsuario(Usuario usuario) throws Exception {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(PATH, true))) {
            var content = usuario.getNumeroDoc() + "|" + usuario.getContrassena() + "|" + usuario.getPerfil();
            bufferedWriter.write(content);
            bufferedWriter.newLine();
        } catch (Exception exe) {
            throw new Exception(exe.getMessage());
        }

        return true;
    }

    public boolean eliminarUsuario(String documento) throws Exception {
        Scanner s = null;
        List<String> logins = new ArrayList<>();
        try {
            s = new Scanner(file);

            while (s.hasNextLine()) {
                String loginLine = s.nextLine();
                if (!loginLine.contains(documento)) {
                    logins.add(loginLine);
                }
                ;
            }

            if (file.exists()) {
                file.delete();
            }

            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(PATH, false))) {
                for (var login : logins) {
                    bufferedWriter.write(login);
                    bufferedWriter.newLine();
                }
            }

            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public String obtenerPerfilUsuario(String documento) {
        Scanner s = null;
        try {
            System.out.println("Leyendo el archivo Login...");
            s = new Scanner(file);

            while (s.hasNextLine()) {
                String loginLine = s.nextLine();
                String[] partes = loginLine.split("\\|");
                if (documento.equals("admin")) {
                    return "admin";
                } else if (partes[0].equals(documento)) {
                    return partes[2];
                }
            }

            return "";

        } catch (Exception exe) {
            return null;
        }
    }

    // prueba alejo
    public boolean registrarEspecialidad(Especialidad especialidad) throws Exception {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(PATH, true))) {
            var content = especialidad.getNombreEspecialidad() + "|" + especialidad.getIdentificador() + "|"
                    + especialidad.getEstado() + "|" + especialidad.getDescripcion();
            bufferedWriter.write(content);
            bufferedWriter.newLine();
        } catch (Exception exe) {
            throw new Exception(exe.getMessage());
        }

        return true;
    }

    // buscamos en login.txt credenciales iniciadas
    // si coinciden devuelve un objeto usuario
    // si no, pos no devuelve null
    public Usuario validarCredenciales(String usuario, String contrasena) {

        try (Scanner s = new Scanner(file)) {
            while (s.hasNextLine()) {
                String[] partes = s.nextLine().split("\\|");

                // Esperamos: usuario|contraseña|perfil
                if (partes.length >= 3) {
                    String nombreUsuario = partes[0];
                    String password = partes[1];
                    String perfil = partes[2];

                    if (nombreUsuario.equals(usuario) && password.equals(contrasena)) {
                        Usuario u = new Login();
                        u.setNumeroDoc(nombreUsuario);
                        u.setContrasenna(password);
                        ;
                        u.setPerfil(perfil);
                        return u;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
