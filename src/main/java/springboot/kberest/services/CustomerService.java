package springboot.kberest.services;

import springboot.kberest.web.model.CustomerDTO;

import java.util.UUID;

/**
 * @Created 03 04 2023 - 10:47 AM
 * @Author Hazeem Hassan
 */
public interface CustomerService {
    CustomerDTO getCustomerById(UUID customerId);

    CustomerDTO saveNewCustomer(CustomerDTO customerDto);

    void updateCustomer(UUID customerId, CustomerDTO customerDto);

    void deleteById(UUID customerId);
}
