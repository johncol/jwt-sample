package com.example.jwtdemo.controller.model;

import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class CreateRequestResult implements Serializable {

  private Long request;
  private boolean success;

  public CreateRequestResult(Long request, boolean success) {
    this.request = request;
    this.success = success;
  }

  public Long getRequest() {
    return request;
  }

  public void setRequest(Long request) {
    this.request = request;
  }

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
}
