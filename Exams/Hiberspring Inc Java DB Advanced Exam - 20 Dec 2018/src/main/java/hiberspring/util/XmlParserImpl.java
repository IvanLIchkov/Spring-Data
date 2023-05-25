package hiberspring.util;

import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

@Component
@SuppressWarnings("unchecked")
public class XmlParserImpl implements XmlParser {

    @Override
    public <O> O parseXml(Class<O> objectClass, String xmlFile) throws JAXBException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(objectClass);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            return (O) unmarshaller.unmarshal(new StringReader(xmlFile));
        }catch (JAXBException e){
            e.printStackTrace();
        }
        return null;
    }
}
