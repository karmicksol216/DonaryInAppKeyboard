package com.c2p.donaryInAppKeyboard;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, OnKeyBoard{


    MyPagerAdapter adapterViewPager;
    //public EditText edt1,edt2,edt3;
    ViewPager vpPager;
    EditText edt;

    public String whichEdt="1";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Remove notification bar
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //set content view AFTER ABOVE sequence (to avoid crash)
        setContentView(R.layout.activity_main_layout);
        getSupportActionBar().hide();


        edt = new EditText(this);

        vpPager = (ViewPager) findViewById(R.id.vpPager);
        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);

        Fragment fm=new Screen1Fragment();
        setScreen(fm);
    }

    public void setScreen(Fragment newFragment){
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onClick(View v) {


    }


    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 3;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return NumberKeyboardFragment.newInstance(0, "Page # 1");
                case 1:
                    return new ABCKeyboardFragment();
                    //return SecondFragment.newInstance(1, "Page # 2");
                case 2:
                    return new HebrewAbcKeybardFragment();
                    //return FirstFragment.newInstance(1, "Page # 1");
                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            return "Page " + position;
        }

    }


    @Override
    public void onKeyPressed(Integer id) {
       // numeric keypress

        //------ actual typing code ----
        switch (id) {

            /*---- for numeric kbd1  start ----*/
            case R.id.row1_btn1:
                edt.setText(edt.getText()+"1");
                break;
            case R.id.row1_btn2:
                 edt.setText( edt.getText()+"2");
                break;
            case R.id.row1_btn3:
                 edt.setText( edt.getText()+"3");
                break;

            case R.id.row2_btn1:
                 edt.setText( edt.getText()+"4");
                break;
            case R.id.row2_btn2:
                 edt.setText( edt.getText()+"5");
                break;
            case R.id.row2_btn3:
                 edt.setText( edt.getText()+"6");
                break;

            case R.id.row3_btn1:
                 edt.setText( edt.getText()+"7");
                break;
            case R.id.row3_btn2:
                 edt.setText( edt.getText()+"8");
                break;
            case R.id.row3_btn3:
                 edt.setText( edt.getText()+"9");
                break;

            case R.id.row4_btn2:
                 edt.setText( edt.getText()+"0");
                break;
            case R.id.row4_btn3:
                //------ the next kbd screen-- >
                //vpPager.setCurrentItem(vpPager.getCurrentItem()+1);
                whichEdt="2";
                //edt2.requestFocus();
                vpPager.setCurrentItem(1);//text
                break;

            case R.id.row1_btn_dollar:
                 edt.setText("\u0024");
                break;
            case R.id.row2_btn_search:
                 edt.setText("");
                break;
            case R.id.row3_btn_menu:
                 edt.setText("");
                break;

            case R.id.row4_btn_cross:
                // enter
                Toast.makeText(this,"Input: "+  edt.getText().toString(),Toast.LENGTH_SHORT).show();
                 edt.setText("");
                break;

            case R.id.row4_btn1:
                // backspace
                if( edt.getText().length()>0)
                     edt.setText( edt.getText().subSequence(0, edt.getText().length()-1));
                break;

            /*---- for numeric kbd1  end ----*/
            default:
                break;
        }
    }



    @Override
    public void onAlphaKeyPressed(Integer pos) {

        // for ABC keyboard input
        String alphabets[] = {"A","B","C","D","E","F","G","H","I","J","K","L","*","M","N","O","P","Q","*","R","S","T","U","V","<","W","X","\u2423","Y","Z"};


        //12-hebrew , 18-numeric, 24-move left, 27-space
        if(pos==12) {
            //move to hebrew kbd
            vpPager.setCurrentItem(2);//text
        } else if(pos==18) {
            //move to numeric kbd
            vpPager.setCurrentItem(0);//text
        }else if(pos==24) {
            //move left
             //vpPager.setCurrentItem(0);//text
            if(edt.getText().length()>0)
             edt.setText(edt.getText().subSequence(0,edt.getText().length()-1));
        }else if(pos==27) {
            edt.setText(edt.getText() + " ");
        } else {
            edt.setText(edt.getText() + alphabets[pos]);
        }
    }

    @Override
    public void onHebrewKeyPressed(Integer pos) {

        String alphabets[] = {"\u05D0","\u05D1","\u05D2","\u05D3","\u05D4","\u05D5","\u05D6","\u05D7","\u05D8","\u05D9","\u05DA","\u05DB","*","\u05DC","\u05DE","\u05DF","\u05E0","\u05E1","*","\u05E2","\u05E3","\u05E4","\u05E5","\u05E6","<","\u05E7","\u05E3","\u2423","\u05E4","\u05E5"};


        //12-hebrew , 18-numeric, 24-move left, 27-space
        if(pos==12) {
            //move to number
            vpPager.setCurrentItem(0);//text
        } else if(pos==18) {
            //move to alphabet
            vpPager.setCurrentItem(1);//text
        }else if(pos==24) {
            //move left
            //vpPager.setCurrentItem(1);//text
            if(edt.getText().length()>0)
                edt.setText(edt.getText().subSequence(0,edt.getText().length()-1));
        }else if(pos==27) {
            edt.setText(edt.getText() + " ");
        } else {
            edt.setText(edt.getText() + alphabets[pos]);
        }

    }


}
