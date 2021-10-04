package com.example.lesson1task1.controller;

import com.example.lesson1task1.entity.Department;
import com.example.lesson1task1.payload.ApiResponse;
import com.example.lesson1task1.payload.DepartmentDto;
import com.example.lesson1task1.service.DepartmentSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentCont {
    @Autowired
    DepartmentSer departmentSer;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> add(@RequestBody DepartmentDto departmentDto){
        ApiResponse apiResponse = departmentSer.add(departmentDto);
        if (apiResponse.isSuccess()){
            return ResponseEntity.status(201).body(apiResponse);
        }
        return ResponseEntity.status(409).body(apiResponse);
    }
    @GetMapping("/list")
    public ResponseEntity<List<Department>> getall(){
        List<Department> getall = departmentSer.getall();
        return ResponseEntity.ok().body(getall);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getOne(@PathVariable int id){
        ApiResponse one = departmentSer.getOne(id);
        if (one.isSuccess()){
           return ResponseEntity.ok().body(one);
        }
        return ResponseEntity.status(409).body(one);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> edit(@PathVariable int id,@RequestBody DepartmentDto departmentDto){
        ApiResponse edit = departmentSer.edit(id, departmentDto);
        if (edit.isSuccess()){
            return ResponseEntity.status(202).body(edit);
        }
            return ResponseEntity.status(409).body(edit);
    }
}
