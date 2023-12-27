package com.abdisadykov.cryptocurrencymicroservice.controller;

import com.abdisadykov.cryptocurrencymicroservice.dto.CurrencyConversionResponse;
import com.abdisadykov.cryptocurrencymicroservice.service.CurrencyExchangeService;
import com.abdisadykov.cryptocurrencymicroservice.service.OperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/convert")
@RequiredArgsConstructor
public class CurrencyExchangeController {

    private final CurrencyExchangeService exchangeService;
    private final OperationService operationService;

    @Value("${api.path}")
    private String apiUrl;


    @GetMapping
    public ResponseEntity<Double> currencyConversion(@RequestParam(name = "from", defaultValue = "EUR") String from,
                                                     @RequestParam(name = "to") String to,
                                                     @RequestParam(name = "amount", defaultValue = "1") String amount
                                                     ) {
        RestTemplate restTemplate = new RestTemplate();
        CurrencyConversionResponse response = restTemplate.getForObject(apiUrl, CurrencyConversionResponse.class);

        if (response != null && response.isSuccess() && response.getRates() != null) {
            Double rate = response.getRates().get(to);
            Double rateFrom = response.getRates().get(from);
            Double rateEuroToKzt = response.getRates().get("KZT");
            Double result;
            if (from.equals("EUR")) {
                result = exchangeService.convertFromEurToOtherCurrency(rate, amount);
                operationService.createOperation(from, to, result);
                return ResponseEntity.ok(result);
            }
            if (from.equals("KZT")) {
                result = exchangeService.convertFromKztToOtherCurrency(rateEuroToKzt, rate, amount);
                operationService.createOperation(from, to, result);
                return ResponseEntity.ok(result);
            }if (to.equals("KZT")) {
                result = exchangeService.convertOtherCurrencyToKzt(rateFrom, rateEuroToKzt, amount);
                operationService.createOperation(from, to, result);
                return ResponseEntity.ok(result);
            }
        } else {

            return ResponseEntity.status(500).body(null);
        }
        return null;
    }
}
