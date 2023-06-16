package com.project.JAVAU2W3PROJECT.interfaces;

public interface GestioneCentroAssistenza {
	void registerObserver(ObserverSmokeControl o);

	void removeObserver(ObserverSmokeControl o);

	void notifyObservers();
}
