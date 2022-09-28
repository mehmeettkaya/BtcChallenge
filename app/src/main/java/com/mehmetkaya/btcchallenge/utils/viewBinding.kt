package com.mehmetkaya.btcchallenge.utils

import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

inline fun <T : ViewBinding> AppCompatActivity.viewBinding(
    crossinline viewBindingFactory: (LayoutInflater) -> T
) = unsafeLazy { viewBindingFactory(layoutInflater) }

fun <T : ViewBinding> Fragment.viewBinding(
    viewBindingFactory: (View) -> T
) = FragmentViewBindingDelegate(this, viewBindingFactory)

inline fun <T : ViewBinding> View.viewBinding(
    crossinline viewBindingFactory: (View) -> T
) = unsafeLazy { viewBindingFactory(this) }
