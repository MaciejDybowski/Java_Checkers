package Dybowski;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseListener;

public class MySquare{
    int width,height,type,x,y;
    String field;

    public MySquare(int x , int y, int width, int height,int type) {
        this.width = width;
        this.height = height;
        this.type = type;
        this.x = x;
        this.y = y;

    }

    Rectangle getSquare(){
        return new Rectangle(this.x ,this.y,this.width,this.height);
    }

    int getType(){
        return this.type;
    }
    void setName(String temp){
        this.field = temp;
    }
}
