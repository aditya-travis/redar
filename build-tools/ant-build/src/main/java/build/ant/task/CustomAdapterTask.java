package build.ant.task;

/**
 * Created by mengf on 12/22/2014.
 */
import org.apache.tools.ant.Project;
public class CustomAdapterTask {
    private Project project;

    public void setProject(Project project) {
        this.project = project;
    }

    public void execute(){
        String projectName = project.getProperty("ant.project.name");
        project.log("Here is project " + projectName + " with task class " + this.getClass().getName() + ".", Project.MSG_INFO);
    }
}
