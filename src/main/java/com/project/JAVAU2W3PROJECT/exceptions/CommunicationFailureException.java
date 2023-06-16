package com.project.JAVAU2W3PROJECT.exceptions;

//questa eccezione potrebbe essere lanciata quando si verifica un errore di comunicazione tra la sonda
//e il centro di controllo (ad esempio, se l’invocazione dell’URL per notificare il personale competente fallisce)

public class CommunicationFailureException extends Exception {
	public CommunicationFailureException(String message) {
		super(message);
	}
}
