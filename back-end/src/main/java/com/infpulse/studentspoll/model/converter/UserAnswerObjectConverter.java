package com.infpulse.studentspoll.model.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infpulse.studentspoll.model.entity.UserAnswerObject;
import lombok.SneakyThrows;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class UserAnswerObjectConverter implements AttributeConverter<UserAnswerObject, String> {

    @SneakyThrows
    @Override
    public String convertToDatabaseColumn(UserAnswerObject userAnswerObject) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(userAnswerObject);
    }

    @SneakyThrows
    @Override
    public UserAnswerObject convertToEntityAttribute(String s) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(s, UserAnswerObject.class);
    }
}
