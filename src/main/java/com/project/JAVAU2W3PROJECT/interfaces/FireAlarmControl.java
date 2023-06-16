package com.project.JAVAU2W3PROJECT.interfaces;

import com.project.JAVAU2W3PROJECT.exceptions.CommunicationFailureException;

public interface FireAlarmControl {
	void triggerAlarm(String idSonda, double latitudine, double longitudine, double smokeLevel)
			throws CommunicationFailureException;
}
