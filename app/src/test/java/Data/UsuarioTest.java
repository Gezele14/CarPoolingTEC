package Data;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)

public class UsuarioTest {

    @Mock
    private Usuario usuario1;
    private Usuario usuario2;

    @Before
    public void setUp() throws Exception {
        usuario1 = new Usuario("Juan D Esquivel",86074248);
        usuario2 = new Usuario();
        usuario2.setNombre("Nano Ramirez");
        usuario2.setCorreo("nano1@gmail.com");
        usuario2.setCedula(116630004);
        usuario2.setCarnet(2016167794);
        usuario2.setCant_autos(2);
        usuario2.setCant_puntos(150);
        usuario2.setCant_viajes(12);
        usuario2.addAmigo(new Usuario("Meca Zeledon", 8563829));
        usuario2.addAuto(new Auto("Toyota","Hilux","MZD-233",5));
        usuario2.addAuto(new Auto("Nissan","Sentra","MSW-127",4));
        usuario2.addSolicitud(new Usuario("Christofer Fernandez", 87642412));
    }


    @Test
    public void usuarioLaunch(){
        assertNotNull(usuario1);
    }
    @Test
    public void usuarioNombre(){
        assertEquals(usuario1.getNombre(),"Juan D Esquivel");
    }
    @Test
    public void usuarioTelefono(){
        assertNotEquals(usuario1.getTelefono(),88568888);
    }
    @Test
    public void usuarioCorreo(){
        assertEquals(usuario2.getCorreo(),"nano1@gmail.com");
    }
    @Test
    public void usuarioCedula(){
        assertNotEquals(usuario2.getCedula(),166630010);
    }
    @Test
    public void usuarioCarnet() {
        assertEquals(usuario2.getCarnet(),2016167794);
    }
    @Test
    public void usuarioListas(){
        assertEquals(usuario2.getListaSolicitudes().size(),usuario2.getListaAmigos().size());
        assertEquals(usuario2.getListaAutosOriginal(),usuario2.getListaAutos());
        assertNotEquals(usuario2.getListaAutos().size(),3);
    }
    @Test
    public void usuarioCantPuntos() {
        assertNotEquals(usuario2.getCant_puntos(), 151);
    }
    @Test
    public void usuarioCantViajes(){
        assertNotEquals(usuario2.getCant_viajes(),13);
    }


    @After
    public void tearDown() throws Exception {
    }
}