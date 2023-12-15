package ExamenMusin;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Mensajes {
	private int id;
	private Departamento deEmpleados;
	private int paraempleados;
	private String asunto;
	private Date fechaEnvio;
	private String mensaje;

	public Mensajes() {
	}

	public Mensajes(int id, Departamento deEmpleados, int empleados, String asunto, Date fechaEnvio, String mensaje) {
		this.id = id;
		this.deEmpleados = deEmpleados;
		this.paraempleados = empleados;
		this.asunto = asunto;
		this.fechaEnvio = fechaEnvio;
		this.mensaje = mensaje;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Departamento getDeEmpleados() {
		return deEmpleados;
	}

	public void setDeEmpleados(Departamento deEmpleados) {
		this.deEmpleados = deEmpleados;
	}

	public int getEmpleados() {
		return paraempleados;
	}

	public void setEmpleados(int empleados) {
		this.paraempleados = empleados;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public Date getFechaEnvio() {
		return fechaEnvio;
	}

	public void setFechaEnvio(Date fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	@Override
	public String toString() {
		return "Para [id=" + id + ", deEmpleados=" + deEmpleados + ", empleados=" + paraempleados + ", asunto=" + asunto
				+ ", fechaEnvio=" + new SimpleDateFormat().format(fechaEnvio) + ", mensaje=" + mensaje + "]";
	}
}
