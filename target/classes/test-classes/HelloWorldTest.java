import org.junit.Assert;
import org.junit.Test;

public class HelloWorldTest {

    @Test
    
    public void testHello(){
        Integer a = 1 + 1;
        Assert.assertEquals(a, 2);
    }
}