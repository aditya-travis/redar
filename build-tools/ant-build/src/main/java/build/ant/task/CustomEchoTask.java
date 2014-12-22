package build.ant.task;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

/**
 * Created by mengf on 12/22/2014.
 */
public class CustomEchoTask extends Task {

    private String message;

    public void setMessage(String message) {
        //this.message = message; //with property resolution disabled
        this.message = getProject().replaceProperties(message); //with property resolution enabled
    }

    public void addText(String text){
        this.message = getProject().replaceProperties(text);
    }


    @Override
    public void execute() throws BuildException {
        log("Here is Project " + getProject().getName());
        log("Task is used in " + getLocation());

        if(message == null){
            throw new BuildException("No message is set");
        }

        log(message);
    }
}
