package com.example.jwtdemo.controller.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotBlank;

public class CreateRequestDto implements Serializable {

  @NotNull
  private BigDecimal identification;

  @NotBlank
  private String captcha;

  public CreateRequestDto() {
  }

  public CreateRequestDto(BigDecimal identification, String captcha) {
    this.identification = identification;
    this.captcha = captcha;
  }

  public BigDecimal getIdentification() {
    return identification;
  }

  public void setIdentification(BigDecimal identification) {
    this.identification = identification;
  }

  public String getCaptcha() {
    return captcha;
  }

  public void setCaptcha(String captcha) {
    this.captcha = captcha;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
}
