package com.iyzico.event.model.event;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by TCMBAS on 29/04/2017.
 */
@RunWith(SpringRunner.class)
@JsonTest
public class EventJsonTest
{
    @Autowired
    private JacksonTester<Event> json;

    @Test
    public void serializeJson() throws Exception
    {
        Event event = new Event("JavaDay Frankfurt");
        assertThat(this.json.write(event)).isEqualTo(new File(this.getClass().getClassLoader().getResource("event.json").getFile()));
        assertThat(this.json.write(event)).isEqualToJson(new File(this.getClass().getClassLoader().getResource("event.json").getFile()));
        assertThat(this.json.write(event)).hasJsonPathStringValue("@.eventName");
        assertThat(this.json.write(event)).extractingJsonPathStringValue("@.eventName").isEqualTo("JavaDay Frankfurt");
    }

    @Test
    public void deserializeJson() throws Exception
    {
        String content = "{\"id\":null,\"createTime\":null,\"deleted\":\"0\",\"eventName\":\"JavaDay Frankfurt\",\"eventDate\":null,\"addressText\":null,\"addressLatitude\":null,\"addressLongitude\":null,\"days\":null,\"trackCount\":null}";
        assertThat(this.json.parse(content)).isEqualTo(new Event("JavaDay Frankfurt"));
        assertThat(this.json.parseObject(content).getEventName()).isEqualTo("JavaDay Frankfurt");
    }
}
