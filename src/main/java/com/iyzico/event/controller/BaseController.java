package com.iyzico.event.controller;

import com.iyzico.event.util.JSFMessageUtil;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.Serializable;

/**
 * Created by TCMBAS on 02/05/2017.
 */
public abstract class BaseController implements Serializable
{
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Setter
    @Qualifier("jsfMessageUtil")
    @Autowired
    protected JSFMessageUtil jsfMessageUtil;

}
