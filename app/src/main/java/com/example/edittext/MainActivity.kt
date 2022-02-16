package com.example.edittext

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var currentLength = 0
    private var maxLength = 20
    private var currentFilter = maxLength

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edt_text.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
//                if (edt_text.length() >= 20) {
//                    if (edt_text.isPaste == false){
//                        txt_noti.setText("Độ dài nhập liệu của bạn không được vượt quá 20")
//                        txt_noti.setTextColor(Color.RED)
//                    }else{
//                        txt_noti.setText("Nhập liệu hợp lệ")
//                        txt_noti.setTextColor(Color.BLACK)
//                    }
//                } else {
//                    txt_noti.setText("Nhập liệu hợp lệ")
//                    txt_noti.setTextColor(Color.BLACK)
//                }

//                if (edt_text.isPaste == false){
//                    if (edt_text.length() >= 20) {
//                        if(edt_text.canInput == false){
//                            edt_text.setText()
//                        }
//                        txt_noti.setText("Độ dài nhập liệu của bạn không được vượt quá 20")
//                        txt_noti.setTextColor(Color.RED)
//                    }else{
//                        txt_noti.setText("Nhập liệu hợp lệ")
//                        txt_noti.setTextColor(Color.BLACK)
//                    }
//                } else {
//                    txt_noti.setText("Nhập liệu hợp lệ")
//                    txt_noti.setTextColor(Color.BLACK)
//                }
//
//                txt_number_charater.setText(edt_text.length().toString())
//                edt_text.isPaste = false

                currentLength = s?.length!!

                if (currentLength == maxLength + 1 && edt_text.isPaste == false) {
                    edt_text.text = s.delete(maxLength, 21)
                    edt_text.setSelection(edt_text.length())
                    txt_noti.setTextColor(Color.RED)
                    txt_noti.text = "Độ dài nhập liệu của bạn không được vượt quá 20"
                } else if (currentLength > maxLength) {
                    if (currentLength == currentFilter + 1) {
                        Log.d("TAG", "afterTextChanged: ")
                        edt_text.text = s.delete(currentFilter, currentLength)
                        edt_text.setSelection(edt_text.length())
                        txt_noti.setTextColor(Color.RED)
                        txt_noti.text = "Độ dài nhập liệu của bạn không được vượt quá 20"
                    }
                    currentFilter = currentLength
                    txt_noti.setTextColor(Color.BLACK)
                    txt_noti.text = "Hợp lệ"
                } else {
                    edt_text.filters = arrayOf()
                    txt_noti.setTextColor(Color.BLACK)
                    txt_noti.setText("Hợp lệ")
                    txt_number_charater.text = edt_text.text?.length.toString()
                }

                edt_text.isPaste = false
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