package com.wixsite.mupbam1.resume.searchwidgetjc

//https://www.youtube.com/watch?v=3oXBnM6fZj0&list=PLSrm9z4zp4mEWwyiuYgVMWcDFdsebhM-r&index=30

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import com.wixsite.mupbam1.resume.searchwidgetjc.ui.theme.SearchWidgetJCTheme

class MainActivity : ComponentActivity() {
    private val mainViewModel:MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SearchWidgetJCTheme {
               MainScreen(mainViewModel = mainViewModel )
            }
        }
    }
}

