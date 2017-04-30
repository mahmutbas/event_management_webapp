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

    public void setAddressLatitude(Double addressLatitude)
    {
        if (addressLatitude != null)
        {
            Assert.isTrue(addressLatitude <= 90.0 && addressLatitude >= -90.0, "");
        }
        this.addressLatitude = addressLatitude;
    }

    public void setAddressLongitude(Double addressLongitude)
    {
        if (addressLongitude != null)
        {
            Assert.isTrue(addressLongitude <= 180.0 && addressLongitude >= -180.0, "");
        }
        this.addressLongitude = addressLongitude;
    }

    public Event(String eventName)
    {
        this.eventName = eventName;
        Assert.notNull(eventName, "eventName must not be null");
        Assert.hasLength(eventName, "eventName can not be empty");
    }
}
