package com.iyzico.event.dao;

import com.iyzico.event.dao.speaker.SpeakerRepository;
import com.iyzico.event.model.speaker.Speaker;
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
 * Created by TCMBAS on 30/04/2017.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class SpeakerRepositoryTest
{
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SpeakerRepository speakerRepository;

    @Test
    public void testPersistNewObject()
    {
        this.entityManager.persist(new Speaker("Mahmut Bas", "Test Driven Development"));
        Speaker speaker = testFindByFullName("Mahmut Bas");
        assertEquals(speaker.getFullName(), "Mahmut Bas");
        assertNotNull(speaker.getId());
    }

    @Test
    public void testRepositoryCanNotBeEmpty()
    {
        List<Speaker> speakerList = speakerRepository.findAll();
        assertNotNull(speakerList);
        assertThat(speakerList.size()).isGreaterThan(0);
    }

    @Test
    public void testFindSpeakersByEventId()
    {
        List<Speaker> speakerList = speakerRepository.findAllSpeakersByEventId("6D499BD5-2825-4A10-976E-8850D23CA36C");
        assertNotNull(speakerList);
        assertThat(speakerList.size()).isGreaterThan(0);
    }

    private Speaker testFindByFullName(String fullName)
    {
        return speakerRepository.findByFullName(fullName);
    }
}
