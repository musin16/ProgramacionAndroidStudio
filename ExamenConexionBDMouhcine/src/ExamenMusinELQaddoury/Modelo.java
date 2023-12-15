package ExamenMusinELQaddoury;

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
	private String url = "jdbc:mysql://localhost:3306/nombreBD";
	private Connection conexion = null;
//select * from tabla t join tabla2 t2 where id=?
	public Modelo() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection(url, us, ps);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Object> obtenerTodos() {
		ArrayList<Object> lista = new ArrayList<Object>();
		try {
			Statement st = conexion.createStatement();
			ResultSet rs = st.executeQuery("select * from nombreTabla");
			while (rs.next()) {
				lista.add(rs.getObject(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	public ArrayList<Object> obtenerTodos2() {
		ArrayList<Object> lista = new ArrayList<Object>();
		try {
			Statement st = conexion.createStatement();
			ResultSet rs = st.executeQuery("select * from nombreTabla");
			while (rs.next()) {
				lista.add(rs.getObject(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	public Object obtenerPersona(int id) {
		try {
			PreparedStatement consulta = conexion.prepareStatement("select * from nombreTabla where id = ?");
			consulta.setInt(1, id);
			ResultSet rs = consulta.executeQuery();
			if (rs.next()) {
				return new Object();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int obtenerFuncion(Object obj) {
		try {
			CallableStatement consulta = conexion.prepareCall("{?=call insertarPersona(?,?)}");
			consulta.setString(2, "");
			consulta.setString(3, "");
			consulta.registerOutParameter(1, Types.INTEGER);
			consulta.executeUpdate();
			return consulta.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public boolean insertarSinCall() {
		try {
			PreparedStatement pr = conexion.prepareStatement("Insert into tabla values(default,?,?,?)");
			pr.setString(1, "");
			pr.setString(2, "");
			pr.setDate(3, new Date(0));
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean transaccion() {
		try {
			conexion.setAutoCommit(false);
			PreparedStatement pr = conexion.prepareStatement("Insert into tabla values(default,?,?,?)");
			pr.setString(1, "");
			pr.setString(2, "");
			pr.setDate(3, new Date(0));
			if (pr.executeUpdate() == 1) {
				conexion.commit();
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void insertarPersona(Object obj) {
		try {
			CallableStatement consulta = conexion.prepareCall("{call insertarPersona(?,?)}");
			consulta.setString(1, "");
			consulta.setString(2, "");
			consulta.setDate(3, new Date(0));
			consulta.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void actualizarPersona(int id, String nombre, String apellido, Date fechaNacimiento) {
		try {
			CallableStatement consulta = conexion.prepareCall("{call actualizarPersona(?,?,?)}");
			consulta.setInt(1, id);
			consulta.setString(2, nombre);
			consulta.setString(3, apellido);
			consulta.setDate(4, fechaNacimiento);
			consulta.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean borrarPersona(int id, String nombre) {
		try {
			PreparedStatement pr = conexion.prepareStatement("Delete from tabla where id=?");
			pr.setInt(1, id);
			if (pr.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void borrarPersona(int id) {
		try {
			CallableStatement consulta = conexion.prepareCall("{call borrarPersona(?)}");
			consulta.setInt(1, id);
			consulta.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getConexion() {
		return conexion;
	}

	public void setConexion(Connection conexion) {
		this.conexion = conexion;
	}

	public void cerrar() {
		try {
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Object> obtenerTodos(int id) {
		ArrayList<Object> lista = new ArrayList<Object>();
		try {
			PreparedStatement consulta = conexion.prepareStatement("select * from nombreTabla  where id = ?");
			consulta.setInt(1, id);
			ResultSet rs = consulta.executeQuery();
			while (rs.next()) {
				lista.add(new Object());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

}
