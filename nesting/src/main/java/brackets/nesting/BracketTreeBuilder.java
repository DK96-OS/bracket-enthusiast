package brackets.nesting;

import java.util.Vector;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import brackets.BracketEnthusiast;

/** The Builder for a Tree of Bracket Nodes.
 */
public final class BracketTreeBuilder {

	/** The Root Node in the Tree.
	 */
	private BracketNode mRoot = null;

	@Nullable
	BracketNode getRoot() {
		return mRoot;
	}

	/** Build a Top Level Array of Bracket Nodes from an Enthusiast.
	 * Clears the Builder before and after this operation.
	 * @param enthusiast The BracketEnthusiast to provide data.
	 * @return An Array of BracketNode which may be empty, or null if brackets are imbalanced.
	 */
	@Nullable
	public BracketNode[] buildFromEnthusiast(
		@Nonnull final BracketEnthusiast enthusiast
	) {
		// Clear Builder before starting
		mRoot = null;
		// Ensure that the Enthusiast contains balanced data
		if (!enthusiast.getAreBracketsBalanced())
			return null;
		// Obtain the Data from the Enthusiast
		final int[] pairs = enthusiast.getBracketPairs();
		if (pairs == null)
			return null;
		// Record the top Level Nodes
		var topLevelNodes = new Vector<BracketNode>(2, 2);
		// Parse the Pairs
		int index = 0;
		while (index < pairs.length) {
			// Try to add to the Root Node
			if (addNode(pairs[index], pairs[index + 1])) {
				index += 2; // Next Pair
			} else {
				topLevelNodes.add(mRoot); // Pop Root and Try Again
				mRoot = null;
			}
		}
		// There's always a root node remaining
		topLevelNodes.add(mRoot);
		mRoot = null;
		return topLevelNodes.toArray(new BracketNode[0]);
	}

	/** Add A Node to The Builder tree.
	 * Empty builder accepts any node, sets it to builder root.
	 * Builder with root tries to add the node anywhere within.
	 * Returns false if unable to add the nade within the root or it's sub-nodes.
	 * Modifies the recent member reference to point to the most recently added node.
	 * @param open  The index of the opening bracket.
	 * @param close The index of the closing bracket.
	 * @return Whether the node was successfully added to the builder.
	 */
	boolean addNode(
		final int open,
		final int close
	) {
		if (mRoot == null) {
			// The builder is empty
			mRoot = new BracketNode(open, close);
			return true;
		}
		// Non-empty tree
		return mRoot.addNode(open, close);
	}

	/** Reset the Builder references.
	 */
	void clear() {
		mRoot = null;
	}

}