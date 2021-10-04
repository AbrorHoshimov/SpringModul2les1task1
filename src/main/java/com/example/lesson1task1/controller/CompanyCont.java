package com.example.lesson1task1.controller;

import com.example.lesson1task1.entity.Company;
import com.example.lesson1task1.payload.ApiResponse;
import com.example.lesson1task1.payload.CompanyDto;
import com.example.lesson1task1.service.CompanyServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/company")
public class CompanyCont {
    @Autowired
    CompanyServ companyServ;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addCompany(@RequestBody CompanyDto companyDto) {
        ApiResponse apiResponse = companyServ.addCompany(companyDto);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Company>> getAll() {
        List<Company> all = companyServ.getAll();
        return ResponseEntity.ok(all);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> edit(@PathVariable int id, @RequestBody CompanyDto companyDto) {
        ApiResponse apiResponse = companyServ.edit(id, companyDto);
        if (apiResponse.isSuccess()) {
            return ResponseEntity.status(202).body(apiResponse);
        }
        return ResponseEntity.status(409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse>deleted(@PathVariable int id){
        ApiResponse delete = companyServ.delete(id);
        if (delete.isSuccess()){
            return ResponseEntity.status(200).body(delete);
        }
        return ResponseEntity.status(409).body(delete);
    }
}