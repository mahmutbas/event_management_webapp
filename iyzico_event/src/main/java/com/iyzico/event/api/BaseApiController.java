package com.iyzico.event.api;

import com.iyzico.event.util.ServiceLocator;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created by TCMBAS on 27/04/2017.
 */
public abstract class BaseApiController
{
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Setter
    @Autowired
    @Qualifier("serviceLocator")
    protected ServiceLocator serviceLocator;
}
