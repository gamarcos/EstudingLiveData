package br.com.gabrielmarcos.estudinglivedata.base.view.util

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Build
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@SuppressLint("ObsoleteSdkInt")
fun isLollipopOrHigher() : Boolean {
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
}

fun loadImageIntoView(itemView: View, view: ImageView, url: String) {
    Glide
        .with(itemView)
        .load(url)
        .apply(
            RequestOptions()
                .error(br.com.gabrielmarcos.estudinglivedata.R.drawable.ic_highlight_off)
                .centerCrop())
        .into(view)
}

fun hideKeyboardFrom(context: Context?, view: View?) {
    val inputMethodManager = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)
}

fun showKeyboard(ettext: EditText, context: Context?) {
    ettext.requestFocus()
    ettext.postDelayed({
        val keyboard = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager?
        keyboard!!.showSoftInput(ettext, 0)
    }, 200)
}