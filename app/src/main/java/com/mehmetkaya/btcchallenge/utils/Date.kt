package com.mehmetkaya.btcchallenge.utils

import android.annotation.SuppressLint
import java.util.*

@SuppressLint("NewApi")
fun Date.toEpochSecond() = this.toInstant().epochSecond
