package com.oleskiy.sendactionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import top.defaults.colorpicker.ColorPickerPopup;
import top.defaults.colorpicker.ColorPickerView;

public class MainActivity extends AppCompatActivity {
int color = 0;
int index = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ColorPickerView colorPickerView = findViewById(R.id.colorPicker);
        Button btn = findViewById(R.id.send);
        EditText indexItem = findViewById(R.id.index_item);

        indexItem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                    index = Integer.parseInt(s.toString());
            }
        });

        colorPickerView.subscribe((color, fromUser, shouldPropagate) -> {
            setColor(color);
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent=new Intent("Updated");
                intent.putExtra("color",color);
                intent.putExtra("index",index);
                intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                intent.setComponent(
                        new ComponentName("com.oleskiy.taboola","com.oleskiy.taboola.view.util.MyBroadcastReceiver"));
                sendBroadcast(intent);
            }
        });
    }

    public void setColor(int color){
        this.color = color;

    }
}
