package ec.ups.edu.negocio;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.ups.edu.Controlador.ClienteDao;
import ec.ups.edu.modelo.Cliente;



/**
 * Esta clase me permite hacer diferentes validaciones o metodos necesarios
 * antes de poder realizar las diferentes funciones basicas en la base de datos
 */

@Stateless
public class ClienteON {
	private String cedulaCliente;
	@Inject
	private ClienteDao daoCliente;
	
	public ClienteON() throws Exception {
		super();
	}
	/**
	 * Método que permite registar el ingreso de un Cliente al sistema
	 * 
	 * @param empleado que se obtiene por medio de los Beans
	 * 
	 * @throws Exception captura algún error que pueda ocurrir al momento de
	 *                   utilizar el metodo
	 */
	public void registarCliente(Cliente empleado) throws Exception {
		try {
			// System.out.println("BEAN"+empleado);
			System.out.println("entra al on ");
			daoCliente.insert(empleado);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro al insertar");
		}

	}
	/**
	 * Método que permite buscar a un cliente por medio de la cedula
	 * 
	 * @param cedula que se obtiene por medio de las cjasa de texto en la interfaz
	 * 
	 * @throws Exception captura algún error que pueda ocurrir al momento de
	 *                   utilizar el metodo
	 */
	public Cliente buscarCliente(String cedula) throws Exception {

		Cliente cliente = daoCliente.read(cedula);
		cedulaCliente = cliente.getCedula();
		System.out.println("BUSQUEDAD CLIENTE CORRECTA " + cedulaCliente);
		return cliente;

	}
	/**
	 * Método que permite actualizar a un Cliente
	 * 
	 * @param cliente Se obtiene al momento que se instancia este metodo. suele ser
	 *                usado para la interfaz
	 * @throws Exception captura algún error que pueda ocurrir al momento de
	 *                   utilizar el metodo
	 */
	public void actaulizarCliente(Cliente cliente) throws Exception {
		String empleCedula = cliente.getCedula();
		if (daoCliente.read(empleCedula) == null) {
			throw new Exception("No existe usuario con esa cédula");
		}
		daoCliente.update(cliente);
		System.out.println("DATOS DEL ON ++" + cliente.getNombre());
		System.out.println("Empleado actualizado");

	}
	/**
	 * Metodo para obtener una lista con todos los cliente
	 * 
	 * 
	 * 
	 * @return daoEmpleado.getEmpleados() Un Empleado registrado en la Base de Datos
	 */
	public List<Cliente> getClienteT() {
		return daoCliente.getEmpleados();

	}
	/**
	 * Metodo para obtener un cliente por su numero de cedula
	 * 
	 * 
	 * 
	 * @return daoEmpleado.getEmpleados() Un Empleado registrado en la Base de Datos
	 */
	public Cliente obtenerDatosPorCedula(String cedula) throws Exception {
		System.err.println("INGRESA AL ON PARA BUSCAR ");
		if (cedula == null) {
			System.out.println("Cedula vacia");
		} else {
			try {
				System.err.println("INGRESA AL ON PARA BUSCAR CLIENTE PO");
				Cliente cliente = daoCliente.obtenerDatosPorCedula(cedula);
				System.out.println("CLIENTE" + cliente);
				return cliente;

			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return null;
	}

}
