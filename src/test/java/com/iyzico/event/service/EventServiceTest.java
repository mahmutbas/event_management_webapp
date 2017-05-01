package com.iyzico.event.service;

import com.google.common.collect.Lists;
import com.iyzico.event.dao.event.EventRepository;
import com.iyzico.event.model.event.Event;
import com.iyzico.event.service.event.EventService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;


/**
 * Created by TCMBAS on 29/04/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EventServiceTest
{
    @MockBean
    private EventRepository eventRepositoryMock;

    @Autowired
    @Qualifier("eventService")
    public EventService eventService;

    @Before
    public void setUp()
    {
        given(this.eventRepositoryMock.findByEventName("Java Day")).willReturn(new Event("Java Day"));
        given(this.eventRepositoryMock.findAll()).willReturn(Lists.newArrayList(new Event("All Event Day")));
    }

    @Test
    public void testFindByEventName()
    {
        Event event = eventService.findByEventName("Java Day");
        assertThat(event.getEventName()).isEqualTo("Java Day");
    }

    @Test
    public void testFindAllEvents()
    {
        List<Event> event = eventService.findAllEvents();
        Assert.assertNotNull(event);
        assertThat(event.iterator().next().getEventName()).isEqualTo("All Event Day");
    }

}
