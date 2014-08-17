package filemonitor;

import org.springframework.integration.file.filters.AbstractFileListFilter;

import java.io.File;

/**
 * Created by meng on 6/11/14.
 */
public class CustomFileFilter extends AbstractFileListFilter<File>{

    @Override
    protected boolean accept(File file) {
        if(file.getName().endsWith("tmp")){
            return false;
        }
        return true;
    }
}
