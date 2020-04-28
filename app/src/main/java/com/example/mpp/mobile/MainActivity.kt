package com.example.mpp.mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mpp.mobile.extension.commitTransaction
import com.example.mpp.mobile.breeds.presentation.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null){
            commitTransaction {
                add(
                    R.id.container,
                    MainFragment()
                )
            }
        }
    }
}
