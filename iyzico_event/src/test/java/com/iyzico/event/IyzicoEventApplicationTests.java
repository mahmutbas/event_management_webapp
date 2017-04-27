package com.iyzico.event;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IyzicoEventApplicationTests
{
    @Test
    public void contextLoads()
    {
        try
        {
            IyzicoEventApplication.main(new String[]{"main"});
        }
        catch (Exception exception)
        {
            fail();
        }
    }
}
