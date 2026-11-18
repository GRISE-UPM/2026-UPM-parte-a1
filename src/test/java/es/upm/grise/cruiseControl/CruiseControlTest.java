package es.upm.grise.cruiseControl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import es.upm.grise.cruiseControl.exceptions.CannotSetSpeedLimitException;
import es.upm.grise.cruiseControl.exceptions.IncorrectSpeedLimitException;
import es.upm.grise.cruiseControl.exceptions.IncorrectSpeedSetException;
import es.upm.grise.cruiseControl.exceptions.SpeedSetAboveSpeedLimitException;

class CruiseControlTest {

	@Test
	void startsDisabledWithoutSpeedSettings() {
		CruiseControl cruiseControl = new CruiseControl(null);

		assertFalse(cruiseControl.isEnabled());
		assertNull(cruiseControl.getSpeedLimit());
		assertNull(cruiseControl.getSpeedSet());
	}

	@Test
	void setsPositiveSpeedLimit() throws IncorrectSpeedLimitException, CannotSetSpeedLimitException {
		CruiseControl cruiseControl = new CruiseControl(null);

		cruiseControl.setSpeedLimit(100);

		assertEquals(100, cruiseControl.getSpeedLimit());
		assertNull(cruiseControl.getSpeedSet());
		assertFalse(cruiseControl.isEnabled());
	}

	@Test
	void rejectsNonPositiveSpeedLimitWithoutChangingState() throws IncorrectSpeedLimitException {
		CruiseControl cruiseControl = new CruiseControl(null);

		assertThrows(IncorrectSpeedLimitException.class, () -> cruiseControl.setSpeedLimit(0));
		assertThrows(IncorrectSpeedLimitException.class, () -> cruiseControl.setSpeedLimit(-1));

		assertNull(cruiseControl.getSpeedLimit());
		assertNull(cruiseControl.getSpeedSet());
		assertFalse(cruiseControl.isEnabled());
	}

	@Test
	void setsPositiveSpeedAndEnablesCruiseControl()
			throws IncorrectSpeedSetException, SpeedSetAboveSpeedLimitException {
		CruiseControl cruiseControl = new CruiseControl(null);

		cruiseControl.setSpeedSet(60);

		assertEquals(60, cruiseControl.getSpeedSet());
		assertTrue(cruiseControl.isEnabled());
	}

	@Test
	void acceptsSpeedSetEqualToSpeedLimit()
			throws IncorrectSpeedLimitException, CannotSetSpeedLimitException,
			IncorrectSpeedSetException, SpeedSetAboveSpeedLimitException {
		CruiseControl cruiseControl = new CruiseControl(null);
		cruiseControl.setSpeedLimit(60);

		cruiseControl.setSpeedSet(60);

		assertEquals(60, cruiseControl.getSpeedSet());
		assertTrue(cruiseControl.isEnabled());
	}

	@Test
	void rejectsNonPositiveSpeedSetWithoutChangingState()
			throws IncorrectSpeedSetException, SpeedSetAboveSpeedLimitException {
		CruiseControl cruiseControl = new CruiseControl(null);

		assertThrows(IncorrectSpeedSetException.class, () -> cruiseControl.setSpeedSet(0));
		assertThrows(IncorrectSpeedSetException.class, () -> cruiseControl.setSpeedSet(-1));

		assertNull(cruiseControl.getSpeedSet());
		assertFalse(cruiseControl.isEnabled());
	}

	@Test
	void rejectsSpeedSetAboveLimitWithoutChangingState()
			throws IncorrectSpeedLimitException, CannotSetSpeedLimitException,
			IncorrectSpeedSetException, SpeedSetAboveSpeedLimitException {
		CruiseControl cruiseControl = new CruiseControl(null);
		cruiseControl.setSpeedLimit(60);

		assertThrows(SpeedSetAboveSpeedLimitException.class, () -> cruiseControl.setSpeedSet(61));

		assertNull(cruiseControl.getSpeedSet());
		assertFalse(cruiseControl.isEnabled());
	}

	@Test
	void rejectsSpeedLimitAfterSpeedSetWithoutChangingLimit()
			throws IncorrectSpeedSetException, SpeedSetAboveSpeedLimitException,
			IncorrectSpeedLimitException, CannotSetSpeedLimitException {
		CruiseControl cruiseControl = new CruiseControl(null);
		cruiseControl.setSpeedSet(60);

		assertThrows(CannotSetSpeedLimitException.class, () -> cruiseControl.setSpeedLimit(100));

		assertNull(cruiseControl.getSpeedLimit());
		assertEquals(60, cruiseControl.getSpeedSet());
		assertTrue(cruiseControl.isEnabled());
	}

	@Test
	void disablesCruiseControlWithoutClearingSpeedSet()
			throws IncorrectSpeedSetException, SpeedSetAboveSpeedLimitException {
		CruiseControl cruiseControl = new CruiseControl(null);
		cruiseControl.setSpeedSet(60);

		cruiseControl.disable();

		assertFalse(cruiseControl.isEnabled());
		assertEquals(60, cruiseControl.getSpeedSet());
	}
}
