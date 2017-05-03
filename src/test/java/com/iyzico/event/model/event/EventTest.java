package com.iyzico.event.model.event;


import com.iyzico.event.model.AbstractEntity;
import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by TCMBAS on 26/04/2017.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class EventTest
{
    private Event event;

    @Test
    public void testEventCreateNewObject() throws Exception
    {
        event = new Event();
        assertNotNull(event);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEventNameNullThrowsException() throws Exception
    {
        event = new Event(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEventNameEmptyThrowsException() throws Exception
    {
        event = new Event(StringUtils.EMPTY);
    }

    @Test
    public void testEventCreateEventSuccessfuly()
    {
        event = new Event("JavaDay", LocalDate.now());
        assertEquals("JavaDay", event.getEventName());
        assertNotNull(event.getInternalId());
        assertEquals(event.getInternalId().length(), 36);
        Assert.assertEquals(event.getDeleted(), AbstractEntity.DEFAULT_DELETED_VALUE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGEOValidateLatitude() throws Exception
    {
        event = new Event();
        event.setAddressLatitude(100.0);
        event.setAddressLatitude(-100.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGEOValidateLongitude() throws Exception
    {
        event = new Event();
        event.setAddressLongitude(190.0);
        event.setAddressLongitude(-190.0);
    }
}
