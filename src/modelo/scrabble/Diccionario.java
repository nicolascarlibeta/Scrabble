package modelo.scrabble;

import java.util.*;
import java.io.*;

public class Diccionario implements Serializable {

	private File palabras;
	
    //INTERFAZ
	
	private void porIndice(int primeraLetra) {
		
		if(primeraLetra >= 97 && primeraLetra <= 102) {
    		palabras = new File("A-F.txt");
    	}
    	else if(primeraLetra >= 103 && primeraLetra <= 109) {
    		palabras = new File("G-M.txt");
    	}
    	else if(primeraLetra >= 110 && primeraLetra <= 115) {
    		palabras = new File("N-S.txt");
    	}
    	else {
    		palabras = new File("T-Z.txt");
    	}
	}
    
    public boolean contieneA(String palabraBuscada) throws IOException{
    	
    	//Tomo la primera letra de la palabra a buscar
    	int primeraLetra = (int) palabraBuscada.toCharArray()[0];
    	
    	//La construyo por el indice
    	porIndice(primeraLetra);
  
    	//Busco la palabra
    	boolean esta = false;
    	BufferedReader diccionario = new BufferedReader(new FileReader(palabras));
    	String linea = diccionario.readLine();
    	if(linea.equals(palabraBuscada)) {
			esta = true;
		}
    	else {
    		while(linea != null && !esta) {
        		if(linea.equals(palabraBuscada)) {
        			esta = true;
        		}
        		linea = diccionario.readLine();
        	}
    	}
    	diccionario.close();
    	return esta;
    }

   
	
}
