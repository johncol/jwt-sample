package com.example.jwtdemo.db.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "REQUEST")
public class Request implements Serializable {

  @Id
  @GeneratedValue
  private Long id;

  @NotNull
  @Column(unique = true, nullable = false)
  private BigDecimal identification;

  @NotNull
  @Column(nullable = false)
  private Boolean done;

  public Request() {
  }

  public Request(BigDecimal identification) {
    this(identification, false);
  }

  public Request(BigDecimal identification, Boolean done) {
    this.identification = identification;
    this.done = done;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public BigDecimal getIdentification() {
    return identification;
  }

  public void setIdentification(BigDecimal identification) {
    this.identification = identification;
  }

  public Boolean getDone() {
    return done;
  }

  public void setDone(Boolean done) {
    this.done = done;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Request request = (Request) o;
    return Objects.equals(id, request.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
}
