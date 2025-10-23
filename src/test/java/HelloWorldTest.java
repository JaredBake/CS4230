import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HelloWorldTest {
    
    @Test
    public void testAddition() {
        Integer result = 1 + 1;
        assertEquals(2, result, "1 + 1 should equal 2");
    }
}