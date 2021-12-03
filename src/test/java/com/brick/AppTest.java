package com.brick;

import static org.junit.Assert.assertTrue;

import com.brick.util.Util;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */

    Util util = new Util();

    @Test
    public void testGetHTML() throws Exception
    {
        util.produceFile("https://www.tokopedia.com/p/handphone-tablet/handphone");
    }
}
