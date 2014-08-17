package model;

/**
 * Created by meng on 6/3/14.
 */
public class ProcessContext {

    private String processingId;
    private String processor;

    public String getProcessingId() {
        return processingId;
    }

    public void setProcessingId(String processingId) {
        this.processingId = processingId;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    @Override
    public String toString() {
        return "ProcessContext{" +
                "processingId='" + processingId + '\'' +
                ", processor='" + processor + '\'' +
                '}';
    }
}
