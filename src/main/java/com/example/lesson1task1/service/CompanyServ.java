package com.example.lesson1task1.service;

import com.example.lesson1task1.entity.Address;
import com.example.lesson1task1.entity.Company;
import com.example.lesson1task1.payload.ApiResponse;
import com.example.lesson1task1.payload.CompanyDto;
import com.example.lesson1task1.repostory.AddressRepos;
import com.example.lesson1task1.repostory.CompanyRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServ {
    @Autowired
    CompanyRepos companyRepos;

    @Autowired
    AddressRepos addressRepos;

    public ApiResponse addCompany(CompanyDto companyDto){
        Company company =new Company();
        company.setCorpName(companyDto.getCorpName());
        company.setDirectorName(companyDto.getDirectorName());

        Address address =new Address();
        address.setHomeNumber(companyDto.getHomeNumber());
        address.setStreet(companyDto.getStreet());
        Address save = addressRepos.save(address);
        company.setAddress(save);
        companyRepos.save(company);
        return new ApiResponse("saved",true);
    }
    public List<Company> getAll(){
        return companyRepos.findAll();
    }
    public ApiResponse edit(int id,CompanyDto companyDto){
        Optional<Company> optionCompany = companyRepos.findById(id);
        if (!optionCompany.isPresent()) return new ApiResponse("not found",false);
        Company company = optionCompany.get();
        company.setCorpName(companyDto.getCorpName());
        company.setDirectorName(company.getDirectorName());
        Optional<Address> optionalAddress = addressRepos.findById(company.getAddress().getId());
        if (!optionalAddress.isPresent()) return new ApiResponse("not found",false);
        Address address = optionalAddress.get();
        address.setStreet(companyDto.getStreet());
        address.setHomeNumber(companyDto.getHomeNumber());
        Address save = addressRepos.save(address);
        company.setAddress(save);
        companyRepos.save(company);
        return new ApiResponse("edited",true);
    }

    public ApiResponse delete(int id) {
        Optional<Company> optionalCompany = companyRepos.findById(id);
        if (!optionalCompany.isPresent()) return new ApiResponse("Not found",false);
        companyRepos.deleteById(id);
        return new ApiResponse("Deleted",true);


    }
}
