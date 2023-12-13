package vista.scrabble.consolagrafica;

import java.io.IOException;
import java.rmi.RemoteException;

import controlador.scrabble.Controlador;
import modelo.scrabble.Diccionario;
import modelo.scrabble.Ficha;
import modelo.scrabble.PremioLetra;
import modelo.scrabble.Jugador;

public class FlujoIngresarPalabra extends Flujo{
	
	public FlujoIngresarPalabra(ConsolaGrafica vista, Controlador controlador) {
		super(vista, controlador);
	}
	
	private String cadenaString = "";
	private int x;
	private int y;
	private boolean horizontal;
	private int idJugador = controlador.obtenerTurnoActual();
	private EstadosPosibles estadoActual = EstadosPosibles.INGRESANDO_PALABRA;
	
	public enum EstadosPosibles{
		INGRESANDO_PALABRA,
		INGRESANDO_COORDENADA_X,
		INGRESANDO_COORDENADA_Y,
		INGRESANDO_DISPOSICION
	}

	public void mostarMenuTextual() {
		vista.mostrarTablero(controlador.obtenerTablero());	
		vista.mostrarEstadoJugador(controlador.obtenerJugadores(idJugador));	
		switch(estadoActual) {
		case INGRESANDO_PALABRA -> vista.mostrarMensaje("Ingrese una palabra:");
		case INGRESANDO_COORDENADA_X -> vista.mostrarMensaje("Ingrese la coordenada HORIZONTAL (A, B, C, D, E, F, ...):");
		case INGRESANDO_COORDENADA_Y -> vista.mostrarMensaje("Ingrese la coordenada VERTICAL (A, B, C, D, E, F, ...): ");
		case INGRESANDO_DISPOSICION -> {
			vista.mostrarMensaje("¿De que forma quiere agregar esta palabra?:");
			vista.mostrarMensaje("1. Horizontal\t2. Vertical:");
			}
		}
	}

	public Flujo elegirOpcion(String opcion) {
		
		try {
			if(controlador.obtenerJugadores(idJugador).getAtril().isEmpty()
					&& controlador.bolsaEstaVacia()) {
				int idGanador = controlador.obtenerGanador();
				return new FlujoFinalPartida(vista,controlador,idGanador);
			}
		} catch (RemoteException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		
		switch(estadoActual) {
			case INGRESANDO_PALABRA -> {
				try {
					return ingresarPalabra(opcion);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			case INGRESANDO_COORDENADA_X -> {
				return ingresarX(opcion);
			}
			case INGRESANDO_COORDENADA_Y -> {
				ingresarY(opcion);
			}
			case INGRESANDO_DISPOSICION ->{
				return ingresarDisposicion(opcion);
			}
	}
			return this;
	}
	
	//INTERFAZ
	
	
	public Flujo ingresarPalabra(String cadenaString) throws IOException {
		Jugador jugadorActual = controlador.obtenerJugadores(idJugador);
		this.cadenaString = cadenaString.toUpperCase();
		char[] cadenaCaracteres = this.cadenaString.toCharArray();
		//Primero, valido que la palabra contenga letras del atril
		for(char c: cadenaCaracteres) {
			if(!jugadorActual.getAtril().contains(c)) {
				vista.mostrarMensaje("Ingrese una palabra que contenga las letras de su atril.");
				return this;
			}
		}
		//Segundo, valido la palabra en el diccionario
		if(!new Diccionario().contieneA(cadenaString.toLowerCase())) {
			vista.mostrarMensaje("La palabra ingresada no es valida, intente con otra.");
			return this;
		}
		if(controlador.esPrimerMovimiento()) {
			this.x = 72;
			this.y = 72;
			estadoActual = EstadosPosibles.INGRESANDO_DISPOSICION;
		}
		else {
			estadoActual = EstadosPosibles.INGRESANDO_COORDENADA_X;			
		}
		return this;
	}
	
	public Flujo ingresarX(String x) {
		x = x.toUpperCase();
		this.x = (int) x.toCharArray()[0];
		if(this.x < 65 || this.x > 79) {
			vista.mostrarMensaje("Ingrese una letra coordenada entre A y O.");
			return this;
		}
		estadoActual = EstadosPosibles.INGRESANDO_COORDENADA_Y;
		return this;
	}
	
	public Flujo ingresarY(String y) {
		y = y.toUpperCase();
		this.y = (int) y.toCharArray()[0];
		if(this.y < 65 || this.y > 79) {
			vista.mostrarMensaje("Ingrese una letra coordenada entre A y O.");
			return this;
		}
		estadoActual = EstadosPosibles.INGRESANDO_DISPOSICION;
		return this;
	}
	
	public Flujo ingresarDisposicion(String opcion) {
		switch(opcion) {
		case "1" -> horizontal = true;
		case "2" -> horizontal = false;
		default -> {
			vista.mostrarMensaje("Ingrese un número valido entre 1 y 2.");
			return this;}
		}
		controlador.agregarPalabra(idJugador,x,y,cadenaString,horizontal);
		return new FlujoOpcionesJuego(vista,controlador);
	}
	
	
	

}
