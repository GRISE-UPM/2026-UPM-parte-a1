package es.upm.grise.cruiseControl;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import CruiseControl;

class CruiseControlTest {

	@Test
	public void smokeTest() {
		CruiseControl cr = mock(CruiseControl.class);

		// comporbar correcta inicializacion
		when(cr.isEnabled()).thenReturn(false);
		when(cr.getSpeedLimit()).thenReturn(null);
		when(cr.getSpeedSet()).thenReturn(null);

		// comprobar excepciones con valores menoes o igual a 0
		when(cr.setSpeedSet(0)).thenReturn(IncorrectSpeedSetException.class);
		when(cr.setSpeedSet(-1)).thenReturn(IncorrectSpeedSetException.class);
		when(cr.setSpeedLimit(0)).thenReturn(IncorrectSpeedLimitException.class);
		when(cr.setSpeedLimit(-1)).thenReturn(IncorrectSpeedLimitException.class);

		// prueba con setspeedset sin speedlimit
		cr.setSpeedSet(5);
		when(cr.isEnabled()).thenReturn(true);
		when(cr.getSpeedSet()).thenReturn(5);

		when(cr.setSpeedLimit(20)).thenReturn(CannotSetSpeedLimitException.class);

		// prueba con disable
		cr.disable();
		when(cr.getSpeedSet()).thenReturn(null);
		when(cr.isEnabled()).thenReturn(false);

		// prueba normal con speedlimit
		cr.setSpeedLimit(20);
		when(cr.isEnabled()).thenReturn(false);
		when(cr.getSpeedLimit()).thenReturn(20);

		// prueba cuando se le pasa un speed set mayor que speed limit
		when(cr.setSpeedSet(40)).thenReturn(SpeedSetAboveSpeedLimitException.class);

	}

}
