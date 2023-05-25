package com.example.football.util;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateAdapter extends XmlAdapter<String, LocalDate> {

    private static final String CUSTOM_DATE_FORMAT = "dd/MM/yyyy";

    @Override
    public LocalDate unmarshal(String stringDate) throws Exception {
        return LocalDate.parse(stringDate, DateTimeFormatter.ofPattern(CUSTOM_DATE_FORMAT));
    }

    @Override
    public String marshal(LocalDate date) throws Exception {
        return date.format(DateTimeFormatter.ofPattern(CUSTOM_DATE_FORMAT));
    }
}
