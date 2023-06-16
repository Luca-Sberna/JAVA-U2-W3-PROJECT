package com.project.JAVAU2W3PROJECT.exceptions;

//questa eccezione potrebbe essere lanciata quando si verifica un errore nella sonda 
//(ad esempio, se la sonda non riesce a rilevare correttamente il livello di fumo)

public class SensorFailureException extends Exception {
	public SensorFailureException(String message) {
		super(message);
	}
}
