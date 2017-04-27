package com.iyzico.event.util;


import com.iyzico.event.model.AbstractEntity;
import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.UUIDGenerator;

import java.io.Serializable;

/**
 * Created by TCMBAS on 25/04/2017.
 */
public class UpperCaseUUIDGenerator extends UUIDGenerator
{
    @Override
    public Serializable generate(SessionImplementor session, Object object) throws HibernateException
    {
        if (object instanceof AbstractEntity) {
            AbstractEntity abstractEntity = (AbstractEntity)object;
            if (abstractEntity.getId() != null) {
                return abstractEntity.getId();
            }
        }
        Serializable generated = super.generate(session, object);
        return StringUtils.upperCase((String) generated);
    }
}

