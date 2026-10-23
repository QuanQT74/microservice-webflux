package com.dongquan.commonservice.utils;
import com.dongquan.commonservice.common.ValidationException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Slf4j
@Data
@NoArgsConstructor
public class CommonFunction {
    @SneakyThrows
    public static void jsonValidate(InputStream inputStream,String json){
        JsonSchema schema = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7)
                .getSchema(inputStream);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(json);
        Set<ValidationMessage> errors = schema.validate(jsonNode);
        Map<String,String> stringSetMap = new HashMap<>();
        for(ValidationMessage error : errors){
            if (stringSetMap.containsKey(
                    formatStringValidate(error.getInstanceLocation().toString()))) {
                String message = stringSetMap.get(formatStringValidate(error.getInstanceLocation().toString()));
                stringSetMap.put(formatStringValidate(error.getInstanceLocation().toString()),message + " " +formatStringValidate(error.getMessage()));
            }
            else {
                stringSetMap.put(formatStringValidate(error.getInstanceLocation().toString()),formatStringValidate(error.getMessage()));
            }
        }
        if (!errors.isEmpty()) {
            throw new ValidationException(
                    "RQ01",
                    stringSetMap,
                    HttpStatus.BAD_REQUEST
            );
        }

    }

    public static String formatStringValidate(String message){
        return message.replace("\\$.","");
    }
}
