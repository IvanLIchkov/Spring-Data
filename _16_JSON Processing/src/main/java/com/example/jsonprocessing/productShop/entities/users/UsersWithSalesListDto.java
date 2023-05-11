package com.example.jsonprocessing.productShop.entities.users;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UsersWithSalesListDto implements Serializable {

    private int usersCount;

    private List<UserFirstLastNamesAgeAndSoldProductsNameAndPriceDto> users;

    public UsersWithSalesListDto(){
        users = new ArrayList<>();
    }

    public int getUsersCount() {
        return usersCount;
    }

    public void setUsersCount(int usersCount) {
        this.usersCount = usersCount;
    }

    public List<UserFirstLastNamesAgeAndSoldProductsNameAndPriceDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserFirstLastNamesAgeAndSoldProductsNameAndPriceDto> users) {
        this.users = users;
    }
}
