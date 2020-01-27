package com.websarva.wings.android.sample;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.speech.RecognizerIntent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private LinearLayout rootView;
    private LayoutInflater inflater;
    public int count=0;
    public int math =0;
    public int num;
    public String[] names = new String[100];
    public String[] bunnkai=new String[100];
    //任意の識別番号
    private static final int REQUEST_CODE = 12345;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        setContentView(R.layout.activity_main);

        rootView = findViewById(R.id.root);
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        Button b = findViewById(R.id.button);
        SampleListener sl = new SampleListener();
        b.setOnClickListener(sl);

        View subView = inflater.inflate(R.layout.sub, null);
        rootView.addView(subView, rootView.getChildCount() - 1);
        View subView1 = inflater.inflate(R.layout.sub1, null);
        rootView.addView(subView1, rootView.getChildCount() - 1);
        View subView2 = inflater.inflate(R.layout.sub2, null);
        rootView.addView(subView2, rootView.getChildCount() - 1);
        View subView3 = inflater.inflate(R.layout.sub3, null);
        rootView.addView(subView3, rootView.getChildCount() - 1);
        View subView4 = inflater.inflate(R.layout.sub4, null);
        rootView.addView(subView4, rootView.getChildCount() - 1);
        View subView5 = inflater.inflate(R.layout.sub5, null);
        rootView.addView(subView5, rootView.getChildCount() - 1);
        View subView6 = inflater.inflate(R.layout.sub6, null);
        rootView.addView(subView6, rootView.getChildCount() - 1);
        View subView7 = inflater.inflate(R.layout.sub7, null);
        rootView.addView(subView7, rootView.getChildCount() - 1);
        View subView8 = inflater.inflate(R.layout.sub8, null);
        rootView.addView(subView8, rootView.getChildCount() - 1);
        View subView9 = inflater.inflate(R.layout.sub9, null);
        rootView.addView(subView9, rootView.getChildCount() - 1);

        vois();

    }

    private class SampleListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            //View subView = inflater.inflate(R.layout.sub, null);
            //rootView.addView(subView, rootView.getChildCount() - 1);

            vois();

        }
    }

    //音声認識が終わると自動で呼び出されるメソッド
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            ArrayList<String> kekka = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

            for(int i =0;i<bunnkai.length;i++){
                if(bunnkai[i]!=null){
                    count=count+1;
                }
            }
            if (kekka.get(0).contains("と")) {
                bunnkai = kekka.get(0).split("と");
            }else{
                bunnkai[0] = kekka.get(0);
            }
            math=0;
            for(int i =0;i<bunnkai.length;i++){
                if(bunnkai[i]!=null){
                    math=math+1;
                }
            }

            storage();
            if((kekka.get(0).contains("入力終了"))||(kekka.get(0).contains("終了"))){
                //入力終了の文字を削除する
                names[count+math-1]="";
                count=count-1;
            }else{
                vois();
            }
            tw();
        }
    }

    public void storage() {
        num = count;
        if (count == 0) {
            for (int i = 0; i < math; i++) {
                names[i] = bunnkai[i];
                num = num + 1;
            }
        } else {
            for (int i = count; i < count + math; i++) {
                names[i] = bunnkai[i - count];
                num = num + 1;
            }
        }
    }

    public void vois(){
        //音声認識用のインテントを作成
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        //認識する言語を指定（この場合は日本語）
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.JAPANESE.toString());
        //認識する候補数の指定
        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 10);
        //音声認識時に表示する案内を設定
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "商品の入力が可能です");
        //音声認識を開始
        //intent.putExtra(RecognizerIntent.EXTRA_PREFER_OFFLINE, true);
        startActivityForResult(intent, REQUEST_CODE);

    }
    public void tw(){
        TextView text = (TextView) findViewById(R.id.sub);
        text.setText(names[0]);
        TextView text1 = (TextView) findViewById(R.id.sub1);
        text1.setText(names[1]);
        TextView text2 = (TextView) findViewById(R.id.sub2);
        text2.setText(names[2]);
        TextView text3 = (TextView) findViewById(R.id.sub3);
        text3.setText(names[3]);
        TextView text4 = (TextView) findViewById(R.id.sub4);
        text4.setText(names[4]);
        TextView text5 = (TextView) findViewById(R.id.sub5);
        text5.setText(names[5]);
        TextView text6 = (TextView) findViewById(R.id.sub6);
        text6.setText(names[6]);
        TextView text7 = (TextView) findViewById(R.id.sub7);
        text7.setText(names[7]);
        TextView text8 = (TextView) findViewById(R.id.sub8);
        text8.setText(names[8]);
        TextView text9 = (TextView) findViewById(R.id.sub9);
        text9.setText(names[9]);


    }
}