package com.postech.msorders.usecase;

import com.postech.msorders.entity.Order;
import jakarta.persistence.EntityNotFoundException;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
@NoArgsConstructor
@Service
public class OrderUseCase {
    static RestTemplate restTemplate = new RestTemplate();

    public static void validateInsertOrder(Order orderNew) {
        findCustomer(String.valueOf(orderNew.getIdCustomer()));

    }

    public static boolean findCustomer(String idCustomer) {
        String url = "http://localhost:8080/customers/" + idCustomer;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        if(response.getStatusCode().is2xxSuccessful())
            return true;
        return false;
    }

}
