package com.iyzico.event.api.event;

import com.google.common.collect.Lists;
import com.iyzico.event.api.event.impl.EventRestControllerImpl;
import com.iyzico.event.model.event.Event;
import com.iyzico.event.service.event.EventService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.ws.rs.core.MediaType;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Created by TCMBAS on 30/04/2017.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(EventRestControllerImpl.class)
public class EventRestMvcTest
{
    @Autowired
    private MockMvc mvc;

    @MockBean(name = "eventService")
    private EventService eventService;

    @Test
    public void testFindAllEventsCheckExpectedJsonFromApi() throws Exception
    {
        given(this.eventService.findAllEvents()).willReturn(Lists.newArrayList(new Event("JavaDay")));
        this.mvc.perform(get("/api/event/all").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"id\":null,\"createTime\":null,\"deleted\":\"0\",\"eventName\":\"JavaDay\",\"eventDate\":null,\"addressText\":null,\"addressLatitude\":null,\"addressLongitude\":null,\"days\":null,\"aboutEvent\":null,\"aboutHost\":null}]"));
    }
}
