package com.iyzico.event.model.event;

import com.iyzico.event.model.AbstractEntity;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;
import org.springframework.util.Assert;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * Created by TCMBAS on 26/04/2017.
 */
@Entity
@Data
@Table(name = "T_EVENT")
@DynamicUpdate
@Where(clause = "DELETED = '0'")
public class Event extends AbstractEntity
{
    private String eventName;
    @Temporal(TemporalType.DATE)
    private Date eventDate;
    private String addressText;
    private Double addressLatitude;
    private Double addressLongitude;
    private Integer days;
    private Integer trackCount;

    public Event()
    {
    }

    public Event(String eventName)
    {
        this.eventName = eventName;
        Assert.hasLength(eventName, "eventName can not be empty");
        Assert.notNull(eventName, "eventName must not be null");
    }
}
