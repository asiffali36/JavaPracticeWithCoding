package com.example.demo.bottApi;
import com.example.demo.UsHouseAdministrationBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class ApiController {
    private ApiService apiService;

    @Autowired
    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }

    UsHouseAdministrationBot obj = new UsHouseAdministrationBot();

    @GetMapping("/getRecord")
    public ResponseEntity<List<ModelClass>> getAllRecords() {
        return ResponseEntity.status(200).body(apiService.getRecord());
    }

    @PostMapping("/insertRecord")
    public int insert(@RequestBody ModelClass modelClass) {
        return  apiService.insertRecord(modelClass);
    }


    @GetMapping("/getById/{id}")
    public ModelClass getById(@PathVariable("id") int id) {

        return obj.getRecordById(id);

    }

    @DeleteMapping("/deleteById/{id}")
    public int deleteById(@PathVariable("id") int id) {
        return apiService.deleteRecord(id);

    }

    @PutMapping("updateRecord/{id}")
    public ModelClass putRecord(@RequestBody ModelClass modelClass, @PathVariable("id") int id) {
        //obj.putRecord(modelClass, id);

        List<ModelClass> arrayList = new ArrayList<>();
        arrayList = apiService.getRecord();
        arrayList.stream().map(
                b->{ if( b.getId()==id)
                {
                    b.setName(modelClass.getName());
                    b.setStatus(modelClass.getStatus());
                    b.setImageUrl(modelClass.getImageUrl());
                }
                    return b;
                }

        ).collect(Collectors.toList());

        apiService.updateRecord(modelClass,id);
        System.out.println("nothing");
        return modelClass;
    }
}