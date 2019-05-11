package com.example.bmi;
import java.util.ArrayList;
import java.util.List;

public class BearExpert {
        List<String> getname(String gender){ //method list
                List<String> NameList = new ArrayList<String>();
                if (gender.equals("Gentleman")){
                        NameList.add("dogy");
                        NameList.add("caty");
                }else{
                        NameList.add("DoFy");
                        NameList.add("Day");
                }
                return NameList;
        }


}
