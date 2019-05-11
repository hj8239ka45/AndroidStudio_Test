package com.example.bmi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Spinner;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
    }

    private BearExpert expert = new BearExpert();//call the BearExpert as expert

    //使用者點擊時呼叫
    public void onClickBMI(View view){

        TextView weight = (TextView) findViewById(R.id.weight);
        EditText height = (EditText) findViewById(R.id.height);
        Spinner gender = (Spinner) findViewById(R.id.gender);
        String Gender = String.valueOf(gender.getSelectedItem());//將spinner選取物件轉為gender

        List<String> namelist = expert.getname(Gender);
        StringBuilder nameformat = new StringBuilder();
        for(String gneder:namelist){
            nameformat.append(gneder).append("\n");
        }
        TextView name = (TextView) findViewById(R.id.name);
        name.setText(nameformat);


        int R_BMI[] = new int[2];
        if (Gender.equals("Gentleman")){    //must be String type
            R_BMI[0] = 25;R_BMI[1] = 20;
        }else if(Gender.equals("Lady")){
            R_BMI[0] = 23;R_BMI[1] = 18;
        }else{
            R_BMI[0] = 24;R_BMI[1] = 19;
        }
        double Height;
        double Weight;
        double BMI = 0;

        int flag = 0;//引入flag控制流程
        //未輸入數值會出現error
        try {
            //身高
            Height = Double.parseDouble(height.getText().toString())/100;
            //體重
            Weight = Double.parseDouble(weight.getText().toString());
            //計算出BMI值
            BMI = Weight / (Height*Height);
        } catch (Exception e) {
            flag = 1;
        }

        if (flag==0) {
            DecimalFormat nf = new DecimalFormat("0.00");
            //結果
            TextView result = (TextView) findViewById(R.id.result);
            result.setText(getText(R.string.bmi_result) + nf.format(BMI));
            //建議
            TextView fieldsuggest = (TextView) findViewById(R.id.suggest);
            if (BMI > R_BMI[0]) //太重了
                fieldsuggest.setText(R.string.advice_heavy);
            else if (BMI < R_BMI[1]) //太輕了
                fieldsuggest.setText(R.string.advice_light);
            else //剛剛好
                fieldsuggest.setText(R.string.advice_average);
        }else{
            TextView result = (TextView) findViewById(R.id.result);
            result.setText(getText(R.string.Error_code));
            System.out.println("true");
        }
    }

    /*
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(calcBMI);
    }

    private View.OnClickListener calcBMI = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DecimalFormat nf = new DecimalFormat("0.00");
            EditText fieldheight = (EditText)findViewById(R.id.height);
            EditText fieldweight = (EditText)findViewById(R.id.weight);
            //身高
            double height = Double.parseDouble(fieldheight.getText().toString())/100;
            //體重
            double weight = Double.parseDouble(fieldweight.getText().toString());
            //計算出BMI值
            double BMI = weight / (height*height);

            //結果
            TextView result = (TextView)findViewById(R.id.result);
            result.setText(getText(R.string.bmi_result) + nf.format(BMI));

            //建議
            TextView fieldsuggest = (TextView)findViewById(R.id.suggest);
            if(BMI > 25) //太重了
                fieldsuggest.setText(R.string.advice_heavy);
            else if(BMI < 20) //太輕了
                fieldsuggest.setText(R.string.advice_light);
            else //剛剛好
                fieldsuggest.setText(R.string.advice_average);
        }
    };*/

}