package com.calculator.android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button num0,num1,num2,num3,num4,num5,num6,num7,num8,num9,bpl,bmi,bmu,bdi,bde,bac,bdo,bpe,beq;
    private EditText txt;
    private String currentText="";
    private boolean counted = false;
    private String former ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt=(EditText)findViewById(R.id.edit_text);
        num0=(Button)findViewById(R.id.zero_btn );
        num1=(Button)findViewById(R.id.one_btn );
        num2=(Button)findViewById(R.id.two_btn );
        num3=(Button)findViewById(R.id.three_btn );
        num4=(Button)findViewById(R.id.four_btn );
        num5=(Button)findViewById(R.id.five_btn );
        num6=(Button)findViewById(R.id.six_btn );
        num7=(Button)findViewById(R.id.seven_btn );
        num8=(Button)findViewById(R.id.eight_btn );
        num9=(Button)findViewById(R.id.nine_btn );
        bpl=(Button)findViewById(R.id.plus_btn );
        bmi=(Button)findViewById(R.id.minus_btn);
        bmu=(Button)findViewById(R.id.multiply_btn );
        bdi=(Button)findViewById(R.id.divide_btn );
        bac=(Button)findViewById(R.id.ac_btn );
        bde=(Button)findViewById(R.id.del_btn );
        bdo=(Button)findViewById(R.id.dot_btn );
        bpe=(Button)findViewById(R.id.percent_btn );
        beq=(Button)findViewById(R.id.equal_btn );
        currentText=txt.getText().toString();

        num0.setOnClickListener(this);
        num1.setOnClickListener(this);
        num2.setOnClickListener(this);
        num3.setOnClickListener(this);
        num4.setOnClickListener(this);
        num5.setOnClickListener(this);
        num6.setOnClickListener(this);
        num7.setOnClickListener(this);
        num8.setOnClickListener(this);
        num9.setOnClickListener(this);
        bpl.setOnClickListener(this);
        bmi.setOnClickListener(this);
        bmu.setOnClickListener(this);
        bdi.setOnClickListener(this);
        bac.setOnClickListener(this);
        bde.setOnClickListener(this);
        bdo.setOnClickListener(this);
        bpe.setOnClickListener(this);
        beq.setOnClickListener(this);

    }
    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.ac_btn:
                currentText ="0";
                break;

            case R.id.del_btn:
                if(currentText.equals("ERROR")){
                    currentText ="0";

                }else if(currentText.length()>0){
                    if(currentText.length()==1){
                        currentText ="0";
                        //counted=false;
                    }else{
                        currentText = currentText.substring(0,currentText.length()-1);
                    }
                }
                break;

            case R.id.dot_btn:
                if(counted){
                    currentText="0.";
                    counted = false;
                }else{
                    if(currentText.contains("+")||currentText.contains("-")||currentText.contains("×")||currentText.contains("÷")){
                        former=GetContent();
                        if(!former.contains(".")){
                            if(former.equals("")){
                                currentText +="0.";
                            }else if(!former.contains("%")){
                            currentText +=".";
                            }
                        }
                    }else {
                        if (!currentText.contains(".")) {
                            if (currentText.equals("")) {
                                currentText += "0.";
                            } else if(!currentText.contains("%")){
                                currentText += ".";
                            }
                        }
                    }
                }
                break;

            case R.id.plus_btn:
                currentText =Symbol("+");
                break;

            case R.id.minus_btn:
                currentText =Symbol("-");
                break;

            case R.id.multiply_btn:
                currentText =Symbol("×");
                break;

            case R.id.divide_btn:
                currentText =Symbol("÷");
                break;
            case R.id.equal_btn:
                if(Judge()){
                    currentText = GetResult();
                    counted =true;
                }
                break;

            case R.id.percent_btn:
                former=currentText.substring(currentText.length()-1);
                if(former.equals("0")||former.equals("1")||former.equals("2")||former.equals("3")||former.equals("4")||former.equals("5")||former.equals("6")||former.equals("7")||former.equals("8")||former.equals("9")){
                    currentText +="%";
                }
                break;

            case R.id.zero_btn:
                currentText =Number(currentText,"0");
                break;

            case R.id.one_btn:
                currentText =Number(currentText,"1");
                break;

            case R.id.two_btn:
                currentText =Number(currentText,"2");
                break;

            case R.id.three_btn:
                currentText =Number(currentText,"3");
                break;

            case R.id.four_btn:
                currentText =Number(currentText,"4");
                break;

            case R.id.five_btn:
                currentText =Number(currentText,"5");
                break;

            case R.id.six_btn:
                currentText =Number(currentText,"6");
                break;

            case R.id.seven_btn:
                currentText =Number(currentText,"7");
                break;

            case R.id.eight_btn:
                currentText =Number(currentText,"8");
                break;

            case R.id.nine_btn:
                currentText =Number(currentText,"9");
                break;
        }
        txt.setText(currentText );
    }

    private String Number(String currentText ,String n){
        if(counted){
            currentText=n;
            counted =false;
        }else{
            former=currentText.substring(currentText.length() - 1);
            if(!former.contains("%")){
                if(currentText.contains("+")||currentText.contains("-")||currentText.contains("×")||currentText.contains("÷")) {
                    former=GetContent();
                    if(former.equals("0")){
                        currentText=currentText.substring(0,currentText.length()-1);
                        currentText +=n;
                    }else{
                        currentText +=n;
                    }
                }else{
                    if(currentText.equals("0")){
                        currentText=n;
                    }else{
                        currentText +=n;
                    }
                }
            }

        }
        return currentText;
    }

    private boolean Judge(){
        String latter="a";
        if(currentText.contains("+")||currentText.contains("-")||currentText.contains("×")||currentText.contains("÷")) {
            if (currentText.contains("+")) {
                former = currentText.substring(currentText.indexOf("+") + 1);
            }else if (currentText.contains("×")) {
                former = currentText.substring(currentText.indexOf("×") + 1);
            }else if (currentText.contains("÷")) {
                former = currentText.substring(currentText.indexOf("÷") + 1);
            }else if (currentText.contains("-")) {
                latter = currentText.substring(0,currentText.lastIndexOf("-"));
                 former= currentText.substring(currentText.lastIndexOf("-") + 1);
            }
            if (former.equals("")||latter.equals("")) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }
    }

    private String GetResult(){
        String result=null;
        String numa;
        String numb;
        double pa;
        double pb;
        double temResult;
        if(currentText.contains("+") ){
            numa=currentText.substring(0,currentText.indexOf("+"));
            numb=currentText.substring(currentText.indexOf("+")+1 );
            pa=IsPercent(numa);
            pb=IsPercent(numb);
            temResult=pa+pb;
            result=String.valueOf(temResult);
        }else if(currentText.contains("×") ){
            numa=currentText.substring(0,currentText.indexOf("×"));
            numb=currentText.substring(currentText.indexOf("×")+1 );
            pa=IsPercent(numa);
            pb=IsPercent(numb);
            temResult=pa*pb;
            result=String.valueOf(temResult);
        }else if(currentText.contains("÷") ){
            numa=currentText.substring(0,currentText.indexOf("÷"));
            numb=currentText.substring(currentText.indexOf("÷")+1 );
            pa=IsPercent(numa);
            pb=IsPercent(numb);
            if(pb==0) {
                result="ERROR";
            }else {
                temResult=pa/pb;
                result = String.valueOf(temResult);
            }
        }else if(currentText.contains("-") ){
            numa=currentText.substring(0,currentText.lastIndexOf("-"));
            numb=currentText.substring(currentText.lastIndexOf("-")+1 );
            pa=IsPercent(numa);
            pb=IsPercent(numb);
            temResult=pa-pb;
            result=String.valueOf(temResult);
        }
        return result;
    }

    private double IsPercent(String a){
        double b;
        if(a.contains("%")){
            a=a.substring(0,a.length()-1);
            b=Double.parseDouble(a);
            b*=0.01;
        }else{
            b=Double.parseDouble(a);
        }
        return b;
    }

    private String GetContent(){
        if (currentText.contains("+")) {
            former = currentText.substring(currentText.indexOf("+") + 1);
        }else if (currentText.contains("×")) {
            former = currentText.substring(currentText.indexOf("×") + 1);
        }else if (currentText.contains("÷")) {
            former = currentText.substring(currentText.indexOf("÷") + 1);
        }else if (currentText.contains("-")) {
            former = currentText.substring(currentText.lastIndexOf("-") + 1);
        }
        return former;
    }

    private String Symbol(String n){
        if(Judge()) {
            currentText = GetResult();
            if(!currentText.equals("ERROR")){
                currentText+=n;
            }
        }else {
            former = currentText.substring(currentText.length() - 1);
            if (former.equals("+") || former.equals("-") || former.equals("×") || former.equals("÷") || former.equals(".")) {
                currentText = currentText.substring(0, currentText.length() - 1);
                currentText += n;
            } else if (!currentText.equals("")) {
                currentText += n;
            }
            if (counted) {
                counted = false;
            }
        }
        return currentText;
    }
}
