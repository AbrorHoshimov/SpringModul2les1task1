package com.example.lesson1task1.service;

import com.example.lesson1task1.entity.Company;
import com.example.lesson1task1.entity.Department;
import com.example.lesson1task1.payload.ApiResponse;
import com.example.lesson1task1.payload.DepartmentDto;
import com.example.lesson1task1.repostory.CompanyRepos;
import com.example.lesson1task1.repostory.DepartmentRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentSer {

    @Autowired
    CompanyRepos companyRepos;
    @Autowired
    DepartmentRepos departmentRepos;


    public ApiResponse add(DepartmentDto departmentDto) {
        Department department =new Department();
        department.setName(departmentDto.getName());
        Optional<Company> optionalCompany = companyRepos.findById(departmentDto.getCompanyId());
        if (!optionalCompany.isPresent()) {
            return new ApiResponse("not found",false);
        }
        Company company = optionalCompany.get();
        department.setCompany(company);
        departmentRepos.save(department);
        return new ApiResponse("saved",true);
    }

    public List<Department> getall() {
        return departmentRepos.findAll();
    }

    public ApiResponse getOne(int id) {
        Optional<Department> optionalDepartment = departmentRepos.findById(id);
        if (!optionalDepartment.isPresent()) {
            return new ApiResponse("not found",false);
        }
        return new ApiResponse("ok",true,optionalDepartment.get());
    }

    public ApiResponse edit(int id, DepartmentDto departmentDto) {
        Optional<Department> optionalDepartment = departmentRepos.findById(id);
        if (!optionalDepartment.isPresent()) return new ApiResponse("not found",false);
        Department department = optionalDepartment.get();
        if (departmentDto.getName()!=null){
            department.setName(departmentDto.getName());
        }
        if (departmentDto.getCompanyId()!=0){
            Optional<Company> optionalCompany = companyRepos.findById(departmentDto.getCompanyId());
            if (!optionalCompany.isPresent()) {
                return new ApiResponse("not found",false);
            }
            Company company = optionalCompany.get();
            department.setCompany(company);
        }
        departmentRepos.save(department);
        return new ApiResponse("Edited",true);
    }
}
