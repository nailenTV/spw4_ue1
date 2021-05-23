package spw4.tsp;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;

public class InversionMutatorTest {

	@Test
	void mutateWithValidPermutation() {
		var p = new Permutation(new int[] {
				0,1,2,3,4,5,6,7
				
		});
		
		TSPSolver.random = new RandomStub(List.of(3,2));
		
		var expected = new int[] {
				0,1,2,6,5,4,3,7
		};
		
		var sut = new InversionMutator();
		var result = sut.mutate(p);
		
		assertArrayEquals(expected, result.getValues());
		
	}
	
	@ParameterizedTest
    @NullSource
    void mutateInvalidWithNullPermutation(Permutation p) {
		var sut = new InversionMutator();

		assertThrows(
				IllegalArgumentException.class, () -> sut.mutate(p)
	        );
	}
	
}
