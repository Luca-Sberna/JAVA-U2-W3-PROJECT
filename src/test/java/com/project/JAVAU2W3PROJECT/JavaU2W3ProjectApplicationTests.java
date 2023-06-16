package com.project.JAVAU2W3PROJECT;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.JAVAU2W3PROJECT.entities.SmokeDetector;
import com.project.JAVAU2W3PROJECT.entities.Sonda;
import com.project.JAVAU2W3PROJECT.exceptions.CommunicationFailureException;
import com.project.JAVAU2W3PROJECT.exceptions.InvalidCoordinatesException;
import com.project.JAVAU2W3PROJECT.exceptions.SensorFailureException;
import com.project.JAVAU2W3PROJECT.interfaces.ObserverSmokeControl;

@SpringBootTest
class JavaU2W3ProjectApplicationTests {
	SmokeDetector smokeDetector = new SmokeDetector();
	private final PrintStream standardOut = System.out;
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

	@BeforeEach
	public void setUp() {
		System.setOut(new PrintStream(outputStreamCaptor));
	}

	@AfterEach
	public void tearDown() {
		System.setOut(standardOut);
	}

	@Test
	public void testMainMethodPrintsExpectedOutput() throws SensorFailureException, CommunicationFailureException {
		JavaU2W3ProjectApplication.main(new String[] {});
		assertTrue(outputStreamCaptor.toString().contains("Settaggio del livello di fumo a 3"));
	}

	@Test
	public void testSetSmokeLevelThrowsExceptionWhenSmokeLevelIsInvalid() throws InvalidCoordinatesException {
		Sonda sondaTest = new Sonda(smokeDetector, "sonda_test", 45.4642, 9.1900);
		assertThrows(SensorFailureException.class, () -> smokeDetector.setSmokeLevel(sondaTest, -1));
	}

	@Test
	public void testSetSmokeLevelSetsTheSmokeLevelAndNotifiesObservers()
			throws InvalidCoordinatesException, CommunicationFailureException, SensorFailureException {
		Sonda sondaTest = new Sonda(smokeDetector, "sonda_test", 45.4642, 9.1900);
		ObserverSmokeControl observer = mock(ObserverSmokeControl.class);
		smokeDetector.registerObserver(observer);
		smokeDetector.setSmokeLevel(sondaTest, 1);
		assertEquals(1, smokeDetector.getSmokeLevel());
		verify(observer).update(1);
	}

	@Test
	public void testInvalidCoordinatesExceptionIsThrownWhenCoordinatesAreInvalid() {
		assertThrows(InvalidCoordinatesException.class, () -> new Sonda(smokeDetector, "sonda_test", 200.0, 200.0));
	}

	@Test
	public void testCommunicationFailureExceptionIsThrownWhenNotificationFails()
			throws InvalidCoordinatesException, CommunicationFailureException {
		// Uno spy è simile a un mock, ma invece di sostituire tutti i metodi della
		// classe con versioni vuote, consente di utilizzare i metodi originali della
		// classe e di sovrascrivere solo i metodi specifici che si desidera modificare
		SmokeDetector smokeDetector = spy(new SmokeDetector());
		Sonda sondaTest = new Sonda(smokeDetector, "sonda_test", 45.4642, 9.1900);
		doThrow(new CommunicationFailureException("Errore di comunicazione")).when(smokeDetector).notifyObservers();
		assertThrows(CommunicationFailureException.class, () -> smokeDetector.setSmokeLevel(sondaTest, 1));
	}

	@Test
	public void testRegisterObserverRegistersAnObserver()
			throws InvalidCoordinatesException, CommunicationFailureException, SensorFailureException {
		Sonda sondaTest = new Sonda(smokeDetector, "sonda_test", 45.4642, 9.1900);
		ObserverSmokeControl observer = mock(ObserverSmokeControl.class);
		smokeDetector.registerObserver(observer);
		smokeDetector.setSmokeLevel(sondaTest, 1);
		verify(observer).update(1);
	}

	@Test
	public void testRemoveObserverRemovesAnObserver()
			throws InvalidCoordinatesException, CommunicationFailureException, SensorFailureException {
		Sonda sondaTest = new Sonda(smokeDetector, "sonda_test", 45.4642, 9.1900);
		ObserverSmokeControl observer = mock(ObserverSmokeControl.class);
		smokeDetector.registerObserver(observer);
		smokeDetector.removeObserver(observer);
		smokeDetector.setSmokeLevel(sondaTest, 1);
		verify(observer, never()).update(1);
	}

	@Test
	public void testSondaConstructorSetsThePropertiesCorrectly() throws InvalidCoordinatesException {
		Sonda sonda = new Sonda(smokeDetector, "sonda_test", 45.4642, 9.1900);
		assertEquals(smokeDetector, sonda.getSmokeDetector());
		assertEquals("sonda_test", sonda.getIdSonda());
		assertEquals(45.4642, sonda.getLatitudine());
		assertEquals(9.1900, sonda.getLongitudine());
	}

}
