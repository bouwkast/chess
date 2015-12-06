/**
 * 
 */
package tester;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * @author andy
 *
 */
public class TestRunner {

    public static void main(String[] args){
        
        Result result = JUnitCore.runClasses(MainTester.class);
        for (Failure failure : result.getFailures()){
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
        
    }
}
