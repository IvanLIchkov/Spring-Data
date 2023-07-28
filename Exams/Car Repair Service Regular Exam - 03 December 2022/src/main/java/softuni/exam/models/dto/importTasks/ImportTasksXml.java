package softuni.exam.models.dto.importTasks;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "tasks")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportTasksXml {

    @XmlElement(name = "task")
    private List<ImportTaskXml> taskXmls;

    public ImportTasksXml() {
    }

    public List<ImportTaskXml> getTaskXmls() {
        return taskXmls;
    }
}
