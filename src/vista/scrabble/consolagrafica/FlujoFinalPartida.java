package vista.scrabble.consolagrafica;

import java.rmi.RemoteException;

import controlador.scrabble.Controlador;

public class FlujoFinalPartida extends Flujo{

	private int idGanador;
	
	public FlujoFinalPartida(ConsolaGrafica vista, Controlador controlador, int idGanador) {
		super(vista, controlador);
		this.idGanador = idGanador;
	}

	public void mostarMenuTextual() {
		vista.mostrarMensaje("El juego ha terminado. ¡Felicidades " + controlador.obtenerJugadores(idGanador).getNombre() + ", sos el ganador!");
		vista.mostrarMensaje("");
		vista.mostrarMensaje("Presiona Intro para continuar.");
	}

	public Flujo elegirOpcion(String opcion) {
		return new FlujoMenuPrincipal(vista,controlador);
	}

}
