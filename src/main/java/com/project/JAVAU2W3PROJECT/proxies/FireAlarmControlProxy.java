package com.project.JAVAU2W3PROJECT.proxies;

import com.project.JAVAU2W3PROJECT.controllers.FireAlarmControllerImpl;
import com.project.JAVAU2W3PROJECT.exceptions.CommunicationFailureException;
import com.project.JAVAU2W3PROJECT.interfaces.FireAlarmControl;

public class FireAlarmControlProxy implements FireAlarmControl {
	private FireAlarmControl fac;

	public FireAlarmControlProxy() {
		this.fac = new FireAlarmControllerImpl();
	}

	@Override
	public void triggerAlarm(String idSonda, double latitudine, double longitudine, double smokeLevel)
			throws CommunicationFailureException {
		if (smokeLevel > 5) {
			try {
				fac.triggerAlarm(idSonda, latitudine, longitudine, smokeLevel);
			} catch (Exception e) {
				throw new CommunicationFailureException("Errore di comunicazione: " + e.getMessage());
			}
		}

	}
}
