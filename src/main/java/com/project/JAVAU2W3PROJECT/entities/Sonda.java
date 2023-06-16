package com.project.JAVAU2W3PROJECT.entities;

import com.project.JAVAU2W3PROJECT.exceptions.CommunicationFailureException;
import com.project.JAVAU2W3PROJECT.exceptions.InvalidCoordinatesException;
import com.project.JAVAU2W3PROJECT.interfaces.FireAlarmControl;
import com.project.JAVAU2W3PROJECT.interfaces.ObserverSmokeControl;
import com.project.JAVAU2W3PROJECT.proxies.FireAlarmControlProxy;

import lombok.Data;

@Data
public class Sonda implements ObserverSmokeControl {
	private SmokeDetector smokeDetector;
	private String idSonda;
	private double latitudine;
	private double longitudine;
	private FireAlarmControl fireAlarmControl;

	public Sonda(SmokeDetector smokeDetector, String idSonda, double latitudine, double longitudine)
			throws InvalidCoordinatesException {

		if (latitudine < -90 || latitudine > 90 || longitudine < -180 || longitudine > 180) {
			throw new InvalidCoordinatesException(
					"Coordinate non valide: latitudine = " + latitudine + ", longitudine = " + longitudine);
		}

		this.smokeDetector = smokeDetector;
		this.idSonda = idSonda;
		this.latitudine = latitudine;
		this.longitudine = longitudine;
		this.fireAlarmControl = new FireAlarmControlProxy();
		smokeDetector.registerObserver(this);
	}

	public void update(double smokeLevel) throws CommunicationFailureException {
		System.out.println("Ricevuto aggiornamento dalla " + idSonda + ": livello di fumo = " + smokeLevel);
		fireAlarmControl.triggerAlarm(idSonda, latitudine, longitudine, smokeLevel);

		if (smokeLevel >= 10) {
			System.out.println("Sonda allarmata: " + idSonda
					+ " Livello mai registrato riunire tutte le forze, non è un esercitazione!");
		} else if (smokeLevel >= 5) {
			System.out.println("Sonda allarmata: " + idSonda + " Preparasi all'emergenza!");
		} else if (smokeLevel == 0) {
			System.out.println("Sonda allarmata: Nessun segnale di fumo per ora alla " + idSonda);
		} else if (smokeLevel <= 4) {
			System.out.println("Sonda allarmata: " + idSonda + " Basso segnale di fumo ma rimanere in allerta!");
		}

	}
}
