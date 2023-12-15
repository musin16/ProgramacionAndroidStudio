package ExamenMusin;

public class Departamento {
	private int numero;
	private String nombre;

	public Departamento() {

	}

	public Departamento(int numero, String nombre) {
		this.numero = numero;
		this.nombre = nombre;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Departamento [numero=" + numero + ", nombre=" + nombre + "]";
	}

}
