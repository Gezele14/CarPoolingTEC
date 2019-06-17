package com.XTEC.carpoolingtec;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;


@RunWith(MockitoJUnitRunner.class)
public class RegisterTest {

    @Mock
    private Register register1;


    @Before
    public void setUp() throws Exception {
        register1 = new Register();
    }

    @Test
    public void registerLaunch() {
        assertNotNull(register1);
    }

    @After
    public void tearDown() throws Exception {
    }


}