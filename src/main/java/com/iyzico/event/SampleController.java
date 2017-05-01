package com.iyzico.event;

import com.google.common.collect.Lists;
import com.iyzico.event.service.speaker.SpeakerService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import java.util.List;

/**
 *
 */

@Component
@RequestScoped
public class SampleController
{
    @Getter
    @Setter
    private String hello="Hello Sample Controller";

    @Getter
    @Setter
    public List<String> testStrings = Lists.newArrayList("Aaa","Bbb","Ccc","Ddd");

    @Setter
    @Getter
    @Autowired
    @Qualifier("speakerService")
    SpeakerService speakerService;

    public void testButton()
    {
        throw new RuntimeException("Sample Exception for error page");
    }

}
