package com.example.edittext

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edt_text.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                if (edt_text.length() >= 20 && edt_text.canPaste == false) {
                    txt_noti.setText("Độ dài nhập liệu của bạn không được vượt quá 20")
                    txt_noti.setTextColor(Color.RED)
                } else {
                    txt_noti.setText("Nhập liệu hợp lệ")
                    txt_noti.setTextColor(Color.BLACK)
                }
                txt_number_charater.setText(edt_text.length().toString())
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int, before: Int,
                count: Int
            ) {
            }
        })

        edt_text.addListener(object : PasteEditTextListener {
            override fun onUpdate() {
                txt_noti.setText("Nhập liệu hợp lệ")
                txt_noti.setTextColor(Color.BLACK)
            }
        })
    }
}