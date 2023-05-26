package softuni.exam.util;

public interface XmlReader {

    <O> O parseXml(Class<O> objectClass, String xmlFile);
}
