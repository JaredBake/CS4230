import org.junit.Assert;
import org.junit.Test;

public class HelloTest {
    @Test
    public void helloTest(){
        Bank bank = new Bank(6.0);
        Assert.assertNotNull(bank);
    }
}
