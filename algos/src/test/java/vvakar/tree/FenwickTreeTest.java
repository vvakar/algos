package vvakar.tree;

import static junit.framework.Assert.assertEquals;

import org.junit.Test;

public class FenwickTreeTest {
	//							   1  2  3  4  5  6   7  8  9  10  11  12 13  14  15  16  17
	private int xs[] = new int[] { 7, 2, 4, 7, 6, 11, 8, 4, 3, 12, 14, 19, 3, 18, 21, 20, 6 };

	@Test
	public void testFenwick() {
		FenwickTree fenwickTree = new FenwickTree(xs);

		assertEquals(7, fenwickTree.query(1));
		assertEquals(45, fenwickTree.query(7));
		assertEquals(97, fenwickTree.query(12));
		assertEquals(165, fenwickTree.query(17));
	}
}
