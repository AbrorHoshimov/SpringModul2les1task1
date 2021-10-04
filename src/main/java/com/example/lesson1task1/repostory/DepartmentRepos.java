package com.example.lesson1task1.repostory;

import com.example.lesson1task1.entity.Address;
import com.example.lesson1task1.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepos extends JpaRepository<Department,Integer> {
}
