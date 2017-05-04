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

import javax.faces.application.FacesMessage;
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

    @Setter
    @Autowired
    @Qualifier("eventService")
    private EventService eventService;

    @Getter
    private Event event;

    @Setter
    @Getter
    private String eventName;

    @Setter
    @Getter
    private Date eventDate;

    public void init() throws IOException
    {
        event = eventService.findByEventName(StringUtils.isEmpty(this.eventName) ? DEFAULT_EVENT_NAME : this.eventName);
        //todo type="localdate" will be in xhtml page with jsf 2.3
        eventDate = Date.from(event.getEventDate().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        eventName = null;
      //  jsfMessageUtil.addMessage(FacesMessage.SEVERITY_WARN,"Lütfen işlem yapmak için bir ilişki seçiniz","");
        jsfMessageUtil.addMessage(FacesMessage.SEVERITY_ERROR,"Hatalı giriş","");
        if (event == null)
        {
            FacesUtils.resetBean("eventController");
            FacesUtils.getExternalContext().redirect("/error/errorOccured.xhtml?faces-redirect=true");
        }
    }
}
