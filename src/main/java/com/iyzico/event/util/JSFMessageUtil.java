package com.iyzico.event.util;

import com.google.common.collect.Maps;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.text.StrSubstitutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.UnexpectedRollbackException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Created by TCMBAS on 02/05/2017.
 */
@Component(value = "jsfMessageUtil")
@ApplicationScoped
public class JSFMessageUtil
{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    static Properties props = null;

    public void addMessage(FacesMessage.Severity severity, String summary, String detail)
    {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
    }

    public void handleException(String premiseErrorMessage, Throwable exception)
    {
        StringBuilder wholeExceptionMessage = new StringBuilder(premiseErrorMessage);
        String exceptionMessage = extractErrorMessageFromException(exception);
        boolean isErrorMessageNotBlankAndNotEqualsToNull = StringUtils.isNotBlank(premiseErrorMessage) && null != exceptionMessage;
        if (isErrorMessageNotBlankAndNotEqualsToNull)
        {
            wholeExceptionMessage.append(" (");
        }
        wholeExceptionMessage.append(exceptionMessage == null ? "" : exceptionMessage);
        if (isErrorMessageNotBlankAndNotEqualsToNull)
        {
            wholeExceptionMessage.append(")");
        }
        logger.info("hata oluştu {}-{}", wholeExceptionMessage.toString(), exception.getMessage());
        logger.info("{}", exception);
        addMessage(FacesMessage.SEVERITY_ERROR, wholeExceptionMessage.toString(), null);
    }

    private String extractErrorMessageFromException(Throwable exception)
    {
        String exceptionMessage = StringUtils.EMPTY;
        if (exception instanceof DataIntegrityViolationException)
        {
            exceptionMessage = "Hatalı veri, lütfen bilgileri kontrol ediniz";
        }
        else if (exception instanceof DuplicateKeyException)
        {
            exceptionMessage = "Çift kayıt hatası, lütfen bilgileri kontrol ediniz";
        }
        else if (exception instanceof UnexpectedRollbackException)
        {
            exceptionMessage = "Veritabanına bilgiler kaydedilemedi, lütfen sistem yöneticisi ile iletişime geçiniz";
        }
        else if (exception instanceof DataAccessException)
        {
            exceptionMessage = "Veritabanına ulaşılamadı, lütfen sistem yöneticisi ile iletişime geçiniz";
        }
        else if (exception instanceof JpaSystemException)
        {
            exceptionMessage = "Veritabanına ulaşılamadı, lütfen sistem yöneticisi ile iletişime geçiniz";
        }
        else
        {
            exceptionMessage = exception.getMessage();
        }
        return exceptionMessage;
    }

    public String findMessage(String key)
    {
        FacesContext fc = FacesContext.getCurrentInstance();
        String mb = fc.getApplication().getMessageBundle();
        ResourceBundle resourceBundle = ResourceBundle.getBundle(mb, fc.getViewRoot().getLocale());
        if (resourceBundle.containsKey(key))
        {
            return resourceBundle.getString(key);
        }
        else
        {
            resourceBundle = ResourceBundle.getBundle(mb);
            return resourceBundle.getString(key);
        }
    }

    public String findMessage(String key, Object... params)
    {
        Map<String, Object> valueMap = buildValueMap(params);
        String message = findMessage(key);
        return StrSubstitutor.replace(message, valueMap);
    }

    private Map<String, Object> buildValueMap(Object... params)
    {
        Map<String, Object> values = Maps.newHashMap();
        for (int i = 0; i < params.length; i++)
        {
            values.put(String.valueOf(i), params[i]);
        }
        return values;
    }

    public static String readPropertyFromLoginInfoProperties(String key) throws IOException
    {
        if (props != null)
        {
            return props.getProperty(key, "");
        }

        //Prevent incorrect lazy initialization and update of static field
        synchronized (Properties.class)
        {
            if (props == null)
            {
                InputStream propFile = null;
                try
                {
                    propFile = JSFMessageUtil.class.getClassLoader().getResourceAsStream("loginInfo.properties");

                    props = new Properties();
                    props.load(new InputStreamReader(propFile, "UTF-8"));

                }
                catch (IOException e)
                {
                    throw e;
                }
                finally
                {
                    if (propFile != null)
                    {
                        propFile.close();
                    }
                }
            }
        }
        return props.getProperty(key, "");
    }
}
