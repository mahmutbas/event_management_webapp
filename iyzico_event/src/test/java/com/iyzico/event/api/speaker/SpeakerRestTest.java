package com.iyzico.event.api.speaker;

import com.google.common.collect.Lists;
import com.iyzico.event.model.speaker.Speaker;
import com.iyzico.event.service.speaker.SpeakerService;
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
 * Created by TCMBAS on 30/04/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpeakerRestTest
{
    @Autowired
    private TestRestTemplate template;

    @MockBean(name = "speakerService")
    private SpeakerService speakerService;

    @Test
    public void testFindAllSpeakersByEventId() throws Exception
    {
        given(speakerService.findAllSpeakersByEventId("6D499BD5-2825-4A10-976E-8850D23CA36C")).willReturn(Lists.newArrayList(new Speaker("Mahmut Bas", "Test Driven Development")));
        ResponseEntity<String> fact = template.getForEntity("/api/speaker/byeventid?eventId={eventId}", String.class, "6D499BD5-2825-4A10-976E-8850D23CA36C");
        assertThat(fact.getBody()).contains("Test Driven Development");
    }
}
