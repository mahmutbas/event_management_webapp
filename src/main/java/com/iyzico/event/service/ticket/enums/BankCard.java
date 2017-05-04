package com.iyzico.event.service.ticket.enums;

import lombok.Getter;

/**
 * Created by TCMBAS on 05/05/2017.
 */
public enum BankCard
{
    HALK_BANK("Halk Bankası", 12l),
    AKBANK("Akbank", 46l),
    DENIZBANK("Denizbank", 134l),
    FINANSBANK("Finansbank", 111l),
    GARANTI_BANKASI("Garanti Bankası", 62l),
    HSBC("HSBC", 123L),
    IS_BANKASI("İş Bankası", 64L),
    VAKIFBANK("Vakıfbank", 15L),
    YAPIKREDI("Yapı Kredi Bankası", 67L);

    @Getter
    private String bankName;
    @Getter
    private Long bankCode;

    private BankCard(String bankName, Long bankCode)
    {
        this.bankName = bankName;
        this.bankCode = bankCode;
    }

}
