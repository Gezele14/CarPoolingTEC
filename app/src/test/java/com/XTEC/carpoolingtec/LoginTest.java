package com.XTEC.carpoolingtec;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.validateMockitoUsage;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class LoginTest {

    @Mock
    private Login login1;

    private Login login2;

    @Before
    public void setUp(){
        login2 = new Login();
    }

    @Test
    public void login(){
        assertNotNull(login1);
    }
    @Test
    public void registro() throws Exception {
    }

    @After
    public void tearDown(){
    }


}