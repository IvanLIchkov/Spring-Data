package com.product_shop.services;

import com.product_shop.entities.users.User;
import com.product_shop.entities.users.UsersAndProductsDto.UsersDto;
import com.product_shop.entities.users.userSoldProductsDto.ExportUserWithSoldProductsDto;
import com.product_shop.entities.users.userSoldProductsDto.ExportSellersDto;
import com.product_shop.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.modelMapper = new ModelMapper();
    }


    @Override
    @Transactional
    public ExportSellersDto findAllWithSoldProducts() {
        List<User> users = this.userRepository.findAllWithSoldProducts();

        List<ExportUserWithSoldProductsDto> dtos =
                users.stream()
                        .map(u -> this.modelMapper.map(u, ExportUserWithSoldProductsDto.class))
                        .collect(Collectors.toList());


        return new ExportSellersDto(dtos);
    }

    @Override
    public UsersDto getAllUsersWithSoldProduct(){
        List<UsersDto> allUsersWithSoldProduct = this.userRepository.getAllUsersWithSoldProduct();

        return null;
    }
}
