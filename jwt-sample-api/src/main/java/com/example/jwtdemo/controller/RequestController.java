package com.example.jwtdemo.controller;

import com.example.jwtdemo.controller.model.CreateRequestDto;
import com.example.jwtdemo.controller.model.CreateRequestResponse;
import com.example.jwtdemo.controller.model.JwtAuthenticationResponse;
import com.example.jwtdemo.db.entity.Request;
import com.example.jwtdemo.security.service.UserAuthenticationService;
import com.example.jwtdemo.service.RequestService;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/request")
public class RequestController {

  private static final Logger logger = LoggerFactory.getLogger(RequestController.class);

  @Autowired
  private RequestService requestService;

  @Autowired
  private UserAuthenticationService userAuthenticationService;

  @PostMapping
  public ResponseEntity<CreateRequestResponse> create(@RequestBody @Valid CreateRequestDto dto, Device device) {
    logger.info("Create request {}", dto);
    CreateRequestResponse response = requestService.create(dto);
    if (response.isSuccess()) {
      String identification = Long.valueOf(dto.getIdentification().longValue()).toString();
      JwtAuthenticationResponse jwt = userAuthenticationService.authenticate(identification, device);
      response.setJwt(jwt);
    }
    return ResponseEntity.ok(response);
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
