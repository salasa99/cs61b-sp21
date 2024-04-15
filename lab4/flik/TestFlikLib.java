package flik;

import org.junit.Assert;
import  org.junit.Test;

public class TestFlikLib {
    @Test
    public void TestIsSameNumber() {
        boolean func_rslt = Flik.isSameNumber(128, 128);
        Assert.assertTrue(func_rslt);
    }
}
