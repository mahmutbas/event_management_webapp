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
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;

/**
 * Created by TCMBAS on 02/05/2017.
 */
@Controller(value = "eventController")
@RequestScoped
public class EventController extends BaseController
{
    public static final String DEFAULT_EVENT_NAME = "JavaDay";

    @Setter
    @Autowired
    @Qualifier("eventService")
    private EventService eventService;

    @Getter
    private Event event;

    @Setter
    @Getter
    private String eventName;

    public void init() throws IOException
    {
        event = eventService.findByEventName(StringUtils.isEmpty(this.eventName) ? DEFAULT_EVENT_NAME : this.eventName);
        eventName = null;
        if (event == null)
        {
            FacesUtils.resetBean("eventController");
            FacesUtils.getExternalContext().redirect("/error/errorOccured.xhtml?faces-redirect=true");
        }
    }
}
