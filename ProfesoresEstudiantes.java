import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ProfesoresEstudiantes {
	ArrayList<String> listaProfesores;
    ArrayList<String> listEstudiantes;
    ArrayList<ArrayList<String>> listaGeneralEstudiantes;
    boolean profesoresRegistrados = false;
    boolean estudiantesRegistrados = false;

    public ProfesoresEstudiantes() {
        listaProfesores = new ArrayList<>();
        listaGeneralEstudiantes = new ArrayList<>();
    }

    public void iniciar() {
        int opcion = 0;

        do {
            opcion = mostrarMenu();
            switch (opcion) {
                case 1:
                    registrarProfesores();
                    break;
                case 2:
                    registrarEstudiantes();
                    break;
                case 3:
                    consultarListaProfesoresYEstudiantes();
                    break;
                case 4:
                    consultaProfesorPorNombre();
                    break;
                case 5:
                    consultaCantidadEstudiantesPorProfesor();
                    break;
                case 6:
                    consultarEstudiantes();
                    break;
                case 7:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        } while (opcion != 7);
    }

    private int mostrarMenu() {
        String menu = "Menú de Opciones:\n" +
                "1. Registrar Profesores\n" +
                "2. Registrar Estudiantes\n" +
                "3. Consultar Lista de Profesores y Estudiantes\n" +
                "4. Consultar Estudiantes de un Profesor\n" +
                "5. Consultar Cantidad de Estudiantes por Profesor\n" +
                "6. Consultar Profesor por Estudiante\n" +
                "7. Salir\n" +
                "Seleccione una opción:";
        return Integer.parseInt(JOptionPane.showInputDialog(menu));
    }

    private void registrarProfesores() {
        System.out.println("\n<<<< Registro de Profesores >>>>");

        int cant = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de profesores"));

        for (int i = 0; i < cant; i++) {
            String nombreProfesor;
            do {
                nombreProfesor = JOptionPane.showInputDialog("Nombre del profesor " + (i + 1));
                if (listaProfesores.contains(nombreProfesor)) {
                    JOptionPane.showMessageDialog(null, "El nombre " + nombreProfesor + " ya está registrado. Ingrese otro nombre.");
                }
            } while (listaProfesores.contains(nombreProfesor));

            listaProfesores.add(nombreProfesor);
        }
        profesoresRegistrados = true;
        System.out.println("Registro de profesores exitoso!\n");
    }

    private void registrarEstudiantes() {
        if (!profesoresRegistrados) {
            JOptionPane.showMessageDialog(null, "Primero debe registrar profesores.");
            return;
        }

        System.out.println("\n<<<< Registro de Estudiantes >>>>");

        for (int i = 0; i < listaProfesores.size(); i++) {
            JOptionPane.showMessageDialog(null, "Ingrese los estudiantes para el profesor " + listaProfesores.get(i));

            listEstudiantes = new ArrayList<>();

            int cant = Integer.parseInt(JOptionPane.showInputDialog("Cantidad de estudiantes"));

            for (int j = 0; j < cant; j++) {
                String nombreEst;
                do {
                    nombreEst = JOptionPane.showInputDialog("Nombre del estudiante " + (j + 1));
                    if (listEstudiantes.contains(nombreEst)) {
                        JOptionPane.showMessageDialog(null, "El nombre " + nombreEst + " ya está registrado en este grupo. Ingrese otro nombre.");
                    }
                } while (listEstudiantes.contains(nombreEst));

                listEstudiantes.add(nombreEst);
            }

            listaGeneralEstudiantes.add(listEstudiantes);
        }
        estudiantesRegistrados = true;
        System.out.println("Registro de estudiantes exitoso!\n");
    }

    private void consultarListaProfesoresYEstudiantes() {
        if (!profesoresRegistrados || !estudiantesRegistrados) {
            JOptionPane.showMessageDialog(null, "Primero debe registrar profesores y estudiantes.");
            return;
        }

        System.out.println("\n<<<< Lista de Profesores y Estudiantes >>>>");
        ArrayList<String> listaTemporal;

        for (int i = 0; i < listaGeneralEstudiantes.size(); i++) {

            listaTemporal = listaGeneralEstudiantes.get(i);
            System.out.println("Profesor: " + listaProfesores.get(i) + " = ");
            System.out.println("[");
            for (int j = 0; j < listaTemporal.size(); j++) {
                System.out.println(listaTemporal.get(j));
                if (j < listaTemporal.size() - 1) {
                    System.out.println(",");
                }
            }
            System.out.println("]\n");
        }
    }

    private void consultaProfesorPorNombre() {
        if (!profesoresRegistrados || !estudiantesRegistrados) {
            JOptionPane.showMessageDialog(null, "Primero debe registrar profesores y estudiantes.");
            return;
        }

        System.out.println("\n<<<< Consulta de Profesor >>>>\n");
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del profesor");

        if (listaProfesores.contains(nombre)) {
            int posicion = listaProfesores.indexOf(nombre);
            ArrayList<String> listaEstudiantesTemporal = listaGeneralEstudiantes.get(posicion);

            System.out.println("Profesor: " + nombre + " = ");
            System.out.println("[");
            for (int i = 0; i < listaEstudiantesTemporal.size(); i++) {
                System.out.println(listaEstudiantesTemporal.get(i));
                if (i < listaEstudiantesTemporal.size() - 1) {
                    System.out.println(",");
                }
            }
            System.out.println("]\n");
        } else {
            System.out.println("No se encuentra el profesor " + nombre + "\n");
        }
    }

    private void consultaCantidadEstudiantesPorProfesor() {
        if (!profesoresRegistrados || !estudiantesRegistrados) {
            JOptionPane.showMessageDialog(null, "Primero debe registrar profesores y estudiantes.");
            return;
        }

        System.out.println("\n<<<< Consulta cantidad de estudiantes de Profesores >>>>\n");
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del profesor para saber la cantidad de estudiantes asociados");

        if (listaProfesores.contains(nombre)) {
            int posicion = listaProfesores.indexOf(nombre);
            ArrayList<String> listaEstudiantesTemporal = listaGeneralEstudiantes.get(posicion);
            System.out.println("La cantidad de estudiantes asociados al profesor " + nombre + " es " + listaEstudiantesTemporal.size());
        } else {
            System.out.println("No se encuentra el profesor " + nombre + "\n");
        }
    }

    private void consultarEstudiantes() {
        if (!profesoresRegistrados || !estudiantesRegistrados) {
            JOptionPane.showMessageDialog(null, "Primero debe registrar profesores y estudiantes.");
            return;
        }

        System.out.println("\n<<<< Consulta de Estudiante >>>>\n");
        ArrayList<String> listTemporal;

        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del estudiante a consultar");

        for (int i = 0; i < listaGeneralEstudiantes.size(); i++) {

            listTemporal = listaGeneralEstudiantes.get(i);

            if (listTemporal.contains(nombre)) {
                for (int j = 0; j < listTemporal.size(); j++) {
                    if (listTemporal.get(j).equalsIgnoreCase(nombre)) {
                        System.out.println("Se encontró al estudiante: " + nombre + " en el grupo del profesor " + listaProfesores.get(i));
                    }
                }
            } else {
                System.out.println("No se encuentra el estudiante en el grupo del profesor " + listaProfesores.get(i));
            }
        }
    }
}
