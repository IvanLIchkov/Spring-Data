package softuni.exam.util;

import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

@Component
public class XmlReaderImpl implements XmlReader {

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
