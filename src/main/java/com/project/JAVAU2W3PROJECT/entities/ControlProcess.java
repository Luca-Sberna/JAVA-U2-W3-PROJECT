package com.project.JAVAU2W3PROJECT.entities;

import com.project.JAVAU2W3PROJECT.interfaces.FireAlarmControl;
import com.project.JAVAU2W3PROJECT.interfaces.ObserverSmokeControl;
import com.project.JAVAU2W3PROJECT.proxies.FireAlarmControlProxy;

import lombok.Data;

@Data
public class ControlProcess implements ObserverSmokeControl {
	private SmokeDetector smokeDetector;
	private String idSonda;
	private double latitudine;
	private double longitudine;
	private FireAlarmControl fireAlarmControl;

	public ControlProcess(SmokeDetector smokeDetector, String idSonda, double latitudine, double longitudine) {
		this.smokeDetector = smokeDetector;
		this.idSonda = idSonda;
		this.latitudine = latitudine;
		this.longitudine = longitudine;
		this.fireAlarmControl = new FireAlarmControlProxy();
		smokeDetector.registerObserver(this);
	}

	public void update(double smokeLevel) {
		System.out.println("Ricevuto aggiornamento dalla " + idSonda + ": livello di fumo = " + smokeLevel);
		fireAlarmControl.triggerAlarm(idSonda, latitudine, longitudine, smokeLevel);
		if (smokeLevel >= 10) {
			System.out.println("Sonda allarmata: " + idSonda
					+ " Livello mai registrato riunire tutte le forze, non è un esercitazione!");
		} else if (smokeLevel > 5) {
			System.out.println("Sonda allarmata: " + idSonda + " Preparasi all'emergenza!");
		} else if (smokeLevel <= 4) {
			System.out.println("Sonda allarmata: " + idSonda + " Rimanere in allerta!");
		}
	}

}
