package ec.ups.edu.servicios;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import ec.ups.edu.modelo.Cliente;
import ec.ups.edu.modelo.Cuenta;
import ec.ups.edu.modelo.Transaccion;
import ec.ups.edu.modelo.Transferencia;
import ec.ups.edu.negocio.ClienteON;
import ec.ups.edu.negocio.CuentaON;
import ec.ups.edu.negocio.TransaccionON;
import ec.ups.edu.negocio.TransferenciaON;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class ServicioSOAP {
	@Inject
	private ClienteON onCliente;
	@Inject
	private CuentaON onCuenta;
	@Inject
	private TransaccionON onTransaccion;
	@Inject
	private TransferenciaON onTransferencia;
	
	private Transaccion tran;
	private Transferencia transferencia; 
	private Cuenta c;
	private Cuenta c2;
	private double saldoNuevo;
	private double saldoCuenta ;
	private double saldoNuevo2;
	private Date fechaA;
	
	@WebMethod
	public String crearCuenta(double saldo, String tipoCuenta, String entidadBancaria, String cedula) throws Exception {
		Cuenta cc = new Cuenta();
		Date fechaApertura = new Date();
		cc.setNumeroCuenta(onCuenta.generarNumeroDeCuenta());
		cc.setFechaApertura(fechaApertura);
		cc.setSaldo(saldo);
		cc.setTipoCuenta(tipoCuenta);
		cc.setEntidadBancaria(entidadBancaria);
		cc.setCuenta_fk(cedula);
		onCuenta.regirestarCuenta(cc);
		return "Cuenta creada con éxito";

	}
	@WebMethod
	public String crearCliente(String cedula, String nombre, String apellido, String correo, String celular) throws Exception {
		Cliente c = new Cliente();
		c.setNombre(nombre);
		c.setApellido(apellido);
		c.setCedula(cedula);
		c.setCorreo(correo);
		c.setCelular(celular);
		onCliente.registarCliente(c);
		return "Cliente registrado con éxito";

	}
	
	@WebMethod
	public String realizarTransaccion( double monto,String tipoTransaccion, String cedula,String cuenta) throws Exception {
		
		tran = new Transaccion();

		try {
			fechaA = new Date();
			System.out.println("fecha Actual" + fechaA);
			tran.setFechaHora(fechaA);
			tran.setMonto(monto);
			tran.setTipoTransaccion(tipoTransaccion);
			c = new Cuenta();
			c = onCuenta.obtenerCuentaPorNumero(cuenta);
			if (tipoTransaccion.equals("Retiro") &&  c.getSaldo() < monto) {
				System.out.println("El monto a retirar es mayor que el saldo");

			} else {
				System.out.println("Ingreso a RETIRO Validacion"+" "+c.getSaldo());
				saldoNuevo = c.getSaldo() - monto;
				
				System.out.println("se ha retiro :" + monto + "" + "de la cuenta"+" "+"SALDO ACTUAL"+" "+saldoNuevo);
			}
			if (tipoTransaccion.equals("Deposito")) {
				System.out.println("Ingreso a DEPOSITO Validacion");
				saldoNuevo = monto + c.getSaldo();
				c.setSaldo(saldoNuevo);
				//onCuenta.actaulizarCuentaCliente(cuenta, saldoNuevo);
				System.out.println("se ha aniadido este valor a la cuenta" + saldoNuevo);
			} else {
				System.out.println("error en la valdiacion");
			}
			tran.setCuenta(c);
			System.out.println("que trae la cuenta" + c);
			tran.setTransaccion_fk(cedula);
			onTransaccion.insertarTransaccion(tran);
			onCuenta.actaulizarCuentaCliente(cuenta, saldoNuevo);
			System.out.println("transaccion creada" + " " + tran);
		} catch (Exception e) {
			throw new Exception("Se ah producido un error" + e.getMessage());

		}
		return "Transaccion registrada con éxito";
		
	}

	@WebMethod
	public List<Transaccion> obtenerTransacciones() {
		return onTransaccion.obtenerTransaccion();
	}
	
	@WebMethod
	public String registrarTransferencia(String cuentaOrigen, double monto, String conceptoTransferencia, double tarifa, String cuentaDestino) {	
		c2 = new Cuenta();
		transferencia = new Transferencia();
		
		try {
		c = new Cuenta();
		c = onCuenta.obtenerCuentaPorNumero(cuentaOrigen);	
		
		System.out.println("cuenta origente"+ c.getNumeroCuenta()+" "+c.getSaldo()+"monto que llega "+" "+monto);
		transferencia.setMonto(monto);
		if (transferencia.getMonto() > c.getSaldo()) {
			System.out.println("El valor a transferir es mayor al que se tiene en la cuenta "+""+c.getSaldo());
		}else {
			c2 = onCuenta.obtenerCuentaPorNumero(cuentaDestino);
			transferencia.setClienteLocal(c);
			transferencia.setConceptoTransferencia(conceptoTransferencia);
			transferencia.setTarifaTransaccion(tarifa);
			//transferencia.setCuenta(c2);
			System.out.println("ENTRA A REGISTRAR TRANSFERENCIA");
			
			saldoCuenta = c.getSaldo() - monto;
			saldoNuevo2 = c2.getSaldo() + monto;
			System.out.println("saldo CUENTA RESTANTO"+ saldoCuenta +" "+" saldo a la cuenta detino"+ " "+saldoNuevo2);
			//onCuenta.actaulizarCuentaCliente(cuentaOrigen,saldoCuenta);
			//onCuenta.actaulizarCuentaCliente(cuentaDestino,saldoNuevo2);
			
			onTransferencia.agregarCuentaTransferecia(transferencia);
		}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	} 
	
}
