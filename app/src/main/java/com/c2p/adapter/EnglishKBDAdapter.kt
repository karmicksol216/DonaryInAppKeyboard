package com.c2p.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.c2p.ViewHolder.EnglishKeyViewHolder
import java.lang.Exception
import android.view.View
import com.bumptech.glide.Glide
import com.c2p.ViewHolder.EnglishKeySqrViewHolder
import com.c2p.donaryInAppKeyboard.R


class EnglishKBDAdapter constructor(context:Context,  listener:OnItemClickListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    var context:Context?=null
    private var listener: OnItemClickListener? = null

    private val TYPE_1 = 1
    private val TYPE_2 = 2

    val alphabets = arrayOf<String>("A","B","C","D","E","F","G","H","I","J","K","L","*","M","N","O","P","Q","*","R","S","T","U","V","<","W","X","\u2423","Y","Z")

    private var selectedPosition = -1

    private var selectCheck: ArrayList<Int>? = null

    init {
        this.selectCheck=ArrayList()
        this.context=context
        this.listener=listener

    }


    override fun onCreateViewHolder(view: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
       val inflater = LayoutInflater.from(view.context)

        var v:View?=null

        if (viewType == TYPE_1) { // for 1
            return EnglishKeySqrViewHolder(inflater,view)
        } else { // for email layout
            return EnglishKeyViewHolder(inflater,view)
        }


    }

    override fun getItemViewType(position: Int): Int {
         if (position==12 || position==18) {
             return TYPE_1

        } else {
             return TYPE_2
        }
    }
    override fun getItemCount(): Int {
        //return list!!.size
        return 30
    }



    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        var view:  RecyclerView.ViewHolder

        if (getItemViewType(position) == TYPE_1) {
            //(viewHolder as CallViewHolder).setCallDetails(employees.get(position))
            var view:  EnglishKeySqrViewHolder=holder as EnglishKeySqrViewHolder
            try {
                //view.btn1_img!!.text = alphabets.get(position)
                if(position== 12){
                    Glide.with(context!!)
                            .load(R.drawable.hebrew_icon)
                            .into(view.btn1_img!!);
                }
                if(position==18){
                    Glide.with(context!!)
                            .load(R.drawable.number_icon)
                            .into(view.btn1_img!!);
                }
            }catch (e:Exception){ e.printStackTrace()}

            view.selectalphabet!!.setOnClickListener {
                selectedPosition=position;
                notifyDataSetChanged();
                var total=getItemCount()
                listener!!.onItemClick(view,position,total);
            }

        } else {
            //(viewHolder as EmailViewHolder).setEmailDetails(employees.get(position))
            var view:  EnglishKeyViewHolder=holder as EnglishKeyViewHolder
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


    }


    interface OnItemClickListener {
        fun onItemClick(item: RecyclerView.ViewHolder, id:Int, total:Int)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }





}