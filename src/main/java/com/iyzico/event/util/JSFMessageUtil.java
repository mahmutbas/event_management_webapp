package com.iyzico.event.util;

import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.context.FacesContext;

/**
 * Created by TCMBAS on 02/05/2017.
 */
@Component(value = "jsfMessageUtil")
@ApplicationScoped
public class JSFMessageUtil
{
    public void addMessage(FacesMessage.Severity severity, String summary, String detail)
    {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
    }
}
