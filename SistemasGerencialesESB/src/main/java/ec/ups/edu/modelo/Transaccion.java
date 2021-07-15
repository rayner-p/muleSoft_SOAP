package ec.ups.edu.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Transaccion implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int codigoTransaccion;	
	private Date fechaHora;
	private String sucursal;
	private double monto;
	private String tipoTransaccion;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cuenta_trans")
	private Cuenta cuenta;
	private String transaccion_fk;  //no tocar
	
	public int getCodigoTransaccion() {
		return codigoTransaccion;
	}
	public void setCodigoTransaccion(int codigoTransaccion) {
		this.codigoTransaccion = codigoTransaccion;
	}
	public Date getFechaHora() {
		return fechaHora;
	}
	public void setFechaHora(java.util.Date fechaA) {
		this.fechaHora = fechaA;
	}
	public String getSucursal() {
		return sucursal;
	}
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
	public String getTipoTransaccion() {
		return tipoTransaccion;
	}
	public void setTipoTransaccion(String tipoTransaccion) {
		this.tipoTransaccion = tipoTransaccion;
	}
	public Cuenta getCuenta() {
		return cuenta;
	}
	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}
	public String getTransaccion_fk() {
		return transaccion_fk;
	}
	public void setTransaccion_fk(String transaccion_fk) {
		this.transaccion_fk = transaccion_fk;
	}
	@Override
	public String toString() {
		return "Transaccion [codigoTransaccion=" + codigoTransaccion + ", fechaHora=" + fechaHora + ", sucursal="
				+ sucursal + ", monto=" + monto + ", tipoTransaccion=" + tipoTransaccion + ", cuenta=" + cuenta
				+ ", transaccion_fk=" + transaccion_fk + "]";
	}

	
	
}
