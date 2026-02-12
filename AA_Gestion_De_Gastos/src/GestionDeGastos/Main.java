package GestionDeGastos;

import java.util.ArrayList;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int opcion = 0;

		ArrayList<Gastos> listaGastos = new ArrayList<>();

		System.out.println("1. Crear nuevo gasto.");
		System.out.println("2. Borrar gasto.");
		System.out.println("3. Ver todos los gastos.");
		System.out.println("4. Salir.");

		opcion = Integer.parseInt(sc.nextLine());
		while (opcion != 4) {

			switch (opcion) {
			case 1:

				Utilidades.crearNuevoGasto(listaGastos, sc);
						
				break;

			case 2:
				
				Utilidades.borrarGasto(listaGastos, sc);
				
				break;

			case 3:

				Utilidades.mostrarGastos(listaGastos);

				break;

			case 4:
				System.out.println("Adios");

				break;
			}

			System.out.println("1. Crear nuevo gasto.");
			System.out.println("2. Borrar gasto.");
			System.out.println("3. Ver todos los gastos.");
			System.out.println("4. Salir.");

			opcion = Integer.parseInt(sc.nextLine());
		}
		sc.close();
	}

}
