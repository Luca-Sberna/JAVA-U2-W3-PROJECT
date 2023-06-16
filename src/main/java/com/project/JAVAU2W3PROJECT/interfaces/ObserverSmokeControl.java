package com.project.JAVAU2W3PROJECT.interfaces;

import com.project.JAVAU2W3PROJECT.exceptions.CommunicationFailureException;

public interface ObserverSmokeControl {
	void update(double smokeLevel) throws CommunicationFailureException;

}
