package com.iyzico.event.model.speaker;

import com.iyzico.event.model.AbstractEntity;
import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by TCMBAS on 30/04/2017.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class SpeakerTest
{
    private Speaker speaker;

    @Test
    public void testSpeakerCreateNewObject() throws Exception
    {
        speaker = new Speaker("Trisha Gee", "Java 9");
        assertNotNull(speaker);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorMandotoryNullThrowsException() throws Exception
    {
        speaker = new Speaker(null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorMandotoryEmptyThrowsException() throws Exception
    {
        speaker = new Speaker(StringUtils.EMPTY, StringUtils.EMPTY);
    }

    @Test
    public void testSpeakerCreateEventSuccessfuly()
    {
        speaker = new Speaker("Trisha Gee", "Java 9");
        assertEquals("Trisha Gee", speaker.getFullName());
        assertEquals("Java 9", speaker.getSubject());
        assertNotNull(speaker.getInternalId());
        assertEquals(speaker.getInternalId().length(), 36);
        Assert.assertEquals(speaker.getDeleted(), AbstractEntity.DEFAULT_DELETED_VALUE);
    }
}
