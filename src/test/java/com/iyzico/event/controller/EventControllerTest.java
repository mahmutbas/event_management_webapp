package com.iyzico.event.controller;

import com.google.common.collect.Lists;
import com.iyzico.event.model.event.Event;
import com.iyzico.event.service.event.EventService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

/**
 * Created by TCMBAS on 05/05/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EventControllerTest
{
    @MockBean(name = "eventService")
    private EventService eventServiceMock;

    @Before
    public void setUp()
    {
        given(this.eventServiceMock.findByEventName("Java Day")).willReturn(new Event("Java Day", LocalDate.of(2020,10,15)));
    }

    @Test
    public void testInit()
    {
        Event event = eventServiceMock.findByEventName("Java Day");
        assertThat(event.getEventName()).isEqualTo("Java Day");
    }
}