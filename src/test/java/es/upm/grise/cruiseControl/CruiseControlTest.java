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

	private final CruiseControl cruiseControl = new CruiseControl(() -> 0);

	@Test
	void startsDisabledWithoutTargetOrLimit() {
		assertFalse(cruiseControl.isEnabled());
		assertNull(cruiseControl.getSpeedSet());
		assertNull(cruiseControl.getSpeedLimit());
	}

	@Test
	void settingPositiveTargetWithoutLimitEnablesCruiseControl() throws Exception {
		cruiseControl.setSpeedSet(1);

		assertEquals(1, cruiseControl.getSpeedSet());
		assertTrue(cruiseControl.isEnabled());
		assertNull(cruiseControl.getSpeedLimit());
	}

	@Test
	void rejectsNonPositiveTargetsWithoutEnablingCruiseControl() {
		assertThrows(IncorrectSpeedSetException.class, () -> cruiseControl.setSpeedSet(0));
		assertThrows(IncorrectSpeedSetException.class, () -> cruiseControl.setSpeedSet(-1));
		assertNull(cruiseControl.getSpeedSet());
		assertFalse(cruiseControl.isEnabled());
	}

	@Test
	void acceptsTargetsBelowAndEqualToLimit() throws Exception {
		cruiseControl.setSpeedLimit(100);
		cruiseControl.setSpeedSet(99);
		assertEquals(99, cruiseControl.getSpeedSet());

		cruiseControl.setSpeedSet(100);
		assertEquals(100, cruiseControl.getSpeedSet());
		assertTrue(cruiseControl.isEnabled());
	}

	@Test
	void rejectsTargetAboveLimitAndKeepsPreviousTarget() throws Exception {
		cruiseControl.setSpeedLimit(100);
		cruiseControl.setSpeedSet(99);

		assertThrows(SpeedSetAboveSpeedLimitException.class, () -> cruiseControl.setSpeedSet(101));
		assertEquals(99, cruiseControl.getSpeedSet());
		assertTrue(cruiseControl.isEnabled());
	}

	@Test
	void acceptsAndUpdatesPositiveLimitWhileDisabled() throws Exception {
		cruiseControl.setSpeedLimit(1);
		assertEquals(1, cruiseControl.getSpeedLimit());

		cruiseControl.setSpeedLimit(100);
		assertEquals(100, cruiseControl.getSpeedLimit());
		assertFalse(cruiseControl.isEnabled());
		assertNull(cruiseControl.getSpeedSet());
	}

	@Test
	void rejectsNonPositiveLimitsAndKeepsPreviousLimit() throws Exception {
		cruiseControl.setSpeedLimit(100);

		assertThrows(IncorrectSpeedLimitException.class, () -> cruiseControl.setSpeedLimit(0));
		assertThrows(IncorrectSpeedLimitException.class, () -> cruiseControl.setSpeedLimit(-1));
		assertEquals(100, cruiseControl.getSpeedLimit());
	}

	@Test
	void rejectsChangingLimitWhileTargetIsSet() throws Exception {
		cruiseControl.setSpeedLimit(100);
		cruiseControl.setSpeedSet(80);

		assertThrows(CannotSetSpeedLimitException.class, () -> cruiseControl.setSpeedLimit(110));
		assertEquals(100, cruiseControl.getSpeedLimit());
	}

	@Test
	void disableClearsTargetAndAllowsChangingLimit() throws Exception {
		cruiseControl.setSpeedLimit(100);
		cruiseControl.setSpeedSet(80);

		cruiseControl.disable();

		assertFalse(cruiseControl.isEnabled());
		assertNull(cruiseControl.getSpeedSet());
		assertEquals(100, cruiseControl.getSpeedLimit());

		cruiseControl.setSpeedLimit(120);
		cruiseControl.setSpeedSet(120);
		assertEquals(120, cruiseControl.getSpeedLimit());
		assertEquals(120, cruiseControl.getSpeedSet());
		assertTrue(cruiseControl.isEnabled());
	}

}
