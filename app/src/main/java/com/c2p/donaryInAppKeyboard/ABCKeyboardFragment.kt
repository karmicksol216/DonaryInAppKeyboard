package com.c2p.donaryInAppKeyboard


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.c2p.adapter.EnglishKBDAdapter
import com.c2p.donaryInAppKeyboards.R
import com.c2p.listeners.OnKeyBoard


class ABCKeyboardFragment : Fragment() , EnglishKBDAdapter.OnItemClickListener{

    var mContext: Context ?= null
    private var mActivity: AppCompatActivity? = null
    var rv: RecyclerView?=null
    var adapter: EnglishKBDAdapter?=null
    var layoutManager: GridLayoutManager? =null
    var listner:EnglishKBDAdapter.OnItemClickListener?=null

    internal lateinit var onKeyBoardlistener: OnKeyBoard


    // newInstance constructor for creating fragment with arguments
    fun newInstance(page: Int, title: String): NumberKeyboardFragment {
        val fragmentFirst = NumberKeyboardFragment()
        val args = Bundle()
        args.putInt("someInt", page)
        args.putString("someTitle", title)
        fragmentFirst.arguments = args
        return fragmentFirst
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mContext=activity as Context
        mActivity=activity as AppCompatActivity;
        onKeyBoardlistener = (activity as OnKeyBoard?)!!

        return inflater.inflate(R.layout.fragment_alphabet_kbd1, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv=view.findViewById(R.id.rv_english) as RecyclerView
        listner=this@ABCKeyboardFragment
        adapter = EnglishKBDAdapter(context!!, listner as ABCKeyboardFragment )
        layoutManager = GridLayoutManager(mContext, 6)

        layoutManager?.setSpanSizeLookup(object : GridLayoutManager.SpanSizeLookup() {

            override fun getSpanSize(position: Int): Int {
                if (position == 27) { // totalRowCount : How many item you want to show
                    return 2// the item in position now takes up 2 spans
                } else {
                    return 1;
                }
            }
        })

        rv!!.layoutManager = layoutManager
        rv!!.adapter = adapter
        rv!!.setHasFixedSize(true)
    }

    //val alphabets = arrayOf<String>("A","B","C","D","E","F","G","H","I","J","K","L","*","M","N","O","P","Q","*","R","S","T","U","V","<","W","X","\u2423","Y","Z")


    override fun onItemClick(item: RecyclerView.ViewHolder, pos: Int, Total: Int) {

        //12-hebrew , 18-numeric, 24-move left, 27-space
        onKeyBoardlistener.onAlphaKeyPressed(pos)
    }

}
