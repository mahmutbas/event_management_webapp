package com.iyzico.event.webservice;

import com.google.common.collect.ImmutableMap;
import com.iyzico.event.configuration.IyzipayApiProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * Created by TCMBAS on 04/05/2017.
 */
public abstract class BaseWebService
{

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
}
