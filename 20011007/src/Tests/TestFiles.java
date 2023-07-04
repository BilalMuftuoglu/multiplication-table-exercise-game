package Tests;

import FileOp.EntitiesSingleton;
import junit.framework.TestCase;

public class TestFiles extends TestCase {

    EntitiesSingleton entities;

    @Override
    protected void setUp(){
        entities = EntitiesSingleton.getInstance();
    }

    public void testUsersFile(){
        assertNotNull(entities.users);
    }

    public void testExercisesFile(){
        assertNotNull(entities.exercises);
    }

    public void testExerciseResultsFile(){
        assertNotNull(entities.exerciseResults);
    }

    public void testExerciseResultDetailsFile(){
        assertNotNull(entities.exerciseResultDetails);
    }

    public void testScoresFile(){
        assertNotNull(entities.scores);
    }
}
