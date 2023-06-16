package com.project.JAVAU2W3PROJECT.entities;

import java.util.ArrayList;
import java.util.List;

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
	public void notifyObservers() {
		for (ObserverSmokeControl observer : observers) {
			observer.update(smokeLevel);
		}
	}

	public void setSmokeLevel(double smokeLevel) {
		this.smokeLevel = smokeLevel;
		notifyObservers();
	}
}
