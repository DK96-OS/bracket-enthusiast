package brackets.nesting;

import java.util.Vector;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import brackets.BracketEnthusiast;

/** The Builder for a Tree of Bracket Nodes.
 */
public final class BracketTreeBuilder {

	BracketNode root = null;

	BracketNode recent = null;

	/** Build a Top Level Array of Bracket Nodes from an Enthusiast.
	 * @param enthusiast The BracketEnthusiast to provide data.
	 * @return An Array of BracketNode which may be empty, or null if brackets are imbalanced.
	 */
	@Nullable
	public BracketNode[] buildFromEnthusiast(
		@Nonnull final BracketEnthusiast enthusiast
	) {
		// Ensure that the Enthusiast contains balanced data
		if (!enthusiast.getAreBracketsBalanced())
			return null;
		// Obtain the Data from the Enthusiast
		final int[] pairs = enthusiast.getBracketPairs();
		if (pairs == null)
			return null;
		// Clear the builder's root
		clear();
		var topLevelNodes = new Vector<BracketNode>(2, 4);
		// Parse the Pairs
		int index = 0;
		while (index < pairs.length) {
			// Try to add to the Root Node
			if (addNode(pairs[index], pairs[index + 1])) {
				index += 2; // Next Pair
			} else {
				// Pop Root and Try Again
				topLevelNodes.add(root);
				clear();
			}
		}
		if (root != null) {
			topLevelNodes.add(root);
		}
		clear();
		return topLevelNodes.toArray(new BracketNode[0]);
	}

	/** Add A Node to The Builder tree.
	 * Empty builder accepts any node, sets it to builder root.
	 * @param open  The index of the opening bracket.
	 * @param close The index of the closing bracket.
	 * @return Whether the node was successfully added to the builder.
	 */
	boolean addNode(
		final int open,
		final int close
	) {
		if (root == null) {
			root = new BracketNode(open, close);
			recent = root;
			return true;
		}
		if (root == recent) {
			// todo:
			return root.addNode(open, close);
		}
		// todo:
		//
		return false;
	}

	/** Reset the Builder references.
	 */
	void clear() {
		root = null;
		recent = null;
	}

}