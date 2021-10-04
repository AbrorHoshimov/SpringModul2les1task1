package com.example.lesson1task1.controller;

import com.example.lesson1task1.entity.Worker;
import com.example.lesson1task1.payload.ApiResponse;
import com.example.lesson1task1.payload.WorkerDto;
import com.example.lesson1task1.service.WorkerServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/worker")
public class WorkerCont {
    @Autowired
    WorkerServ workerServ;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> add(@RequestBody WorkerDto workerDto){
        ApiResponse apiResponse = workerServ.add(workerDto);
        if (apiResponse.isSuccess()) return ResponseEntity.status(201).body(apiResponse);
        return ResponseEntity.status(409).body(apiResponse);
    }
    @GetMapping("/list")
    public ResponseEntity<List<Worker>> getall(){
        List<Worker> getall = workerServ.getall();
        return ResponseEntity.ok(getall);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getOne(@PathVariable int id){
        ApiResponse apiResponse = workerServ.getOne(id);
        if (apiResponse.isSuccess()) return ResponseEntity.ok(apiResponse);
        return ResponseEntity.status(409).body(apiResponse);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> edit(@PathVariable int id,@RequestBody WorkerDto workerDto){
        ApiResponse apiResponse = workerServ.edit(id, workerDto);
        if (apiResponse.isSuccess())return ResponseEntity.status(202).body(apiResponse);
        return ResponseEntity.status(409).body(apiResponse);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleted(@PathVariable int id){
        ApiResponse apiResponse = workerServ.deleted(id);
        if (apiResponse.isSuccess())return ResponseEntity.ok(apiResponse);
        return ResponseEntity.status(409).body(apiResponse);
    }

}
