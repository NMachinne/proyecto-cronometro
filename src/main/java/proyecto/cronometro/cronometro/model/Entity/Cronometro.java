package proyecto.cronometro.cronometro.model.Entity;

import proyecto.cronometro.cronometro.Interfaces.Icronometro;

public class Cronometro implements Icronometro{
	public int vuelta;
	public int horas;
	public int minutos;
	public int segundos;
	public int miliSegundos;
	public boolean corriendo;

	public Cronometro(int vuelta, int horas, int minutos, int segundos, int miliSegundos) {
		super();
		this.vuelta = vuelta;
		this.horas = horas;
		this.minutos = minutos;
		this.segundos = segundos;
		this.miliSegundos = miliSegundos;
	}

	public Cronometro() {
		this(0, 00, 00, 00, 000);
	}
	
	public Cronometro( int horas, int minutos, int segundos, int miliSegundos) {
		this.horas = horas;
		this.minutos = minutos;
		this.segundos = segundos;
		this.miliSegundos = miliSegundos;
	}

	public int getVuelta() {
		return vuelta;
	}

	public void setVuelta(int vuelta) {
		this.vuelta = vuelta;
	}

	public int getHoras() {
		return horas;
	}

	public void setHoras(int horas) {
		this.horas = horas;
	}

	public int getMinutos() {
		return minutos;
	}

	public void setMinutos(int minutos) {
		this.minutos = minutos;
	}

	public int getSegundos() {
		return segundos;
	}

	public void setSegundos(int segundos) {
		this.segundos = segundos;
	}

	public int getMiliSegundos() {
		return miliSegundos;
	}

	public void setMiliSegundos(int miliSegundos) {
		this.miliSegundos = miliSegundos;
	}

	@Override
	public String toString() {
		return "Cronometro [horas=" + horas + ", minutos=" + minutos + ", segundos=" + segundos + ", miliSegundos="
				+ miliSegundos + ", corriendo=" + corriendo + "]";
	}
	
	
}
