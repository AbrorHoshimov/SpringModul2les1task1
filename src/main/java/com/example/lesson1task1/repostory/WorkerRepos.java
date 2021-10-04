package com.example.lesson1task1.repostory;

import com.example.lesson1task1.entity.Address;
import com.example.lesson1task1.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepos extends JpaRepository<Worker,Integer> {
}
