package com.iyzico.event.model.speaker;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by TCMBAS on 30/04/2017.
 */
@RunWith(SpringRunner.class)
@JsonTest
public class SpeakerJsonTest
{
    @Autowired
    private JacksonTester<Speaker> json;

    @Test
    public void serializeJson() throws Exception
    {
        Speaker speaker = new Speaker("Trisha Gee", "Java 9");
        assertThat(this.json.write(speaker)).isEqualTo(new File(this.getClass().getClassLoader().getResource("speaker.json").getFile()));
        assertThat(this.json.write(speaker)).isEqualToJson(new File(this.getClass().getClassLoader().getResource("speaker.json").getFile()));
        assertThat(this.json.write(speaker)).hasJsonPathStringValue("@.fullName");
        assertThat(this.json.write(speaker)).extractingJsonPathStringValue("@.fullName").isEqualTo("Trisha Gee");
    }

    @Test
    public void deserializeJson() throws Exception
    {
        String content = "{\"id\":null,\"createTime\":null,\"deleted\":\"0\",\"fullName\":\"Trisha Gee\",\"photo\":null,\"shortDefination\":null,\"company\":null,\"subject\":\"Java 9\",\"event\":null}";
        assertThat(this.json.parse(content)).isEqualTo(new Speaker("Trisha Gee", "Java 9"));
        assertThat(this.json.parseObject(content).getFullName()).isEqualTo("Trisha Gee");
    }

}
