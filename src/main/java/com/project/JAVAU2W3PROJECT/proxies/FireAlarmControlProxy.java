package com.project.JAVAU2W3PROJECT.proxies;

import com.project.JAVAU2W3PROJECT.controllers.FireAlarmControllerImpl;
import com.project.JAVAU2W3PROJECT.interfaces.FireAlarmControl;

public class FireAlarmControlProxy implements FireAlarmControl {
	private FireAlarmControl fireAlarmControl;

	public FireAlarmControlProxy() {
		this.fireAlarmControl = new FireAlarmControllerImpl();
	}

	@Override
	public void triggerAlarm(String idSonda, double latitudine, double longitudine, double smokeLevel) {
		if (smokeLevel > 5) {
			fireAlarmControl.triggerAlarm(idSonda, latitudine, longitudine, smokeLevel);
		}

	}
}
