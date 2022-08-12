package com.example.demo;

import com.example.demo.bottApi.ApiController;
import com.example.demo.bottApi.ModelClass;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


public class UsHouseAdministrationBot {
    @Autowired
    ApiController apiController;
    String MemberName;
    String MemberDesignation;
    String imageLink;
    ModelClass modelClass;
    ArrayList<ModelClass> arrayList = new ArrayList<>();
    public ArrayList<ModelClass> usBotApi()
    {
        AtomicInteger ID_GENERATOR = new AtomicInteger(1);
        Document doc = null;
        try {
            doc = Jsoup.connect("https://cha.house.gov/taxonomy/term/801").get();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        doc.head();
        ModelClass modelClassObj ;
        Elements column = doc.select("#region-content > div > div > div.center-wrapper > div.panel-col-last.panel-panel >div");
        if (arrayList.isEmpty()) {
            for (Element headline : column) {

                for (Element image : headline.select(".each-member")) {
                    Elements imageData = image.select("img");
                     imageLink = imageData.attr("src");
                     MemberName = imageData.attr("title");
                     MemberDesignation = image.select(".each-member-ss-dd").text();

                    String[] splitdesig = MemberDesignation.split("~");
                    String state = splitdesig[1];

                    int autoVal = ID_GENERATOR.getAndIncrement();

                    modelClassObj = new ModelClass(autoVal, MemberName, state, imageLink);
                    arrayList.add(modelClassObj);
                }
            }
            System.out.println(arrayList);
        }
        return arrayList;
    }

    public ModelClass addRecord(ModelClass record)
    {
        arrayList.add(record);
        return record;
    }
    public ModelClass getRecordById(int id)
    {
        Optional<ModelClass> id_val = arrayList.stream().filter(u -> u.getId() == (int) id).findFirst();
        ModelClass modelClass =  id_val.get();
        return modelClass;
    }

    public void putRecord(ModelClass modelClass,int id)
    {
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
    }
    public ArrayList<ModelClass> deleteRecord(int id)
    {
       arrayList= (ArrayList<ModelClass>) arrayList.stream().filter(ModelClass->ModelClass.getId()!=id).collect(Collectors.toList());
        System.out.println(arrayList);
        return  arrayList;
    }

//    public void insertRecord(ModelClass modelClass) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        System.out.println("Enter your Name");
//        String name = br.readLine();
//
//        System.out.println("Enter your des");
//        String desig = br.readLine();
//
//        System.out.println("Enter your imageUrl");
//        String image = br.readLine();
//        {  modelClass.setName(name),
//                modelClass.setStatus(desig),
//                modelClass.setImageUrl(image)}
//        apiController.insert(modelClass);
//
//
//    }
}
