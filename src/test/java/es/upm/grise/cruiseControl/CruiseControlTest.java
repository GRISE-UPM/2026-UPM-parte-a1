package es.upm.grise.cruiseControl;

import es.upm.grise.cruiseControl.exceptions.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class CruiseControlTest {

	@Test
	public void initialSpeedNull() throws Exception {
		Speedometer speedometer = mock(Speedometer.class);
		CruiseControl cruiseControl = new CruiseControl(speedometer);
		assertNull(cruiseControl.getSpeedSet());
		assertNull(cruiseControl.getSpeedLimit());
	}

	@Test
	public void setSpeedSet() throws Exception {
		Speedometer speedometer = mock(Speedometer.class);
		CruiseControl cruiseControl = new CruiseControl(speedometer);
		cruiseControl.setSpeedSet(100);
		assertEquals(100, cruiseControl.getSpeedSet());
	}

	@Test
	public void setSpeedLimit() throws Exception {
		Speedometer speedometer = mock(Speedometer.class);
		CruiseControl cruiseControl = new CruiseControl(speedometer);
		cruiseControl.setSpeedLimit(120);
		assertEquals(120, cruiseControl.getSpeedLimit());
	}

	@Test
	public void setSpeedLimitAfterSetSpeedSet() throws Exception {
		Speedometer speedometer = mock(Speedometer.class);
		CruiseControl cruiseControl = new CruiseControl(speedometer);
		cruiseControl.setSpeedSet(100);
		assertThrows(CannotSetSpeedLimitException.class, () -> cruiseControl.setSpeedLimit(120));
	}

	@Test
	public void setZeroOrNegativeSpeedSet() throws Exception {
		Speedometer speedometer = mock(Speedometer.class);
		CruiseControl cruiseControl = new CruiseControl(speedometer);
		assertThrows(IncorrectSpeedSetException.class, () -> cruiseControl.setSpeedSet(0));
		assertThrows(IncorrectSpeedSetException.class, () -> cruiseControl.setSpeedSet(-10));
	}

	@Test 
	public void setSpeedSetAboveSpeedLimit() throws Exception {
		Speedometer speedometer = mock(Speedometer.class);
		CruiseControl cruiseControl = new CruiseControl(speedometer);
		cruiseControl.setSpeedLimit(100);
		assertThrows(SpeedSetAboveSpeedLimitException.class, () -> cruiseControl.setSpeedSet(120));
	}

	@Test 
	public void setZeroOrNegativeSpeedLimit() throws Exception {
		Speedometer speedometer = mock(Speedometer.class);
		CruiseControl cruiseControl = new CruiseControl(speedometer);
		assertThrows(IncorrectSpeedLimitException.class, () -> cruiseControl.setSpeedLimit(0));
		assertThrows(IncorrectSpeedLimitException.class, () -> cruiseControl.setSpeedLimit(-10));
	}

	@Test
	public void disableCruiseControl() throws Exception {
		Speedometer speedometer = mock(Speedometer.class);
		CruiseControl cruiseControl = new CruiseControl(speedometer);
		cruiseControl.setSpeedSet(100);
		assertTrue(cruiseControl.isEnabled());
		cruiseControl.disable();
		assertFalse(cruiseControl.isEnabled());
		assertNull(cruiseControl.getSpeedSet());
	}
}
