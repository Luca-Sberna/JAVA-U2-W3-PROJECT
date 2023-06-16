package com.project.JAVAU2W3PROJECT.entities;

import java.util.ArrayList;
import java.util.List;

import com.project.JAVAU2W3PROJECT.exceptions.CommunicationFailureException;
import com.project.JAVAU2W3PROJECT.exceptions.SensorFailureException;
import com.project.JAVAU2W3PROJECT.interfaces.GestioneCentroAssistenza;
import com.project.JAVAU2W3PROJECT.interfaces.ObserverSmokeControl;

import lombok.Data;

@Data
public class SmokeDetector implements GestioneCentroAssistenza {
	private List<ObserverSmokeControl> observers;
	private double smokeLevel;

	public SmokeDetector() {
		this.observers = new ArrayList<>();
		this.smokeLevel = 0;
	}

	@Override
	public void registerObserver(ObserverSmokeControl o) {
		observers.add(o);
	}

	@Override
	public void removeObserver(ObserverSmokeControl o) {
		observers.remove(o);
	}

	@Override
	public void notifyObservers() throws CommunicationFailureException {
		for (ObserverSmokeControl observer : observers) {
			observer.update(smokeLevel);
		}
	}

	public void setSmokeLevel(Sonda sonda, double smokeLevel)
			throws SensorFailureException, CommunicationFailureException {
		if (smokeLevel < 0) {
			throw new SensorFailureException("Livello di fumo non valido controllare sensore: " + smokeLevel);
		}
		this.smokeLevel = smokeLevel;
		notifyObservers();
	}
}
