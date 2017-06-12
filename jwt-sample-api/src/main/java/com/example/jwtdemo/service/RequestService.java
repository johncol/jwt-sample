package com.example.jwtdemo.service;

import com.example.jwtdemo.controller.model.CreateRequestDto;
import com.example.jwtdemo.controller.model.CreateRequestResponse;
import com.example.jwtdemo.db.entity.Request;
import java.util.List;

public interface RequestService {

  CreateRequestResponse create(CreateRequestDto dto);

  void update(Long id);

  List<Request> getAll();

}
