package com.XTEC.carpoolingtec;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;

public class InicioTest {

    @Mock
    private Inicio inicio1;

    @Before
    public void setUp() throws Exception {
        inicio1 = new Inicio();
    }

    @Test
    public void inicioLaunch() {
        assertNotNull(inicio1);
    }

    @After
    public void tearDown() throws Exception {
    }
}