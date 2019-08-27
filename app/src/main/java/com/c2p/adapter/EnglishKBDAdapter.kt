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
import com.c2p.donaryInAppKeyboards.R






class EnglishKBDAdapter constructor(context:Context,  listener:OnItemClickListener, listener2:OnItemLongClickListener ): RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    var context:Context?=null
    private var listener: OnItemClickListener? = null
    private var listener2: OnItemLongClickListener? = null

    private val TYPE_1 = 1
    private val TYPE_2 = 2

    val alphabets = arrayOf<String>(
            "A","B","C","D","E","*",
            "F","G","H","I","J","K",
            "L","M","N","O","P","Q",
            "R","S","T","U","V","W",
            "X","Y","Z","\u2423",">"," "
            )

    private var selectedPosition = -1

    private var selectCheck: ArrayList<Int>? = null

    init {
        this.selectCheck=ArrayList()
        this.context=context
        this.listener=listener
        this.listener2=listener2

    }


    override fun onCreateViewHolder(view: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
       val inflater = LayoutInflater.from(view.context)

        var v:View?=null

        if (viewType == TYPE_1) { // for Square
            return EnglishKeySqrViewHolder(inflater,view)
        } else { // for email layout
            return EnglishKeyViewHolder(inflater,view)
        }


    }

    override fun getItemViewType(position: Int): Int {
         if (position==5 || position==27) {
             return TYPE_1 //square

        } else {
             return TYPE_2 //circular
        }
    }
    override fun getItemCount(): Int {
        //return list!!.size
        return alphabets.size-1
    }



    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        var view:  RecyclerView.ViewHolder

        if (getItemViewType(position) == TYPE_1) {
            //(viewHolder as CallViewHolder).setCallDetails(employees.get(position))
            var view:  EnglishKeySqrViewHolder=holder as EnglishKeySqrViewHolder
            try {
                //view.btn1_img!!.text = alphabets.get(position)
                if(position== 5){
                    Glide.with(context!!)
                            .load(R.drawable.back34)
                            //.override(30, 50)
                            .into(view.btn1_img!!);
                }
                if(position==27){
                    Glide.with(context!!)
                            .load(R.drawable.spbar_big2)
                            //.override(160, 120)
                            .into(view.btn1_img!!);

                }
            }catch (e:Exception){ e.printStackTrace()}




            view.selectalphabet!!.setOnClickListener {
                selectedPosition=position;
                notifyDataSetChanged();
                var total=getItemCount()
                listener!!.onItemClick(view,position,total);
            }

            view.selectalphabet!!.setOnLongClickListener(View.OnLongClickListener {
                listener2!!.onItemLongClicked(position)
                true
            })

        } else {
            //(viewHolder as EmailViewHolder).setEmailDetails(employees.get(position))
            var view:  EnglishKeyViewHolder=holder as EnglishKeyViewHolder

            try {
                view.btn1_txt!!.text = alphabets.get(position)
                if(position==29){
                    view.btn1_txt!!.background=context?.getResources()!!.getDrawable(R.drawable.circle_black_key);
                }

            }catch (e:Exception){ e.printStackTrace()}

            view.selectalphabet!!.setOnClickListener {
                selectedPosition=position;
                notifyDataSetChanged();
                var total=getItemCount()
                listener!!.onItemClick(view,position,total);
            }

            view.selectalphabet!!.setOnLongClickListener(View.OnLongClickListener {
                listener2!!.onItemLongClicked(position)
                true
            })
        }


    }


    interface OnItemClickListener {
        fun onItemClick(item: RecyclerView.ViewHolder, id:Int, total:Int)
    }

    interface OnItemLongClickListener {
        fun onItemLongClicked(position: Int): Boolean
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }





}