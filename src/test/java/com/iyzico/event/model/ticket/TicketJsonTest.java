package com.iyzico.event.model.ticket;

import com.iyzico.event.model.ticket.enums.PeriodType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by TCMBAS on 03/05/2017.
 */
@RunWith(SpringRunner.class)
@JsonTest
public class TicketJsonTest
{
    @Autowired
    private JacksonTester<Ticket> json;

    @Test
    public void serializeJson() throws Exception
    {
        Ticket ticket = new Ticket(LocalDate.of(2018, Month.FEBRUARY, 7), LocalDate.of(2018, Month.AUGUST, 6), PeriodType.LAGGARD, BigDecimal.TEN);
        assertThat(this.json.write(ticket)).isEqualTo(new File(this.getClass().getClassLoader().getResource("ticket.json").getFile()));
        assertThat(this.json.write(ticket)).isEqualToJson(new File(this.getClass().getClassLoader().getResource("ticket.json").getFile()));
        assertThat(this.json.write(ticket)).hasJsonPathStringValue("@.endDate");
        assertThat(this.json.write(ticket)).extractingJsonPathStringValue("@.periodType").isEqualTo(PeriodType.LAGGARD.toString());
    }

    @Test
    public void deserializeJson() throws Exception
    {
        String content = "{\"id\":null,\"createTime\":null,\"deleted\":\"0\",\"startDate\":\"2018-02-07\",\"endDate\":\"2018-08-06\",\"periodType\":\"LAGGARD\",\"price\":10,\"event\":null}";
        assertThat(this.json.parse(content)).isEqualTo(new Ticket(LocalDate.of(2018, Month.FEBRUARY, 7), LocalDate.of(2018, Month.AUGUST, 6), PeriodType.LAGGARD, BigDecimal.TEN));
        assertThat(this.json.parseObject(content).getStartDate()).isEqualTo(LocalDate.of(2018, Month.FEBRUARY, 07));
    }
}
