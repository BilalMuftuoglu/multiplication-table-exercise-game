package Tests;

import Entities.User;
import junit.framework.TestCase;

public class TestValidation extends TestCase {

    @Override
    protected void setUp(){

    }

    public void testUsernameMessage(){
        String result = User.usernamePasswordCompatibility("bi","123456");
        assertEquals("Username must be at least 3 characters",result);
    }

    public void testPasswordMessage(){
        String result = User.usernamePasswordCompatibility("bilal","12345");
        assertEquals("Password must be at least 6 characters",result);
    }
}
