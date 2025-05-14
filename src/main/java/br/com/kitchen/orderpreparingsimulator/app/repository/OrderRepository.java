package br.com.kitchen.orderpreparingsimulator.app.repository;

import br.com.kitchen.orderpreparingsimulator.app.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByStatus(String status);
}

