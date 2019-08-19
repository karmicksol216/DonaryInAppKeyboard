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
import android.widget.EditText;
import android.widget.Toast;

import com.c2p.listeners.OnKeyBoard;

import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, OnKeyBoard {


    MyPagerAdapter adapterViewPager;
    //public EditText edt1,edt2,edt3;
    ViewPager vpPager;
    EditText edt;

    public String whichEdt="1";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

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


        @Override
        public int getCount() {
            return NUM_ITEMS;
        }


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



    public void setNumericKeyboard(){
        vpPager.setCurrentItem(0);//number
    }

    public void setAlphabetKeyboard(){
        vpPager.setCurrentItem(1);//text
    }


    @Override
    public void onKeyPressed(final Integer Id) {
       // numeric keypress

       /* //------ actual typing code ----
        switch (id) {

            *//*---- for numeric kbd1  start ----*//*
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
                whichEdt="2";
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

            *//*---- for numeric kbd1  end ----*//*
            default:
                break;
        }*/

        new Thread(new Runnable() {
            @Override
            public void run() {

                int keycode = KeyEvent.KEYCODE_0;

                switch (Id) {
                    case R.id.row4_btn2:      keycode = KeyEvent.KEYCODE_0      ; break;
                    case  R.id.row1_btn1:     keycode = KeyEvent.KEYCODE_1      ; break;
                    case  R.id.row1_btn2:     keycode = KeyEvent.KEYCODE_2      ; break;
                    case  R.id.row1_btn3:     keycode = KeyEvent.KEYCODE_3      ; break;
                    case  R.id.row2_btn1:     keycode = KeyEvent.KEYCODE_4      ; break;
                    case  R.id.row2_btn2:     keycode = KeyEvent.KEYCODE_5      ; break;
                    case  R.id.row2_btn3:     keycode = KeyEvent.KEYCODE_6      ; break;
                    case  R.id.row3_btn1:     keycode = KeyEvent.KEYCODE_7      ; break;
                    case  R.id.row3_btn2:     keycode = KeyEvent.KEYCODE_8      ; break;
                    case  R.id.row3_btn3:     keycode = KeyEvent.KEYCODE_9      ; break;
                    case  R.id.row4_btn1:     keycode = KeyEvent.KEYCODE_DEL    ; break;
                    case  R.id.row4_btn3:     keycode = KeyEvent.KEYCODE_ENTER  ; break;
                }
                Instrumentation inst = new Instrumentation();
                inst.sendKeyDownUpSync(keycode);
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

        new Thread(new Runnable() {
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
        }).start();

        /*//12-hebrew , 18-numeric, 24-move left, 27-space
        if(pos==12) {
            //move to hebrew kbd
            vpPager.setCurrentItem(2);//text
        } else if(pos==18) {
            //move to numeric kbd
            vpPager.setCurrentItem(0);//numeric
        }else if(pos==24) {
            //move left
             //vpPager.setCurrentItem(0);//text
            if(edt.getText().length()>0)
             edt.setText(edt.getText().subSequence(0,edt.getText().length()-1));
        }else if(pos==27) {
            //edt.setText(edt.getText() + " ");
        } else {
            edt.setText(edt.getText() + alphabets[pos]);
        }*/
    }

    @Override
    public void onHebrewKeyPressed(final Integer pos) {

        final String alphabets[] = {
                "\u05D0","\u05D1","\u05D2","\u05D3","\u05D4","\u05D5",
                "\u05D6","\u05D7","\u05D8","\u05D9","\u05DA","\u05DB",
                "*","\u05DC","\u05DE","\u05DF","\u05E0","\u05E1",
                "*","\u05E2","\u05E3","\u05E4","\u05E5","\u05E6",
                "<","\u05E7","\u05E3","\u2423","\u05E4","\u05E5"
        };

        new Thread(new Runnable() {
            @Override
            public void run() {

                Instrumentation inst = new Instrumentation();//KeyEvent.KEYCODE_SHIFT_LEFT
                int keycode = KeyEvent.KEYCODE_0;

                switch (pos) {
                    case  0:      inst.sendStringSync(getStringFromUnocode(alphabets[pos])); break;
                    case  1:      inst.sendStringSync(decodeUnicode(alphabets[pos])); break;
                    case  2:      inst.sendStringSync(getString(R.string.h1)); break;

                    case  3:      inst.sendStringSync("\0x05D0"); break;
                    case  4:      inst.sendStringSync(alphabets[pos]); break;
                    case  5:      inst.sendStringSync(alphabets[pos]); break;

                    case  6:      inst.sendStringSync(alphabets[pos]); break;
                    case  7:      inst.sendStringSync(alphabets[pos]); break;
                    case  8:      inst.sendStringSync(alphabets[pos]); break;
                    case  9:      inst.sendStringSync(alphabets[pos]); break;
                    case  10:     inst.sendStringSync(alphabets[pos]); break;
                    case  11:     inst.sendStringSync(alphabets[pos]);break;

                    case  12:
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                vpPager.setCurrentItem(0);//numeric
                            }
                        });
                        break; //HEBREW

                    case  13:     inst.sendStringSync(alphabets[pos]); break;
                    case  14:     inst.sendStringSync(alphabets[pos]);break;
                    case  15:     inst.sendStringSync(alphabets[pos]); break;
                    case  16:     inst.sendStringSync(alphabets[pos]); break;
                    case  17:     inst.sendStringSync(alphabets[pos]); break;

                    case  18:
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                vpPager.setCurrentItem(1);//ABC
                            }
                        });

                        break; //123

                    case  19:      inst.sendStringSync(alphabets[pos]); break;
                    case  20:      inst.sendStringSync(alphabets[pos]); break;
                    case  21:      inst.sendStringSync(alphabets[pos]); break;
                    case  22:      inst.sendStringSync(alphabets[pos]); break;
                    case  23:      inst.sendStringSync(alphabets[pos]); break;

                    case  24:     keycode = KeyEvent.KEYCODE_DEL ;
                                  inst.sendKeyDownUpSync(keycode);     ; break; //<

                    case  25:     inst.sendStringSync(alphabets[pos]); break;
                    case  26:     inst.sendStringSync(alphabets[pos]); break;

                    case  27:     keycode = KeyEvent.KEYCODE_SPACE;
                                  inst.sendKeyDownUpSync(keycode);      ; break; //SPACE

                    case  28:     inst.sendStringSync(alphabets[pos]); break;
                    case  29:     inst.sendStringSync(alphabets[pos]);  break;
                }
            }
        }).start();

        /*//12-hebrew , 18-numeric, 24-move left, 27-space
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
        }*/

    }


    private String getStringFromUnocode( String theuni){

        String utf8Text = theuni;
        byte[] bytes = utf8Text.getBytes(StandardCharsets.UTF_8);
        String text = new String(bytes, StandardCharsets.UTF_8);
        return text;
    }

    private static String decodeUnicode(String theString) {
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len;) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed   \\uxxxx   encoding.");
                        }

                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';
                    else if (aChar == 'n')
                        aChar = '\n';
                    else if (aChar == 'f')
                        aChar = '\f';
                    outBuffer.append(aChar);
                }
            } else
                outBuffer.append(aChar);
        }
        return outBuffer.toString();
    }


}
