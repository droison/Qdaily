package com.qdaily.util;

import android.app.Activity;
import android.content.Context;

import com.droison.util.ImageUtils;
import com.qdaily.ui.R;

/**
 * Created by song on 9/10/14.
 */
public class ImageScale {
    private static int screenWidth;
    private static int tab2ImgWidth;
    private static int tab2ImgHeight;
    private static int tab4CategoryHeight;
    private static int tab4TopHeight;

    private static int tab1PPTWidth;
    private static int tab1PPTHeigh;
    private static int tab1GridWidth;
    private static int tab1GridHeight;

    public static int getScreenWidth(Activity mActivity){
        if (screenWidth == 0){
            screenWidth = ImageUtils.getScreenWidth(mActivity);
        }
        return screenWidth;
    }

    public static int getTab2ImgWidth(Activity mActivity){
        if (tab2ImgWidth==0){
            tab2ImgWidth = getScreenWidth(mActivity) - 3*mActivity.getResources().getDimensionPixelOffset(R.dimen.recommend_grid_margin);
            tab2ImgWidth = tab2ImgWidth/2;
        }
        return tab2ImgWidth;
    }

    public static int getTab2ImgHeight(Activity mActivity){
        if (tab2ImgHeight==0){
            tab2ImgHeight = getTab2ImgWidth(mActivity) * 304/531;
        }
        return tab2ImgHeight;
    }

    public static int getTab4CategoryHeight(Activity mActivity){
        if (tab4CategoryHeight==0){
            tab4CategoryHeight = getScreenWidth(mActivity) * 250/1080;
        }
        return tab4CategoryHeight;
    }

    public static int getTab4TopHeight(Activity mActivity){
        if (tab4TopHeight==0){
            tab4TopHeight = mActivity.getResources().getDimensionPixelOffset(R.dimen.t4_top_list_heigh);
        }
        return tab4TopHeight;
    }

    public static int getTab1PPTWidth(Activity mActivity){
        if (tab1PPTWidth == 0){
            tab1PPTWidth = getScreenWidth(mActivity) - 4 * mActivity.getResources().getDimensionPixelOffset(R.dimen.t1_padding);
        }
        return tab1PPTWidth;
    }

    public static int getTab1PPTHeigh(Activity mActivity){
        if (tab1PPTHeigh == 0){
            tab1PPTHeigh = getTab1PPTWidth(mActivity) * 600/1068;
        }
        return tab1PPTHeigh;
    }

    public static int getTab1GridWidth(Activity mActivity){
        if (tab1GridWidth == 0){
            tab1GridWidth = getTab1PPTWidth(mActivity)/2 - mActivity.getResources().getDimensionPixelOffset(R.dimen.t1_padding);
        }
        return tab1GridWidth;
    }

    public static int getTab1GridHeight(Activity mActivity){
        if (tab1GridHeight == 0){
            tab1GridHeight = getTab1GridWidth(mActivity) * 763/531;
        }
        return tab1GridHeight;
    }
}
