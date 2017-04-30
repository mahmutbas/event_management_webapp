package com.iyzico.event.api.event;

import com.google.common.collect.Lists;
import com.iyzico.event.model.event.Event;
import com.iyzico.event.service.event.EventService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

/**
 * Created by TCMBAS on 29/04/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EventRestTest
{
    @Autowired
    private TestRestTemplate template;

    @MockBean(name = "eventService")
    private EventService eventService;

    @Test
    public void testFindByEventName() throws Exception
    {
        given(eventService.findAllEvents()).willReturn(Lists.newArrayList(new Event("JavaDay")));
        ResponseEntity<String> fact = template.getForEntity("/api/event/all", String.class);
        assertThat(fact.getBody()).contains("JavaDay");
    }
}
