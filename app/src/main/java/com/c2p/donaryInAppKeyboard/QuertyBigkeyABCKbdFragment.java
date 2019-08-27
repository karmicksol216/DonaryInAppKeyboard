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


public class QuertyBigkeyABCKbdFragment extends Fragment implements View.OnClickListener , View.OnLongClickListener{

    private String title;
    private int page;

    TextView row1_btn0,row1_btn1,row1_btn2,row1_btn3,row1_btn4;
    TextView row2_btn0,row2_btn1,row2_btn2,row2_btn3,row2_btn4;
    TextView row3_btn0,row3_btn1,row3_btn2,row3_btn3,row3_btn4;
    TextView row4_btn0,row4_btn1,row4_btn2,row4_btn3,row4_btn4;
    TextView row5_btn0,row5_btn1,row5_btn2,row5_btn3,row5_btn4;
    TextView row6_btn0,row6_btn1,row6_btn2,row6_btn3;
    TextView row7_btn1,row7_btn2,row7_btn3;

    ImageView row6_btn4;

    //ImageView row4_btn_cross, row2_btn_search,row3_btn_menu, row4_btn1;

    OnKeyBoard onKeyBoardlistener;




    // newInstance constructor for creating fragment with arguments
    public static QuertyBigkeyABCKbdFragment newInstance(int page, String title) {
        QuertyBigkeyABCKbdFragment fragmentFirst = new QuertyBigkeyABCKbdFragment();
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
        View view = inflater.inflate(R.layout.fragment_qwerty_big_abc, container, false);

        //---keys-----------------
        row1_btn0 = (TextView) view.findViewById(R.id.row1_btn0);
        row1_btn1 = (TextView) view.findViewById(R.id.row1_btn1);
        row1_btn2 = (TextView) view.findViewById(R.id.row1_btn2);
        row1_btn3 = (TextView) view.findViewById(R.id.row1_btn3);
        row1_btn4 = (TextView) view.findViewById(R.id.row1_btn4);

        //---keys-----------------
        row2_btn0 = (TextView) view.findViewById(R.id.row2_btn0);
        row2_btn1 = (TextView) view.findViewById(R.id.row2_btn1);
        row2_btn2 = (TextView) view.findViewById(R.id.row2_btn2);
        row2_btn3 = (TextView) view.findViewById(R.id.row2_btn3);
        row2_btn4 = (TextView) view.findViewById(R.id.row2_btn4);

        //---keys-----------------
        row3_btn0 = (TextView) view.findViewById(R.id.row3_btn0);
        row3_btn1 = (TextView) view.findViewById(R.id.row3_btn1);
        row3_btn2 = (TextView) view.findViewById(R.id.row3_btn2);
        row3_btn3 = (TextView) view.findViewById(R.id.row3_btn3);
        row3_btn4 = (TextView) view.findViewById(R.id.row3_btn4);

        //---keys-----------------
        row4_btn0 = (TextView) view.findViewById(R.id.row4_btn0);
        row4_btn1 = (TextView) view.findViewById(R.id.row4_btn1);
        row4_btn2 = (TextView) view.findViewById(R.id.row4_btn2);
        row4_btn3 = (TextView) view.findViewById(R.id.row4_btn3);
        row4_btn4 = (TextView) view.findViewById(R.id.row4_btn4);


        row5_btn0 = (TextView) view.findViewById(R.id.row5_btn0);
        row5_btn1 = (TextView) view.findViewById(R.id.row5_btn1);
        row5_btn2 = (TextView) view.findViewById(R.id.row5_btn2);
        row5_btn3 = (TextView) view.findViewById(R.id.row5_btn3);
        row5_btn4 = (TextView) view.findViewById(R.id.row5_btn4);

        //---keys-----------------
        row6_btn0 = (TextView) view.findViewById(R.id.row6_btn0);
        row6_btn1 = (TextView) view.findViewById(R.id.row6_btn1);
        row6_btn2 = (TextView) view.findViewById(R.id.row6_btn2);
        row6_btn3 = (TextView) view.findViewById(R.id.row6_btn3);
        row6_btn4 = (ImageView) view.findViewById(R.id.row6_btn4);


        row7_btn1 = (TextView) view.findViewById(R.id.row7_btn1);
        row7_btn2 = (TextView) view.findViewById(R.id.row7_btn2);
        row7_btn3 = (TextView) view.findViewById(R.id.row7_btn3);


        //--- key listeners -----------------
        row1_btn0.setOnClickListener(this);
        row1_btn1.setOnClickListener(this);
        row1_btn2.setOnClickListener(this);
        row1_btn3.setOnClickListener(this);
        row1_btn4.setOnClickListener(this);

        row2_btn0.setOnClickListener(this);
        row2_btn1.setOnClickListener(this);
        row2_btn2.setOnClickListener(this);
        row2_btn3.setOnClickListener(this);
        row2_btn4.setOnClickListener(this);

        row3_btn0.setOnClickListener(this);
        row3_btn1.setOnClickListener(this);
        row3_btn2.setOnClickListener(this);
        row3_btn3.setOnClickListener(this);
        row3_btn4.setOnClickListener(this);

        row4_btn0.setOnClickListener(this);
        row4_btn1.setOnClickListener(this);
        row4_btn2.setOnClickListener(this);
        row4_btn3.setOnClickListener(this);
        row4_btn4.setOnClickListener(this);

        row5_btn0.setOnClickListener(this);
        row5_btn1.setOnClickListener(this);
        row5_btn2.setOnClickListener(this);
        row5_btn3.setOnClickListener(this);
        row5_btn4.setOnClickListener(this);


        row6_btn0.setOnClickListener(this);
        row6_btn1.setOnClickListener(this);
        row6_btn2.setOnClickListener(this);
        row6_btn3.setOnClickListener(this);

        row6_btn4.setOnClickListener(this);
        row6_btn4.setOnLongClickListener(this);

        row7_btn1.setOnClickListener(this);
        row7_btn2.setOnClickListener(this);
        row7_btn3.setOnClickListener(this);





        return view;
    }

    @Override
    public void onClick(View v) {
        onKeyBoardlistener.onQwertyKeyPressed(v.getId());
    }



    @Override
    public boolean onLongClick(View v) {
        onKeyBoardlistener.onLongKeyPressed(v.getId());
        return false;
    }
}