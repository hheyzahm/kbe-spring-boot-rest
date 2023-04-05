package springboot.kberest.web.mappers;

import org.mapstruct.Mapper;
import springboot.kberest.domain.Customer;
import springboot.kberest.web.model.CustomerDTO;


/**
 * @Created 03 04 2023 - 9:49 AM
 * @Author Hazeem Hassan
 */
@Mapper
public interface CustomerMapper {
    Customer customerDtoToCustomer(CustomerDTO dto);

    CustomerDTO customerToCustomerDto(Customer customer);
}
