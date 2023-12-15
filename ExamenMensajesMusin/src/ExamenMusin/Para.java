package ExamenMusin;

public class Para {
	private Mensajes mensajes;
	private int menEmpleados;
	private int leido;

	public Para() {
	}

	public Para(Mensajes mensajes, int menEmpleados, int leido) {
		this.mensajes = mensajes;
		this.menEmpleados = menEmpleados;
		this.leido = leido;
	}

	public Mensajes getMensajes() {
		return mensajes;
	}

	public void setMensajes(Mensajes mensajes) {
		this.mensajes = mensajes;
	}

	public int getMenEmpleados() {
		return menEmpleados;
	}

	public void setMenEmpleados(int menEmpleados) {
		this.menEmpleados = menEmpleados;
	}

	public int getLeido() {
		return leido;
	}

	public void setLeido(int leido) {
		this.leido = leido;
	}

	@Override
	public String toString() {
		return "Para [mensajes=" + mensajes.toString() + ", menEmpleados=" + menEmpleados + ", leido=" + leido + "]";
	}

}
