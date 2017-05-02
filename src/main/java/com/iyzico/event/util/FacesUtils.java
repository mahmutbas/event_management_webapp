package com.iyzico.event.util;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by TCMBAS on 02/05/2017.
 */
public class FacesUtils
{
    public static void addMessage(FacesMessage.Severity severity, String summary, String detail)
    {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
    }

    public static String getContextPath()
    {
        return ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getContextPath();
    }

    public static FacesContext getFacesContext()
    {
        return FacesContext.getCurrentInstance();
    }

    public static ExternalContext getExternalContext()
    {
        return getFacesContext().getExternalContext();
    }

    public static HttpServletRequest getHttpRequest()
    {
        return (HttpServletRequest)getExternalContext().getRequest();
    }

    public static void resetBean(String beanName)
    {
        Map<String,Object> viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap();
        if(viewMap.get(beanName)!=null)
        {
            getFacesContext().getELContext().getELResolver().setValue(getFacesContext().getELContext(), null, beanName, null);
        }
    }

    public static Object getBeanFromELContext(String beanName)
    {
        ELContext elContext = getFacesContext().getELContext();
        return elContext.getELResolver().getValue(elContext, null,  beanName);
    }

    public static UIComponent findComponentById(UIComponent uiComponent, String id)
    {
        if (id.equals(uiComponent.getId()))
            return uiComponent;

        Iterator<UIComponent> childUIComponentIterator = uiComponent.getFacetsAndChildren();
        while (childUIComponentIterator.hasNext())
        {
            UIComponent found = findComponentById(childUIComponentIterator.next(), id);
            if (found != null)
            {
                return found;
            }
        }
        return null;
    }

    public static void makeEnabledAndReadOnlyElement(UIComponent uiComponent)
    {
        uiComponent.setValueExpression("readOnly", getValueExpression(FacesContext.getCurrentInstance(), "#{false}"));
        uiComponent.setValueExpression("disabled", getValueExpression(FacesContext.getCurrentInstance(), "#{false}"));
    }

    public static void makeDisabledAndReadOnlyElement(UIComponent uiComponent)
    {
        uiComponent.setValueExpression("readOnly", getValueExpression(FacesContext.getCurrentInstance(), "#{true}"));
        uiComponent.setValueExpression("disabled", getValueExpression(FacesContext.getCurrentInstance(), "#{true}"));
    }

    private static ValueExpression getValueExpression(FacesContext facesContext, String name)
    {
        Application app = facesContext.getApplication();
        ExpressionFactory elFactory = app.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        return elFactory.createValueExpression(elContext, name, Object.class);
    }

}
