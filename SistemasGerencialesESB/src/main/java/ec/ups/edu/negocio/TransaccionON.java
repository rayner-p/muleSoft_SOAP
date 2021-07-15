package ec.ups.edu.negocio;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.ups.edu.Controlador.TransaccionDao;
import ec.ups.edu.modelo.Transaccion;

/**
 * Esta clase me permite hacer diferentes validaciones o metodos necesarios
 * antes de poder realizar las diferentes funciones basicas en la base de datos
 */

@Stateless
public class TransaccionON {
	@Inject
	private TransaccionDao daoTransaccion;
	
	public TransaccionON() throws Exception {
		super();
	}
	public void insertarTransaccion(Transaccion transaccion) {
		System.out.println("on insert tran" +transaccion);
		daoTransaccion.insertarTransaccion(transaccion);
	}
	
	public List<Transaccion> obtenerTransaccion() {
		return daoTransaccion.obtenerTransaccion();
	}
	
	/**
	 * Metodo que permite buscar las transacciones de un usuario entre fechas
	 * 
	 * @param cedula Numero de cedula de la persona que busca
	 * @param fechaI La fecha de inicio desde donde se quieren ver las
	 *               transacciones.
	 * @param fechaF La fecha de fin hasta donde se quieren ver las transacciones.
	 * @return Una lista de las transacciones/movimientos del usuario entre las
	 *         fechas indicadas.
	 * @throws Exception Excepción por si el cliente no tiene transacciones.
	 */
	public List<Transaccion> obtenerTransaccionesFechaHora(String cedula, Date fechaI, Date fechaF) {
		//String fechaInicio = fechaI.setTime(00:00:00.000000);" 00:00:00.000000";
		//String fechaFinal = fechaF + " 23:59:59.000000";
		 
		try {
			return daoTransaccion.getListaTransaccionesFechas(cedula, fechaI, fechaI);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public List<Transaccion> obtenerTransaccionXEDUL(String cedula){
		return daoTransaccion.obtenerTransaccionXEDUL(cedula);
	}

}
