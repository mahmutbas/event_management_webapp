package com.iyzico.event.controller.speaker;

import com.iyzico.event.controller.BaseController;
import com.iyzico.event.model.event.Event;
import com.iyzico.event.service.event.EventService;
import com.iyzico.event.service.speaker.SpeakerService;
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
    private final EventService eventService;

    private final SpeakerService speakerService;

    @Autowired
    public SpeakerController(@Qualifier("eventService") EventService eventService, @Qualifier("speakerService") SpeakerService speakerService)
    {
        this.eventService = eventService;
        this.speakerService = speakerService;
    }
}
