package spw4.tsp;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PermutationTest {

    @ParameterizedTest(name = "length = {0}")
    @ValueSource(ints = {2, 4, 130})
    void ctorWithValidLength(int length){
        Permutation p = new Permutation(length);

        //int[] expected = new int[] {0, 1, 2}; // order does not matter
        int[] expected = IntStream.range(0, length).toArray();

        assertThat(p.getValues()).containsExactlyInAnyOrder(expected);
    }

    @ParameterizedTest(name = "length = {0}")
    @ValueSource(ints = {0, 1, -1})
    void ctorWithInvalidLength(int length){
        assertThrows(
            IllegalArgumentException.class,
            () -> new Permutation(length)
        );
    }

    @ParameterizedTest
    @MethodSource
    void ctorWithValidValues(int [] values){
        Permutation p = new Permutation(values);

        assertArrayEquals(values, p.getValues());
    }
    static Stream<Arguments> ctorWithValidValues(){
        return Stream.of(
            Arguments.of(new int[]{ 0, 1 }),
            Arguments.of(new int[]{ 1, 0 }),
            Arguments.of(new int[]{ 0, 1, 2, 3 }),
            Arguments.of(new int[]{ 4, 3, 0, 1, 2 })
        );
    }

    @ParameterizedTest
    @MethodSource
    @NullAndEmptySource
    void ctorWithInvalidValues(int [] values){
        assertThrows(IllegalArgumentException.class,
            () -> new Permutation(values)
        );
    }
    static Stream<Arguments> ctorWithInvalidValues(){
        return Stream.of(
            Arguments.of(new int[]{ 0 }),
            Arguments.of(new int[]{ 2, 0 }),
            Arguments.of(new int[]{ 0, 1, 1 }),
            Arguments.of(new int[]{ 0, 1, -2 })
        );
    }
    
    @Test
    public void testValidToString()
    {
    	Permutation p = new Permutation(2);
        String expected = "[0, 1]";
       assertEquals(expected, p.toString());
    }
    
    @Test
    public void testInvalidToString()
    {
    	Permutation p = new Permutation(3);
        String expected = "[2, 1, 0]";
        assertNotEquals(expected, p.toString());
    }
    
    @ParameterizedTest(name = "length = {0}")
    @ValueSource(ints = {2, 4, 130})
    void createRandomTestValidLength(int length){
  	
    	List<Integer> list = new ArrayList<>();
    	for(int i = 0; i < length;i++)
    		list.add(0);
    	
    	TSPSolver.random = new RandomStub<Integer>(list);
    	
    	var p = Permutation.createRandom(length);
        int[] expected = IntStream.range(0, length).toArray();
         
        assertThat(p.getValues()).containsExactlyInAnyOrder(expected);
    }
    
    @ParameterizedTest(name = "length = {0}")
    @ValueSource(ints = {0, 1, -1})
    void createRandomTestInvalidLength(int length){
        assertThrows(
            IllegalArgumentException.class,
            () -> Permutation.createRandom(length)
        );
    }
    
    
    
}