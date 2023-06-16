package com.project.JAVAU2W3PROJECT;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.project.JAVAU2W3PROJECT.entities.SmokeDetector;
import com.project.JAVAU2W3PROJECT.entities.Sonda;
import com.project.JAVAU2W3PROJECT.exceptions.CommunicationFailureException;
import com.project.JAVAU2W3PROJECT.exceptions.InvalidCoordinatesException;
import com.project.JAVAU2W3PROJECT.exceptions.SensorFailureException;
import com.project.JAVAU2W3PROJECT.factory.SmokeDetectorFactory;

@SpringBootApplication
public class JavaU2W3ProjectApplication {

	public static void main(String[] args) throws SensorFailureException, CommunicationFailureException {
		SpringApplication.run(JavaU2W3ProjectApplication.class, args);

		SmokeDetector smokeDetector1 = SmokeDetectorFactory.createSmokeDetector();
		SmokeDetector smokeDetector2 = SmokeDetectorFactory.createSmokeDetector();

		try {
			Sonda sonda_1 = new Sonda(smokeDetector1, "sonda_1", 45.4642, 9.1900);
			Sonda sonda_2 = new Sonda(smokeDetector2, "sonda_2", 40.4642, 90.1900);
		} catch (InvalidCoordinatesException e) {
			e.printStackTrace();
		}

		System.out.println("");

		System.out.println("Settaggio del livello di fumo a 3");
		smokeDetector1.setSmokeLevel(3);

		System.out.println("");

		System.out.println("Settaggio del livello di fumo a 6");
		smokeDetector1.setSmokeLevel(6);

		System.out.println("");

		System.out.println("Settaggio del livello di fumo a 10");
		smokeDetector1.setSmokeLevel(10);

		System.out.println("");

		System.out.println("Settaggio del livello di fumo a 0");
		smokeDetector1.setSmokeLevel(0);

		System.out.println("");
		System.out.println("");

		System.out.println("Settaggio del livello di fumo a 1");
		smokeDetector2.setSmokeLevel(1);

		System.out.println("");

		System.out.println("Settaggio del livello di fumo a 5");
		smokeDetector2.setSmokeLevel(5);

		System.out.println("");

		System.out.println("Settaggio del livello di fumo a 15");
		smokeDetector2.setSmokeLevel(15);

		System.out.println("");

		System.out.println("Settaggio del livello di fumo a 0");
		smokeDetector2.setSmokeLevel(0);
	}

}
