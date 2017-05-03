package com.iyzico.event.model.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.iyzico.event.model.AbstractEntity;
import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

import static com.google.common.base.Preconditions.checkArgument;

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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate eventDate;
    private String addressText;
    private Double addressLatitude;
    private Double addressLongitude;
    private Integer days;
    @Column(length = 500)
    private String aboutEvent;
    @Column(length = 500)
    private String aboutHost;

    public Event()
    {
    }

    public void setAddressLatitude(Double addressLatitude)
    {
        if (addressLatitude != null)
        {
            checkArgument(addressLatitude <= 90.0 && addressLatitude >= -90.0);
        }
        this.addressLatitude = addressLatitude;
    }

    public void setAddressLongitude(Double addressLongitude)
    {
        if (addressLongitude != null)
        {
            checkArgument(addressLongitude <= 180.0 && addressLongitude >= -180.0);
        }
        this.addressLongitude = addressLongitude;
    }

    public Event(String eventName)
    {
        this.eventName = eventName;
        checkArgument(StringUtils.isNotEmpty(eventName), "eventName can not be empty");
    }

    public Event(String eventName, LocalDate eventDate)
    {
        this.eventName = eventName;
        this.eventDate = eventDate;
        checkArgument(StringUtils.isNotEmpty(eventName), "eventName can not be empty");
    }
}
