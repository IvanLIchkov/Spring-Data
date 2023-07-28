package softuni.exam.util;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateAdapterXml extends XmlAdapter<String, LocalDateTime> {

    private static final String CUSTOM_FORMAT_STRING = "yyyy-MM-dd HH:mm:ss";


    @Override
    public LocalDateTime unmarshal(String v) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(CUSTOM_FORMAT_STRING);
        LocalDateTime dateTime = LocalDateTime.parse(v, formatter);
        return dateTime;
    }

    @Override
    public String marshal(LocalDateTime v) throws Exception {
        return null;
    }
}
