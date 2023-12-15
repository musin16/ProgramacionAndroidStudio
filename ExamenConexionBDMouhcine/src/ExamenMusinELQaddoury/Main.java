package ExamenMusinELQaddoury;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static Modelo ad = new Modelo();
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("Elige una opcion: ");
		System.out.println("[1]");
		System.out.println("[2]");
		System.out.println("[3]");
		System.out.println("[4]");
		int opcion = sc.nextInt();
		sc.nextLine();
		do {
			switch (opcion) {
			case 1:
				ej1();
				break;
			case 2:
				ej2();
				break;
			case 3:
				ej3();
				break;
			case 4:
				ej4();
				break;
			}
		} while (opcion != 0);
	}

	private static void ej4() {
		// TODO Auto-generated method stub

	}

	private static void ej3() {
		// TODO Auto-generated method stub

	}

	private static void ej2() {
		// TODO Auto-generated method stub

	}

	private static void ej1() {
		// TODO Auto-generated method stub

	}

	private static void mostrarE() {
		ArrayList<Object> obj = ad.obtenerTodos();
		for (Object object : obj) {
			System.out.println(object);
		}
	}

	private static void mostrar2() {
		ArrayList<Object> obj = ad.obtenerTodos();
		for (Object object : obj) {
			System.out.println(object);
		}
	}

	private static void mostrarPorID(int id) {
		ArrayList<Object> obj = ad.obtenerTodos(id);
		for (Object object : obj) {
			System.out.println(object);
		}
	}

}
