package com.project.JAVAU2W3PROJECT.interfaces;

import java.util.Observer;

public interface GestioneCentroAssistenza {
	void registerObserver(Observer o);

	void removeObserver(Observer o);

	void notifyObservers();
}
