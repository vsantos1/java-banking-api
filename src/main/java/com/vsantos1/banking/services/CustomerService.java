package com.vsantos1.banking.services;

import com.vsantos1.banking.mapper.MapperUtils;
import com.vsantos1.banking.models.Customer;
import com.vsantos1.banking.repositories.CustomerRepository;
import com.vsantos1.banking.vo.CustomerVO;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public boolean isEmailAlreadyRegistered(String email) {
        return customerRepository.findByEmail(email).isPresent();
    }

    public boolean isCpfAlreadyRegistered(String cpf) {
        return customerRepository.findByCpf(cpf).isPresent();
    }

    @Transactional
    public CustomerVO save(Customer customer) {

        return MapperUtils.parseObject(customerRepository.save(customer), CustomerVO.class);
    }

    public Optional<CustomerVO> findById(Long id) {
        return customerRepository.findById(id).map(customer -> MapperUtils.parseObject(customer, CustomerVO.class));
    }

    @Transactional
    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }

    public Page<CustomerVO> findAll(Pageable pageable) {
        Page<Customer> customers = customerRepository.findAll(pageable);

        return customers.map(customer -> MapperUtils.parseObject(customer, CustomerVO.class));

    }
}
