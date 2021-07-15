package ec.ups.edu.Controlador;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.ups.edu.modelo.Transaccion;

@Stateless
public class TransaccionDao {
	
	private Connection con;
	// Atributo de la clase
	@PersistenceContext(name = "SistemasGerencialesESBPersistenceUnit")
	
	private EntityManager em;

	public TransaccionDao() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Metodo que permite insertar una transaccion dentro de la base de datos por
	 * emdio de JPA
	 * 
	 * @param transaccion transaccion que se va a usar los servicios
	 * @return true devuelve verdadero en caso de que el cliente exista.
	 */
	public boolean insertarTransaccion(Transaccion transaccion) {
		System.out.println("entro registro transacc" );
		em.persist(transaccion);
		return true;

	}

	/**
	 * Metodo que permite actualizar una Transferencia Local en la base de datos
	 * 
	 * @param t Transferencia Local que se va a actualizar en la base
	 */
	public void update(Transaccion t) {
		em.merge(t);
	}

	/**
	 * Metodo que permite listar todas las transacciones dentro de la base
	 * 
	 * @return listado si se realiza la actualziacion devuelve la lista con todos
	 *         los datos existentes
	 */
	public List<Transaccion> obtenerTransaccion() {
		// obtener todas las cuentas con fehca de transaccion y saldo
		// select distinct c.*, t.* from Transaccion t join Cuenta c on t.cuenta_trans =
		// '404000000002' and c.numero_cuenta = t.cuenta_trans
		String jpql = "SELECT c FROM Transaccion c";
		Query q = em.createQuery(jpql, Transaccion.class);
		List<Transaccion> listado = q.getResultList();
		return listado;

	}

	/**
	 * Metodoq que me permite consultar entre fechas las transacciones realizadas
	 * por el cliente.
	 * 
	 * @param cedula pertenece al cliente logueado
	 * @param fechI  fecha inicio de la busqeuda
	 * @param fechaF fecha final de la búsqueda
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Transaccion> getListaTransaccionesFechas(String cedula, Date fechaI, Date fechaF) throws Exception {
		System.out.println("INGRESAN ESTO DATOS A LA BUSQUEDA" + cedula + " " + fechaI + " " + fechaF);
		// select * from transaccion where cedula_cliente = 0105011399 AND fecha BETWEEN
		// '2020-06-03 20:21:40.090000' AND '2020-06-03 23:22:39.160000';
		String tl = "select s.tipo_transaccion, s.monto, s.cuenta_trans, c.saldo from transaccion s join cuenta c on numero_cuenta=:cedula AND s.fecha_hora >=:fechaI AND s.fecha_hora <=:fechaF";

		List<Transaccion> ll = em.createNativeQuery(
				"select s.tipo_transaccion, s.monto, s.cuenta_trans, c.saldo from transaccion s join cuenta c on numero_cuenta=:cedula AND s.fecha_hora >=:fechaI AND s.fecha_hora <=:fechaF ",
				Transaccion.class).setParameter("cedula", cedula).setParameter("fechaI", fechaI)
				.setParameter("fechaF", fechaF).getResultList();
		System.out.println("RESULTADO DEL QUERY " + ll);
		return ll;

	}

	@SuppressWarnings("unchecked")
	public List<Transaccion> obtenerTransaccionXEDUL(String cedula) {
		System.out.println("INGRESA A CEDU TRAN" + cedula);
		List<Transaccion> ll = em.createNativeQuery(
				"select * from transaccion where transaccion.cuenta_trans in (select  cuen.numero_cuenta from cuenta cuen join cliente cli on cuen.cuenta_fk = cli.cedula and cli.cedula =:cedula)",
				Transaccion.class).setParameter("cedula", cedula).getResultList();
		System.out.println("RESULTADO DEL QUERY " + ll);
		return ll;
	}
}
