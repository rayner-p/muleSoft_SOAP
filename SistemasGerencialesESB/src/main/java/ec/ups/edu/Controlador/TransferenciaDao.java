package ec.ups.edu.Controlador;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import ec.ups.edu.modelo.Cuenta;
import ec.ups.edu.modelo.Transferencia;

@Stateless
public class TransferenciaDao {
	
	private Connection con;
	// Atributo de la clase
	@PersistenceContext(name = "SistemasGerencialesESBPersistenceUnit")
	
	private EntityManager em;
	
	public TransferenciaDao() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Metodo que permite crear una transferencia en la base de datos
	 * 
	 * @param transfe TranferenciaLocal que se actualiza en la base
	 */
	public void insert(Transferencia transfe) {
		System.out.println("ingreso dao trsn" +" "+transfe);
		em.persist(transfe);
	}

	/**
	 * Metodo que permite actualizar una transferencia en la base de datos
	 * 
	 * @param tra TranferenciaLocal que se actualiza en la base
	 */
	public void update(Transferencia tra) {
		em.merge(tra);
	}

	/**
	 * Metodo que permite obtener una Transferencia Local de la base de datos
	 * 
	 * @param codigoTra Codigo que se utilizara para obtener la Transferencia Local
	 * @return Una Transferencia Local que se encuentre registrado en la base
	 */
	public Transferencia read(int codigoTra) {
		return em.find(Transferencia.class, codigoTra);
	}

	/**
	 * Metodo que permite eliminar una Transferencia Local de la base de datos
	 * 
	 * @param codigoTra Codigo que se utiliza para poder eliminar la Transferencia
	 *                  Local
	 */
	public void delete(int codigoTra) {
		Transferencia c = read(codigoTra);
		em.remove(c);
	}

	/**
	 * Metodo que permite obtener las Transferencias Locales que estan registradas
	 * en la base de datos
	 * 
	 * @return Lista de Transferencias Locales que estan registradas en la base de
	 *         datos
	 * @throws SQLException
	 */
	public List<Cuenta> getTransfereciaLocals(String cuentaOrigen) throws SQLException {
		@SuppressWarnings("unchecked")
		List<Cuenta> resultadoCuentas = em.createNativeQuery(
				"SELECT cuenta.numeroCuenta, cuenta.fechaApertura, cuenta.saldo, cuenta.tipoCuenta,cuenta.entidadBancaria, cuenta.cuenta_fk"
						+ " from (select distinct cuenta_destino, cuenta.* " + "from transferencia "
						+ "join cuenta on cuenta.numeroCuenta = transferencia.cuenta_destino where cuentaOrigen=:cuentaOrigen) as cuenta",
				Cuenta.class).setParameter("cuentaOrigen", cuentaOrigen).getResultList();
		System.out.println("RESULTADO DEL QUERY "+ resultadoCuentas);
		return resultadoCuentas;
		/*
		 * String sql =
		 * "select cuenta.numero_cuenta, cuenta.fecha_apertura, cuenta.saldo, cuenta.tipo_cuenta, cuenta.cuenta_fk from (select distinct cuenta_destino, cuenta.* from transferencia_local join cuenta on cuenta.numero_cuenta = transferencia_local.cuenta_destino where cuenta_origen= "
		 * +"'" + cuentaOrigen +"')as cuenta";
		 * 
		 * + "cuenta.numero_cuenta," + " cuenta.fecha_apertura," + " cuenta.saldo, " +
		 * "cuenta.tipo_cuenta, " + "cuenta.cuenta_f " + "FROM (select distinct " +
		 * "cuenta_destino," + " cuenta.* " + "FROM transferencia_local" +
		 * "JOIN cuenta  ON cuenta.numero_cuenta  = transferencia_local.cuenta_destino"
		 * + "where" + "cuenta_origen like :cuentaOrigen )" + "as cuenta")
		 * 
		 * .addEntity("phone", Phone.class ) .setParameter("cuentaOrigen",cuentaOrigen )
		 * .addJoin( "pr", "phone.person") .list();
		 * 
		 * 
		 * 
		 * PreparedStatement ps = con.prepareStatement(sql); ResultSet resultado =
		 * ps.executeQuery();
		 * 
		 * @SuppressWarnings("unchecked") List<String> resultadoTransferencua =
		 * (List<String>) resultado; ps.executeUpdate(); ps.close(); return
		 * resultadoTransferencua;
		 */
		// String jpql = "SELECT distinct t.cuenta_destino FROM TransfereciaLocal t
		// where t.cuenta_origen= :cuentaOrigens";
		// Query q = em.createQuery(jpql, TranferenciaLocal.class);
		// q.setParameter("cuentaCliente", cuentaOrigen);
		// List<String> resultadoTransferencua = (List<String>) q.getSingleResult();
		// return resultadoTransferencua;
	}

	/**
	 * Metodo que permite obtener las Transferencias Locales que estan registradas
	 * en la base de datos
	 * 
	 * @return Lista de Transferencias Locales que estan registradas en la base de
	 *         datos
	 */

	public Transferencia obtenerClienteCuenta(String numeroCuenta) {
		String jpql = "SELECT t, c from Cliente join Cuenta on cedula=:cuentaCliente and c.cuenta_fk = t.cedula";
		Query q = em.createQuery(jpql, Transferencia.class);
		q.setParameter("cuentaCliente", numeroCuenta);
		Transferencia cuentaDeAhorro = (Transferencia) q.getSingleResult();
		return cuentaDeAhorro;
	}
}
