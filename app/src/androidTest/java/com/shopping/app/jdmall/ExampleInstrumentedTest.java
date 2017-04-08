package com.shopping.app.jdmall;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.shopping.app.jdmall.bean.SkuBean;
import com.shopping.app.jdmall.utils.SkuTransferUtils;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.shopping.app.jdmall", appContext.getPackageName());
    }

    @Test
    public void test1(){
        SkuBean skuBean = new SkuBean();
        skuBean.setId(1);
        skuBean.setAttrs(new int[]{1,3});
        skuBean.setNum(3);
        List<SkuBean> skuBeen = new ArrayList<>();
        skuBeen.add(skuBean);
        skuBeen.add(skuBean);
        String s = SkuTransferUtils.ListToString(skuBeen);
       System.out.println(s);

    }

    @Test
    public void test2(){

        List<SkuBean> skuBeen = SkuTransferUtils.StringToList("1:3:1,2|2:2:2,3");
        System.out.println(skuBeen);

    }

}
