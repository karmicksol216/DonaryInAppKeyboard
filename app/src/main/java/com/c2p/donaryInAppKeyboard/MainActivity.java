package com.c2p.donaryInAppKeyboard;

import android.app.Instrumentation;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.c2p.adapter.OnSwipeTouchListener;
import com.c2p.customviews.HorizontalViewPager;
import com.c2p.donaryInAppKeyboards.R;
import com.c2p.listeners.OnKeyBoard;

import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, OnKeyBoard {


    MyPagerAdapter adapterViewPager;
    //public EditText edt1,edt2,edt3;
    HorizontalViewPager vpPager;
    EditText edt;
    LinearLayout main;

    public String whichEdt="1";

    public static boolean isupKeys=false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        setContentView(R.layout.activity_main_layout);
        getSupportActionBar().hide();


        main = (LinearLayout) findViewById(R.id.main);
        edt = new EditText(this);

        vpPager = (HorizontalViewPager) findViewById(R.id.vpPager);
        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);
        vpPager.setCurrentItem(150);


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
        private static int NUM_ITEMS = 300;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }


        @Override
        public int getCount() {
            return NUM_ITEMS;
        }


        @Override
        public Fragment getItem(int position) {
            int fragmentPos = position % 3;
            switch (fragmentPos) {
                case 0:
                    return FragmentParentTwo.newInstance(0, Color.BLUE); //ABC
                case 1:
                    return FragmentParentOne.newInstance(0, Color.BLUE); //Numeric
                case 2:
                    return FragmentParentThree.newInstance(0, Color.BLUE);//Hebrew
                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            return "Page " + position;
        }

        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

    }



    public void setNumericKeyboard(){
        vpPager.setCurrentItem(0);//number
    }

    public void setAlphabetKeyboard(){
        vpPager.setCurrentItem(1);//text
    }


    //############## ****** ALL KEYBOARD CALLBACKS  ** start****** #####################
    @Override
    public void onKeyPressed(final Integer Id) {
       // numeric keypress

        View view= this.getCurrentFocus();
        final InputConnection ic = view.onCreateInputConnection(new EditorInfo());//getCurrentInputConnection();



        switch (Id) {

            case  R.id.row1_btn1:     ic.commitText("1", 0);     ; break;
            case  R.id.row1_btn2:     ic.commitText("2", 0);     ; break;
            case  R.id.row1_btn3:     ic.commitText("3", 0);     ; break;
            case  R.id.row2_btn1:     ic.commitText("4", 0);     ; break;
            case  R.id.row2_btn2:     ic.commitText("5", 0);     ; break;
            case  R.id.row2_btn3:     ic.commitText("6", 0);     ; break;
            case  R.id.row3_btn1:     ic.commitText("7", 0);     ; break;
            case  R.id.row3_btn2:     ic.commitText("8", 0);     ; break;
            case  R.id.row3_btn3:     ic.commitText("9", 0);     ; break;
            case  R.id.row4_space:    ic.commitText(" ", 0);     ; break;
            case  R.id.row4_btn1:     ic.commitText("0", 0);     ; break;
            case  R.id.row4_btn2:     ic.commitText(".", 0);     ; break;

            case  R.id.row2_btn_search:
                performSearch();
                break;

            case  R.id.row3_btn_menu:
                openMenu();
                break;

            case  R.id.row1_back:
                //ic.deleteSurroundingText(1, 0);
                break;
        }


        new Thread(new Runnable() {
            @Override
            public void run() {

                int keycode = KeyEvent.KEYCODE_0;
                Instrumentation inst = new Instrumentation();

                switch (Id) {
                    case R.id.row1_back:
                        keycode = KeyEvent.KEYCODE_DEL ;
                        inst.sendKeyDownUpSync(keycode);
                        break;

                    case  R.id.row4_btn3:
                        //keycode = KeyEvent.KEYCODE_ENTER ;
                        //inst.sendKeyDownUpSync(keycode);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                vpPager.setCurrentItem(2);//Hebrew
                            }
                        });
                        break;
                }


            }
        }).start();
    }


    @Override
    public void onAlphaKeyPressed(final Integer pos) {

        // for ABC keyboard input order
        /*String alphabets2[] = {  "A","B","C","D","E","F",
                                "G","H","I","J","K","L",
                                "*","M","N","O","P","Q",
                                "*","R","S","T","U","V",
                                "<","W","X","\u2423","Y","Z"
                            };*/

        String alphabets[]  ={
                    "A", "B", "C", "D", "E", "*",
                    "F", "G", "H", "I", "J", "K",
                    "L", "M", "N", "O", "P", "Q",
                    "R", "S", "T", "U", "V", "W",
                    "X", "Y", "Z", "\u2423", ">", " "
        };

        View view= this.getCurrentFocus();
        final InputConnection ic = view.onCreateInputConnection(new EditorInfo());//getCurrentInputConnection();

        //12-hebrew , 18-numeric, 24-move left, 27-space
        if(pos==5) {
            ic.deleteSurroundingText(1, 0);
        }else if(pos==27) {
            ic.commitText(" ", 1);
        } else if(pos!=28){
            ic.commitText(alphabets[pos], 0);
        }

        new Thread(new Runnable() {
            @Override
            public void run() {

                Instrumentation inst = new Instrumentation();
                int keycode = KeyEvent.KEYCODE_0;

                switch (pos) {

                    case  28:
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                vpPager.setCurrentItem(1);//Number
                            }
                        });
                        break; //HEBREW


                    case  1000:
                        keycode = KeyEvent.KEYCODE_NAVIGATE_NEXT;
                        inst.sendKeyDownUpSync(keycode);      ; break; //SPACE


                }
            }
        }).start();



        /*new Thread(new Runnable() {
            @Override
            public void run() {

                Instrumentation inst = new Instrumentation();//KeyEvent.KEYCODE_SHIFT_LEFT
                int keycode = KeyEvent.KEYCODE_0;

                switch (pos) {
                    case  0:     keycode = KeyEvent.KEYCODE_A; inst.sendStringSync("A"); break;
                    case  1:     keycode = KeyEvent.KEYCODE_B; inst.sendStringSync("B"); break;
                    case  2:     keycode = KeyEvent.KEYCODE_C; inst.sendStringSync("C"); break;
                    case  3:     keycode = KeyEvent.KEYCODE_D; inst.sendStringSync("D"); break;
                    case  4:     keycode = KeyEvent.KEYCODE_E ; inst.sendStringSync("E"); break;
                    case  5:     keycode = KeyEvent.KEYCODE_F; inst.sendStringSync("F"); break;

                    case  6:     keycode = KeyEvent.KEYCODE_G; inst.sendStringSync("G"); break;
                    case  7:     keycode = KeyEvent.KEYCODE_H ; inst.sendStringSync("H"); break;
                    case  8:     keycode = KeyEvent.KEYCODE_I; inst.sendStringSync("I"); break;
                    case  9:     keycode = KeyEvent.KEYCODE_J; inst.sendStringSync("J"); break;
                    case  10:     keycode = KeyEvent.KEYCODE_K ; inst.sendStringSync("K"); break;
                    case  11:     keycode = KeyEvent.KEYCODE_L; inst.sendStringSync("L");break;

                    case  12:
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                vpPager.setCurrentItem(2);//hebrew
                            }
                        });
                        break; //HEBREW

                    case  13:     keycode = KeyEvent.KEYCODE_M; inst.sendStringSync("M"); break;
                    case  14:     keycode = KeyEvent.KEYCODE_N; inst.sendStringSync("N");break;
                    case  15:     keycode = KeyEvent.KEYCODE_O; inst.sendStringSync("O"); break;
                    case  16:     keycode = KeyEvent.KEYCODE_P; inst.sendStringSync("P"); break;
                    case  17:     keycode = KeyEvent.KEYCODE_Q; inst.sendStringSync("Q"); break;

                    case  18:
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                vpPager.setCurrentItem(0);//NUMERIC
                            }
                        });

                        break; //123

                    case  19:     keycode = KeyEvent.KEYCODE_R; inst.sendStringSync("R"); break;
                    case  20:     keycode = KeyEvent.KEYCODE_T; inst.sendStringSync("S"); break;
                    case  21:     keycode = KeyEvent.KEYCODE_V ; inst.sendStringSync("T"); break;
                    case  22:     keycode = KeyEvent.KEYCODE_U ; inst.sendStringSync("U"); break;
                    case  23:     keycode = KeyEvent.KEYCODE_V; inst.sendStringSync("V"); break;

                    case  24:     keycode = KeyEvent.KEYCODE_DEL ; inst.sendKeyDownUpSync(keycode);     ; break; //<

                    case  25:     keycode = KeyEvent.KEYCODE_W; inst.sendStringSync("W"); break;
                    case  26:     keycode = KeyEvent.KEYCODE_X; inst.sendStringSync("X"); break;

                    case  27:     keycode = KeyEvent.KEYCODE_SPACE; inst.sendKeyDownUpSync(keycode);      ; break; //SPACE

                    case  28:     keycode = KeyEvent.KEYCODE_Y ; inst.sendStringSync("Y"); break;
                    case  29:     keycode = KeyEvent.KEYCODE_Z ; inst.sendStringSync("Z");  break;
                }
            }
        }).start();*/


    }

    @Override
    public void onHebrewKeyPressed(final Integer pos) {

        View view= this.getCurrentFocus();
        final InputConnection ic = view.onCreateInputConnection(new EditorInfo());//getCurrentInputConnection();


        /*final String alphabets2[] = {
                "\u05D5", "\u05D4", "\u05D3", "\u05D2", "\u05D1", "\u05D0",
                "\u05DB", "\u05DA", "\u05D9", "\u05D8", "\u05D7", "\u05D6",
                "*", "\u05E1", "\u05E0", "\u05DF", "\u05DE", "\u05DC",
                "*", "\u05E6", "\u05E5", "\u05E4", "\u05E3", "\u05E2",
                "<", "\u05EA", "\u05E9", "\u2423", "\u05E8", "\u05E7"
        };*/


        final String alphabets[] = {
                "\u05D4","\u05D3","\u05D2","\u05D1","\u05D0","*",
                "\u05D9","\u05D8","\u05D7","\u05D6","\u05D5","*",
                "*","\u05E0","\u05DE","\u05DC","\u05DB","*",
                "\u05E7","\u05E6","\u05E4","\u05E2","\u05E1","⇑",
                "\u05EA","*","\u05E9",">"," "," "};

        if(pos!=5 && pos!=11 && pos!=12 && pos!=17 && pos !=23 && pos!=25 && pos!=27 )
        {
                ic.commitText(alphabets[pos], 0);
        }


        new Thread(new Runnable() {
            @Override
            public void run() {

                Instrumentation inst = new Instrumentation();
                int keycode = KeyEvent.KEYCODE_0;

                //5 ->back || 11 ->menu || 12 ->123 || 17 ->abc || 25 -> space

                switch (pos) {

                    case  5:      keycode = KeyEvent.KEYCODE_DEL ;
                                  inst.sendKeyDownUpSync(keycode);
                                  break; //<

                    case  11:
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                openMenu();
                            }
                        });
                        break; //Menu

                    case  12:
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                vpPager.setCurrentItem(1);//numeric
                            }
                        });
                        break;

                    case  17:
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                vpPager.setCurrentItem(0);//ABC
                            }
                        });
                        break;

                    case  25:
                        keycode = KeyEvent.KEYCODE_SPACE;
                        inst.sendKeyDownUpSync(keycode);
                        break; //SPACE

                    case  27:

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                vpPager.setCurrentItem(0);//numeric
                            }
                        });
                        //keycode = KeyEvent.KEYCODE_ENTER;
                        //inst.sendKeyDownUpSync(keycode);
                        break; //>


                }
            }
        }).start();




    }


    @Override
    public void onSymbolKeyPressed(final Integer Id) {


        View view= this.getCurrentFocus();
        final InputConnection ic = view.onCreateInputConnection(new EditorInfo());//getCurrentInputConnection();

        if(ic ==null)
            return;

        switch (Id) {
            case  R.id.row1_btn1:      ic.commitText("(", 0);      ; break;
            case  R.id.row1_btn2:      ic.commitText(")", 0);     ; break;
            case  R.id.row1_btn3:      ic.commitText("*", 0);      ; break;

            case  R.id.row2_btn1:      ic.commitText("%", 0);      ; break;
            case  R.id.row2_btn2:      ic.commitText("&", 0);      ; break;
            case  R.id.row2_btn3:      ic.commitText("'", 0);      ; break;

            case  R.id.row3_btn1:      ic.commitText("+", 0);      ; break;
            case  R.id.row3_btn2:      ic.commitText("@", 0);      ; break;
            case  R.id.row3_btn3:      ic.commitText("-", 0);     ; break;

            case  R.id.row4_space:     ic.commitText(" ", 0);     ; break;
            case  R.id.row4_btn1:      ic.commitText("#", 0);     ; break;
            case  R.id.row4_btn2:      ic.commitText(".", 0);     ; break;

            case  R.id.row2_btn_search:
                performSearch();
                break;

            case  R.id.row3_btn_menu:
                openMenu();
                break;
        }

        new Thread(new Runnable() {
            @Override
            public void run() {

                Instrumentation inst = new Instrumentation();//KeyEvent.KEYCODE_SHIFT_LEFT
                int keycode = KeyEvent.KEYCODE_0;

                switch (Id) {
                    case R.id.row1_back:
                        keycode = KeyEvent.KEYCODE_DEL ;
                        inst.sendKeyDownUpSync(keycode);
                        break;

                    case  R.id.row4_btn3:
                        //keycode = KeyEvent.KEYCODE_ENTER ;
                        //inst.sendKeyDownUpSync(keycode);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                vpPager.setCurrentItem(2);//hebrew
                            }
                        });
                        break;
                }


            }
        }).start();

    }


    @Override
    public void onQwertyKeyPressed(final Integer Id) {


        View view= this.getCurrentFocus();
        final InputConnection ic = view.onCreateInputConnection(new EditorInfo());//getCurrentInputConnection();

        if(ic ==null)
            return;

        switch (Id) {
            case  R.id.row1_btn0:      ic.commitText("Q", 0);      ; break;
            case  R.id.row1_btn1:      ic.commitText("E", 0);     ; break;
            case  R.id.row1_btn2:      ic.commitText("T", 0);      ; break;
            case  R.id.row1_btn3:      ic.commitText("U", 0);      ; break;
            case  R.id.row1_btn4:      ic.commitText("O", 0);      ; break;

            case  R.id.row2_btn0:      ic.commitText("W", 0);      ; break;
            case  R.id.row2_btn1:      ic.commitText("R", 0);     ; break;
            case  R.id.row2_btn2:      ic.commitText("Y", 0);      ; break;
            case  R.id.row2_btn3:      ic.commitText("I", 0);      ; break;
            case  R.id.row2_btn4:      ic.commitText("P", 0);      ; break;

            case  R.id.row3_btn0:      ic.commitText("A", 0);      ; break;
            case  R.id.row3_btn1:      ic.commitText("D", 0);     ; break;
            case  R.id.row3_btn2:      ic.commitText("G", 0);      ; break;
            case  R.id.row3_btn3:      ic.commitText("J", 0);      ; break;
            case  R.id.row3_btn4:      ic.commitText("L", 0);      ; break;

            case  R.id.row4_btn0:      ic.commitText("S", 0);      ; break;
            case  R.id.row4_btn1:      ic.commitText("F", 0);     ; break;
            case  R.id.row4_btn2:      ic.commitText("H", 0);      ; break;
            case  R.id.row4_btn3:      ic.commitText("K", 0);      ; break;
            case  R.id.row4_btn4:      ic.commitText(",", 0);      ; break;

            case  R.id.row5_btn0:      ic.commitText("Z", 0);      ; break;
            case  R.id.row5_btn1:      ic.commitText("C", 0);     ; break;
            case  R.id.row5_btn2:      ic.commitText("B", 0);      ; break;
            case  R.id.row5_btn3:      ic.commitText("M", 0);      ; break;
            case  R.id.row5_btn4:      ic.commitText(".", 0);      ; break;

            case  R.id.row6_btn0:      ic.commitText("X", 0);      ; break;
            case  R.id.row6_btn1:      ic.commitText("V", 0);      ; break;
            case  R.id.row6_btn2:      ic.commitText("N", 0);      ; break;
            case  R.id.row6_btn3:      ic.commitText("?", 0);      ; break;

            case  R.id.row7_btn1:      ic.commitText("/", 0);      ; break;
            case  R.id.row7_btn2:      ic.commitText(" ", 0);      ; break;

        }

        new Thread(new Runnable() {
            @Override
            public void run() {

                Instrumentation inst = new Instrumentation();//KeyEvent.KEYCODE_SHIFT_LEFT
                int keycode = KeyEvent.KEYCODE_0;

                switch (Id) {

                    case  R.id.row6_btn4:
                        keycode = KeyEvent.KEYCODE_DEL;
                        inst.sendKeyDownUpSync(keycode);     ; break;
                    case  R.id.row7_btn3:
                        keycode = KeyEvent.KEYCODE_ENTER;
                        inst.sendKeyDownUpSync(keycode);     ; break;
                }

            }
        }).start();

    }


    @Override
    public void onHebrewQwertyKeyPressed(final Integer Id) {


        View view= this.getCurrentFocus();
        final InputConnection ic = view.onCreateInputConnection(new EditorInfo());//getCurrentInputConnection();

        if(ic ==null)
            return;

        switch (Id) {
            case  R.id.row1_btn0:      ic.commitText("ק", 0);      ; break;
            case  R.id.row1_btn1:      ic.commitText("א", 0);     ; break;
            case  R.id.row1_btn2:      ic.commitText("ו", 0);      ; break;
            case  R.id.row1_btn3:      ic.commitText("ם", 0);      ; break;
            case  R.id.row1_btn4:      ic.commitText("פ", 0);      ; break;

            case  R.id.row2_btn0:      ic.commitText("ר", 0);      ; break;
            case  R.id.row2_btn1:      ic.commitText("ט", 0);     ; break;
            case  R.id.row2_btn2:      ic.commitText("ן", 0);      ; break;
            case  R.id.row2_btn3:      ic.commitText("ך", 0);      ; break;


            case  R.id.row3_btn0:      ic.commitText("ש", 0);      ; break;
            case  R.id.row3_btn1:      ic.commitText("ג", 0);     ; break;
            case  R.id.row3_btn2:      ic.commitText("ע", 0);      ; break;
            case  R.id.row3_btn3:      ic.commitText("ח", 0);      ; break;
            case  R.id.row3_btn4:      ic.commitText("ף", 0);      ; break;

            case  R.id.row4_btn0:      ic.commitText("ד", 0);      ; break;
            case  R.id.row4_btn1:      ic.commitText("כ", 0);     ; break;
            case  R.id.row4_btn2:      ic.commitText("י", 0);      ; break;
            case  R.id.row4_btn3:      ic.commitText("ל", 0);      ; break;


            case  R.id.row5_btn0:      ic.commitText("ז", 0);      ; break;
            case  R.id.row5_btn1:      ic.commitText("ב", 0);     ; break;
            case  R.id.row5_btn2:      ic.commitText("נ", 0);      ; break;
            case  R.id.row5_btn3:      ic.commitText("צ", 0);      ; break;
            case  R.id.row5_btn4:      ic.commitText("ץ", 0);      ; break;

            case  R.id.row6_btn0:      ic.commitText("ס", 0);      ; break;
            case  R.id.row6_btn1:      ic.commitText("ה", 0);     ; break;
            case  R.id.row6_btn2:      ic.commitText("מ", 0);      ; break;
            case  R.id.row6_btn3:      ic.commitText("ת", 0);      ; break;

            case  R.id.row7_btn1:      ic.commitText(" ", 0);      ; break;
        }

        new Thread(new Runnable() {
            @Override
            public void run() {

                Instrumentation inst = new Instrumentation();//KeyEvent.KEYCODE_SHIFT_LEFT
                int keycode = KeyEvent.KEYCODE_0;

                switch (Id) {

                    case  R.id.row7_btn0:
                        keycode = KeyEvent.KEYCODE_DEL;
                        inst.sendKeyDownUpSync(keycode);     ; break;

                    case  R.id.row7_btn2:
                        //keycode = KeyEvent.KEYCODE_ENTER;
                        //inst.sendKeyDownUpSync(keycode);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                vpPager.setCurrentItem(0);//numeric
                            }
                        });
                        break;
                }

            }
        }).start();

    }

    @Override
    public void onLongKeyPressed(Integer value) {


        View view= this.getCurrentFocus();
        final InputConnection ic = view.onCreateInputConnection(new EditorInfo());//getCurrentInputConnection();
        EditText edt=(EditText)view;
        edt.setText("");
    }

    //<<<<<<<<<<<<<<<< ****** kbd callbacks end ****** <<<<<<<<<<<<<<<<<<<<<<<




    public void performSearch(){
        /* search operation goes here */
        Toast.makeText(this,"Search - To Do",Toast.LENGTH_SHORT).show();
    }

    public void openMenu(){
        /* opens menu */
        Toast.makeText(this,"Opens Menu - To Do",Toast.LENGTH_SHORT).show();
    }
}
