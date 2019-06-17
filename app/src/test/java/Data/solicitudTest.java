package Data;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class solicitudTest {

    @Mock
    private solicitud solicitud1;

    @Before
    public void setUp() throws Exception {
        solicitud1 = new solicitud("Juan Esquivel",2);
    }

    @Test
    public void solicitudLaunch(){
        assertNotNull(solicitud1);
    }
    @Test
    public void usuarioNombre(){
        assertEquals(solicitud1.getNombre(),"Juan Esquivel");
    }
    @Test
    public void usuarioTelefono(){
        assertNotEquals(solicitud1.getId(),8);
    }

    @After
    public void tearDown() throws Exception {
    }
}