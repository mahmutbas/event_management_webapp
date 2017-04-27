package com.iyzico.event.dao;

import com.iyzico.event.dao.event.EventRepository;
import com.iyzico.event.model.event.Event;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by TCMBAS on 27/04/2017.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class EventRepositoryTest
{
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EventRepository eventRepository;

    @Test
    public void testPersistNewObject()
    {
       Event abc= this.entityManager.persist(new Event("JavaDay Frankfurt"));
        Event event = testFindByEventName("JavaDay Frankfurt");
        assertEquals(event.getEventName(), "JavaDay Frankfurt");
        assertNotNull(event.getId());
    }

    @Test
    public void testRepositoryCanNotBeEmpty()
    {
        List<Event> eventList = eventRepository.findAll();
        assertNotNull(eventList);
        assertThat(eventList.size()).isGreaterThan(0);
    }

    private Event testFindByEventName(String eventName)
    {
        return eventRepository.findByEventName(eventName);
    }
}
