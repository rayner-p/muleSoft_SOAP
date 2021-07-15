package ec.ups.edu.negocio;

import java.util.Date;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.ups.edu.Controlador.CuentaDao;
import ec.ups.edu.modelo.Cliente;
import ec.ups.edu.modelo.Cuenta;

/**
 * Esta clase me permite hacer diferentes validaciones o metodos necesarios
 * antes de poder realizar las diferentes funciones basicas en la base de datos
 */

@Stateless
public class CuentaON {
	private String cedulaCliente;

	@Inject
	private CuentaDao daoCuenta;
	@Inject
	private ClienteON daoCliente;

	public CuentaON()  throws Exception {
		super();
	}

	/**
	 * Método que permite registar el ingreso de una nueva Cuenta al sistema
	 * 
	 * @param cuenta que se obtiene por medio de los Beans de la aprte gráfica
	 * 
	 * @throws Exception captura algún error que pueda ocurrir al momento de
	 *                   utilizar el metodo
	 */

	public void regirestarCuenta(Cuenta cuenta) throws Exception {

		daoCuenta.insert(cuenta);
		System.out.println("Cuenta creado");

	}

	/**
	 * Metodo que nos permite generar un numero de cuenta aleatorio
	 * 
	 *
	 * @return resultadoFinal Nos devuele una cadena de texto con el numero ue se ha
	 *         generado
	 */

	public String generarNumeroDeCuenta() {
		System.out.println("ingresa al on generar num");
		int numeroInicio = 4040;
		List<Cuenta> listaCuentas = listaCuenta();
		System.out.println("lista" + listaCuentas);
		int numero = listaCuentas.size() + 1;
		String resultado = String.format("%08d", numero);
		String resultadoFinal = String.valueOf(numeroInicio) + resultado;
		System.out.println("resultado del crear num cuenta on " + resultadoFinal);
		return resultadoFinal;
	}

	public List<Cuenta> listaCuenta() {
		List<Cuenta> clientes = daoCuenta.obtenerCuenta2();
		System.out.println("obtener cuenta on cuenta" + clientes);
		return clientes;
	}

	/**
	 * Metodo que permite guardar una cuenta de ahorro
	 * 
	 * @param c Cuenta de ahorro que se guarda
	 */
	public void guardarCuentaDeAhorros(Cuenta c) {
		Cliente cliente2 = new Cliente();
		Date fech = new Date();
		try {
			System.out.println("Cuenta ON +" + c.getNumeroCuenta());
			cliente2 = daoCliente.buscarCliente(cedulaCliente);
			if (c.getSaldo() != 0.0) {
				c.setFechaApertura(fech);
				c.setCuenta_fk(cedulaCliente);
				daoCuenta.insert(c);
			} else {
				System.out.println("Ingrese Saldo");
			}

		} catch (Exception e) {
			System.out.println("ERROR AL CREAR LA CUENTA");
			e.printStackTrace();
		}

	}

	/**
	 * Metodo que me permite obtener una cuenta de ahorros que pertenezca a un
	 * cliente
	 * 
	 * @param cedulaCliente Cedula del cliente de la cuenta de ahorros
	 * @return Cuenta de ahorro obtenida de la busqueda
	 */
	public Cuenta getCuentaCedulaCliente(String cedulaCliente) {
		System.out.println("entra al buscar cuenta");
		Cuenta cuentaDeAhorro = daoCuenta.getCuentaCedulaCliente(cedulaCliente);
		System.out.println(cuentaDeAhorro);
		return cuentaDeAhorro;
	}

	/**
	 * 
	 */
	public Cuenta obtenerSaldoClienteCuenta(String numeroCuenta) {
		Cuenta valor = new Cuenta();
		try {
			valor = daoCuenta.obtenerSaldoClienteCuenta(numeroCuenta);
		} catch (SQLException e) {
			System.err.println("ERROR EN EL ON AL OBTENER SALDO CUENTA");
			e.printStackTrace();
		}
		return valor;
	}

	/**
	 * 
	 */
	public String actaulizarCuentaCliente(String numeroCuenta, double valor) {
		if (numeroCuenta == null) {
			System.out.println("No hay datos en la cuenta");
		} else {
			System.out.println("qOBTIENES s " + "" + numeroCuenta + "" + valor);
			daoCuenta.actaulizarCuentaCliente(numeroCuenta, valor);
			System.out.println("se actualizan cuenta");
		}
		return null;
	}

	/**
	 * 
	 */
	public Cuenta obtenerCuentaPorNumero(String numerCuenta) {
		Cuenta cuentB = new Cuenta();
		if (numerCuenta == null) {
			System.out.println("No hay datos en la cuenta");
		} else {

			cuentB = daoCuenta.obtenerCuentaPorNumero(numerCuenta);
			System.out.println("vale obtener cuenta por num");
		}
		return cuentB;
	}

}
