package com.gazorpazorp.model.dtoMapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.gazorpazorp.model.Customer;
import com.gazorpazorp.model.User;
import com.gazorpazorp.model.dto.CustomerDetailsDto;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

	CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
	
	@Mapping(source="customer.id", target="id")
	@Mapping(target="firstName")
	@Mapping(target="lastName")
	@Mapping(target="email")
	@Mapping(target="address")
	@Mapping(target="paymentMethod")
	CustomerDetailsDto customerAndUserToCustomerDetailsDto(Customer customer, User user);
}
