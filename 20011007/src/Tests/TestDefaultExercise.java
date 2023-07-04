package Tests;

import Entities.Exercise;
import FileOp.EntitiesSingleton;
import junit.framework.TestCase;

public class TestDefaultExercise extends TestCase {

    private Exercise exercise;
    private boolean isReady = false;//This class cannot be tested if the application has never been run before

    @Override
    protected void setUp(){
        if(!EntitiesSingleton.getInstance().exercises.isEmpty()){
            exercise = EntitiesSingleton.getInstance().exercises.get(0);
            isReady = true;
        }
    }

    public void testExerciseId(){
        if(isReady){
            int result = exercise.getId();
            assertEquals(0,result);
        }
    }
    public void testExerciseN(){
        if(isReady){
            int result = exercise.getN();
            assertEquals(5,result);
        }
    }
    public void testExerciseA1(){
        if(isReady){
            int result = exercise.getA1();
            assertEquals(1,result);
        }
    }
    public void testExerciseA2(){
        if(isReady){
            int result = exercise.getA2();
            assertEquals(10,result);
        }
    }
    public void testExerciseB1(){
        if (isReady){
            int result = exercise.getB1();
            assertEquals(1,result);
        }
    }
    public void testExerciseB2(){
        if (isReady){
            int result = exercise.getB2();
            assertEquals(10,result);
        }
    }

    public void testExerciseTitle(){
        if(isReady){
            String result = exercise.getTitle();
            assertEquals("Default mode",result);
        }
    }


}
