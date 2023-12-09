package vista.scrabble.consolagrafica;

import java.rmi.RemoteException;

import controlador.scrabble.Controlador;
import modelo.scrabble.Jugador;
import vista.scrabble.consolagrafica.FlujoIngresarPalabra.EstadosPosibles;

public class FlujoCambiarFichas extends Flujo{

	private int idJugador;
	
	public FlujoCambiarFichas(ConsolaGrafica vista, Controlador controlador, int idJugador) {
		super(vista, controlador);
		this.idJugador = idJugador;
	}
	
    public void mostarMenuTextual() {
    	vista.mostrarTablero(controlador.obtenerTablero());	
		vista.mostrarEstadoJugador(controlador.obtenerJugadores(idJugador));	
        vista.mostrarMensaje("Ingrese una palabra que contenga las letras que desea cambiar (en cualquier orden): ");
    }

    public Flujo elegirOpcion(String opcion) {
    	Jugador jugadorActual = null;
		jugadorActual = controlador.obtenerJugadores(idJugador);
    	opcion = opcion.toUpperCase();
		char[] cadenaCaracteres = opcion.toCharArray();
		for(char c: cadenaCaracteres) {
			if(jugadorActual != null && !jugadorActual.getAtril().contains(c)) {
				vista.mostrarMensaje("Ingrese una palabra que contenga las letras de su atril.");
				return this;
			}
		}
		controlador.cambiarFichas(idJugador, cadenaCaracteres);
		return new FlujoIngresarPalabra(vista,controlador,controlador.siguienteTurno());
	}
    
}
	
