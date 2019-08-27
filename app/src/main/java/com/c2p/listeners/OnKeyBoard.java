package com.c2p.listeners;

public interface OnKeyBoard {

    void onKeyPressed(Integer value);
    void onAlphaKeyPressed(Integer  value);
    void onHebrewKeyPressed(Integer  value);
    void onSymbolKeyPressed(Integer value);
    void onQwertyKeyPressed(Integer  value);
    void onHebrewQwertyKeyPressed(Integer  value);

    void onLongKeyPressed(Integer value);
}