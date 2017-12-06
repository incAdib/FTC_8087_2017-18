package org.firstinspires.ftc.teamcode;

import android.graphics.Bitmap;
import android.graphics.Color;

import java.nio.ByteBuffer;
import com.vuforia.Frame;
import com.vuforia.PIXEL_FORMAT;

import org.firstinspires.ftc.robotcore.internal.android.dx.util.ByteArray;

/**
 * Created by zac on 10/6/2017.
 */

public class JewelSide {

    private Bitmap pic;//the picture that contains both jewels and a pictograph in bitmap format

    public JewelSide(Frame frame){
        if(frame.getImage(0).getFormat() == PIXEL_FORMAT.RGBA8888) {//testing to see wat format the image is in so we can convert it into a bitmap
            pic = Bitmap.createBitmap(frame.getImage(0).getBufferWidth(), frame.getImage(0).getHeight(), Bitmap.Config.ARGB_8888);//defining the size of the image so it can be copied into bitmap format
        }else if(frame.getImage(0).getFormat() == PIXEL_FORMAT.RGB565){//testing to see what format the image is in so we can convert it into a bitmap
            pic = Bitmap.createBitmap(frame.getImage(0).getBufferWidth(), frame.getImage(0).getHeight(), Bitmap.Config.RGB_565);//defining the size of the image so it can be copied into bitmap format
        }
        pic.copyPixelsFromBuffer(ByteBuffer.wrap(frame.getImage(0).getPixels().array()));//copying the image into bitmap format
    }

    public JewelSide(Bitmap pic){
        this.pic = pic;
    }

    public boolean blueRightSide(){ // a method that returns true if the blue jewel is on the right side false if not

        int redTotalX = 0;//sum of all x coordinates belonging to pixels with a red value above the threshold used for averaging
        int blueTotalX = 0;//sum of all x coordinates belonging to pixels with a blue value above the threshold used for averaging
        int threshold = 254;//threshold that determines if a pixel is red or blue

        int minNum = 25;//minimum number of pixels needed for a jewel to be detected
        int redNum = 0;//number of red pixels detected used for averaging and determining if a ball is detected
        int blueNum = 0;//number of blue pixels detected averaging and determining if a ball is detected`

        for(int y = 0; y < pic.getHeight(); y++){//iterating through every collum of pixels
            for(int x = 0; x < pic.getWidth(); x++){//iterating through every row of pixels

                if(Color.red(pic.getPixel(x,y)) >= threshold){//testing if the pixel is red
                    redTotalX += x;//adding the x value of the pixel that was previously determined to be red to the total x value of red pixels
                    redNum++;//declaring that another red pixel was found
                }

                if(Color.blue(pic.getPixel(x,y)) >= threshold){//testing if the pixel is blue
                    blueTotalX += x;//adding the x value of the pixel that was previously determined to be blue to the total x value of blue pixels
                    blueNum++;//declaring that another blue pixel was found
                }
            }
        }

        if(redNum < minNum  && blueNum > minNum){//testing to see if only the blue jewel has been detected
            return false;// this code will only run when the pictograph is detected so if only the blue jewel is detected it is on the left
        }else if(blueNum < minNum && redNum > minNum){//testing to see i only the red jewel has been detected
            return true;// this code will only run when the pictograph is detected so if only the red jewel is detected it is on the left
        }

        if(redTotalX/redNum > blueTotalX/blueNum){//the further to the right something is in the picture the higher its x value it is so if the avg x value of all red pixels in the image is higher than the avg blue values then the red ball is on the right.            this tests what side the blue jewel is on
            return false;//returns false based on the test above ^^
        }else{
            return true;//returns true based on the test above ^^
        }    }

}