package com.shopsphere.demos;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.ResourceBundle;

public final class InternationalizationDemo {

    private InternationalizationDemo() {
    }

    public static void main(String[] args) {
        Locale[] locales = {
            Locale.forLanguageTag("en-IN"),
            Locale.forLanguageTag("hi-IN")
        };

        for (Locale locale : locales) {
            ResourceBundle messages = ResourceBundle.getBundle(
                "com.shopsphere.common.i18n.messages",
                locale
            );

            System.out.println("Locale: " + locale);
            System.out.println(messages.getString("home.title"));

            NumberFormat currencyFormat =
                NumberFormat.getCurrencyInstance(locale);
            System.out.println(
                currencyFormat.format(new BigDecimal("1299.50"))
            );

            DateTimeFormatter dateFormat =
                DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)
                    .withLocale(locale);
            System.out.println(dateFormat.format(LocalDate.now()));

            System.out.println();
        }
    }
}
