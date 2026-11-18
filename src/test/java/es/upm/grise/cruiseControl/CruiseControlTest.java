package es.upm.grise.cruiseControl;

import org.junit.jupiter.api.Test;

import es.upm.grise.cruiseControl.exceptions.CannotSetSpeedLimitException;
import es.upm.grise.cruiseControl.exceptions.IncorrectSpeedLimitException;
import es.upm.grise.cruiseControl.exceptions.IncorrectSpeedSetException;
import es.upm.grise.cruiseControl.exceptions.SpeedSetAboveSpeedLimitException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CruiseControlTest {

	@Test
	public void initializesSpeedSetAndSpeedLimitAsNull() {
		CruiseControl cruiseControl = new CruiseControl(null);

		assertNull(cruiseControl.getSpeedSet());
		assertNull(cruiseControl.getSpeedLimit());
		assertFalse(cruiseControl.isEnabled());
	}

	@Test
	public void setsPositiveSpeedSetAndEnablesCruiseControl() throws Exception {
		CruiseControl cruiseControl = new CruiseControl(null);

		cruiseControl.setSpeedSet(50);

		assertEquals(50, cruiseControl.getSpeedSet());
		assertTrue(cruiseControl.isEnabled());
	}

	@Test
	public void rejectsZeroSpeedSet() {
		CruiseControl cruiseControl = new CruiseControl(null);

		assertThrows(IncorrectSpeedSetException.class, () -> cruiseControl.setSpeedSet(0));
		assertNull(cruiseControl.getSpeedSet());
		assertFalse(cruiseControl.isEnabled());
	}

	@Test
	public void rejectsNegativeSpeedSet() {
		CruiseControl cruiseControl = new CruiseControl(null);

		assertThrows(IncorrectSpeedSetException.class, () -> cruiseControl.setSpeedSet(-1));
		assertNull(cruiseControl.getSpeedSet());
		assertFalse(cruiseControl.isEnabled());
	}

	@Test
	public void setsPositiveSpeedLimit() throws Exception {
		CruiseControl cruiseControl = new CruiseControl(null);

		cruiseControl.setSpeedLimit(120);

		assertEquals(120, cruiseControl.getSpeedLimit());
	}

	@Test
	public void rejectsZeroSpeedLimit() {
		CruiseControl cruiseControl = new CruiseControl(null);

		assertThrows(IncorrectSpeedLimitException.class, () -> cruiseControl.setSpeedLimit(0));
		assertNull(cruiseControl.getSpeedLimit());
	}

	@Test
	public void rejectsNegativeSpeedLimit() {
		CruiseControl cruiseControl = new CruiseControl(null);

		assertThrows(IncorrectSpeedLimitException.class, () -> cruiseControl.setSpeedLimit(-1));
		assertNull(cruiseControl.getSpeedLimit());
	}

	@Test
	public void acceptsSpeedSetEqualToSpeedLimit() throws Exception {
		CruiseControl cruiseControl = new CruiseControl(null);
		cruiseControl.setSpeedLimit(50);

		cruiseControl.setSpeedSet(50);

		assertEquals(50, cruiseControl.getSpeedSet());
		assertTrue(cruiseControl.isEnabled());
	}

	@Test
	public void rejectsSpeedSetAboveSpeedLimit() throws Exception {
		CruiseControl cruiseControl = new CruiseControl(null);
		cruiseControl.setSpeedLimit(50);

		assertThrows(SpeedSetAboveSpeedLimitException.class, () -> cruiseControl.setSpeedSet(51));
		assertNull(cruiseControl.getSpeedSet());
		assertFalse(cruiseControl.isEnabled());
	}

	@Test
	public void rejectsSettingSpeedLimitAfterSpeedSet() throws Exception {
		CruiseControl cruiseControl = new CruiseControl(null);
		cruiseControl.setSpeedSet(50);

		assertThrows(CannotSetSpeedLimitException.class, () -> cruiseControl.setSpeedLimit(120));
		assertNull(cruiseControl.getSpeedLimit());
	}

	@Test
	public void disableResetsSpeedSetAndDisablesCruiseControl() throws Exception {
		CruiseControl cruiseControl = new CruiseControl(null);
		cruiseControl.setSpeedSet(50);

		cruiseControl.disable();

		assertFalse(cruiseControl.isEnabled());
		assertNull(cruiseControl.getSpeedSet());
	}

}
