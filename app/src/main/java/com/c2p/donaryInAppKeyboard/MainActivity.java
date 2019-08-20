package com.c2p.donaryInAppKeyboard;

import android.app.Instrumentation;
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
import com.c2p.donaryInAppKeyboards.R;
import com.c2p.listeners.OnKeyBoard;

import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, OnKeyBoard {


    MyPagerAdapter adapterViewPager;
    //public EditText edt1,edt2,edt3;
    ViewPager vpPager;
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

        vpPager = (ViewPager) findViewById(R.id.vpPager);
        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);

        /*main.setOnTouchListener(new OnSwipeTouchListener(this) {

            public void onSwipeUp() {
                Toast.makeText(getApplicationContext(), "UP", Toast.LENGTH_SHORT).show();
                //adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
                //vpPager.setAdapter(adapterViewPager);
                //vpPager.setCurrentItem(4);//hebrew
            }
            public void onSwipeRight() {
                //Toast.makeText(getApplicationContext(), "RIGHT SWIPE", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeLeft() {
                //Toast.makeText(getApplicationContext(), "LEFT SWIPE", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeDown() {
                Toast.makeText(getApplicationContext(), "DOWN", Toast.LENGTH_SHORT).show();
            }
        });*/


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
        private static int NUM_ITEMS = 4;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }


        @Override
        public int getCount() {
            return NUM_ITEMS;
        }


        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                     return NumberKeyboardFragment.newInstance(0, "99");
                case 1:
                    return new ABCKeyboardFragment();
                    //return SecondFragment.newInstance(1, "Page # 2");
                case 2:
                    return new HebrewAbcKeybardFragment();
                    //return FirstFragment.newInstance(1, "Page # 1");
                case 3:
                    return SymbolKeyboardFragment.newInstance(0, "Page # 1");
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


    //##############ALL KEYBOARD CALLBACKS#####################3
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
            case  R.id.row4_btn2:     ic.commitText("0", 0);     ; break;

        }


        new Thread(new Runnable() {
            @Override
            public void run() {

                int keycode = KeyEvent.KEYCODE_0;
                Instrumentation inst = new Instrumentation();

                switch (Id) {
                    case R.id.row1_btn_dollar:      keycode = KeyEvent.KEYCODE_NUMPAD_0 ;inst.sendKeyDownUpSync(keycode);     ; break;
                    case  R.id.row4_btn1:     keycode = KeyEvent.KEYCODE_DEL ;inst.sendKeyDownUpSync(keycode);   ; break;
                    case  R.id.row4_btn3:     keycode = KeyEvent.KEYCODE_ENTER ; inst.sendKeyDownUpSync(keycode); ; break;
                }


            }
        }).start();
    }

    @Override
    public void onAlphaKeyPressed(final Integer pos) {

        // for ABC keyboard input order
        String alphabets[] = {  "A","B","C","D","E","F",
                                "G","H","I","J","K","L",
                                "*","M","N","O","P","Q",
                                "*","R","S","T","U","V",
                                "<","W","X","\u2423","Y","Z"
                            };

        View view= this.getCurrentFocus();
        final InputConnection ic = view.onCreateInputConnection(new EditorInfo());//getCurrentInputConnection();

        //12-hebrew , 18-numeric, 24-move left, 27-space
        if(pos==12) {
           vpPager.setCurrentItem(2);//hebrew
        } else if(pos==18) {
            vpPager.setCurrentItem(0);//numeric
        }else if(pos==24) {
           vpPager.setCurrentItem(0);//left-numeric
        }else if(pos==27) {
            ic.commitText(" ", 1);
        } else {
            ic.commitText(alphabets[pos], 0);
        }


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


        final String alphabets[] = {
                "\u05D5", "\u05D4", "\u05D3", "\u05D2", "\u05D1", "\u05D0",
                "\u05DB", "\u05DA", "\u05D9", "\u05D8", "\u05D7", "\u05D6",
                "*", "\u05E1", "\u05E0", "\u05DF", "\u05DE", "\u05DC",
                "*", "\u05E6", "\u05E5", "\u05E4", "\u05E3", "\u05E2",
                "<", "\u05EA", "\u05E9", "\u2423", "\u05E8", "\u05E7"
        };

        if((pos>=0 && pos<=11) || (pos>=13 && pos<=17)
                || (pos>=19 && pos<=23)
                || (pos>=13 && pos<=17)
                || (pos>=25 && pos<=26)
                || (pos>=28 && pos<=29) )
        {
                ic.commitText(alphabets[pos], 0);
        }

        new Thread(new Runnable() {
            @Override
            public void run() {

                Instrumentation inst = new Instrumentation();
                int keycode = KeyEvent.KEYCODE_0;

                switch (pos) {

                    case  12:
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                vpPager.setCurrentItem(0);//numeric
                            }
                        });
                        break; //HEBREW

                    case  18:
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                vpPager.setCurrentItem(1);//ABC
                            }
                        });

                        break; //123

                    case  24:     keycode = KeyEvent.KEYCODE_DEL ;
                                  inst.sendKeyDownUpSync(keycode);     ; break; //<


                    case  27:     keycode = KeyEvent.KEYCODE_SPACE;
                                  inst.sendKeyDownUpSync(keycode);      ; break; //SPACE


                }
            }
        }).start();




    }


    @Override
    public void onSymbolKeyPressed(final Integer Id) {


        new Thread(new Runnable() {
            @Override
            public void run() {

                Instrumentation inst = new Instrumentation();//KeyEvent.KEYCODE_SHIFT_LEFT
                int keycode = KeyEvent.KEYCODE_0;

                switch (Id) {
                    case R.id.row4_btn2:      inst.sendStringSync("$");      ; break;
                    case  R.id.row1_btn1:     inst.sendStringSync("(");      ; break;
                    case  R.id.row1_btn2:     inst.sendStringSync(")");      ; break;
                    /*case  R.id.row1_btn3:     keycode = KeyEvent.KEYCODE_3      ; break;
                    case  R.id.row2_btn1:     keycode = KeyEvent.KEYCODE_4      ; break;
                    case  R.id.row2_btn2:     keycode = KeyEvent.KEYCODE_5      ; break;
                    case  R.id.row2_btn3:     keycode = KeyEvent.KEYCODE_6      ; break;
                    case  R.id.row3_btn1:     keycode = KeyEvent.KEYCODE_7      ; break;
                    case  R.id.row3_btn2:     keycode = KeyEvent.KEYCODE_8      ; break;
                    case  R.id.row3_btn3:     keycode = KeyEvent.KEYCODE_9      ; break;
                    case  R.id.row4_btn1:     keycode = KeyEvent.KEYCODE_DEL    ; break;
                    case  R.id.row4_btn3:     keycode = KeyEvent.KEYCODE_ENTER  ; break;*/
                }

            }
        }).start();

    }


}
