package com.c2p.donaryInAppKeyboard;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.c2p.donaryInAppKeyboards.R;
import com.c2p.listeners.OnKeyBoard;


public class NumberKeyboardFragment extends Fragment implements View.OnClickListener {

    private String title;
    private int page;

    TextView row1_btn1,row1_btn2,row1_btn3;
    TextView row2_btn1,row2_btn2,row2_btn3;
    TextView row3_btn1,row3_btn2,row3_btn3;
    TextView row4_btn1,row4_btn2, row4_btn3, row4_space;


    ImageView row1_back, row2_btn_search, row3_btn_menu;

    OnKeyBoard onKeyBoardlistener;




    // newInstance constructor for creating fragment with arguments
    public static NumberKeyboardFragment newInstance(int page, String title) {
        NumberKeyboardFragment fragmentFirst = new NumberKeyboardFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
        onKeyBoardlistener= (OnKeyBoard) getActivity();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nbd1, container, false);

        //---number keys-----------------
        row1_btn1 = (TextView) view.findViewById(R.id.row1_btn1);
        row1_btn1.setOnClickListener(this);

        row1_btn2 = (TextView) view.findViewById(R.id.row1_btn2);
        row1_btn2.setOnClickListener(this);

        row1_btn3 = (TextView) view.findViewById(R.id.row1_btn3);
        row1_btn3.setOnClickListener(this);

        row2_btn1 = (TextView) view.findViewById(R.id.row2_btn1);
        row2_btn1.setOnClickListener(this);

        row2_btn2 = (TextView) view.findViewById(R.id.row2_btn2);
        row2_btn2.setOnClickListener(this);

        row2_btn3 = (TextView) view.findViewById(R.id.row2_btn3);
        row2_btn3.setOnClickListener(this);


        row3_btn1 = (TextView) view.findViewById(R.id.row3_btn1);
        row3_btn1.setOnClickListener(this);

        row3_btn2 = (TextView) view.findViewById(R.id.row3_btn2);
        row3_btn2.setOnClickListener(this);

        row3_btn3 = (TextView) view.findViewById(R.id.row3_btn3);
        row3_btn3.setOnClickListener(this);


        row4_btn1 = (TextView) view.findViewById(R.id.row4_btn1);
        row4_btn1.setOnClickListener(this);

        row4_btn2 = (TextView) view.findViewById(R.id.row4_btn2);
        row4_btn2.setOnClickListener(this);

        row4_btn3 = (TextView) view.findViewById(R.id.row4_btn3);
        row4_btn3.setOnClickListener(this);

        row4_space = (TextView) view.findViewById(R.id.row4_space);
        row4_space.setOnClickListener(this);

        //----------------action keys---------------------

        row1_back = (ImageView) view.findViewById(R.id.row1_back);
        row2_btn_search = (ImageView) view.findViewById(R.id.row2_btn_search);
        row3_btn_menu = (ImageView) view.findViewById(R.id.row3_btn_menu);

        row1_back.setOnClickListener(this);
        row2_btn_search.setOnClickListener(this);
        row3_btn_menu.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {
        onKeyBoardlistener.onKeyPressed(v.getId());
    }
}