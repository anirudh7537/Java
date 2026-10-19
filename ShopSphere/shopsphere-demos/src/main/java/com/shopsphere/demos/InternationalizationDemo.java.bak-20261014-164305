
$root = "C:\Users\aniru\Desktop\JAVA projects\ShopSphere"

Get-ChildItem $root -Force -Recurse -File |
    Where-Object {
        $_.FullName -notmatch '\\(target|\.git)\\' -and
        ($_.Name -eq ".env" -or $_.Name -like ".env.*")
    } |
    ForEach-Object {
        $_.FullName.Replace($root, ".")
    }

    package com.shopsphere.demos;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

public final class InternationalizationDemo {
    private InternationalizationDemo() { }
    public static void main(String[] args) {
        for (Locale locale : new Locale[]{Locale.forLanguageTag("en-IN"), Locale.forLanguageTag("hi-IN")}) {
            ResourceBundle messages = ResourceBundle.getBundle("com.shopsphere.common.i18n.messages", locale);
            System.out.println(messages.getString("home.title"));
            System.out.println(NumberFormat.getCurrencyInstance(locale).format(new BigDecimal("1299.50")));
            System.out.println(DateTimeFormatter.ofLocalizedDate(java.time.format.FormatStyle.LONG).withLocale(locale).format(LocalDate.now()));
        }
    }
}
