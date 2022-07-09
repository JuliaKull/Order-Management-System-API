package com.bta.service.impl;

import com.bta.dto.*;
import com.bta.mapper.WebMapper;
import com.bta.model.Customer;
import com.bta.model.CustomerOrder;
import com.bta.model.OrderLine;
import com.bta.model.Product;
import com.bta.repository.CustomerOrderRepository;
import com.bta.repository.CustomerRepository;
import com.bta.repository.OrderLineRepository;
import com.bta.repository.ProductRepository;
import com.bta.service.CustomerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;


@Service
public class CustomerOrderServiceImpl implements CustomerOrderService {

    @Autowired
    private WebMapper<CustomerOrderDTO, CustomerOrder> mapper;

    @Autowired
    private CustomerOrderRepository repository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderLineRepository orderLineRepository;

    @Override
    @Transactional
    public void create(CustomerOrderDTO customerOrder) {
        final var orderToCreate = mapper.toEntity(customerOrder);
        repository.save(orderToCreate);
    }

    @Transactional
    @Override
    public void createAll(CustomerOrdersDTO customerOrderDtos) {
        customerOrderDtos.getCustomerOrders()
                .forEach(orderDto -> {
                    final CustomerOrder customerOrder = createOrder(orderDto.getCustomer().getEmail());

                    orderDto.getOrderLines().forEach(orderLineDTO -> {
                        final ProductDTO productDTO = orderLineDTO.getProduct();
                        orderLineRepository.save(OrderLine.builder()
                                .quantity(orderLineDTO.getQuantity())
                                .product(getOrCreateProduct(productDTO.getSkuCode(),
                                        productDTO.getName(),
                                        productDTO.getUnitPrice()))
                                .customerOrder(customerOrder)
                                .build());

                    });
                });
    }

    private static String generateOrederNumber() {
        return UUID.randomUUID().toString().substring(0, 19);
    }

    private Customer enrichCustomer(String email) {

        Customer customer = customerRepository.findByEmail(email);
        if (customer == null) {
            throw new RuntimeException("Invalid customer");
        }
        return customer;
    }

    private CustomerOrder createOrder(String customerEmail) {
        return repository.save(CustomerOrder.builder()
                .orderNumber(generateOrederNumber())
                .customer(enrichCustomer(customerEmail))
                .submissionDate(ZonedDateTime.now())
                .build()
        );
    }

    private Product getOrCreateProduct(final Integer skuCode, final String productName, final Integer unitPrice) {
        Product product = productRepository.findBySkuCode(skuCode);
        if (product != null) {
            return product;
        }

        product = Product.builder().skuCode(skuCode)
                .name(productName)
                .unitPrice(unitPrice)
                .build();
        return productRepository.save(product);
    }

    public List<CustomerOrderDTO> getAll() {
        return mapper.toDtos(repository.findAll());
    }
}
