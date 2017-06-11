package com.example.jwtdemo.controller;

import com.example.jwtdemo.controller.model.CreateRequestDto;
import com.example.jwtdemo.controller.model.CreateRequestResult;
import com.example.jwtdemo.entity.Request;
import com.example.jwtdemo.service.RequestService;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author John Alexander Cely Suarez
 */
@RestController
@RequestMapping("/request")
public class RequestController {

  private static final Logger logger = LoggerFactory.getLogger(RequestController.class);

  @Autowired
  private RequestService requestService;

  @PostMapping
  public ResponseEntity<CreateRequestResult> create(@RequestBody @Valid CreateRequestDto dto) {
    logger.info("Create request {}", dto);
    CreateRequestResult result = requestService.create(dto);
    return ResponseEntity.ok(result);
  }

  @PutMapping("/{id}")
  public ResponseEntity update(@PathVariable Long id) {
    logger.info("Update request");
    requestService.update(id);
    return ResponseEntity.ok().build();
  }

  @GetMapping
  public List<Request> getRequests() {
    logger.info("Get all requests");
    return requestService.getAll();
  }

}
