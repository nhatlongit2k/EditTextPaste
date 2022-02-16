package com.example.edittext

import android.R
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import java.lang.NullPointerException

class PasteEditText : androidx.appcompat.widget.AppCompatEditText {
    var listeners: ArrayList<PasteEditTextListener>
    var isPaste = false

    constructor(context: Context?) : super(context!!) {
        listeners = ArrayList()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs) {
        listeners = ArrayList()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context!!,
        attrs,
        defStyle
    ) {
        listeners = ArrayList()
    }

    fun addListener(listener: PasteEditTextListener) {
        try {
            listeners.add(listener)
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }
    }

    override fun onTextContextMenuItem(id: Int): Boolean {
        when (id) {
            R.id.cut -> onTextCut()
            R.id.paste -> {
                isPaste = true
                onTextPaste()
            }
            R.id.copy -> onTextCopy()
        }
        val consumed = super.onTextContextMenuItem(id)
        return consumed
    }

    fun onTextCut() {}
    fun onTextCopy() {}

    fun onTextPaste() {
        for (listener in listeners) {
            listener.onUpdate()
        }
    }
}