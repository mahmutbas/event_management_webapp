package com.iyzico.event.util;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by TCMBAS on 27/04/2017.
 */
public class CustomInterceptor extends EmptyInterceptor
{
    @Override
    public boolean onSave(
            Object entity,
            Serializable id,
            Object[] state,
            String[] propertyNames,
            Type[] types)
    {
        setPropertyState(state, propertyNames, "createTime", new Date());
        return true;
    }

    private void setPropertyState(Object[] propertyStates, String[] propertyNames, String propertyName, Object propertyState)
    {
        for (int i = 0; i < propertyNames.length; i++)
        {
            if (propertyName.equals(propertyNames[i]))
            {
                propertyStates[i] = propertyState;
                return;
            }
        }
    }
}
