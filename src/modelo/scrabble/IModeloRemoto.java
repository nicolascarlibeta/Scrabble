package modelo.scrabble;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

import ar.edu.unlu.rmimvc.observer.IObservableRemoto;

public interface IModeloRemoto extends IObservableRemoto, Serializable{
	
	void addJugador(Jugador jugador) throws RemoteException;
	
	Jugador desconectarJugador(Jugador jugador) throws RemoteException;

	void comenzarPartida() throws RemoteException;

	boolean cambiarFichas(char[] fichasACambiar) throws RemoteException;

	boolean agregarPalabra(String x, String y, Palabra palabraActual, String disposicion) throws RemoteException, IOException;

	void siguienteTurno() throws RemoteException;

	void guardarPartida() throws IOException, ClassNotFoundException, RemoteException;

	int obtenerGanador() throws RemoteException;

	boolean isPrimerMovimiento() throws RemoteException;
	
	void pasarTurno() throws RemoteException;

	Casillero[][] getTablero() throws RemoteException;

	BolsaFichas getBolsaDeFichas() throws RemoteException;

	ArrayList<Jugador> getJugadores() throws RemoteException;

	boolean isVacia() throws RemoteException;

	int getCantidadFichas() throws RemoteException;

	int getTurnoActual() throws RemoteException;
	
	void notificarObservadores(Object obj) throws RemoteException;
	
	ArrayList<Partida> getListaPartidas() throws IOException, ClassNotFoundException, RemoteException;
	
	void cargarPartida(int idPartida) throws IOException, ClassNotFoundException, RemoteException;

	ArrayList<Jugador> getTop5Jugadores() throws RemoteException, ClassNotFoundException, IOException;

	boolean validarPalabra(int x, int y, Palabra palabraActual, boolean horizontal) throws RemoteException;
	
	int getCantidadJugadores() throws RemoteException;
	


}