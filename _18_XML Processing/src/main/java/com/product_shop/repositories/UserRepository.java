package com.product_shop.repositories;

import com.product_shop.entities.users.User;

import com.product_shop.entities.users.UsersAndProductsDto.ExportUserDto;
import com.product_shop.entities.users.UsersAndProductsDto.SoldProductCountDto;
import com.product_shop.entities.users.UsersAndProductsDto.UsersDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u " +
            "where " +
            "(select count (p) " +
            "from Product p " +
            "where p.seller = u and p.buyer is not null) = (select count (p)" +
            "from Product p " +
            "where p.seller = u ) " +
            "order by u.lastName, u.firstName")
    List<User> findAllWithSoldProducts();

    @Query("select new com.product_shop.entities.users.UsersAndProductsDto.UsersDto(" +
            "count(u)," +
            "(select new com.product_shop.entities.users.UsersAndProductsDto.ExportUserDto(u1.firstName, u1.lastName, u1.age, size(u1.sellingItems), u1.sellingItems) " +
            "from User u1 where size(u1.sellingItems) >=1)) " +
            "from User u " +
            "where size(u.sellingItems) >=1 " +
            "order by size(u.sellingItems) desc , u.lastName asc")
    List<UsersDto> getAllUsersWithSoldProduct();

}
