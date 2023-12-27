package com.abdisadykov.cryptocurrencymicroservice.service;

import org.springframework.stereotype.Service;

@Service
public class CurrencyExchangeService {


    public Double convertFromEurToOtherCurrency(Double rate, String amount) {
        return rate*Integer.parseInt(amount);
    }

    public Double convertFromKztToOtherCurrency(Double rateEuroToKzt, Double rateEuroToOther, String amount) {
        Double kztRate = 1/rateEuroToKzt;
        return kztRate*rateEuroToOther*Integer.parseInt(amount);
    }

    public Double convertOtherCurrencyToKzt(Double rate, Double rateEuroToKzt, String amount) {
        return (1/rate)*rateEuroToKzt*Integer.parseInt(amount);
    }
}
