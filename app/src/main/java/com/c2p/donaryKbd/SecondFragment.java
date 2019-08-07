package com.c2p.donaryKbd;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class SecondFragment extends Fragment implements View.OnClickListener, View.OnLongClickListener {
    // Store instance variables
    private String title;
    private int page;

    TextView row11_btn1,row11_btn2,row11_btn3,row11_btn4;
    TextView row22_btn1;


    // newInstance constructor for creating fragment with arguments
    public static SecondFragment newInstance(int page, String title) {
        SecondFragment fragmentFirst = new SecondFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);

        row11_btn1 = (TextView) view.findViewById(R.id.row11_btn1);
        row11_btn1.setOnClickListener(this); // calling onClick() method
        row11_btn1.setOnLongClickListener(this);


        row11_btn2 = (TextView) view.findViewById(R.id.row11_btn2);
        row11_btn2.setOnClickListener(this); // calling onClick() method
        row11_btn2.setOnLongClickListener(this);

        row11_btn3 = (TextView) view.findViewById(R.id.row11_btn3);
        row11_btn3.setOnClickListener(this); // calling onClick() method
        row11_btn3.setOnLongClickListener(this);

        row11_btn4 = (TextView) view.findViewById(R.id.row11_btn4);
        row11_btn4.setOnClickListener(this); // calling onClick() method
        row11_btn4.setOnLongClickListener(this);

        row22_btn1 = (TextView) view.findViewById(R.id.row22_btn1);
        row22_btn1.setOnClickListener(this); // calling onClick() method



        return view;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.row11_btn1:
                // do your code
                ((Main2Activity)getActivity()).edt2.setText(((Main2Activity)getActivity()).edt2.getText()+"A");
                break;

            case R.id.row11_btn2:
                // do your code
                ((Main2Activity)getActivity()).edt2.setText(((Main2Activity)getActivity()).edt2.getText()+"B");
                break;
            case R.id.row11_btn3:
                // do your code
                ((Main2Activity)getActivity()).edt2.setText(((Main2Activity)getActivity()).edt2.getText()+"C");
                break;

            case R.id.row11_btn4:
                // do your code
                ((Main2Activity)getActivity()).edt2.setText(((Main2Activity)getActivity()).edt2.getText()+"D");
                break;

            case R.id.row22_btn1:
                // do your code
                ((Main2Activity)getActivity()).edt2.setText(((Main2Activity)getActivity()).edt2.getText().subSequence(0,((Main2Activity)getActivity()).edt2.getText().length()-1));
                break;

            default:
                break;
        }

    }

    @Override
    public boolean onLongClick(View v) {

        switch (v.getId()) {

            case R.id.row11_btn1:
                // do your code
                ((Main2Activity)getActivity()).edt2.setText(((Main2Activity)getActivity()).edt2.getText()+"a");
                break;

            case R.id.row11_btn2:
                // do your code
                ((Main2Activity)getActivity()).edt2.setText(((Main2Activity)getActivity()).edt2.getText()+"b");
                break;
            case R.id.row11_btn3:
                // do your code
                ((Main2Activity)getActivity()).edt2.setText(((Main2Activity)getActivity()).edt2.getText()+"c");
                break;

            case R.id.row11_btn4:
                // do your code
                ((Main2Activity)getActivity()).edt2.setText(((Main2Activity)getActivity()).edt2.getText()+"d");
                break;

            case R.id.row22_btn1:
                // do your code
                ((Main2Activity)getActivity()).edt2.setText(((Main2Activity)getActivity()).edt2.getText().subSequence(0,((Main2Activity)getActivity()).edt2.getText().length()-1));
                break;

            default:
                break;
        }
        return true;
    }
}