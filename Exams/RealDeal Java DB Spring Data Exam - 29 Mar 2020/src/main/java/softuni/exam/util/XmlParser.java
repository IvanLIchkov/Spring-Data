package softuni.exam.util;

public interface XmlParser {

    <O> O parseXml(Class<O> objectClass, String xmlFile);
}
