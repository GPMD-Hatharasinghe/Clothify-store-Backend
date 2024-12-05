package edu.icet.ClothifyStor.repository;

import edu.icet.ClothifyStor.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository <OrderEntity,Integer>{
}
