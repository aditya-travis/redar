package filemonitor;

import org.springframework.integration.file.FileNameGenerator;
import org.springframework.messaging.Message;

/**
 * Created by meng on 6/10/14.
 */
public class CustomFilenameGenerator implements FileNameGenerator {
    @Override
    public String generateFileName(Message<?> message) {
        return "Generated_File_" + message.getHeaders().get("fileId");
    }
}
