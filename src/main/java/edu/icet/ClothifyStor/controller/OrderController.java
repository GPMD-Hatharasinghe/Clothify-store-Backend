package edu.icet.ClothifyStor.controller;

import edu.icet.ClothifyStor.model.Order;
import edu.icet.ClothifyStor.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {
    private final OrderService orderService;
    @PostMapping("/add")
    Order persist(@RequestBody Order order, @RequestHeader("Authorization") String token){
        log.info("order {}" , order);
        return orderService.save(order, token);
    }
    @GetMapping("/get-all")
    List<Order> retrive(){
        return orderService.load();
    }
    @PatchMapping("/status/{orderId}")
    public boolean updateStatus(@PathVariable Integer orderId , @RequestBody String status){
        return orderService.updateStatus(orderId, status);
    }
}
