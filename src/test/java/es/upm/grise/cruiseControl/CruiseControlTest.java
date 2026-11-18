package es.upm.grise.cruiseControl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.upm.grise.cruiseControl.exceptions.CannotSetSpeedLimitException;
import es.upm.grise.cruiseControl.exceptions.IncorrectSpeedLimitException;
import es.upm.grise.cruiseControl.exceptions.IncorrectSpeedSetException;
import es.upm.grise.cruiseControl.exceptions.SpeedSetAboveSpeedLimitException;

class CruiseControlTest {

	private CruiseControl control;

	@BeforeEach
	void prepararControl() {
		control = new CruiseControl(() -> 0);
	}

	// Se comprueba que el control de crucero empieza desactivado, sin velocidad objetivo ni límite de velocidad.
	@Test
	void estadoInicial() {
		assertFalse(control.isEnabled());
		assertNull(control.getSpeedSet());
		assertNull(control.getSpeedLimit());
	}

	// Se comprueba que se puede establecer una velocidad objetivo positiva sin límite, lo que activa el control de crucero.
	@Test
	void activaElControlAlFijarLaVelocidad() throws Exception {
		control.setSpeedSet(60);

		assertTrue(control.isEnabled());
		assertEquals(60, control.getSpeedSet());
	}

	// Se comprueba que se aceptan velocidades objetivo por debajo o iguales al límite.
	@Test
	void puedeSubirLaVelocidadHastaElLimite() throws Exception {
		control.setSpeedLimit(90);
		control.setSpeedSet(70);
		assertEquals(70, control.getSpeedSet());

		control.setSpeedSet(90);
		assertEquals(90, control.getSpeedSet());
	}

	// Se comprueba que no se puede establecer una velocidad objetivo no positiva sin límite, y que no se activa el control de crucero.
	@Test
	void rechazaUnaVelocidadObjetivoNoPositiva() {
		assertThrows(IncorrectSpeedSetException.class, () -> control.setSpeedSet(0));
		assertThrows(IncorrectSpeedSetException.class, () -> control.setSpeedSet(-10));
		assertFalse(control.isEnabled());
		assertNull(control.getSpeedSet());
	}

	// Se comprueba que se rechazan velocidades objetivo por encima del límite.
	@Test
	void rechazaUnaVelocidadPorEncimaDelLimite() throws Exception {
		control.setSpeedLimit(90);

		assertThrows(SpeedSetAboveSpeedLimitException.class, () -> control.setSpeedSet(91));
		assertNull(control.getSpeedSet());
		assertFalse(control.isEnabled());
	}

	// Se comprueba que se rechazan límites de velocidad no positivos y que se mantiene el límite anterior.
	@Test
	void conservaElLimiteSiElNuevoEsInvalido() throws Exception {
		control.setSpeedLimit(80);

		assertThrows(IncorrectSpeedLimitException.class, () -> control.setSpeedLimit(0));
		assertThrows(IncorrectSpeedLimitException.class, () -> control.setSpeedLimit(-20));
		assertEquals(80, control.getSpeedLimit());
	}

	// Se comprueba que no se puede cambiar el límite de velocidad mientras hay una velocidad objetivo establecida, y que se mantiene el límite anterior.
	@Test
	void bloqueaElCambioDeLimiteMientrasEstaActivo() throws Exception {
		control.setSpeedLimit(100);
		control.setSpeedSet(75);

		assertThrows(CannotSetSpeedLimitException.class, () -> control.setSpeedLimit(90));
		assertEquals(100, control.getSpeedLimit());
	}

	// Se comprueba que desactivar el control de crucero borra la velocidad objetivo y permite cambiar el límite de velocidad.
	@Test
	void permiteCambiarElLimiteDespuesDeDesactivar() throws Exception {
		control.setSpeedLimit(100);
		control.setSpeedSet(75);

		control.disable();
		assertFalse(control.isEnabled());
		assertNull(control.getSpeedSet());
		assertEquals(100, control.getSpeedLimit());

		control.setSpeedLimit(80);
		assertEquals(80, control.getSpeedLimit());
	}

}
