package Tests;

import junit.framework.*;

public class TestAll{

    public static Test suite(){
        TestSuite suite =  new TestSuite("All Tests");
        suite.addTestSuite(TestParent.class);
        suite.addTestSuite(TestValidation.class);
        suite.addTestSuite(TestDefaultExercise.class);
        suite.addTestSuite(TestFiles.class);
        return suite;
    }

}
