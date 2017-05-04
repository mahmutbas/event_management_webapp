package com.iyzico.event.webservice.iyzipay;

import com.iyzico.event.configuration.IyzipayApiProperties;
import com.iyzico.event.model.dto.BinNumberDTO;
import com.iyzico.event.webservice.BaseWebService;
import com.iyzico.event.webservice.exception.WebServiceException;
import com.iyzipay.Options;
import com.iyzipay.model.BinNumber;
import com.iyzipay.model.Locale;
import com.iyzipay.request.RetrieveBinNumberRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by TCMBAS on 04/05/2017.
 */
@Service
public class InquireBinResultWebService extends BaseWebService
{
    public static final String SUCCESS = "success";
    private final Options options;

    @Autowired
    public InquireBinResultWebService(IyzipayApiProperties iyzipayApiProperties)
    {
        this.options = new Options();
        options.setApiKey(iyzipayApiProperties.getApiKey());
        options.setSecretKey(iyzipayApiProperties.getApiSecret());
        options.setBaseUrl(iyzipayApiProperties.getBaseUrl());
    }

    public BinNumberDTO retrieveBinNumber(String cardNumber) throws WebServiceException
    {
        RetrieveBinNumberRequest request = new RetrieveBinNumberRequest();
        request.setLocale(Locale.TR.getValue());
        request.setConversationId("123456789");
        request.setBinNumber(cardNumber.substring(0, 6));

        BinNumber binNumber;
        try
        {
            binNumber = BinNumber.retrieve(request, options);
        }
        catch (Exception e)
        {
            logger.error("Exception occured while calling web service", e);
            throw new WebServiceException("Exception occured while calling web service", e);
        }
        if (SUCCESS.equals(binNumber.getStatus()))
        {
            return new BinNumberDTO(binNumber.getBankCode(), binNumber.getCardType());
        }
        return null;
    }
}
