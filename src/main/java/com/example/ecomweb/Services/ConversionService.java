package com.example.ecomweb.Services;

import org.springframework.stereotype.Service;

@Service
public class ConversionService {

    public int convertStringToInt(String value) {
        try {
            return Integer.parseInt(value.replaceAll("[^\\d]", ""));
        } catch (NumberFormatException e) {
            // Handle parsing error
            return 0; // or throw an exception
        }
    }
}
