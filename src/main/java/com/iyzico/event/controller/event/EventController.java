package com.iyzico.event.controller.event;

import com.iyzico.event.controller.BaseController;
import com.iyzico.event.model.event.Event;
import com.iyzico.event.service.event.EventService;
import com.iyzico.event.util.FacesUtils;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.faces.bean.RequestScoped;
import java.io.IOException;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by TCMBAS on 02/05/2017.
 */
@Controller(value = "eventController")
@RequestScoped
public class EventController extends BaseController
{
    public static final String DEFAULT_EVENT_NAME = "JavaDay";

    private final EventService eventService;

    @Getter
    private Event event;

    @Setter
    @Getter
    private String eventName;

    @Setter
    @Getter
    private Date eventDate;

    @Autowired
    public EventController(@Qualifier("eventService") EventService eventService)
    {
        this.eventService = eventService;
    }

    public void init() throws IOException
    {
        event = eventService.findByEventName(StringUtils.isEmpty(this.eventName) ? DEFAULT_EVENT_NAME : this.eventName);
        //type="localdate" will be in xhtml page with jsf 2.3
        eventDate = Date.from(event.getEventDate().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        eventName = null;
        if (event == null)
        {
            FacesUtils.resetBean("eventController");
            FacesUtils.getExternalContext().redirect("/error/errorOccured.xhtml?faces-redirect=true");
        }
    }
}
