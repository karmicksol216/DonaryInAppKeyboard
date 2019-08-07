package com.c2p.donaryKbd;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class NumericKBD1Fragment extends Fragment implements View.OnClickListener {

    private String title;
    private int page;

    TextView row1_btn1,row1_btn2,row1_btn3;
    TextView row2_btn1,row2_btn2,row2_btn3;
    TextView row3_btn1,row3_btn2,row3_btn3;
    TextView row4_btn2,row4_btn3;

    TextView row1_btn_dollar,row4_btn_cross;
    ImageView row2_btn_search,row3_btn_menu, row4_btn1;

    OnKeyBoard onKeyBoardlistener;




    // newInstance constructor for creating fragment with arguments
    public static NumericKBD1Fragment newInstance(int page, String title) {
        NumericKBD1Fragment fragmentFirst = new NumericKBD1Fragment();
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


        row4_btn2 = (TextView) view.findViewById(R.id.row4_btn2);
        row4_btn2.setOnClickListener(this);

        row4_btn3 = (TextView) view.findViewById(R.id.row4_btn3);
        row4_btn3.setOnClickListener(this);

        //--action keys

        row1_btn_dollar = (TextView) view.findViewById(R.id.row1_btn_dollar);
        row2_btn_search = (ImageView) view.findViewById(R.id.row2_btn_search);
        row3_btn_menu = (ImageView) view.findViewById(R.id.row3_btn_menu);
        row4_btn_cross = (TextView) view.findViewById(R.id.row4_btn_cross);
        row4_btn1 = (ImageView) view.findViewById(R.id.row4_btn1);

        row1_btn_dollar.setOnClickListener(this);
        row2_btn_search.setOnClickListener(this);
        row3_btn_menu.setOnClickListener(this);
        row4_btn_cross.setOnClickListener(this);
        row4_btn1.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {
        onKeyBoardlistener.onKeyPressed(v.getId());
    }
}