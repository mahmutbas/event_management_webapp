package com.iyzico.event.webservice.iyzipay;

import com.iyzico.event.model.dto.BinNumberDTO;
import com.iyzico.event.webservice.exception.WebServiceException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by TCMBAS on 04/05/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class InquireBinResultWebServiceTest
{
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Autowired
    public InquireBinResultWebService inquireBinResultWebService;

    @Test
    public void testRetrieveBinNumberwithValidCard() throws Exception, WebServiceException
    {
        BinNumberDTO binNumberDTO = inquireBinResultWebService.retrieveBinNumber("5400360000000003");
        Assert.assertNotNull(binNumberDTO);
        Assert.assertEquals(binNumberDTO.getBackCode(), Long.valueOf("62"));
        Assert.assertEquals(binNumberDTO.getCardType(), "CREDIT_CARD");
    }

    @Test
    public void testRetrieveBinNumberwithInvalidCard() throws Exception, WebServiceException
    {
        BinNumberDTO binNumberDTO = inquireBinResultWebService.retrieveBinNumber("5526080000000006");
        Assert.assertNotNull(binNumberDTO);
        Assert.assertEquals(binNumberDTO.getBackCode(), Long.valueOf("46"));
        Assert.assertEquals(binNumberDTO.getCardType(), "CREDIT_CARD");
    }

    @Test
    public void testRetrieveBinNumbershouldReturnNull() throws Exception, WebServiceException
    {
        BinNumberDTO binNumberDTO = inquireBinResultWebService.retrieveBinNumber("12312312313213");
        Assert.assertNull(binNumberDTO);
    }

    @Test
    public void testRetrieveBinNumbershouldThrowWebServiceException() throws Exception, WebServiceException
    {
        //todo web service exception yazılacak
//        thrown.expect(IyzipayWebServiceException.class);
//        thrown.expectMessage("Exception occured while calling web service");
//        BinNumberDTO binNumberDTO = inquireBinResultWebService.retrieveBinNumber("12312312313213");
    }

}