package vvakar.tree;

import static org.junit.Assert.assertEquals;
import static vvakar.tree.SegmentTree.MAX;
import static vvakar.tree.SegmentTree.MIN;
import static vvakar.tree.SegmentTree.SUM;

import org.junit.Test;

public class SegmentTreeTest {
							   	  // 0  1  2  3  4   5   6   7   8  9  10
	private int[] keys = new int[] { 9, 3, 7, 1, 8, 12, 10, 20, 15, 18, 5 };

	@Test
	public void testMinSegmentTree() {
		SegmentTree tree = new SegmentTree(keys, MIN);

		assertEquals(1, tree.query(1, 7));
		assertEquals(1, tree.query(0, 6));
		assertEquals(1, tree.query(3, 6));
		assertEquals(8, tree.query(4, 6));
		assertEquals(8, tree.query(4, 7));
		assertEquals(10, tree.query(5, 7));
		assertEquals(12, tree.query(5, 6));
		assertEquals(1, tree.query(0, 11));

		tree.update(1, -3);
		assertEquals(-3, tree.query(1, 7));
		assertEquals(-3, tree.query(0, 6));
		assertEquals(1, tree.query(3, 6));
		assertEquals(8, tree.query(4, 6));
		assertEquals(12, tree.query(5, 6));
		assertEquals(-3, tree.query(0, 11));
	}

	@Test
	public void testMaxSegmentTree() {
		SegmentTree tree = new SegmentTree(keys, MAX);

		assertEquals(12, tree.query(1, 7));
		assertEquals(12, tree.query(0, 6));
		assertEquals(12, tree.query(3, 6));
		assertEquals(12, tree.query(4, 6));
		assertEquals(12, tree.query(4, 7));
		assertEquals(12, tree.query(5, 7));
		assertEquals(12, tree.query(5, 6));
		assertEquals(20, tree.query(0, 11));

		tree.update(1, 100);
		assertEquals(100, tree.query(1, 7));
		assertEquals(100, tree.query(0, 6));
		assertEquals(12, tree.query(3, 6));
		assertEquals(12, tree.query(4, 6));
		assertEquals(12, tree.query(5, 6));
		assertEquals(100, tree.query(0, 11));
	}

	@Test
	public void testSumSegmentTree() {
		SegmentTree tree = new SegmentTree(keys, SUM);

		assertEquals(41, tree.query(1, 7));
		assertEquals(40, tree.query(0, 6));
		assertEquals(21, tree.query(3, 6));
		assertEquals(20, tree.query(4, 6));
		assertEquals(30, tree.query(4, 7));
		assertEquals(22, tree.query(5, 7));
		assertEquals(12, tree.query(5, 6));
		assertEquals(108, tree.query(0, 11));

		tree.update(1, 100);
		assertEquals(128, tree.query(1, 6));
		assertEquals(137, tree.query(0, 6));
		assertEquals(21, tree.query(3, 6));
		assertEquals(20, tree.query(4, 6));
		assertEquals(12, tree.query(5, 6));
		assertEquals(205, tree.query(0, 11));
	}

}
