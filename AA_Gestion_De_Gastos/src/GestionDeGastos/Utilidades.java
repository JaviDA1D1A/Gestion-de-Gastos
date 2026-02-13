package GestionDeGastos;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Utilidades {

	public static void crearNuevoGasto(ArrayList<Gastos> listaGastos, Scanner sc) {

		String motivoGasto;
		double cantidad;
		String fecha;
		String tipoSeleccionado;
		String tipo = "Desconocido";

		System.out.println("===Crear nuevo gasto===");

		System.out.println("Introduce el motivo: ");
		motivoGasto = sc.nextLine();

		System.out.println("Introduce la cantidad: ");
		cantidad = Double.parseDouble(sc.nextLine());

		System.out.println("Introduce la fecha:");
		fecha = sc.nextLine();

		System.out.println("Tipo de gasto: \n 1. Bizum \n 2. Automático \n 3. Otros");
		tipoSeleccionado = sc.nextLine();

		if (tipoSeleccionado.equals("1")) {
			tipo = "Bizum";
		} else if (tipoSeleccionado.equals("2")) {
			tipo = "Automático";
		} else {
			System.out.println("Opción no válida, se guardará como 'Otros'");
			tipo = "Otros";
		}

		Gastos nuevoGasto = new Gastos(motivoGasto, cantidad, fecha, tipo);

		listaGastos.add(nuevoGasto);

		System.out.println("Gasto guardado correctamente. \n");

	}

	public static void mostrarGastos(ArrayList<Gastos> listaGastos) {

		int suma = 0;

		System.out.println("===Todos los Gastos===");

		if (listaGastos.isEmpty()) {
			System.out.println("===Lista vacia===\n");
		} else {

			for (int i = 0; i < listaGastos.size(); i++) {
				Gastos gastoActual = listaGastos.get(i);

				gastoActual.mostrarDatos();
				System.out.println(suma);
			}
		}

	}

	public static void borrarGasto(ArrayList<Gastos> listaGastos, Scanner sc) {

		if (listaGastos.isEmpty()) {
			System.out.println("No hay gastos registrados para borrar.");
			return;
		}

		System.out.println("--- Borrar Gasto ---");

		for (int i = 0; i < listaGastos.size(); i++) {
			System.out.println((i + 1) + ". " + listaGastos.get(i).getMotivoGasto() + " ("
					+ listaGastos.get(i).getCantidad() + "€)");
		}

		System.out.println("Introduce el número del gasto que quieres eliminar:");

		try {
			int seleccion = Integer.parseInt(sc.nextLine());

			if (seleccion > 0 && seleccion <= listaGastos.size()) {

				listaGastos.remove(seleccion - 1);

				System.out.println("Gasto eliminado correctamente.");

			} else {
				System.out.println("Error: El número introducido no corresponde a ningún gasto.");
			}

		} catch (NumberFormatException e) {
			System.out.println("Error: Debes introducir un número válido.");
		} catch (Exception e) {
			System.out.println("Ocurrió un error inesperado: " + e.getMessage());
		}
	}

	public static void mostrarEnFichero(ArrayList<Gastos> listaGastos) {
	    
	    try (PrintWriter escritor = new PrintWriter(new FileWriter("Resumen.txt"))) {
	        
	        escritor.println("===LISTADO DE GASTOS===");
	        
	        for (Gastos g : listaGastos) {
	            escritor.println(g.toString()); 
	         
	        }
	        System.out.println("Fichero de texto creado correctamente.");
	        
	    } catch (IOException e) {
	        System.out.println("Error al escribir el fichero de texto: " + e.getMessage());
	    }
	}

}
