/*******************************************************************************
 * 2021, All rights reserved.
 *******************************************************************************/
package Clases.ito.poo;

import java.time.LocalDate;
// Start of user code (user defined imports)

// End of user code

/**
 * Description of CuentaBancaria.
 * 
 * @author Kevin
 */
public class CuentaBancaria implements Comparable<CuentaBancaria> {
	
	private long numCuenta = 0L;
	private String nomCliente = "";
	private float Saldo = 0F;
	private LocalDate fechaApertura = null;
	private LocalDate fechaActualizacion = null;
	
	public CuentaBancaria() {
		
		super();
		
	}
	public CuentaBancaria(long numCuenta, String nomCliente, float saldo, LocalDate fechaApertura) {
		super();
		this.numCuenta = numCuenta;
		this.nomCliente = nomCliente;
		Saldo = saldo;
		this.fechaApertura = fechaApertura;
	}
	
	public boolean Deposito(float Cantidad) {
		boolean Deposito = false;
		if(this.fechaApertura==null)
			System.out.println("La cuenta no est� activa");
		else {
			Deposito = true;
			this.setSaldo(this.getSaldo()+Cantidad);
			this.fechaActualizacion=LocalDate.now();
		}
		
		return Deposito;
	}
	

	public boolean Retiro(float Cantidad) {
		
		boolean Retiro = false;
		if(Cantidad<=this.getSaldo()) {
			Retiro = true;
			this.setSaldo(this.getSaldo()-Cantidad);
			this.fechaActualizacion=LocalDate.now();
		}
		else
			System.out.println("La cantidad a retirar sobrepasa el saldo");
		return Retiro;
	}

	
	public long getNumCuenta() {
		
		return this.numCuenta;
	}

	public void setNumCuenta(long newNumCuenta) {
		
		this.numCuenta = newNumCuenta;
	}

	
	public String getNomCliente() {
		return this.nomCliente;
	}

	
	public void setNomCliente(String newNomCliente) {
		this.nomCliente = newNomCliente;
	}

	public float getSaldo() {
		return this.Saldo;
	}

	public void setSaldo(float newSaldo) {
		this.Saldo = newSaldo;
	}

	public LocalDate getFechaApertura() {
		return this.fechaApertura;
	}

	public void setFechaApertura(LocalDate newFechaApertura) {
		this.fechaApertura = newFechaApertura;
	}

	public LocalDate getFechaActualizacion() {
		return this.fechaActualizacion;
	}

	public void setFechaActualizacion(LocalDate newFechaActualizacion) {
		this.fechaActualizacion = newFechaActualizacion;
	}

	@Override
	public String toString() {
		return "CuentaBancaria [numCuenta=" + numCuenta + ", nomCliente=" + nomCliente + ", Saldo=" + Saldo
				+ ", fechaApertura=" + fechaApertura + ", fechaActualizacion=" + fechaActualizacion + "]";
	}
	@Override
	public int compareTo(CuentaBancaria o) {
		int compare=0;
		if (this.numCuenta<o.getNumCuenta())
			compare=-1;
		else if(this.numCuenta>o.getNumCuenta())
			compare=1;
		return compare;
		
	}

}