package com.project.JAVAU2W3PROJECT.exceptions;

//questa eccezione potrebbe essere lanciata quando le coordinate geografiche della sonda non sono valide 
//(ad esempio, se la latitudine o la longitudine non sono comprese nei limiti consentiti)

public class InvalidCoordinatesException extends Exception {
	public InvalidCoordinatesException(String message) {
		super(message);
	}
}
