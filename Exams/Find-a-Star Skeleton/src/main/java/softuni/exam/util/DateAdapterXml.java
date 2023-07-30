package softuni.exam.util;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateAdapterXml extends XmlAdapter<String, LocalDate> {

    private static final String CUSTOM_FORMAT_STRING = "yyyy-MM-dd";


    @Override
    public LocalDate unmarshal(String v) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(CUSTOM_FORMAT_STRING);
        LocalDate dateTime = LocalDate.parse(v, formatter);
        return dateTime;
    }

    @Override
    public String marshal(LocalDate v) throws Exception {
        return null;
    }
}
