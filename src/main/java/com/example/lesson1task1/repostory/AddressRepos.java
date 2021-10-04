package com.example.lesson1task1.repostory;

import com.example.lesson1task1.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepos extends JpaRepository<Address,Integer> {
}
