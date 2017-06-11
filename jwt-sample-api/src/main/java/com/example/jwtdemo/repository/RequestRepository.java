package com.example.jwtdemo.repository;

import com.example.jwtdemo.entity.Request;
import java.math.BigDecimal;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

  Optional<Request> findOneByIdentification(BigDecimal identification);

}
