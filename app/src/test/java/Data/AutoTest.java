package Data;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class AutoTest {

    @Mock
    private Auto auto1;

    @Before
    public void setUp() throws Exception {
        auto1 = new Auto(1, "BMW", "M5", "JDR-125",5);
    }


    @Test
    public void autoLaunch(){
        assertNotNull(auto1);
    }
    @Test
    public void Marca() {
        assertEquals(auto1.getMarca(),"BMW");
        assertNotEquals(auto1.getMarca(),"Aston Martin");
    }
    @Test
    public void Modelo() {
        assertEquals(auto1.getModelos(),"M5");
    }
    @Test
    public void placa() {
        assertNotEquals(auto1.getPlaca(),"JDE-242");
    }
    @Test
    public void cantPersonas() {
        assertNotEquals(auto1.getCapacidad(),6);
    }


    @After
    public void tearDown() throws Exception {
    }







}