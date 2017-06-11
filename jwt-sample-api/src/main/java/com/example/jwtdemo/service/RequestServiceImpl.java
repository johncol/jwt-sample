package com.example.jwtdemo.service;

import com.example.jwtdemo.controller.model.CreateRequestDto;
import com.example.jwtdemo.controller.model.CreateRequestResult;
import com.example.jwtdemo.entity.Request;
import com.example.jwtdemo.repository.RequestRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestServiceImpl implements RequestService {

  @Autowired
  private RequestRepository repository;

  @Override
  public CreateRequestResult create(CreateRequestDto dto) {
    Request request = repository.findOneByIdentification(dto.getIdentification())
        .orElseGet(() -> new Request(dto.getIdentification()));
    repository.save(request);
    return new CreateRequestResult(request.getId(), true);
  }

  @Override
  public void update(Long id) {
    Request request = repository.getOne(id);
    request.setDone(true);
    repository.saveAndFlush(request);
  }

  @Override
  public List<Request> getAll() {
    return repository.findAll();
  }

}
