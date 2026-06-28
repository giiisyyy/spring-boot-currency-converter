package com.example.currency;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

@Controller
public class CurrencyController {

    @Value("${currency.api.url}")
    private String apiUrl;

    private final MessageSource messageSource;
    private final RestTemplate restTemplate;

    public CurrencyController(MessageSource messageSource, RestTemplate restTemplate) {
        this.messageSource = messageSource;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/")
    public String index(Model model) {
        try {
            Map<String, Object> ratesResponse = restTemplate.getForObject(apiUrl, Map.class);
            Map<String, Object> rates = (Map<String, Object>) ratesResponse.get("rates");
            model.addAttribute("currencies", new TreeMap<>(rates).keySet());
        } catch (Exception e) {
            model.addAttribute("currencies", java.util.List.of("EUR", "USD", "MXN", "GBP"));
        }
        return "index";
    }

    @GetMapping("/convert")
    public String convert(@RequestParam double amount,
                          @RequestParam String from,
                          @RequestParam String to,
                          @RequestParam(defaultValue = "ca") String lang,
                          Model model) {
        try {
            Map<String, Object> response = restTemplate.getForObject(apiUrl, Map.class);
            Map<String, Object> rates = (Map<String, Object>) response.get("rates");

            double rateFrom = ((Number) rates.get(from)).doubleValue();
            double rateTo = ((Number) rates.get(to)).doubleValue();
            double result = (amount / rateFrom) * rateTo;

            Locale locale = new Locale(lang);

            model.addAttribute("amount", amount);
            model.addAttribute("from", from);
            model.addAttribute("to", to);
            model.addAttribute("result", String.format("%.2f", result));
            model.addAttribute("lang", lang);
            model.addAttribute("resultTitle", messageSource.getMessage("result.title", null, locale));
            model.addAttribute("backText", messageSource.getMessage("result.back", null, locale));

            return "result";

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", true);
            return "result";
        }
    }
}