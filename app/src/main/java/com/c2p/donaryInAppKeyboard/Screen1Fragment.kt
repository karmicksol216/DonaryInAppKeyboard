package com.c2p.donaryInAppKeyboard


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import com.c2p.adapter.EnglishKBDAdapter
import com.c2p.donaryInAppKeyboards.R

import com.c2p.donaryInAppKeyboard.MainActivity as MainActivity1


class Screen1Fragment : Fragment() , View.OnClickListener{


    var mContext: Context?= null
    public var mActivity: AppCompatActivity? = null
    public var edt1: EditText?=null
    public var edt2:EditText?=null
    public var edt3:EditText?=null

    public var edt1ll: LinearLayout?=null
    public var edt2ll:LinearLayout?=null
    public var edt3ll:LinearLayout?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view : View =inflater.inflate(R.layout.fragment_screen1, container, false)
        mContext=activity as Context
        mActivity= activity as MainActivity1?

        edt1 = view.findViewById(R.id.edt1) as EditText
        edt2 = view.findViewById(R.id.edt2) as EditText
        edt3 = view.findViewById(R.id.edt3) as EditText

        edt1ll = view.findViewById(R.id.edt1ll) as LinearLayout
        edt2ll = view.findViewById(R.id.edt2ll) as LinearLayout
        edt3ll = view.findViewById(R.id.edt3ll) as LinearLayout


        edt1?.setOnClickListener(this@Screen1Fragment)
        edt2?.setOnClickListener(this@Screen1Fragment)
        edt3?.setOnClickListener(this@Screen1Fragment)

        edt1ll?.setOnClickListener(this@Screen1Fragment)
        edt2ll?.setOnClickListener(this@Screen1Fragment)
        edt3ll?.setOnClickListener(this@Screen1Fragment)


        edt1?.setShowSoftInputOnFocus(false)
        edt2?.setShowSoftInputOnFocus(false)
        edt3?.setShowSoftInputOnFocus(false)

        //-- this is required show that system keyboard dont popup while editext
        //gets focus.

        /*edt1?.setInputType(InputType.TYPE_NULL)
        edt2?.setInputType(InputType.TYPE_NULL)
        edt3?.setInputType(InputType.TYPE_NULL)*/

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    override fun onClick(v: View) {

        //---------detects and switches keyboard acccording to input type ----
        when (v.id) {

            R.id.edt1ll , R.id.edt1 -> {
                //(activity as com.c2p.donaryInAppKeyboard.MainActivity).whichEdt = "1"
                (activity as com.c2p.donaryInAppKeyboard.MainActivity).vpPager.setCurrentItem(0)//number

            }
            R.id.edt2ll , R.id.edt2-> {
                //(activity as com.c2p.donaryInAppKeyboard.MainActivity).whichEdt = "2"
                (activity as com.c2p.donaryInAppKeyboard.MainActivity).vpPager.setCurrentItem(1)//text
            }
            R.id.edt3ll,  R.id.edt3-> {
                //(activity as com.c2p.donaryInAppKeyboard.MainActivity).whichEdt = "3"
                (activity as com.c2p.donaryInAppKeyboard.MainActivity).vpPager.setCurrentItem(0)//number
            }
        }


        //---------identifies which edittext to handle ----
        /*if ((activity as com.c2p.donaryInAppKeyboard.MainActivity).whichEdt == "1") {
            (activity as com.c2p.donaryInAppKeyboard.MainActivity).edt = edt1
        }
        if ((activity as com.c2p.donaryInAppKeyboard.MainActivity).whichEdt == "2") {
            (activity as com.c2p.donaryInAppKeyboard.MainActivity).edt = edt2
        }
        if ((activity as com.c2p.donaryInAppKeyboard.MainActivity).whichEdt == "3") {
            (activity as com.c2p.donaryInAppKeyboard.MainActivity).edt = edt3
        }*/

    }

}
