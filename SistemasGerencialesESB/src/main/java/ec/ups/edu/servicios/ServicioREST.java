package ec.ups.edu.servicios;

import java.util.Date;
import java.util.List;

import javax.ws.rs.Produces;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import ec.ups.edu.modelo.Cliente;
import ec.ups.edu.modelo.Cuenta;
import ec.ups.edu.modelo.Transaccion;
import ec.ups.edu.negocio.ClienteON;
import ec.ups.edu.negocio.CuentaON;
import ec.ups.edu.negocio.TransaccionON;

@Path("transacciones")
public class ServicioREST {

	@Inject
	private ClienteON onCliente;
	@Inject
	private CuentaON onCuenta;
	@Inject
	private TransaccionON onTransaccion;

	private Transaccion tran;
	private Cliente cli;
	private Cuenta c;

	private double saldoNuevo;
	private Date fechaA;

	public ServicioREST() {
		// TODO Auto-generated constructor stub
	}

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	@Path("/crearCliente")
	public String crearCliente(@QueryParam("cedula") String cedula, @QueryParam("nombre") String nombre,
			@QueryParam("apellido") String apellido, @QueryParam("correo") String correo,
			@QueryParam("celular") String celular) throws Exception {
		Cliente c = new Cliente();
		c.setCedula(cedula);
		c.setNombre(nombre);
		c.setApellido(apellido);
		c.setCorreo(correo);
		c.setCelular(celular);
		onCliente.registarCliente(c);
		return "Cliente registrado con éxito";

	}

	/**
	 * Metodo a enviar para registrar las restricciones
	 * 
	 * @param transac
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/registroTransaccion/{monto}/{transaccion}/{cedulaCliente}/{cuenta}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String realizarTransaccion(@PathParam("monto") double monto,
			@PathParam("transaccion") String tipoTransaccion, @PathParam("cedulaCliente") String cedula,
			@PathParam("cuenta") String cuenta) throws Exception {
		tran = new Transaccion();

		try {
			fechaA = new Date();
			System.out.println("fecha Actual" + fechaA);
			tran.setFechaHora(fechaA);
			tran.setMonto(monto);
			tran.setTipoTransaccion(tipoTransaccion);
			c = new Cuenta();
			c = onCuenta.obtenerCuentaPorNumero(cuenta);
			tran.setCuenta(c);
			System.out.println("que trae la cuenta" + c);
			tran.setTransaccion_fk(cedula);
			onTransaccion.insertarTransaccion(tran);

			System.out.println("transaccion creada" + " " + tran);
		} catch (Exception e) {
			throw new Exception("Se ah producido un error" + e.getMessage());

		}
		return "Transaccion registrada con éxito";
	}

	@GET
	@Path("/listaTransaccion")
	@Produces(MediaType.APPLICATION_JSON)

	@Consumes(MediaType.APPLICATION_JSON)
	public List<Transaccion> obtenerTransacciones() throws Exception {
		try {
			return onTransaccion.obtenerTransaccion();
		} catch (Exception e) {
			throw new Exception("Se ah producido un error" + e.getMessage());

		}
	}

	@GET
	@Path("/cliente/{cedula}")
	@Produces(MediaType.APPLICATION_JSON)

	@Consumes(MediaType.APPLICATION_JSON)
	public Cliente c(@PathParam("cedula") String cedula) throws Exception {
		Cliente cc = new Cliente();
		try {

			cc = onCliente.buscarCliente(cedula);
			System.out.println(cc.toString());

		} catch (Exception e) {
			throw new Exception("Se ah producido un error" + e.getMessage());

		}
		return cc;
	}

	@GET
	@Path("/cuenta/{cedula}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Cuenta obtenerCuenta(@PathParam("cedula") String cedula) throws Exception {
		try {
			return onCuenta.getCuentaCedulaCliente(cedula);
		} catch (Exception e) {
			throw new Exception("Se ah producido un error" + e.getMessage());

		}
	}

	@GET
	@Path("/clienteCuenta/{numeroCuenta}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Cuenta obtenerCuentaCliente(@PathParam("numeroCuenta") String cedula) throws Exception {
		try {
			return onCuenta.obtenerCuentaPorNumero(cedula);
		} catch (Exception e) {
			throw new Exception("Se ah producido un error" + e.getMessage());

		}
	}

	@PUT
	@Path("/actualizarCuenta")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String actualziarC(@QueryParam("cuenta") String cuenta, @QueryParam("saldo") double saldo) throws Exception {
		try {
			return onCuenta.actaulizarCuentaCliente(cuenta, saldo);
		} catch (Exception e) {
			throw new Exception("Se ah producido un error" + e.getMessage());

		}
	}

}
