package vista.scrabble.consolagrafica;

import java.io.IOException;
import java.util.ArrayList;

import controlador.scrabble.Controlador;
import modelo.scrabble.Partida;

public class FlujoElegirPartida extends Flujo{

	public FlujoElegirPartida(ConsolaGrafica vista, Controlador controlador) {
		super(vista, controlador);
	}

	public void mostarMenuTextual() {
		vista.mostrarPartidasGuardadas();
		vista.mostrarMensaje("Seleccione una partida: ");
	}

	public Flujo elegirOpcion(String opcion) {
		int idPartida = Integer.parseInt(opcion);
		try {
			controlador.cargarPartida(idPartida);
			return new FlujoIngresarPalabra(vista,controlador,controlador.obtenerTurnoActual());
		} catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			return this;
		}
	}

}
