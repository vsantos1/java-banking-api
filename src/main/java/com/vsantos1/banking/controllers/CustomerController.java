package com.vsantos1.banking.controllers;

import com.vsantos1.banking.mapper.MapperUtils;
import com.vsantos1.banking.models.Customer;
import com.vsantos1.banking.services.CustomerService;
import com.vsantos1.banking.vo.CustomerVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;

@RestController
@RequestMapping(value = "/api/v1")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(value = "/customers")
    public ResponseEntity<Page<CustomerVO>> getCustomer(@PageableDefault(size = 10, value = 10) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findAll(pageable));
    }

    @PostMapping(value = "/customers", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerVO> createCustomer(CustomerVO customerVO) {
        Customer customer = new Customer();
        MapperUtils.copyProperties(customerVO, customer);

        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.save(customer));
    }

    @PutMapping(value = "/customers/{customer_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerVO> updateCustomer(@PathVariable("customer_id") Long id, @RequestBody CustomerVO customerVO) {
        Customer customer = new Customer();
        MapperUtils.copyProperties(customerVO, customer);

        return ResponseEntity.status(HttpStatus.OK).body(customerService.update(id, customer));
    }
}
