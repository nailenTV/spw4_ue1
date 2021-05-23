package spw4.tsp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;


public class RandomselectorTest {
	
	@ParameterizedTest
    @NullAndEmptySource
	void selectTestNullSolutions( List<Solution<Integer>> val) {
		RandomSelector<Integer> rs = new RandomSelector<>();
		 assertThrows(
		            IllegalArgumentException.class,
		            () -> rs.select(val,-3)
		        );
	}
	
	@ParameterizedTest
	@ValueSource(ints = {0, -4})
	void selectTestInvalidParentSize(int parent) {
		RandomSelector<Integer> rs = new RandomSelector<>();
		Solution<Integer> so = new Solution<>(2,parent);
		List<Solution<Integer>> solutions = new ArrayList<Solution<Integer>>();
		solutions.add(so);
		 assertThrows(
		            IllegalArgumentException.class,
		            () -> rs.select(solutions,-1)
		        );
	}
	
	@ParameterizedTest(name = "parent = {0}")
	@ValueSource(ints = {2, 4})
	void randomSelectorTest(int parent) {
		List<Integer> list = new ArrayList<>();
    	for(int i = 0; i < parent;i++)
    		list.add(0);
    	TSPSolver.random = new RandomStub<Integer>(list);
    	
    	RandomSelector<Integer> rs = new RandomSelector<>();
		Solution<Integer> so = new Solution<>(2,parent);
		List<Solution<Integer>> solutions = new ArrayList<Solution<Integer>>();
		for(int i = 0; i < parent;i++)
			solutions.add(so);
		List<Solution<Integer>> selected = new ArrayList<>();
		selected = rs.select(solutions, parent);
		
		assertEquals(selected,solutions);
		 
	}
	
	
}
