package com.iyzico.event.service;

import com.google.common.collect.Lists;
import com.iyzico.event.dao.speaker.SpeakerRepository;
import com.iyzico.event.model.speaker.Speaker;
import com.iyzico.event.service.speaker.SpeakerService;
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
 * Created by TCMBAS on 30/04/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpeakerServiceTest
{
    @MockBean
    private SpeakerRepository speakerRepositoryMock;

    @Autowired
    @Qualifier("speakerService")
    public SpeakerService speakerService;

    @Before
    public void setUp()
    {
        given(this.speakerRepositoryMock.findByFullName("Mahmut Bas")).willReturn(new Speaker("Mahmut Bas", "Test Driven Development"));
        given(this.speakerRepositoryMock.findAll()).willReturn(Lists.newArrayList(new Speaker("Mahmut Bas", "Test Driven Development")));
        given(this.speakerRepositoryMock.findAllSpeakersByEventId("6D499BD5-2825-4A10-976E-8850D23CA36C")).willReturn(Lists.newArrayList(new Speaker("Mahmut Bas", "Test Driven Development")));
    }

    @Test
    public void testFindByEventName()
    {
        Speaker speaker = speakerService.findByFullName("Mahmut Bas");
        assertThat(speaker.getSubject()).isEqualTo("Test Driven Development");
    }

    @Test
    public void testFindAllEvents()
    {
        List<Speaker> speakers = speakerService.findAllSpeakers();
        Assert.assertNotNull(speakers);
        assertThat(speakers.iterator().next().getSubject()).isEqualTo("Test Driven Development");
    }

    @Test
    public void testFindAllSpeakersByEventId()
    {
        List<Speaker> speakers = speakerService.findAllSpeakersByEventId("6D499BD5-2825-4A10-976E-8850D23CA36C");
        Assert.assertNotNull(speakers);
        assertThat(speakers.iterator().next().getSubject()).isEqualTo("Test Driven Development");
    }
}
