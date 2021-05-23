package spw4.tsp;


import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class SolutionTest {

	@ParameterizedTest(name = "value = {0}")
    @ValueSource(doubles = {2.3, 4.2, 130.9})
    void ctorWithValidLength(double value){
        Solution<Integer> so = new Solution<>(2, value);
        
        assertAll(() -> assertEquals(2,so.getSolutionData()),
        		() ->assertEquals(value,so.getQuality()));
    }

	@Test
	void ctorWithInvalidSolutionInput(){
		 assertThrows(IllegalArgumentException.class,
		            () -> new Solution<Integer>(null,23.2)
		        );
    }
	
	@ParameterizedTest()
	@ValueSource(doubles = {Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY})
	void ctorWithInvalidQualityInput(double quality){
		 assertThrows(IllegalArgumentException.class,
		            () -> new Solution<Integer>(2,quality)
		        );
    }
	
	@ParameterizedTest(name = "value = {0}")
    @ValueSource(doubles = {4,-3, 2})
    void compareToSolutionTestSmallerInput(double value){
        Solution<Integer> so = new Solution<>(2, 10);
        Solution<Integer> soInput = new Solution<>(2, value); 
        assertTrue(so.compareTo(soInput) == 1);
    }
	
	@ParameterizedTest(name = "value = {0}")
    @ValueSource(ints = {4,-3, 2})
	 void compareToSolutionTestEqual(int value){
	        Solution<Integer> so = new Solution<>(value, 10);
	        Solution<Integer> soInput = new Solution<>(2, 10); 
	        assertTrue(so.compareTo(soInput) == 0);
	 }
	
	@ParameterizedTest(name = "value = {0}")
    @ValueSource(doubles = {11, 22})
    void compareToSolutionTestGreater(double value){
        Solution<Integer> so = new Solution<>(2, 10);
        Solution<Integer> soInput = new Solution<>(2, value); 
        assertTrue(so.compareTo(soInput) == -1);
    }
	
	@ParameterizedTest(name = "value = {0}")
	@ValueSource(doubles = {10, 22})
	 void compareToSolutionTestEqualsSmaller(double value){
	       Solution<Integer> so = new Solution<>(2, 10);
	        Solution<Integer> soInput = new Solution<>(2, value); 
	        assertTrue(so.compareTo(soInput) <= 0);
	}
	
	@ParameterizedTest(name = "value = {0}")
	@ValueSource(doubles = {10, 8})
	 void compareToSolutionTestEqualsGreater(double value){
	       Solution<Integer> so = new Solution<>(2, 10);
	        Solution<Integer> soInput = new Solution<>(2, value); 
	        assertTrue(so.compareTo(soInput) >= 0);
	}
	
	@ParameterizedTest(name = "doubles = {0}")
    @ValueSource(doubles = {11.3, 22.33333})
	void toStringSolutionTest(double value) {
		Solution<Integer> so = new Solution<>(2, value);
		String str = new String(String.format("%.3f", so.getQuality()) + "   " + so.getSolutionData().toString());
	
		assertEquals(str,so.toString());
	}
}
