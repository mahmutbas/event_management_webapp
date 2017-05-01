package com.iyzico.event.model.speaker;

import com.iyzico.event.model.AbstractEntity;
import com.iyzico.event.model.event.Event;
import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;
import org.springframework.util.Assert;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by TCMBAS on 30/04/2017.
 */
@Entity
@Data
@Table(name = "T_SPEAKER")
@DynamicUpdate
@Where(clause = "DELETED = '0'")
public class Speaker extends AbstractEntity
{
    private String fullName;
    @Lob
    private String photo;
    private String shortDefination;
    private String company;
    private String subject;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EVENT_ID")
    private Event event;

    public Speaker()
    {
    }

    public Speaker(String fullName, String subject)
    {
        Assert.isTrue(StringUtils.isNotEmpty(fullName), "fullName can not be null or empty");
        Assert.isTrue(StringUtils.isNotEmpty(subject), "subject can not be null or empty");
        this.fullName = fullName;
        this.subject = subject;
    }

}
