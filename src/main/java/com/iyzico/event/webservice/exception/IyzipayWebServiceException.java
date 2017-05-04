package com.iyzico.event.webservice.exception;

/**
 * Created by TCMBAS on 04/05/2017.
 */
public class IyzipayWebServiceException extends Throwable
{
    public IyzipayWebServiceException(String message, Exception e)
    {
        super(message, e);
    }
}
