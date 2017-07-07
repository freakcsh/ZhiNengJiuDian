package com.androidjob.zhinengjiudian;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by thinkpad on 2016/8/10.
 */
public class LoginActivity extends Activity{
    private EditText edtUser;
    private EditText edtPass;
    private ImageButton login_DengLu;
    private ImageButton login_ZuCe;
    private TextView login_Forget;
    private CheckBox ch_Remember;
    SharedPreferences pref;
    SharedPreferences.Editor edtior;

    private Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        chuShiHua();
        spinner=(Spinner)findViewById(R.id.name);
        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(this,R.array.names,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(0,true);

        spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                ((TextView) arg0.getChildAt(0)).setTextColor(Color.WHITE);
                arg0.setVisibility(View.VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                ((TextView) arg0.getChildAt(0)).setTextColor(Color.WHITE);
                arg0.setVisibility(View.VISIBLE);
            }
        });
    }
    public void chuShiHua(){
        edtUser=(EditText)findViewById(R.id.edtUser);
        edtPass=(EditText)findViewById(R.id.edtPass);
        login_DengLu=(ImageButton) findViewById(R.id.login_DengLu);
        login_ZuCe=(ImageButton)findViewById(R.id.login_ZuCe);
        login_Forget=(TextView)findViewById(R.id.login_Forget);
        ch_Remember=(CheckBox)findViewById(R.id.ch_Remember);
        pref=getSharedPreferences("Login",MODE_PRIVATE);
        edtior=pref.edit();
        String name = pref.getString("userName", "");
        if (name==null) {
            ch_Remember.setChecked(false);
        }else {
            ch_Remember.setChecked(true);
            edtUser.setText(name);
        }
    }
    public void dengLuOnClick(View v){
        switch (v.getId()) {
            case R.id.login_DengLu:
                String name = edtUser.getText().toString().trim();
                String pass = edtPass.getText().toString().trim();
                if ("admin".equals(name)&&"123456".equals(pass)) {
                    if (ch_Remember.isChecked()) {
                        edtior.putString("password",pass);
                        edtior.putString("userName", name);
                        edtior.commit();
                        Intent iLogin=new Intent();
                        iLogin.setClass(LoginActivity.this,MainActivity.class);
                        startActivity(iLogin);
                        finish();
                    }else {
                        edtior.remove("userName");
                        edtior.remove("password");
                        edtior.commit();
                    }
                    Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(LoginActivity.this, "禁止登陆", Toast.LENGTH_LONG).show();
                }
                break;

            default:
                break;
        }
    }




}
