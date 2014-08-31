package design.patterns.intepreter;

/**
 *
 * Class for storing the input and output
 * Created by Feng on 31/8/14.
 */
public class Context {
    private String input;
    private int output;

    public Context(String input) {
        this.input = input;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public int getOutput() {
        return output;
    }

    public void setOutput(int output) {
        this.output = output;
    }
}
