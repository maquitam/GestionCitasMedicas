package app;

import UI.LoginView;
import UI.PacienteView;
import objetos.Especialidad;
import objetos.Paciente;
import repositorio.EspecialidadRepositorio;
import servicios.ServicioPaciente;

public class Main {

    public static void main(String[] args) {

        // Crear o buscar el paciente actual
        LoginView login = new LoginView();
      try{ EspecialidadRepositorio especialidadRepositorio = new EspecialidadRepositorio();
       var lista = especialidadRepositorio.getEspecialidadesList();
            System.out.println("------------------------------------");

       System.out.println(lista[0]);
            System.out.println("------------------------------------");

        }catch(Exception e){
            System.out.println("------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("------------------------------------");

        }


        ServicioPaciente servicioPaciente = new ServicioPaciente();

        // Intentar buscar paciente existente por documento
        Paciente pacienteActual = servicioPaciente.obtenerPaciente("123456");

        // Si no existe, crear uno de prueba
        if (pacienteActual == null) {
            pacienteActual = new Paciente(
                1,                  // id
                "Juan",             // nombres
                "Pérez",            // apellidos
                "CC",               // tipoDocumento
                "123456",        // numeroDocumento
                "3001234567",       // telefono
                "juanperez@mail.com", // correo
                "Cra 10 #5-20",     // direccion
                "Paciente",         // tipoUsuario
                "1234",             // contrasena
                "1990-01-01",       // fechaNacimiento
                "M",                // genero
                "Activo"            // estado
            );
        }

        // Iniciar la vista del paciente
        try {
            PacienteView vistaPaciente = new PacienteView(pacienteActual.getNombres());
            vistaPaciente.setVisible(true);

            System.out.println("✅ Paciente logueado: " + pacienteActual.getNombres());
        } catch (Exception e) {
            System.err.println("❌ Error al iniciar la vista del paciente: " + e.getMessage());
            e.printStackTrace();
        }
    }
}