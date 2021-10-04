package com.example.lesson1task1.service;

import com.example.lesson1task1.entity.Address;
import com.example.lesson1task1.entity.Department;
import com.example.lesson1task1.entity.Worker;
import com.example.lesson1task1.payload.ApiResponse;
import com.example.lesson1task1.payload.WorkerDto;
import com.example.lesson1task1.repostory.AddressRepos;
import com.example.lesson1task1.repostory.DepartmentRepos;
import com.example.lesson1task1.repostory.WorkerRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerServ {
    @Autowired
    WorkerRepos workerRepos;
    @Autowired
    AddressRepos addressRepos;
    @Autowired
    DepartmentRepos departmentRepos;

    public ApiResponse add(WorkerDto workerDto){
        Worker worker =new Worker();
        worker.setName(workerDto.getName());

        Address address=new Address();
        address.setStreet(workerDto.getStreet());
        address.setHomeNumber(workerDto.getHome());
        Address address1 = addressRepos.save(address);
        worker.setAddress(address1);
        Optional<Department> optionalDepartment = departmentRepos.findById(workerDto.getDepartmentId());
        if (!optionalDepartment.isPresent()) {
            return new ApiResponse("Not found",false);
        }
        Department department = optionalDepartment.get();
        worker.setDepartment(department);
        workerRepos.save(worker);
        return new ApiResponse("saved",true);
    }

    public List<Worker> getall() {
        return workerRepos.findAll();
    }

    public ApiResponse getOne(int id) {
        Optional<Worker> optionalWorker = workerRepos.findById(id);
        if (!optionalWorker.isPresent()) {
            return new ApiResponse("Not found",false);
        }
        return new ApiResponse("found",true,optionalWorker.get());
    }

    public ApiResponse edit(int id, WorkerDto workerDto) {
        Optional<Worker> optionalWorker = workerRepos.findById(id);
        if (!optionalWorker.isPresent()) {
            return new ApiResponse("not found",false);
        }
        Worker worker = optionalWorker.get();
        if (workerDto.getName()!=null){
            worker.setName(workerDto.getName());
        }
        Optional<Address> addressOptional = addressRepos.findById(worker.getAddress().getId());
        Address address = addressOptional.get();
        if (workerDto.getStreet()!=null){
            address.setStreet(workerDto.getStreet());
        }
        if (workerDto.getHome()!=null){
            address.setHomeNumber(workerDto.getHome());
         }
        worker.setAddress(address);
        workerRepos.save(worker);
        return new ApiResponse("edit",true);
    }
    public ApiResponse deleted(int id){
        Optional<Worker> optionalWorker = workerRepos.findById(id);
        if (!optionalWorker.isPresent()) {
            return new ApiResponse("Not found",false);
        }
        workerRepos.deleteById(id);
        return new ApiResponse("Deleted",true);
    }
}
