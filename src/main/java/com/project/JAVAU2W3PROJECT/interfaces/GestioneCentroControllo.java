package com.project.JAVAU2W3PROJECT.interfaces;

import com.project.JAVAU2W3PROJECT.exceptions.CommunicationFailureException;

public interface GestioneCentroControllo {
	void registerObserver(ObserverSmokeControl o);

	void removeObserver(ObserverSmokeControl o);

	void notifyObservers() throws CommunicationFailureException;
}
