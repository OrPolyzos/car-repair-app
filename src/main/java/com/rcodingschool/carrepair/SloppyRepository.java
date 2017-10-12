package com.rcodingschool.carrepair;

import com.rcodingschool.carrepair.Domain.User;

import java.util.ArrayList;
import java.util.List;

public class SloppyRepository {

    public static List<User> getAllUsers() {
        User user1 = new User("Oresths", "Polyzos", "123456789", "asdasd", "ore@pol", "User");
        User user2 = new User("Tolhs", "Gakhs", "000002222", "asdasdas", "tol@gak", "Admin");
        List<User> myList = new ArrayList<>();
        myList.add(user1);
        myList.add(user2);
        return myList;
    }
}
