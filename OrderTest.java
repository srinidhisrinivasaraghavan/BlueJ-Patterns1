

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class OrderTest
{

    public OrderTest()
    {
    }

    @Before
    public void setUp()
    {
    }

    @After
    public void tearDown()
    {
    }

    @Test
    public void baseTest()
    {
        OrderManager.printOrderAndPrice();
    }

    @Test
    public void TestMakeOrderThenPrint()
    {
        OrderManager om=new OrderManager();
        om.makeOrderAndPrintOrderAndPrice();
    }
}


