package com.st.jombaykotlinassignment

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast

val ViewGroup.layoutInflater: LayoutInflater get() = LayoutInflater.from(context)

fun Context.toast(message: String?) = Toast.makeText(this, message, Toast.LENGTH_LONG).show();
