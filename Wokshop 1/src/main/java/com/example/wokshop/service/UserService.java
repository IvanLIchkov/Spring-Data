package com.example.wokshop.service;

import com.example.wokshop.models.User;
import com.example.wokshop.models.dto.RegistrationDto;
import com.example.wokshop.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Autowired          //Chrez @Qualifier se opredelq koi model iskame da polzvame primerno razlichni TypeMap
    public UserService(@Qualifier("altModelMapper") ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    public void register(RegistrationDto dto){
        User user = this.modelMapper.map(dto, User.class);
        this.userRepository.save(user);
    }
}
