package com.iyzico.event.model.dto;

import com.iyzico.event.model.dto.enums.Result;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by TCMBAS on 04/05/2017.
 */
@Data
public class CheckoutResultDTO
{
    private Result result;
    private String resultDescription;

    public CheckoutResultDTO(Result result, String resultDescription)
    {
        this.result = result;
        this.resultDescription = resultDescription;
    }
}
