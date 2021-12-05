package com.brick;

import static org.junit.Assert.assertTrue;

//import com.brick.util.TokopediaUtil;
import com.brick.util.TokopediaUtil;
//import com.brick.util.Util2;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @Test
    public void testGetHTML() throws Exception
    {
        TokopediaUtil.produceTopProductsInfoFile();
    }
}
