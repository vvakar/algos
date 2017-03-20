package vvakar.tree;

/**
 * Binary Index (Fenwick) Tree.
 * @See https://www.topcoder.com/community/data-science/data-science-tutorials/binary-indexed-trees/
 */

public class FenwickTree {
	private final int[] ftree; // 1-based, for simplicity

	public FenwickTree(int[] xs) {
		int n = xs.length;
		ftree = new int[n+1];

		// initialize
		for(int i = 1; i <= n; ++i)
			update(i, xs[i-1]);
	}

	public void update(int i, int val) {
		for(;i < ftree.length; i += lsb(i))
			ftree[i] += val;
	}

	public int query(int i) {
		int total = 0;
		for(; i > 0; i -= lsb(i))
			total += ftree[i];
		return total;
	}

	private static int lsb(int i) {
		return i & -i;
	}
}
