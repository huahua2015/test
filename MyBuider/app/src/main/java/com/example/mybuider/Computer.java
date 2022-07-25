package com.example.mybuider;

public abstract class Computer {
    protected String mBoard;
    protected String mDisplay;
    protected String mOS;
    protected Computer(){}
    //设置主板
    public void setBoard(String board){
        this.mBoard=board;
    }
    //设置显示器
    public void setDisplay(String display) {
        mDisplay=display;
    }
    //设置操作系统
    public abstract void  setOS();

    @Override
    public String toString() {
        StringBuilder stringBuilder=new StringBuilder();
        if (mBoard!=null) {
            stringBuilder.append("myBoard is:"+mBoard);
        }
        if (mDisplay!=null) {
            stringBuilder.append("\nmDisplay is:"+mDisplay);
        }
        if (mOS!=null) {
            stringBuilder.append("\nmyOS is:"+mOS);
        }
        return stringBuilder.toString();
    }
}
