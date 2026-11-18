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
	public void cruiseControlStartsDisabledWithoutValues() throws Exception {
		CruiseControl cruiseControl = new CruiseControl(() -> 0);

		assertFalse(cruiseControl.isEnabled());
		assertNull(cruiseControl.getSpeedLimit());
		assertNull(cruiseControl.getSpeedSet());
	}

	@Test
	public void setSpeedLimitStoresAValidLimit() throws Exception {
		CruiseControl cruiseControl = new CruiseControl(() -> 0);

		cruiseControl.setSpeedLimit(100);

		assertEquals(100, cruiseControl.getSpeedLimit());
	}

	@Test
	public void setSpeedSetStoresValueAndEnablesCruiseControl() throws Exception {
		CruiseControl cruiseControl = new CruiseControl(() -> 0);

		cruiseControl.setSpeedSet(80);

		assertEquals(80, cruiseControl.getSpeedSet());
		assertTrue(cruiseControl.isEnabled());
	}

	@Test
	public void setSpeedSetAcceptsSpeedEqualToLimit() throws Exception {
		CruiseControl cruiseControl = new CruiseControl(() -> 0);
		cruiseControl.setSpeedLimit(100);

		cruiseControl.setSpeedSet(100);

		assertEquals(100, cruiseControl.getSpeedSet());
		assertTrue(cruiseControl.isEnabled());
	}

	@Test
	public void disableStopsAndClearsTheCruiseControl() throws Exception {
		CruiseControl cruiseControl = new CruiseControl(() -> 0);
		cruiseControl.setSpeedSet(80);

		cruiseControl.disable();

		assertFalse(cruiseControl.isEnabled());
		assertNull(cruiseControl.getSpeedSet());
	}

	@Test
	public void disableKeepsSpeedLimitAndAllowsChangingIt() throws Exception {
		CruiseControl cruiseControl = new CruiseControl(() -> 0);
		cruiseControl.setSpeedLimit(100);
		cruiseControl.setSpeedSet(80);

		cruiseControl.disable();

		assertEquals(100, cruiseControl.getSpeedLimit());
		cruiseControl.setSpeedLimit(120);
		assertEquals(120, cruiseControl.getSpeedLimit());
	}

	@Test
	public void setSpeedSetRejectsZeroOrNegativeSpeed() throws Exception {
		CruiseControl cruiseControl = new CruiseControl(() -> 0);

		assertThrows(IncorrectSpeedSetException.class, () -> cruiseControl.setSpeedSet(0));
		assertThrows(IncorrectSpeedSetException.class, () -> cruiseControl.setSpeedSet(-10));
	}

	@Test
	public void setSpeedSetRejectsSpeedAboveLimit() throws Exception {
		CruiseControl cruiseControl = new CruiseControl(() -> 0);
		cruiseControl.setSpeedLimit(100);

		assertThrows(SpeedSetAboveSpeedLimitException.class, () -> cruiseControl.setSpeedSet(101));
	}

	@Test
	public void setSpeedLimitRejectsZeroOrNegativeLimit() throws Exception {
		CruiseControl cruiseControl = new CruiseControl(() -> 0);

		assertThrows(IncorrectSpeedLimitException.class, () -> cruiseControl.setSpeedLimit(0));
		assertThrows(IncorrectSpeedLimitException.class, () -> cruiseControl.setSpeedLimit(-10));
	}

	@Test
	public void setSpeedLimitRejectsChangeAfterSpeedSet() throws Exception {
		CruiseControl cruiseControl = new CruiseControl(() -> 0);
		cruiseControl.setSpeedSet(80);

		assertThrows(CannotSetSpeedLimitException.class, () -> cruiseControl.setSpeedLimit(100));
	}

}