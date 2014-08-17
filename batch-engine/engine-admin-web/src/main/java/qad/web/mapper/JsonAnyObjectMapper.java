package qad.web.mapper;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by meng on 7/5/14.
 */
public class JsonAnyObjectMapper extends ObjectMapper {

    public JsonAnyObjectMapper() {
        super();
        this.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    }
}
