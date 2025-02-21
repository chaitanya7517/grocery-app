package com.example.product_service.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "user-service")
public interface UserClient {

    @GetMapping("/user/authenticate")
    Boolean authenticate(@RequestHeader("apiKey") String apiKey);
}
