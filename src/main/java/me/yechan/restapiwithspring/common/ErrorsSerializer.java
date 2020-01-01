package me.yechan.restapiwithspring.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.validation.Errors;

import java.io.IOException;

@JsonComponent
public class ErrorsSerializer extends JsonSerializer<Errors> {
    @Override
    public void serialize(Errors errors, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartArray();
        errors.getFieldErrors().stream().forEach(e -> {
            try {
                gen.writeStartObject();
                gen.writeStringField("field", e.getField());
                gen.writeStringField("objectName", e.getObjectName());
                gen.writeStringField("code", e.getCode());
                gen.writeStringField("defaultMessage", e.getDefaultMessage());
                Object rejectedValue = e.getRejectedValue();
                if(rejectedValue != null) {
                    gen.writeStringField("rejectedValue", rejectedValue.toString());
                }
                gen.writeEndObject();
            } catch(IOException e1) {
                e1.printStackTrace();
            }
        });

        errors.getGlobalErrors().forEach(e -> {
            try {
                gen.writeStartObject();
                gen.writeStringField("objectName", e.getObjectName());
                gen.writeStringField("code", e.getCode());
                gen.writeStringField("defaultMessage", e.getDefaultMessage());
                gen.writeEndObject();
            } catch(IOException e1) {
                e1.printStackTrace();
            }
        });
        gen.writeEndArray();
    }
}
//@JsonComponent
//public class ErrorsSerializer extends JsonSerializer<Errors> {
//    @Override
//    public void serialize(Errors errors, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
//        jsonGenerator.writeStartArray();
//        errors.getFieldErrors().forEach(e -> {
//                try {
//                    jsonGenerator.writeStartObject();
//                    jsonGenerator.writeStringField("field", e.getField());
//                    jsonGenerator.writeStringField("objectName", e.getObjectName());
//                    jsonGenerator.writeStringField("code", e.getCode());
//                    jsonGenerator.writeStringField("defaultMessage", e.getDefaultMessage());
//                    Object rejectValue = e.getRejectedValue();
//                    if(rejectValue != null) {
//                        jsonGenerator.writeStringField("rejectedValue", rejectValue.toString());
//                    }
//                    jsonGenerator.writeEndObject();
//                } catch (IOException e1) {
//                    e1.printStackTrace();
//                }
//        });
//
//        errors.getGlobalErrors().forEach(e -> {
//            try {
//                jsonGenerator.writeStartObject();
//                jsonGenerator.writeStringField("objectName", e.getObjectName());
//                jsonGenerator.writeStringField("code", e.getCode());
//                jsonGenerator.writeStringField("defaultMessage", e.getDefaultMessage());
//                jsonGenerator.writeEndObject();
//            } catch (IOException e1) {
//                e1.printStackTrace();
//            }
//        });
//        jsonGenerator.writeEndArray();
//    }

