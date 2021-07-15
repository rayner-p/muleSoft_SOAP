package ec.ups.edu.Controlador;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import ec.ups.edu.utils.*;

import ec.ups.edu.modelo.Cuenta;


@Stateless
public class CuentaDao {

	
	private Connection con;
	// Atributo de la clase
	@PersistenceContext(name = "SistemasGerencialesESBPersistenceUnit")
	
	private EntityManager em;

	// private static EntityTransaction transactionObj = em.getTransaction();

	/**
	 * Metodo que permite registrar una cuenta de ahorro en la base de datos
	 * 
	 * @param c Cuenta que se registrar
	 */
	public void insert(Cuenta c) {
		em.persist(c);
	}

	/**
	 * Metodo que permite actualizar una cuenta de ahorro en la base de datos
	 * 
	 * @param c Cuenta que se actualiza
	 */
	public void update(Cuenta c) {
		em.merge(c);
	}

	/**
	 * Metodo que permite obtener una cuenta de ahorro de la base de datos
	 * 
	 * @param numeroCuentaDeAhorro Numero de la cuenta que se busca
	 * @return una cuenta de ahorro que este registrada en la base
	 */
	public Cuenta read(String cuenta) {
		return em.find(Cuenta.class, cuenta);
	}

	/**
	 * Metodo que permite eliminar una cuenta de ahorro de la base de datos
	 * 
	 * @param numeroCuentaDeAhorro Numero de la cuenta que queremos eliminar
	 */
	public void delete(String numeroCuenta) {
		Cuenta c = read(numeroCuenta);
		em.remove(c);
	}

	/**
	 * Metodo que permite obtener las cuentas de ahorro que estan registrados en la
	 * base de datos
	 * 
	 * @return Lista de cuentas de ahorros que estan registradas en la base de datos
	 */

	@SuppressWarnings("unchecked")
	public List<Cuenta> obtenerCuenta2() {
		String jpql = "SELECT * FROM cuenta  ";
		Query q = em.createNativeQuery(jpql, Cuenta.class);
		System.out.println("retorno" + q.getResultList());
		return q.getResultList();
	}

	/**
	 * Metodo que permite obtener una cuenta de ahorro en base a su codigo
	 * registrado en la base de datos
	 * 
	 * @param cedulaCliente Cedula del cliente que queremos buscar
	 * @return Cuenta de ahorro que tenga un cliente registrado en la base
	 */
	public Cuenta getCuentaCedulaCliente(String cedulaCliente) {
		System.out.println(cedulaCliente);
		try {
			System.out.println("ingresa al dao cuenta get cedula cliente" + cedulaCliente);
			String jpql = "SELECT * FROM cuenta  WHERE cuenta_fk = :cedulaCliente";
			System.out.println("ESTO ES EM" + em);
			Query q = em.createNativeQuery(jpql, Cuenta.class); // estaba cliente.class
			q.setParameter("cedulaCliente", cedulaCliente);
			System.out.println("termina");
			return (Cuenta) q.getSingleResult();

		} catch (NoResultException e) {
			// System.out.println(e.getMessage());
			System.out.println("ERROR AL OBTENER CUENTA DE LA BD");

		}
		return null;
	}

	public Cuenta obtenerCuentaPorNumero(String numerCuenta) {
		System.out.println("num dao " + numerCuenta);
		String jpql = "SELECT c FROM Cuenta c WHERE c.numeroCuenta = :cedulaCliente";
		System.out.println("esto es el em" + " " + em);
		Query q = em.createQuery(jpql, Cuenta.class);
		q.setParameter("cedulaCliente", numerCuenta);
		Cuenta cuentaDeAhorro = (Cuenta) q.getSingleResult();
		return cuentaDeAhorro;
	}

	public String actaulizarCuentaCliente(String numeroCuenta, double valor) {
		System.out.println("Entra al dao" + numeroCuenta + valor);
		Query query = em.createNativeQuery("UPDATE cuenta  SET saldo=:valor WHERE numeroCuenta=:codigo");
		query.setParameter("valor", valor);
		query.setParameter("codigo", numeroCuenta);
		System.out.println("query" + query); // int result =
		query.executeUpdate();
		return "hecho";

		/*
		 * 
		 * 
		 * Query query =
		 * em.createQuery("UPDATE cuenta   SET saldo=:valor WHERE numero_cuenta=:codigo"
		 * ); query.setParameter("valor", valor); query.setParameter("codigo",
		 * numeroCuenta); int updateCount = query.executeUpdate(); if (updateCount > 0)
		 * { System.out.println("Record For Id: " + numeroCuenta + valor +
		 * " Is Updated"); }
		 * 
		 * // transactionObj.commit();
		 * FacesContext.getCurrentInstance().addMessage("editSchoolForm:schoolId", new
		 * FacesMessage("School Record #" + valor + " Is Successfully Updated In Db"));
		 * return "RegistrarTransaccion.xhtml";
		 */
	}

	public Cuenta obtenerSaldoClienteCuenta(String numeroCuenta) throws SQLException {
		String jpql = "SELECT c FROM Cuenta c   WHERE Cuenta.numeroCuenta = :numCuenta";
		Query q = em.createQuery(jpql, Cuenta.class);
		q.setParameter("numCuenta", numeroCuenta);
		return (Cuenta) q.getSingleResult();
	}

}
