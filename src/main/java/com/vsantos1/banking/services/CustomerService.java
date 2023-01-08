package com.vsantos1.banking.services;

import com.vsantos1.banking.mapper.MapperUtils;
import com.vsantos1.banking.models.Customer;
import com.vsantos1.banking.repositories.CustomerRepository;
import com.vsantos1.banking.vo.CustomerVO;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
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

  public CustomerVO update(Long id,Customer customer){
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if(customerOptional.isEmpty()){
            throw new ResourceNotFoundException("Customer not found");
        }
        customerOptional.get().setBalance(customer.getBalance() == null ? customerOptional.get().getBalance() : customer.getBalance());
        customerOptional.get().setCpf(customer.getCpf() == null ? customerOptional.get().getCpf() : customer.getCpf());
        customerOptional.get().setEmail(customer.getEmail() == null ? customerOptional.get().getEmail() : customer.getEmail());
        customerOptional.get().setName(customer.getName() == null ? customerOptional.get().getName() : customer.getName());
        customerOptional.get().setCardUnlocked(customer.getCardUnlocked() == null ? customerOptional.get().getCardUnlocked() : customer.getCardUnlocked());
        customerOptional.get().setCardLimit(customer.getCardLimit() == null ? customerOptional.get().getCardLimit() : customer.getCardLimit());

        return MapperUtils.parseObject(customerRepository.save(customerOptional.get()), CustomerVO.class);
  }
}
