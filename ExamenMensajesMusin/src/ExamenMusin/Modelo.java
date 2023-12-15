package ExamenMusin;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

public class Modelo {

	private String us = "root";
	private String ps = "root";
	private String url = "jdbc:mysql://localhost:3306/nombreBaseDatos";
	private Connection conexion = null;

	public Modelo() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection(url, us, ps);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getConexion() {
		return conexion;
	}

	public void setConexion(Connection conexion) {
		this.conexion = conexion;
	}

	public int getEmpleado(int numEmp, String dni) {
		try (CallableStatement call = conexion.prepareCall("{? = call login(?,?)}")) {
			call.setInt(2, numEmp);
			call.setString(3, dni);
			call.registerOutParameter(1, Types.INTEGER);
			call.executeUpdate();
			return call.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 1;
	}

	public ArrayList<Departamento> getDepartamentos() {
		ArrayList<Departamento> deptos = new ArrayList<Departamento>();
		try {

			Statement consulta = conexion.createStatement();
			ResultSet res = consulta.executeQuery("select * from departamento");
			while (res.next()) {
				deptos.add(new Departamento(res.getInt(1), res.getString(2)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return deptos;
	}

	public Departamento getDepartamento(int id) {
		PreparedStatement ps = null;
		try {
			ps = conexion.prepareStatement("select * from departamento where numero =?");
			ps.setInt(1, id);
			ResultSet res = ps.executeQuery();
			if (res.next()) {
				return new Departamento(res.getInt(1), res.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public ArrayList<Empleados> obtenerEmpDepartamentoConcreto(int id) {
		ArrayList<Empleados> emp = new ArrayList();
		try {
			PreparedStatement ps = conexion.prepareStatement("select * from empleado e join "
					+ "departamento d on (d.numero=e.departamento) where e.departamento=?");
			ps.setInt(1, id);
			ResultSet res = ps.executeQuery();
			while (res.next()) {
				emp.add(new Empleados(res.getInt(1), res.getString(2), res.getString(3), res.getString(4),
						res.getDate(5), new Departamento(res.getInt(6), res.getString(7)), res.getString(8)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emp;
	}

	public boolean escribirMensaje(Mensajes mensaje, ArrayList<Empleados> emp) {
		try {
			conexion.setAutoCommit(false);
			PreparedStatement ps = conexion.prepareStatement("Insert into mensaje values (default,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, mensaje.getEmpleados());
			ps.setInt(2, mensaje.getDeEmpleados().getNumero());
			ps.setString(3, mensaje.getAsunto());
			ps.setDate(4, new Date(mensaje.getFechaEnvio().getTime()));
			ps.setString(5, mensaje.getMensaje());
			int fila = ps.executeUpdate();
			ResultSet id = ps.getGeneratedKeys();
			if (id.next()) {
				mensaje.setId(id.getInt(1));
			}
			if (fila == 1) {
				int cont = 0;
				for (Empleados empleados : emp) {
					ps = conexion.prepareStatement("Insert into para values (?,?,?)");
					ps.setInt(1, mensaje.getId());
					ps.setInt(2, empleados.getCodigo());
					ps.setInt(3, 1);
					int fila2 = ps.executeUpdate();
					if (fila2 == 1) {
						cont++;
					}
				}
				if (emp.size() == cont) {
					conexion.commit();
					return true;
				}
			}
		} catch (SQLException e) {
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return false;
	}

	public ArrayList<Mensajes> obtenerMensajes(int id) {
		ArrayList<Mensajes> mensajes = new ArrayList<Mensajes>();
		try {
			PreparedStatement ps = conexion.prepareStatement(
					"select id,numero,nombre,deEmpleado,asunto,fechaEnvio,mensaje,leido from mensaje m  join departamento d on"
							+ "(m.id=d.numero) where id=?");
			ps.setInt(1, id);
			ResultSet res = ps.executeQuery();
			while (res.next()) {
				mensajes.add(new Mensajes(res.getInt(1), new Departamento(res.getInt(2), res.getString(3)),
						res.getInt(4), res.getString(5), res.getDate(6), res.getString(7)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mensajes;
	}

	public ArrayList<Para> obtenerMensajesEmp(int numEmp2) {

		ArrayList<Para> paraList = new ArrayList<Para>();
		try {
			PreparedStatement ps = conexion.prepareStatement("SELECT id,numero,nombre,deEmpleado,asunto,fechaEnvio,"
					+ "m.mensaje,leido FROM mensaje m JOIN "
					+ "departamento p ON m.paraDepartamento=p.numero join para pa on pa.mensaje=m.id where pa.paraEmpleado=?;");
			ps.setInt(1, numEmp2);
			ResultSet res = ps.executeQuery();
			while (res.next()) {
				paraList.add(new Para(new Mensajes(res.getInt(1), new Departamento(res.getInt(2), res.getString(3)),
						res.getInt(4), res.getString(5), res.getDate(6), res.getString(7)), numEmp2, res.getInt(8)));

			}
			ps = conexion.prepareStatement("UPDATE para set leido=0 where paraEmpleado=?");
			ps.setInt(1, numEmp2);
			if (ps.executeUpdate() == 1) {
				System.out.println("Se han leido todos los mensajes");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return paraList;
	}

	public ArrayList<Para> obtenerMensajesEmpEstadisticas(int numEmp) {
		ArrayList<Para> paraList = new ArrayList<Para>();
		try {
			PreparedStatement ps = conexion.prepareStatement(
					"SELECT nombre,COUNT(deEmpleado),MIN(fechaEnvio),MAX(fechaEnvio) FROM mensaje m JOIN departamento p "
							+ "ON m.paraDepartamento=p.numero " + "join para pa on pa.mensaje=m.id where  deEmpleado=? "
							+ "GROUP BY paraDepartamento; ");
			ps.setInt(1, numEmp);
			ResultSet res = ps.executeQuery();
			while (res.next()) {
				paraList.add(new Para(new Mensajes(0, new Departamento(0, res.getString(1)), res.getInt(2),
						res.getString(3), res.getDate(4), ""), numEmp, 0));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return paraList;
	}

	public boolean cambiarContra(String contrasenia, int cod) {
		try {
			PreparedStatement ps = conexion
					.prepareStatement("update empleado set ps=sha2(?,0),cambiarPs=0 where codigo=?");
			ps.setString(1, contrasenia);
			ps.setInt(2, cod);
			if (ps.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}