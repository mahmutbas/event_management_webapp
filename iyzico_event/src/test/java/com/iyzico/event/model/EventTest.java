package com.iyzico.event.model;


import com.iyzico.event.model.event.Event;
import org.apache.commons.lang.StringUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

/**
 * Created by TCMBAS on 26/04/2017.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class EventTest
{
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private Event event;

    @Test(expected = IllegalArgumentException.class)
    public void testEventName_NullThrowsException() throws Exception
    {
        event = new Event(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEventName_EmptyThrowsException() throws Exception
    {
        event = new Event(StringUtils.EMPTY);
    }

    @Test
    public void testEvent_CreateNewObject()
    {
        event = new Event();
        assertNotNull(event);
    }

    @Test
    public void testEvent_CreateEventSuccessfuly()
    {
        event = new Event("JavaDay");
        assertEquals("JavaDay", event.getEventName());
        assertNotNull(event.getInternalId());
        assertEquals(event.getInternalId().length(), 36);
        assertEquals(event.getDeleted(), AbstractEntity.DEFAULT_DELETED_VALUE);
    }
}
