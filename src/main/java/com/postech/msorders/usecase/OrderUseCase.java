package com.postech.msorders.usecase;

import com.postech.msorders.entity.Item;
import com.postech.msorders.entity.Order;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component
@NoArgsConstructor
//@Service
public class OrderUseCase {
    static RestTemplate restTemplate = new RestTemplate();

    private String msProductsUrl;
    private String msCustomersUrl;

    @Value("${api.msproducts.url}")
    public void setMsProductsUrl(String msProductsUrl) {
        this.msProductsUrl = msProductsUrl;
    }

    @Value("${api.mscustomers.url}")
    public void setMsCustomersUrl(String msCustomersUrl) {
        this.msCustomersUrl = msCustomersUrl;
    }

    public void validateInsertOrder(Order orderNew)  {
        findCustomer(String.valueOf(orderNew.getIdCustomer()));
    }

    boolean findCustomer(String idCustomer) {
        String url = msCustomersUrl + idCustomer;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        if(response.getStatusCode().is2xxSuccessful())
            return true;
        return false;
    }

    public void validateProductAvailability(Order orderNew) {
        findProduct(orderNew.getItens());
    }

    boolean findProduct(List<Item> itens) {
        for(Item item : itens) {
            UUID idProduct = item.getIdProduct();
            BigDecimal quantity = item.getQuantity();

            String url = msProductsUrl + idProduct + "/" + quantity;

            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            if (response.getBody().matches("false"))
                return false;
        }
        return true;
    }

}
