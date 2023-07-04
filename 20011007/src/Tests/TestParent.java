package Tests;

import Entities.User;
import FileOp.EntitiesSingleton;
import junit.framework.TestCase;

public class TestParent extends TestCase{

    private User parent;
    private boolean isReady = false;//This class cannot be tested if the application has never been run before

    protected void setUp(){
        if(!EntitiesSingleton.getInstance().users.isEmpty()){
            parent = EntitiesSingleton.getInstance().users.get(0);
            isReady = true;
        }
    }

    public void testAdminRole(){
        if(isReady) {
            String result = parent.getRole();
            assertEquals("Parent",result);
        }
    }

    public void testAdminUsername(){
        if(isReady) {
            String result = parent.getUsername();
            assertEquals("parent", result);
        }
    }

    public void testAdminPassword(){
        if (isReady){
            String result = parent.getPassword();
            assertEquals("123456",result);
        }
    }

    public void testAdminID(){
        if (isReady) {
            int result = parent.getId();
            assertTrue(result == 0);
        }
    }
}
