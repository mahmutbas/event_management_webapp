package com.iyzico.event.dao.ticket;

import com.iyzico.event.model.ticket.SpecialDiscount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

/**
 * Created by TCMBAS on 04/05/2017.
 */
@Repository("specialDiscountRepository")
public interface SpecialDiscountRepository extends JpaRepository<SpecialDiscount, String>
{
    @Query(value = "select specialDiscount from SpecialDiscount specialDiscount where specialDiscount.code=:code and :registrationDate between specialDiscount.specialDate and specialDiscount.finishDate")
    SpecialDiscount findByDiscountCodeAndDate(@Param("code") String code, @Param("registrationDate") LocalDate registrationDate);

}
