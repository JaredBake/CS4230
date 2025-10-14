import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class credentialsTest {

    @Test
    public void testGetUsername() {
        credentials cred = new credentials("user1", "pass1");
        assertEquals("user1", cred.getUsername());
    }

    @Test
    public void testGetPassword() {
        credentials cred = new credentials("user1", "pass1");
        assertEquals("pass1", cred.getPassword());
    }
}
