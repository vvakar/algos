package vvakar.tree;

import java.util.Arrays;

/**
 * Segment tree inspired by http://codeforces.com/blog/entry/18051
 */
public class SegmentTree {
	final int n; // nearest gt or e power of two
	final int[] stree; // 1-based balanced tree representation
	final Op op;

	public SegmentTree(int[] arr, Op op) {
		this.op = op;
		n = getNextEqualOrGtPowerOfTwo(arr.length);
		stree = new int[n * 2];
		Arrays.fill(stree, op.ZERO);

		// copy arr into stree
		for(int i = 0; i < arr.length; ++i)
			stree[n + i] = arr[i];

		// build stree
		for(int i = n-1; i > 0; --i)
			stree[i] = op.apply(stree[i<<1], stree[i<<1|1]);
	}

	public int query(int l, int rExcl) {
		int total = op.ZERO;

		for(l += n, rExcl += n; l < rExcl; l >>= 1, rExcl >>= 1) {
			if((l & 1) == 1) total = op.apply(total, stree[l++]);
			if((rExcl & 1) == 1) total = op.apply(total, stree[--rExcl]);
		}
		return total;
	}

	public void update(int i, int val) {
		int c = i + n;
		stree[c] = val;

		while((c = c >> 1) > 0)
			stree[c] = op.apply(stree[c << 1], stree[(c << 1) + 1]);
	}

	private static int getNextEqualOrGtPowerOfTwo(int x) {
		int n = 1;
		while(n < x) n <<= 1;
		return n;
	}

	public static abstract class Op {
		public final int ZERO;
		public Op(int zero) {
			this.ZERO = zero;
		}
		abstract int apply(int a, int b);
	}

	public final static Op SUM = new Op(0) {
		@Override
		int apply(int a, int b) { return a + b; }
	};

	public final static Op MAX = new Op(Integer.MIN_VALUE) {
		@Override
		int apply(int a, int b) { return Math.max(a,b); }
	};

	public final static Op MIN = new Op(Integer.MAX_VALUE) {
		@Override
		int apply(int a, int b) { return Math.min(a,b); }
	};
}
