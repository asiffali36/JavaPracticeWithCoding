package com.example.demo.bottApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ApiService {
    private ApiRepo apiRepo;

    @Autowired
    public ApiService(ApiRepo apiRepo) {
        this.apiRepo = apiRepo;
    }



    public List<ModelClass> getRecord(){
        return apiRepo.getRecord();
    }


    public int insertRecord(ModelClass modelClass){

        return apiRepo.insertRecord(modelClass);
    }
    public int deleteRecord(int id){
        return apiRepo.deleteRecord(id);
    }
    public int updateRecord(ModelClass modelClass,int id){
        return apiRepo.updateRecord(modelClass,id);
    }

}
