package test_data;


import java.util.HashMap;
import java.util.Map;

public class GoRestTestData {

    public Map<String,String>dataKeyMap(String name, String email, String gender, String status){
        Map<String,String> dataKeyMap= new HashMap<>();
        dataKeyMap.put("name", "Dharani Shukla");
        dataKeyMap.put("email","dharani_shukla@dare.io" );
        dataKeyMap.put("gender", "male");
        dataKeyMap.put("status", "active");

    return dataKeyMap;
    }

    public Map<String,Object>expectedDataMap(Object meta,Map<String,String> data){

        Map<String,Object>expectedData= new HashMap<>();
        expectedData.put("meta", meta);
        expectedData.put("data", data);

        return expectedData;
    }
}
