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

    @Before
    public void setUp(){
        login1 = new Login();
    }

    @Test
    public void loginLaunch(){
        assertNotNull(login1);
    }

    @After
    public void tearDown(){
    }


}