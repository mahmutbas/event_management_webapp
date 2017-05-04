package com.iyzico.event.model.ticket;

import com.iyzico.event.model.AbstractEntity;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by TCMBAS on 04/05/2017.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class SpecialDiscountTest
{
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private SpecialDiscount specialDiscount;

    @Test
    public void testSpecialDiscountCreateNewObject() throws Exception
    {
        specialDiscount = new SpecialDiscount("BECK", LocalDate.now(), LocalDate.now(), 10);
        assertNotNull(specialDiscount);
    }

    @Test
    public void testCreateSpecialDiscountSuccessfuly()
    {
        specialDiscount = new SpecialDiscount("BECK", LocalDate.of(2018, Month.APRIL, 28), LocalDate.of(2018, Month.APRIL, 29), 10);
        specialDiscount.setSpecialDay("Kent Beck Dogumgunu");
        assertEquals("BECK", specialDiscount.getCode());
        assertEquals(LocalDate.of(2018, Month.APRIL, 28), specialDiscount.getSpecialDate());
        assertEquals(LocalDate.of(2018, Month.APRIL, 29), specialDiscount.getFinishDate());
        assertEquals("Kent Beck Dogumgunu", specialDiscount.getSpecialDay());
        assertNotNull(specialDiscount.getInternalId());
        assertEquals(specialDiscount.getInternalId().length(), 36);
        Assert.assertEquals(specialDiscount.getDeleted(), AbstractEntity.DEFAULT_DELETED_VALUE);
    }

    @Test
    public void testOnConstructorPercentageNullWillThrowsException() throws Exception
    {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Percentage can not be null!");
        specialDiscount = new SpecialDiscount("BECK", LocalDate.now(), LocalDate.now(), null);
    }

    @Test
    public void testOnConstructorPercentageOutOfLimitsWillThrowsException() throws Exception
    {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Percentage must be between 0 and 100!");
        specialDiscount = new SpecialDiscount("BECK", LocalDate.now(), LocalDate.now(), -10);
    }

    @Test
    public void testPercentageSetterOutOfLimitsWillThrowsException() throws Exception
    {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Percentage must be between 0 and 100!");
        specialDiscount = new SpecialDiscount();
        specialDiscount.setCode("BECK");
        specialDiscount.setSpecialDate(LocalDate.of(2018, Month.APRIL, 30));
        specialDiscount.setFinishDate(LocalDate.of(2018, Month.APRIL, 30));
        specialDiscount.setSpecialDay("Kent Beck Dogum gunu");
        specialDiscount.setPercentage(120);
    }
}
