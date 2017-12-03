package pl.mg.cfm.api.configuration;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.io.IOException;
import java.util.Date;

@Configuration
public class WebAppConfiguration {


    @Bean
    public MappingJackson2HttpMessageConverter jsonDateMapper() {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true);
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addDeserializer(Date.class, new UnixTimestampDeserializer());
        simpleModule.addSerializer(Date.class, new UnixTimestampSerializer());
        mapper.registerModule(simpleModule);
        jsonConverter.setObjectMapper(mapper);
        return jsonConverter;
    }

    public class UnixTimestampDeserializer extends StdDeserializer<Date> {
        private static final long serialVersionUID = 7254284878528413343L;

        public UnixTimestampDeserializer() {
            super(Date.class);
        }

        @Override
        public Date deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            String timestamp = jp.getText().trim();

            try {
                return new Date(Long.valueOf(timestamp));
            } catch (NumberFormatException e) {
                return null;
            }
        }
    }

    public class UnixTimestampSerializer extends StdSerializer<Date> {
        private static final long serialVersionUID = -1874723475679725526L;

        public UnixTimestampSerializer() {
            super(Date.class);
        }

        @Override
        public void serialize(Date date, JsonGenerator json, SerializerProvider provider) throws IOException, JsonGenerationException {
            String out = Long.toString(date.getTime());
            json.writeString(out);
        }
    }

}
