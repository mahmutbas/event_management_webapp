package com.iyzico.event.controller.speaker;

import com.iyzico.event.controller.BaseController;
import com.iyzico.event.service.event.EventService;
import com.iyzico.event.service.speaker.SpeakerService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.faces.bean.RequestScoped;

/**
 * Created by TCMBAS on 02/05/2017.
 */
@Controller(value = "speakerController")
@RequestScoped
public class SpeakerController extends BaseController
{
    @Setter
    @Getter
    @Autowired
    @Qualifier("eventService")
    private EventService eventService;

    @Setter
    @Autowired
    @Qualifier("speakerService")
    private SpeakerService speakerService;

}
