package spw4.tsp;

import java.util.Iterator;
import java.util.Random;

public class RandomStub<T> extends Random {
	
	private Iterator<T> iterator;
	
	RandomStub(Iterable<T> values){
		iterator = values.iterator();
		
	}
	
	@Override
	public int nextInt() {
		return (int)iterator.next();
	}
	@Override
	public int nextInt(int ignored) {
		return (int)iterator.next();
	}
}
