package com.example.lesson1task1.repostory;

import com.example.lesson1task1.entity.Address;
import com.example.lesson1task1.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepos extends JpaRepository<Company,Integer> {
}
