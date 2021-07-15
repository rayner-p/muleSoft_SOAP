package ec.ups.edu.Controlador;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Connection;
import ec.ups.edu.modelo.Cliente;

@Stateless
public class ClienteDao {
	

	private Connection con;
	// Atributo de la clase
	@PersistenceContext(name = "SistemasGerencialesESBPersistenceUnit")
	
	private EntityManager em;

	/**
	 * Constructor que permite inicializar la clase
	 * 
	 */
	public ClienteDao() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Metodo que permite insertar un cliente dentro de la base de datos por emdio
	 * de JPA
	 * 
	 * @param cliente Cliente que se va a usar los servicios
	 * @return true devuelve verdadero en caso de que el cliente exista.
	 */
	public boolean insert(Cliente cliente) throws SQLException {
		em.persist(cliente);
		return true;
	}

	/**
	 * Metodo que permite buscar un cliente a traves de la base por medio de JPA
	 * 
	 * @param cedula varianle que se ingresa para la consulta
	 * @return empleado Cliente cuya cedula es igual a la que se ingresa
	 */
	public Cliente read(String cedula) {
		Cliente empleado = new Cliente();
		empleado = em.find(Cliente.class, cedula);
		System.out.println(empleado);
		return empleado;
	}

	/**
	 * Metodo que permite obtener una lista de clientes a tra ves de JPA 2
	 * 
	 * @return listado lista de todos los clientes que tiene la base;
	 */
	public List<Cliente> getEmpleados() {

		String jpql = "SELECT c FROM Cliente c";

		Query q = em.createQuery(jpql, Cliente.class);
		List<Cliente> listado = q.getResultList();
		System.out.println("ESTO ES DEL DAO CLIENTE LISTAR" + listado);
		return listado;
	}

	/**
	 * Metodo que permite actualizar clientes
	 * 
	 * @param cliente Cliente cuyos datos se quiere actualizr
	 * @return true si se realiza la actualziacion devuelve cerdadero
	 */
	public boolean update(Cliente cliente) {
		em.merge(cliente);
		return true;
	}

	/**
	 * Metodo que permite eliminar un cliente
	 * 
	 * @param cedula Variable que se ingresa y sirve para comparar con una existente
	 * @return true Devuelve verdadero la cedula si esta es igual a la que está en
	 *         la base
	 */
	public boolean delete(String cedula) {
		Cliente cliente = this.read(cedula);
		em.remove(cliente);
		return true;
	}

	public Cliente obtenerDatosPorCedula(String cedula) throws Exception {
		try {
			String jpl = "select c from Cliente c Where c.cedula =:contr";
			Query q = em.createQuery(jpl, Cliente.class);
			q.setParameter("contr", cedula);
			return (Cliente) q.getSingleResult();

		} catch (NoResultException e) {
			// System.out.println(e.getMessage());
			throw new Exception("Revisar numero de cedula");
		}
	}

}
