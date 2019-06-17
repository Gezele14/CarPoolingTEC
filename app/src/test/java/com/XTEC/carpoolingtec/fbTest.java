package com.XTEC.carpoolingtec;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import  com.facebook.*;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.internal.FetchedAppSettingsManager;
import com.facebook.internal.Utility;

import static com.facebook.FacebookSdk.getApplicationContext;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class fbTest {

    @Mock
    private fb fb1;

    @Before
    public void setUp(){
    }

    @Test
    public void fbLaunch() throws Exception{
        assertNotNull(fb1);
    }
    @Test
    public void checklogin() {
        assertNotNull(fb1);
    }

    @After
    public void tearDown() throws Exception {
    }

}