package com.postech.msorders.usecase;

import com.postech.msorders.entity.Item;
import com.postech.msorders.entity.Order;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Component
@NoArgsConstructor
@Service
public class OrderUseCase {
    static RestTemplate restTemplate = new RestTemplate();

    @Value("${api.msproducts.url}")
    private static String msProductsUrl;

    @Value("${api.mscustomers.url}")
    private static String msCustomersUrl;

    public static void validateInsertOrder(Order orderNew)  {
        findCustomer(String.valueOf(orderNew.getIdCustomer()));
    }

    private static boolean findCustomer(String idCustomer) {
        String url = msCustomersUrl + idCustomer;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        if(response.getStatusCode().is2xxSuccessful())
            return true;
        return false;
    }

    public static void validateProductAvailability(Order orderNew) {
        findProduct(orderNew.getItens());
    }

    private static boolean findProduct(List<Item> itens) {
        for(Item item : itens) {
            Long idProduct = item.getIdProduct();
            BigDecimal quantity = item.getQuantity();

            ResponseEntity<String> response = restTemplate.getForEntity(
                    msProductsUrl + idProduct + "/" + quantity, String.class);
        }
    }

}
