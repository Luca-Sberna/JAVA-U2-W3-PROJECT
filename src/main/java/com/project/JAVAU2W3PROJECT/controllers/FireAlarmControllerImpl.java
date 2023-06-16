package com.project.JAVAU2W3PROJECT.controllers;

import com.project.JAVAU2W3PROJECT.interfaces.FireAlarmControl;

public class FireAlarmControllerImpl implements FireAlarmControl {

	@Override
	public void triggerAlarm(String idSonda, double latitudine, double longitudine, double smokeLevel) {
		String url = "http://host/alarm?idsonda=" + idSonda + "&lat=" + latitudine + "&lon=" + longitudine
				+ "&smokelevel=" + smokeLevel;
		// invoca l'URL per notificare il personale competente
	}

}
