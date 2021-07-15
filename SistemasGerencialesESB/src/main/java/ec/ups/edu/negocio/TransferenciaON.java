package ec.ups.edu.negocio;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.ups.edu.Controlador.ClienteDao;
import ec.ups.edu.Controlador.TransferenciaDao;
import ec.ups.edu.modelo.Cuenta;
import ec.ups.edu.modelo.Transferencia;

@Stateless
public class TransferenciaON {
	@Inject
	private TransferenciaDao daoTransferenciaR;
	@Inject
	private ClienteDao daoCliente;

	public TransferenciaON() {
		super();
	}

	/**
	 * 
	 */
	public void agregarCuentaTransferecia(Transferencia transferencia) throws Exception {

		System.out.println("Transferencia");
		daoTransferenciaR.insert(transferencia);

	}

	// metodo que sirve para actualizar los datos del cliente
	public Transferencia obtenerClienteCuenta(String numeroCuenta) {
		if (daoTransferenciaR.obtenerClienteCuenta(numeroCuenta) == null) {
			System.out.println("No existe cliente con ese número de cuenta");

		} else {
			System.out.println("existe usuario");
		}
		return null;
	}

	/**
	 * Metodo que permite obtener las transferencias locales de un cliente
	 * 
	 * 
	 * 
	 * @return Lista de transferencias de un cliemte
	 */

	public List<Cuenta> getTransfereciasOrigenes(String cuenta) {
		try {
			List<Cuenta> resultados = (List<Cuenta>) daoTransferenciaR.getTransfereciaLocals(cuenta);
			return resultados;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
