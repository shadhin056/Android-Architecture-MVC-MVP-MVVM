package com.shadhin.androidarchitecture

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.shadhin.androidarchitecture.mvc.MVCActivity
import com.shadhin.androidarchitecture.mvp.MVPActivity
import com.shadhin.androidarchitecture.mvvm.MVVMActivity

class MainActivity : AppCompatActivity() {
    private lateinit var btnMVC: Button
    private lateinit var btnMVP: Button
    private lateinit var btnMVVM: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init();
        buttonAction();
    }

    private fun buttonAction() {
        btnMVC.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            //startActivity(intent);
            startActivity(MVCActivity.getIntent(this));
        };
        btnMVP.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            //startActivity(intent);
            startActivity(MVPActivity.getIntent(this));
        };
        btnMVVM.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            //startActivity(intent);
            startActivity(MVVMActivity.getIntent(this));
        }
    }

    private fun init() {
        btnMVC = findViewById(R.id.btnMVC) as Button
        btnMVP = findViewById(R.id.btnMVP) as Button
        btnMVVM = findViewById(R.id.btnMVVM) as Button
    }
}
