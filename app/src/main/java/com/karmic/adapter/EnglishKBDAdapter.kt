package com.karmic.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.karmic.ViewHolder.EnglishKeyViewHolder
import java.lang.Exception


class EnglishKBDAdapter constructor(context:Context,  listener:OnItemClickListener): RecyclerView.Adapter<EnglishKeyViewHolder>() {

    var context:Context?=null
    private var listener: OnItemClickListener? = null

    val alphabets = arrayOf<String>("A","B","C","D","E","F","G","H","I","J","K","L","*","M","N","O","P","Q","*","R","S","T","U","V","<","W","X","\u2423","Y","Z")

    private var selectedPosition = -1

    private var selectCheck: ArrayList<Int>? = null

    init {
        this.selectCheck=ArrayList()
        this.context=context
        this.listener=listener

    }


    override fun onCreateViewHolder(view: ViewGroup, p1: Int): EnglishKeyViewHolder {
       val inflater = LayoutInflater.from(view.context)
       return EnglishKeyViewHolder(inflater,view)
    }

    override fun getItemCount(): Int {
        //return list!!.size
        return 30
    }


    override fun onBindViewHolder(view: EnglishKeyViewHolder, position: Int) {

        try {
            view.btn1_txt!!.text = alphabets.get(position)
        }catch (e:Exception){ e.printStackTrace()}

        view.selectalphabet!!.setOnClickListener {
            selectedPosition=position;
            notifyDataSetChanged();
            var total=getItemCount()
            listener!!.onItemClick(view,position,total);
        }
    }


    interface OnItemClickListener {
        fun onItemClick(item: EnglishKeyViewHolder,id:Int,total:Int)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }



}