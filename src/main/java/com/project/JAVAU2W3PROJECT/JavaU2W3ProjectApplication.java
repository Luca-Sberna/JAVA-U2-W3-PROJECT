package com.project.JAVAU2W3PROJECT;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.project.JAVAU2W3PROJECT.entities.ControlProcess;
import com.project.JAVAU2W3PROJECT.entities.SmokeDetector;
import com.project.JAVAU2W3PROJECT.factory.SmokeDetectorFactory;

@SpringBootApplication
public class JavaU2W3ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaU2W3ProjectApplication.class, args);

		SmokeDetector smokeDetector = SmokeDetectorFactory.createSmokeDetector();
		ControlProcess controlProcess = new ControlProcess(smokeDetector, "sonda_1", 45.4642, 9.1900);

		System.out.println("");

		System.out.println("Settaggio del livello di fumo a 3");
		smokeDetector.setSmokeLevel(3);

		System.out.println("");

		System.out.println("Settaggio del livello di fumo a 6");
		smokeDetector.setSmokeLevel(6);

		System.out.println("");

		System.out.println("Settaggio del livello di fumo a 10");
		smokeDetector.setSmokeLevel(10);
	}

}
