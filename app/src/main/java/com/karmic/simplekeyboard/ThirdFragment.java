package com.karmic.simplekeyboard;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


public class ThirdFragment extends Fragment implements View.OnClickListener {
    // Store instance variables
    private String title;
    private int page;

    TextView row1_btn1,row1_btn2,row1_btn3,row1_btn4;
    TextView row2_btn1;
    EditText edt1;

    // newInstance constructor for creating fragment with arguments
    public static ThirdFragment newInstance(int page, String title) {
        ThirdFragment fragmentFirst = new ThirdFragment();
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
        View view = inflater.inflate(R.layout.fragment_third, container, false);

        row1_btn1 = (TextView) view.findViewById(R.id.row1_btn1);
        row1_btn1.setOnClickListener(this); // calling onClick() method

        row1_btn2 = (TextView) view.findViewById(R.id.row1_btn2);
        row1_btn2.setOnClickListener(this); // calling onClick() method

        row1_btn3 = (TextView) view.findViewById(R.id.row1_btn3);
        row1_btn3.setOnClickListener(this); // calling onClick() method

        row1_btn4 = (TextView) view.findViewById(R.id.row1_btn4);
        row1_btn4.setOnClickListener(this); // calling onClick() method

        row2_btn1 = (TextView) view.findViewById(R.id.row2_btn1);
        row2_btn1.setOnClickListener(this); // calling onClick() method

        edt1 = (EditText) view.findViewById(R.id.edt1);

        return view;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.row1_btn1:
                // do your code
                edt1.setText(edt1.getText()+"1");
                break;

            case R.id.row1_btn2:
                // do your code
                edt1.setText(edt1.getText()+"2");
                break;
            case R.id.row1_btn3:
                // do your code
                edt1.setText(edt1.getText()+"3");
                break;

            case R.id.row1_btn4:
                // do your code
                edt1.setText(edt1.getText()+"4");
                break;

            case R.id.row2_btn1:
                // do your code
                edt1.setText(edt1.getText().subSequence(0,edt1.getText().length()-1));
                break;

            default:
                break;
        }

    }
}