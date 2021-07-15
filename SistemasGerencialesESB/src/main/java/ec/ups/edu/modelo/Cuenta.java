package ec.ups.edu.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Cuenta implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String numeroCuenta;
	private Date fechaApertura;
	private double saldo;
	private String tipoCuenta;
	private String entidadBancaria;
	private String cuenta_fk;

	/**
	 * Metodo que permite obtener el atributo numeroCuenta
	 * 
	 * @return El atributo numeroCuenta de esta clase
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	/**
	 * Metodo que permite asignar el atributo numeroCuenta
	 * 
	 * @param El atributo numeroCuenta de esta clase
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	/**
	 * Metodo que permite obtener el atributo fechaApertura
	 * 
	 * @return El atributo fechaApertura de esta clase
	 */
	public Date getFechaApertura() {
		return fechaApertura;
	}

	/**
	 * Metodo que permite asignar el atributo fechaApertura
	 * 
	 * @param El atributo fechaApertura de esta clase
	 */
	public void setFechaApertura(java.util.Date fech) {
		this.fechaApertura = fech;
	}

	/**
	 * Metodo que permite obtener el atributo saldo
	 * 
	 * @return El atributo saldo de esta clase
	 */
	public double getSaldo() {
		return saldo;
	}

	/**
	 * Metodo que permite asignar el atributo saldo
	 * 
	 * @param El atributo saldo de esta clase
	 */
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	/**
	 * Metodo que permite asignar el atributo cuenta_fk
	 * 
	 * @return El atributo cuenta_fk de esta clase
	 */
	public String getCuenta_fk() {
		return cuenta_fk;
	}

	/**
	 * Metodo que permite asignar el atributo cuenta_fk
	 * 
	 * @param El atributo cuenta_fk de esta clase
	 */
	public void setCuenta_fk(String cuenta_fk) {
		this.cuenta_fk = cuenta_fk;
	}

	/**
	 * Metodo que permite obtener el atributo tipoCuenta
	 * 
	 * @return El atributo tipoCuenta de esta clase
	 */
	public String getTipoCuenta() {
		return tipoCuenta;
	}

	/**
	 * Metodo que permite asignar el atributo tipoCuenta
	 * 
	 * @param El atributo tipoCuenta de esta clase
	 */
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	
	
	public String getEntidadBancaria() {
		return entidadBancaria;
	}

	public void setEntidadBancaria(String entidadBancaria) {
		this.entidadBancaria = entidadBancaria;
	}

	@Override
	public String toString() {
		return "Cuenta [numeroCuenta=" + numeroCuenta + ", fechaApertura=" + fechaApertura + ", saldo=" + saldo
				+ ", tipoCuenta=" + tipoCuenta + ", entidadBancaria=" + entidadBancaria + ", cuenta_fk=" + cuenta_fk
				+ "]";
	}

	
}
