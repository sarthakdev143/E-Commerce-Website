package com.example.ecomweb.Services;

import java.text.DecimalFormat;

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

    public String formatCurrency(String value) {
        try {
            // Convert string to integer
            int intValue = Integer.parseInt(value.replaceAll("[^\\d]", ""));

            // Format integer to include currency symbol and comma
            DecimalFormat decimalFormat = new DecimalFormat("₹#,###");
            return decimalFormat.format(intValue);
        } catch (NumberFormatException e) {
            // Handle parsing error
            return "₹0"; // or throw an exception
        }
    }
}
