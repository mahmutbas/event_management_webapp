package com.iyzico.event.util;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.util.Map;

/**
 * Created by TCMBAS on 02/05/2017.
 */
public class FacesUtils
{
    public static FacesContext getFacesContext()
    {
        return FacesContext.getCurrentInstance();
    }

    public static ExternalContext getExternalContext()
    {
        return getFacesContext().getExternalContext();
    }

    public static void resetBean(String beanName)
    {
        Map<String, Object> viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap();
        if (viewMap.get(beanName) != null)
        {
            getFacesContext().getELContext().getELResolver().setValue(getFacesContext().getELContext(), null, beanName, null);
        }
    }
}
