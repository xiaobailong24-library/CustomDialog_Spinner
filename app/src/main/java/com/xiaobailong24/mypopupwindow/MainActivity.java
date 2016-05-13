package com.xiaobailong24.mypopupwindow;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.yarolegovich.lovelydialog.LovelyCustomDialog;

public class MainActivity extends AppCompatActivity implements SpinnerAdapter {

    private static final String TAG = "MainActivity";

    Button mButton;
    LovelyCustomDialog lcd;
    View layout;
    ArrayAdapter<String> adapter1 = null;    //市级适配器
    ArrayAdapter<String> adapter2 = null;    //县级适配器
    ArrayAdapter<String> adapter3 = null;    //乡级适配器
    ArrayAdapter<String> adapter4 = null;    //村级适配器
    int spinner1Position = 0;
    int spinner2Position = 0;
    int spinner3Position = 0;

    @SuppressLint("HandlerLeak")
    public static Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 0: // 更新textView
                    Bundle bundle = (Bundle) msg.obj;
                    //                    et2.setText(bundle.getString("fullN"));
                    //                    et3.setText(bundle.getString("validP"));
                    //                    et4.setText(bundle.getString("fastK"));
                    break;
                case 1: // 根据地区获取数据
                    //                    area = (String) msg.obj;
                    //                    getDataByArea(area);
                    //                    way = 1;
                    break;
                case 2: // 根据GPS获取数据
                    //                    way = 2;
                    break;
            }

        }

        ;
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initSpinner();

        //        SpinnerAdapter.adapter1
        lcd = new LovelyCustomDialog(this);
        lcd.setView(layout);
        //        lcd.setTopColorRes(R.color.darkDeepOrange);
        //        lcd.setTitle("Hello");
        //        lcd.setMessage("World");
        //        lcd.setIcon(R.mipmap.ic_launcher);
        //                        .configureView(/* ... */)

        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "onClick: ");
                lcd.show();
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, "onRestart: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: ");
    }


    private void initSpinner() {
        Log.e(TAG, "initSpinner");
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        layout = layoutInflater.inflate(R.layout.mypopup, null);
        final Spinner spinner1 = (Spinner) layout.findViewById(R.id.sel_spinner1);
        final Spinner spinner2 = (Spinner) layout.findViewById(R.id.sel_spinner2);
        final Spinner spinner3 = (Spinner) layout.findViewById(R.id.sel_spinner3);
        final Spinner spinner4 = (Spinner) layout.findViewById(R.id.sel_spinner4);

        //市
        adapter1 = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_spinner_item, str_spinner1);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setSelection(0, true);  //设置默认选中项，此处为默认选中第1个值
        //县
        adapter2 = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_spinner_item, str_spinner2[0]);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setSelection(0, true);  //默认选中第0个
        //乡镇
        adapter3 = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_spinner_item, str_spinner3[0][0]);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);
        spinner3.setSelection(0, true);
        //村庄
        adapter4 = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_spinner_item, str_spinner4[0][0][0]);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(adapter4);
        spinner4.setSelection(0, true);

        spinner1.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                //arg2为当前省级选中的值的序号
                //将地级适配器的值改变为city[position]中的值
                adapter2 = new ArrayAdapter<String>(
                        MainActivity.this, android.R.layout.simple_spinner_item, str_spinner2[arg2]);
                adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                // 设置二级下拉列表的选项内容适配器
                spinner2.setAdapter(adapter2);
                spinner1Position = arg2;    //记录当前市级序号，留给下面修改县级适配器时用

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });
    /*
     *
	 *
	 */
        spinner2.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                adapter3 = new ArrayAdapter<String>(MainActivity.this,
                        android.R.layout.simple_spinner_item, str_spinner3[spinner1Position][arg2]);
                adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner3.setAdapter(adapter3);
                spinner2Position = arg2;    //记录当前级序号，留给下面修改县级适配器时用
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });
        spinner3.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                adapter4 = new ArrayAdapter<String>(MainActivity.this,
                        android.R.layout.simple_spinner_item, str_spinner4[spinner1Position][spinner2Position][arg2]);
                adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner4.setAdapter(adapter4);
                spinner3Position = arg2;    //记录当前级序号，留给下面修改县级适配器时用
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

        Button okButton = (Button) layout.findViewById(R.id.dialog_button_ok);
        Button cancleButton = (Button) layout.findViewById(R.id.dialog_button_cancel);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "okButton clicked");
                lcd.dismiss();
                Log.d(TAG, spinner1.getSelectedItem().toString() + spinner2.getSelectedItem()
                        + spinner3.getSelectedItem() + spinner4.getSelectedItem());
                Toast.makeText(MainActivity.this, "您选择了" + spinner1.getSelectedItem().toString() + spinner2.getSelectedItem()
                        + spinner3.getSelectedItem() + spinner4.getSelectedItem(), Toast.LENGTH_LONG).show();


                //发送消息
                Message msg = MainActivity.handler.obtainMessage();
                msg.what = 1;
                msg.obj = spinner4.getSelectedItem().toString();
                MainActivity.handler.sendMessage(msg);



            }
        });
        cancleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "cancleButton clicked");
                lcd.dismiss();
            }
        });

    }

}

