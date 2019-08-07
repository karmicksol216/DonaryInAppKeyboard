package com.karmic.ViewHolder

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView


import android.widget.LinearLayout
import com.karmic.simplekeyboard.R


class EnglishKeySqrViewHolder (inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.eng_sqr_kbd_list_item, parent, false)) {

    public var btn1_img: ImageView? = null

    public var selectalphabet:RelativeLayout?=null
    private var previousPosition = 0

    init {
        btn1_img = itemView.findViewById(R.id.btn1_img)
        selectalphabet=itemView.findViewById(R.id.selectalphabet)
        selectalphabet!!.setOnClickListener {
        }
    }

}