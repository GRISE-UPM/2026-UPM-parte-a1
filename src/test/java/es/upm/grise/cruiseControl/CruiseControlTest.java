package es.upm.grise.cruiseControl;

import es.upm.grise.cruiseControl.exceptions.CannotSetSpeedLimitException;
import es.upm.grise.cruiseControl.exceptions.IncorrectSpeedLimitException;
import es.upm.grise.cruiseControl.exceptions.IncorrectSpeedSetException;
import es.upm.grise.cruiseControl.exceptions.SpeedSetAboveSpeedLimitException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CruiseControlTest {

	@Test
	public void smokeTest() {
		CruiseControl testing = new CruiseControl();
        assertNotNull(testing);
	}

	@Test
	public void speedcorrectSetTest() throws IncorrectSpeedSetException, SpeedSetAboveSpeedLimitException {
		CruiseControl testing = new CruiseControl();
		testing.setSpeedSet(10);
		assertEquals(10, testing.getSpeedSet());
		assertTrue(testing.isEnabled());
	}

	@Test
	public void speedIncorrectSetTest() throws IncorrectSpeedSetException, SpeedSetAboveSpeedLimitException {
		CruiseControl testing = new CruiseControl();
		assertThrows(IncorrectSpeedSetException.class, () -> testing.setSpeedSet(-3));
	}

	@Test
	public void speedaboveLimitSetTest() throws IncorrectSpeedSetException, SpeedSetAboveSpeedLimitException, CannotSetSpeedLimitException, IncorrectSpeedLimitException {
		CruiseControl testing = new CruiseControl();
		testing.setSpeedLimit(3);
		assertThrows(SpeedSetAboveSpeedLimitException.class, () -> testing.setSpeedSet(5));
	}

	@Test
	public void setWrongLimit() throws CannotSetSpeedLimitException, IncorrectSpeedLimitException {
		CruiseControl testing = new CruiseControl();
		assertThrows(IncorrectSpeedLimitException.class, () -> testing.setSpeedLimit(-3));
	}

	@Test
	public void setLimitDisabled() throws CannotSetSpeedLimitException, IncorrectSpeedLimitException, IncorrectSpeedSetException, SpeedSetAboveSpeedLimitException {
		CruiseControl testing = new CruiseControl();
		testing.setSpeedSet(5);
		assertThrows(CannotSetSpeedLimitException.class, () -> testing.setSpeedLimit(5));
	}

	@Test
	public void disableTest() throws IncorrectSpeedSetException, SpeedSetAboveSpeedLimitException {
		CruiseControl testing = new CruiseControl();
		testing.setSpeedSet(5);
		assertTrue(testing.isEnabled());
		testing.disable();
		assertFalse(testing.isEnabled());
		assertNull(testing.getSpeedSet());
	}

}
