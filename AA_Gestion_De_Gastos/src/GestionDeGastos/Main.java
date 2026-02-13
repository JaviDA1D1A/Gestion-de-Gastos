package GestionDeGastos;

import java.io.IOException;
import java.util.ArrayList;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);

		int opcion = 0;

		ArrayList<Gastos> listaGastos = new ArrayList<>();
		
		
		
		//String motivoGasto, double cantidad, String fecha, String tipo
		
		Gastos alquiler = new Gastos("Alquiler", 800, "01", "Automático");
		Gastos Spotify = new Gastos("Spotify", 11.99, "03", "Automático");
		
		listaGastos.add(Spotify);
		listaGastos.add(alquiler);
		
		System.out.println("1. Crear nuevo gasto.");
		System.out.println("2. Borrar gasto.");
		System.out.println("3. Ver todos los gastos.");
		System.out.println("4. Mostrar datos en fichero.");
		System.out.println("5. Salir.");

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
				
				
				
				Utilidades.mostrarEnFichero(listaGastos);
				
				break;

			case 5:
				System.out.println("Adios");

				break;
			}

			System.out.println("1. Crear nuevo gasto.");
			System.out.println("2. Borrar gasto.");
			System.out.println("3. Ver todos los gastos.");
			System.out.println("4. Mostrar datos en fichero.");
			System.out.println("5. Salir.");
			
			opcion = Integer.parseInt(sc.nextLine());
		}
		sc.close();
	}

}
