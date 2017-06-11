package com.example.jwtdemo.service;

import com.example.jwtdemo.controller.model.CreateRequestDto;
import com.example.jwtdemo.controller.model.CreateRequestResult;
import com.example.jwtdemo.entity.Request;
import java.math.BigDecimal;
import java.util.List;

public interface RequestService {

  CreateRequestResult create(CreateRequestDto dto);

  void update(Long id);

  List<Request> getAll();

}
