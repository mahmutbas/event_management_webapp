package com.iyzico.event.model.dto;

import com.iyzico.event.model.dto.enums.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by TCMBAS on 05/05/2017.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class CheckoutResultDTOTest
{
    private CheckoutResultDTO checkoutResult;

    @Test
    public void testCheckoutResultCreateNewObject() throws Exception
    {
        checkoutResult = new CheckoutResultDTO(Result.FAILURE, "");
        assertNotNull(checkoutResult);
    }

    @Test(expected = NullPointerException.class)
    public void testCheckoutResultNullThrowsException() throws Exception
    {
        checkoutResult = new CheckoutResultDTO(null, "");
    }

    @Test
    public void testEventCreateEventSuccessfuly()
    {
        checkoutResult = new CheckoutResultDTO(Result.FAILURE, "mesaj");
        assertEquals("mesaj", checkoutResult.getResultDescription());
        assertEquals(Result.FAILURE, checkoutResult.getResult());

        checkoutResult.setResultDescription("Mesajımız");
        checkoutResult.setResult(Result.SUCCESS);
        assertEquals("Mesajımız", checkoutResult.getResultDescription());
        assertEquals(Result.SUCCESS, checkoutResult.getResult());
    }
}
