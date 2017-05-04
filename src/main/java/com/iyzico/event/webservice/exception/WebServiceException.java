package com.iyzico.event.webservice.exception;

/**
 * Created by TCMBAS on 04/05/2017.
 */
public class WebServiceException extends Throwable
{
    public WebServiceException(String message, Exception e)
    {
        super(message, e);
    }
}
