package GestionDeGastos;

import java.io.Serializable;

public class Gastos implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String MotivoGasto;
	private double Cantidad;
	private String fecha;
	private String tipo;

	public Gastos(String motivoGasto, double cantidad, String fecha, String tipo) {
		this.MotivoGasto = motivoGasto;
		this.Cantidad = cantidad;
		this.fecha = fecha;
		this.tipo = tipo;
	}

	public String getMotivoGasto() {
		return MotivoGasto;
	}

	public void setMotivoGasto(String motivoGasto) {
		MotivoGasto = motivoGasto;
	}

	public double getCantidad() {
		return Cantidad;
	}

	public void setCantidad(double cantidad) {
		Cantidad = cantidad;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void mostrarDatos() {
		System.out.println("===Gasto===");
		System.out.println("Motivo: " + getMotivoGasto());
		System.out.println("Cantidad: " + getCantidad() + " €");
		System.out.println("Fecha de cobro: " + getFecha() + "/01");
		System.out.println("Tipo de gasto : " + getTipo());
		System.out.println();
	}

}
