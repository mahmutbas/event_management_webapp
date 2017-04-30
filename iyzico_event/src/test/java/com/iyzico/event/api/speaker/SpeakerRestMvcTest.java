package com.iyzico.event.api.speaker;

import com.google.common.collect.Lists;
import com.iyzico.event.api.speaker.impl.SpeakerRestControllerImpl;
import com.iyzico.event.model.speaker.Speaker;
import com.iyzico.event.service.speaker.SpeakerService;
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
@WebMvcTest(SpeakerRestControllerImpl.class)
public class SpeakerRestMvcTest
{
    @Autowired
    private MockMvc mvc;

    @MockBean(name = "speakerService")
    private SpeakerService speakerService;

    @Test
    public void testFindAllSpeakersByEventIdCheckExpectedJsonFromApi() throws Exception
    {
        given(this.speakerService.findAllSpeakersByEventId("FFFE7232-2D23-11E7-93AE-92361F002671")).willReturn(Lists.newArrayList(new Speaker("Mahmut Bas", "Test Driven Development")));
        this.mvc.perform(get("/api/speaker/byeventid?eventId={eventId}", "FFFE7232-2D23-11E7-93AE-92361F002671").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"id\":null,\"createTime\":null,\"deleted\":\"0\",\"fullName\":\"Mahmut Bas\",\"photo\":null,\"shortDefination\":null,\"company\":null,\"subject\":\"Test Driven Development\",\"event\":null}]"));
    }
}
