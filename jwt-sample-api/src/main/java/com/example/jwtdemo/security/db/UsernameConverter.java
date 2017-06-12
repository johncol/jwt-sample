package com.example.jwtdemo.security.db;

import java.math.BigDecimal;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class UsernameConverter implements AttributeConverter<String, BigDecimal> {

  @Override
  public BigDecimal convertToDatabaseColumn(String attribute) {
    return attribute == null ? null : new BigDecimal(attribute);
  }

  @Override
  public String convertToEntityAttribute(BigDecimal dbData) {
    return dbData == null ? null : Long.valueOf(dbData.longValue()).toString();
  }
}
