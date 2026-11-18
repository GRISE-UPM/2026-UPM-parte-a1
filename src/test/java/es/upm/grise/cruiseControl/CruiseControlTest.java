package es.upm.grise.cruiseControl;

import org.junit.jupiter.api.Test;

class CruiseControlTest {

	@Test
	public void smokeTest() {}





	@Test
    public void testSetSpeedSetIncorrectoNegativo() {
        CruiseControl control = new CruiseControl();
        int velocidadInvalida = -10;
        IncorrectSpeedSetException excepcion = assertThrows(
            IncorrectSpeedSetException.class, 
            () -> {
                control.setSpeedSet(velocidadInvalida);
            }
        );
    }
    
    @Test
    public void testSetSpeedSetValido() {
        CruiseControl control = new CruiseControl();
        int velocidadValida = 80;
        control.setSpeedSet(velocidadValida);
        assertEquals(velocidadValida, control.getSpeedSet());
        assertTrue(control.isEnabled());
    }

@Test
    public void testEstablecerSpeedLimitDosVeces() {
        CruiseControl control = new CruiseControl();
        
        control.setSpeedSet(100); Exception
        CannotSetSpeedLimitException excepcion = assertThrows(
            CannotSetSpeedLimitException.class, 
            () -> {
                control.setSpeedLimit(120);
            }
        );
        assertNotNull(excepcion);
    }
}