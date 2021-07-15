package ec.ups.edu.modelo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
@Entity
public class Transferencia implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int idTransferencia;
	@OneToOne(fetch=FetchType.EAGER, cascade = {CascadeType.ALL })
	@JoinColumn(name="cuenta_origen")
	private Cuenta cuentaOrigen;
	private double monto;
	private String conceptoTransferencia;
	private double tarifaTransaccion = 0.0;
	@OneToOne(fetch=FetchType.EAGER, cascade = {CascadeType.ALL })
	@JoinColumn(name="cuenta_destino")
	private Cuenta cuenta;
	
	
	public Cuenta getCuenta() {
		return cuenta;
	}
	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}
	public int getIdTransferencia() {
		return idTransferencia;
	}
	public void setIdTransferencia(int idTransferencia) {
		this.idTransferencia = idTransferencia;
	}
	public Cuenta getClienteLocal() {
		return cuentaOrigen;
	}
	public void setClienteLocal(Cuenta cuentaOrigen) {
		this.cuentaOrigen = cuentaOrigen;
	}
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
	public String getConceptoTransferencia() {
		return conceptoTransferencia;
	}
	public void setConceptoTransferencia(String conceptoTransferencia) {
		this.conceptoTransferencia = conceptoTransferencia;
	}
	public double getTarifaTransaccion() {
		return tarifaTransaccion;
	}
	public void setTarifaTransaccion(double tarifaTransaccion) {
		this.tarifaTransaccion = tarifaTransaccion;
	}
	@Override
	public String toString() {
		return "TranferenciaLocal [idTransferencia=" + idTransferencia + ", cuentaOrigen=" + cuentaOrigen + ", monto="
				+ monto + ", conceptoTransferencia=" + conceptoTransferencia + ", tarifaTransaccion="
				+ tarifaTransaccion + ", cuenta=" + cuenta + "]";
	}
}
