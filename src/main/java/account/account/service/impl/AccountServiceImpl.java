package account.account.service.impl;

import account.account.dto.CustomerDto;
import account.account.entity.Account;
import account.account.entity.Customer;
import account.account.exception.NotFoundException;
import account.account.mapper.CustomerMapper;
import account.account.repository.AccountRepository;
import account.account.repository.CustomerRepository;
import account.account.service.AccountService;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class AccountServiceImpl implements AccountService {

  private final CustomerRepository customerRepository;

  private final AccountRepository accountRepository;

  private final CustomerMapper customerMapper;

  @Override
  public void createCustomer(CustomerDto customerDto) {

    Customer customer = customerMapper.toEntity(customerDto);
    Customer customerSaved = customerRepository.save(customer);

    accountRepository.save(createAccount(customerSaved));
  }

  @Override
  public CustomerDto getCustomerByPhoneNumber(String phoneNumber) {
    Customer customer = customerRepository.findCustomerByMobileNumber(phoneNumber);
    if (customer == null) {
      throw new NotFoundException("Customer with mobile number " + phoneNumber + " not found");
    }
    return customerMapper.toDto(customerRepository.findCustomerByMobileNumber(phoneNumber));
  }

  private Account createAccount(Customer customer) {
    return Account.builder()
            .customerId(customer.getCustomerId())
            .accountNumber(UUID.randomUUID().toString())
            .accountType("SAVING")
            .branchAddress("120 Nam Tu Liem, Ha Noi")
            .build();
  }
}
