package com.multimedia2018.ubaya.a160818031_advweek4.view

import android.view.View
import com.multimedia2018.ubaya.a160818031_advweek4.model.Student

interface ButtonDetailClickListener {
    fun onButtonDetailClick(v: View)
}

interface SDButtonUpdateClickListener {
    fun onSDButtonUpdateClick(v: View)
}

interface SDButtonNotificationClickListener{
    fun onSDButtonNotificationClick(v: View, obj: Student)
}

