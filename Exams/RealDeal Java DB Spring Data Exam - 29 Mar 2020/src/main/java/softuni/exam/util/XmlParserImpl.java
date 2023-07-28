package softuni.exam.util;

import org.springframework.stereotype.Component;

import javax.xml.bind.*;
import java.io.StringReader;

@Component
public class XmlParserImpl implements XmlParser {

    @Override
    public <O> O parseXml(Class<O> objectClass, String xmlFile) {
        try {
            JAXBContext context = JAXBContext.newInstance(objectClass);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (O) unmarshaller.unmarshal(new StringReader(xmlFile));
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}
