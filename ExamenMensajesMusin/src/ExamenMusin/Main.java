package ExamenMusin;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {
	public static Modelo ad = new Modelo();
	public static Scanner sc = new Scanner(System.in);
	public static int numEmp = 0;

	public static void main(String[] args) {

		System.out.println("Introduce tu numero de empleados: ");
		numEmp = sc.nextInt();
		sc.nextLine();
		System.out.println("Dni de empleados: ");
		String dni = sc.nextLine();
		logueado(numEmp, dni);

	}

	private static void logueado(int numEmp, String dni) {
		int codicion = ad.getEmpleado(numEmp, dni);
		switch (codicion) {
		case 0:
			System.out.println("El empleado no existe o la contrasenia es incorrecta");
			break;
		case 1:
			System.out.println("El empleado dentro");
			menuEmpleado();
			break;
		case 2:
			System.out.println("Introduce una nueva contrasenia: ");
			String contrasenia = sc.nextLine();
			if (ad.cambiarContra(contrasenia, numEmp)) {
				System.out.println("Has modificado la contrasenia correctamente");
			} else {
				System.out.println("Error al modificar la contrasenia correctamente");
			}
			System.out.println("El empleado ha sido registrado");

			menuEmpleado();
			break;
		}

	}

	private static void menuEmpleado() {
		int op = 0;
		do {
			System.out.println("1. Enviar mensaje\r\n" + "2. Leer mensajes\r\n" + "3. Estadística mensajes");
			op = sc.nextInt();
			sc.nextLine();
			switch (op) {
			case 1:
				mostrarMostrarMensajes(5);
				enviarMensaje();
				break;
			case 2:
				leerMensaje();
				break;
			case 3:
				estadisticasMensajes();
			default:
				break;
			}
		} while (op != 0);
		System.out.println("Elige una opcion: ");

	}

	private static void estadisticasMensajes() {
		/*
		 * Mostrar para el usuario logueado la siguiente información sobre los mensajes
		 * enviados a los departamentos: NombreDepartameto Nº MesajesEnviados
		 * FechaPrimerMensaje FechaÚltimoMensaje
		 */
		ArrayList<Para> paraList = ad.obtenerMensajesEmpEstadisticas(numEmp);
		for (Para m : paraList) {
			System.out.println("Nombre departamento: " + m.getMensajes().getDeEmpleados().getNombre()
					+ "Cantidad de mensajes: " + m.getMensajes().getEmpleados() + "Fecha primer mensaje: "
					+ m.getMensajes().getAsunto() + "Fecha ultimo mensaje: " + m.getMensajes().getFechaEnvio());
		}
	}

	private static void mostrarEmpleadosDeUnDepartamento(int id) {
		ArrayList<Empleados> emp = ad.obtenerEmpDepartamentoConcreto(id);
		for (Empleados e : emp) {
			System.out.println(e);
		}
	}

	private static void mostrarDepartamentos() {
		ArrayList<Departamento> de = ad.getDepartamentos();
		for (Departamento d : de) {
			System.out.println(d);
		}

	}

	private static void leerMensaje() {
		mostrarMensajesDeUnEmpleado();
	}

	private static void mostrarMensajesDeUnEmpleado() {
		ArrayList<Para> paraList = ad.obtenerMensajesEmp(numEmp);
		for (Para m : paraList) {
			System.out.println(m);
		}

	}

	private static void enviarMensaje() {
		mostrarDepartamentos();
		System.out.println("Introduce un id departamento");
		int id = sc.nextInt();
		sc.nextLine();
		Departamento de = ad.getDepartamento(id);
		if (de != null) {
			System.out.println("Introduce el asunto:");
			String asunto = sc.nextLine();
			System.out.println("Introduce el mensaje:");
			String mensaje = sc.nextLine();
			mostrarEmpleadosDeUnDepartamento(id);
			ArrayList<Empleados> emp = ad.obtenerEmpDepartamentoConcreto(id);
			if (!emp.isEmpty()) {
				Mensajes mens = new Mensajes(id, de, numEmp, asunto, new Date(), mensaje);

				if (ad.escribirMensaje(mens, emp)) {
					System.out.println("Mensaje escrito");
					mostrarMostrarMensajes(mens.getId());
				}
			}
		}
	}

	private static void mostrarMostrarMensajes(int id) {
		ArrayList<Mensajes> p = ad.obtenerMensajes(id);
		for (Mensajes m : p) {
			System.out.println(m);
		}

	}
}