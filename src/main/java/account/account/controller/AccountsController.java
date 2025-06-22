package account.account.controller;

import account.account.dto.CustomerDto;
import account.account.dto.ResponseDto;
import account.account.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class AccountsController {

  private final AccountService accountService;

  @PostMapping("/create")
  public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto customerDto) {

    accountService.createCustomer(customerDto);
    return ResponseEntity.ok(new ResponseDto("Creat account successfully", HttpStatus.OK.toString()));
  }

  @GetMapping("/{phoneNumber}/account")
  public ResponseEntity<CustomerDto> createAccount(@PathVariable(name = "phoneNumber") String phoneNumber) {
    return ResponseEntity.status(HttpStatus.OK).body(accountService.getCustomerByPhoneNumber(phoneNumber));
  }

}
