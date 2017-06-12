package com.example.jwtdemo.service;

import com.example.jwtdemo.controller.model.CreateRequestDto;
import com.example.jwtdemo.controller.model.CreateRequestResponse;
import com.example.jwtdemo.db.entity.Request;
import com.example.jwtdemo.db.repository.RequestRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestServiceImpl implements RequestService {

  @Autowired
  private RequestRepository repository;

  @Override
  public CreateRequestResponse create(CreateRequestDto dto) {
    Request request = repository.findOneByIdentification(dto.getIdentification())
        .orElseGet(() -> new Request(dto.getIdentification()));
    repository.save(request);
    return new CreateRequestResponse(request.getId(), true);
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
