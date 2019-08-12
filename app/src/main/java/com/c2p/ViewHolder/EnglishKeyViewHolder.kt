package com.c2p.ViewHolder

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView


import com.c2p.donaryInAppKeyboard.R


class EnglishKeyViewHolder (inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.eng_kbd_list_item, parent, false)) {

    public var btn1_txt: TextView? = null

    public var selectalphabet:RelativeLayout?=null
    private var previousPosition = 0

    init {
        btn1_txt = itemView.findViewById(R.id.btn1_txt)
        selectalphabet=itemView.findViewById(R.id.selectalphabet)
        selectalphabet!!.setOnClickListener {
        }
    }

}