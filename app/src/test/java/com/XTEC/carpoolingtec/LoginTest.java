package com.XTEC.carpoolingtec;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginTest {

    private Login login1;

    @Before
    public void setUp(){
        login1 = new Login();
    }

    @Test
    public void loginNotNull(){
        assertNotNull(login1);
    }

    //@Test
    //public void registro() throws Exception {
    //}

    //@Test
    //public void login() {
    //}
}