package ExamenMusin;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Empleados {
	private int codigo;
	private String dni;
	private String ps;
	private String nombre;
	private Date date;
	private Departamento deEmpleados;
	private String cambiarPs;

	public Empleados() {

	}

	public Empleados(int codigo, String dni, String ps, String nombre, Date date, Departamento deEmpleados,
			String cambiarPs) {
		this.codigo = codigo;
		this.dni = dni;
		this.ps = ps;
		this.nombre = nombre;
		this.date = date;
		this.deEmpleados = deEmpleados;
		this.cambiarPs = cambiarPs;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getPs() {
		return ps;
	}

	public void setPs(String ps) {
		this.ps = ps;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Departamento getDeEmpleados() {
		return deEmpleados;
	}

	public void setDeEmpleados(Departamento deEmpleados) {
		this.deEmpleados = deEmpleados;
	}

	public String getCambiarPs() {
		return cambiarPs;
	}

	public void setCambiarPs(String cambiarPs) {
		this.cambiarPs = cambiarPs;
	}

	@Override
	public String toString() {
		return "Empleados [codigo=" + codigo + ", Dni:" + dni + ", ps=" + ps + ", nombre=" + nombre + ", Fecha="
				+ new SimpleDateFormat().format(date) + ", Nombre departamenteo:" + deEmpleados.getNombre()
				+ ", cambiarPs=" + cambiarPs + "]";
	}

}
