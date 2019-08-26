package com.c2p.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.c2p.ViewHolder.HebrewKeyViewHolder
import java.lang.Exception
import android.view.View
import com.bumptech.glide.Glide
import com.c2p.ViewHolder.HebrewKeySqrViewHolder
import com.c2p.donaryInAppKeyboards.R


class HebrewKBDAdapter constructor(context:Context, listener:OnItemClickListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    var context:Context?=null
    private var listener: OnItemClickListener? = null

    private val TYPE_1 = 1
    private val TYPE_2 = 2



    val alphabets2 = arrayOf<String>(
            "\u05D5","\u05D4","\u05D3","\u05D2","\u05D1","\u05D0",
            "\u05DB","\u05DA","\u05D9","\u05D8","\u05D7","\u05D6",
            "*","\u05E1","\u05E0","\u05DF","\u05DE","\u05DC",
            "*","\u05E6","\u05E5","\u05E4","\u05E3","\u05E2",
            "<","\u05EA","\u05E9","\u2423","\u05E8","\u05E7")

    //5 ->back || 11 ->menu || 12 ->123 || 17 ->abc || 25 -> space
    val alphabets = arrayOf<String>(
            "\u05D4","\u05D3","\u05D2","\u05D1","\u05D0","*",
            "\u05D9","\u05D8","\u05D7","\u05D6","\u05D5","*",
            "*","\u05E0","\u05DE","\u05DC","\u05DB","*",
            "\u05E7","\u05E6","\u05E4","\u05E2","\u05E1","⇑",
            "\u05EA","*","\u05E9",">","")

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
            return HebrewKeySqrViewHolder(inflater,view)
        } else { // for email layout
            return HebrewKeyViewHolder(inflater,view)
        }


    }

    override fun getItemViewType(position: Int): Int {
         if (position==5 || position==11 || position==12 || position==17 || position==25) {
             return TYPE_1

        } else {
             return TYPE_2
        }
    }
    override fun getItemCount(): Int {
        //return list!!.size
        return alphabets.size-1
    }



    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        var view:  RecyclerView.ViewHolder

        if (getItemViewType(position) == TYPE_1) {

            var view:  HebrewKeySqrViewHolder=holder as HebrewKeySqrViewHolder
            try {
                //view.btn1_img!!.text = alphabets.get(position)
                if(position == 5){
                    Glide.with(context!!)
                            .load(R.drawable.back34)
                            .override(30, 40)
                            .into(view.btn1_img!!);
                }

                if(position == 11){
                    Glide.with(context!!)
                            .load(R.drawable.menu_icon)
                            .into(view.btn1_img!!);
                }

                if(position == 12){
                    Glide.with(context!!)
                            .load(R.drawable.number_icon)
                            .into(view.btn1_img!!);
                }
                if(position == 17){
                    Glide.with(context!!)
                            .load(R.drawable.alphabet_icon)
                            .into(view.btn1_img!!);
                }

                if(position == 25){
                    Glide.with(context!!)
                            .load(R.drawable.spbar_big2)
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

            var view:  HebrewKeyViewHolder=holder as HebrewKeyViewHolder
            try {
                view.btn1_txt!!.text = alphabets.get(position)
                if(position==28 || position==29){
                    view.btn1_txt!!.background=context?.getResources()!!.getDrawable(R.drawable.circle_black_key);
                }

            }catch (e:Exception){ e.printStackTrace()}

            view.selectalphabet!!.setOnClickListener {
                selectedPosition=position;
                //notifyDataSetChanged();
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