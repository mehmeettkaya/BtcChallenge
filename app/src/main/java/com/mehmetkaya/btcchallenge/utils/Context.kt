package com.mehmetkaya.btcchallenge.utils

import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG

val Context.inflater: LayoutInflater
    get() = LayoutInflater.from(this)

fun Context.showError(message: String) {
    Toast.makeText(this, message, LENGTH_LONG).show()
}
