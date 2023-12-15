package brackets.nesting;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/** A Bracket Node Tree Interface.
 * Each Node has an array of Sub-Nodes, which are randomly accessible.
 */
public interface BracketNodeInterface {

	/** Obtain the open and close bracket indices of this node.
	 * @return An Array containing the two index values.
	 */
	@Nonnull
	int[] getIndices();

	/** Determine the number of direct internal nodes.
	 * @return The count of nodes directly inside this node.
	 */
	int countSubNodes();

	/** Obtain the BracketNode at the given index of the SubNode Array.
	 * @param index The Index of the Node to obtain.
	 * @return The BracketNode, or null if the index was invalid.
	 */
	@Nullable
	BracketNodeInterface getSubNodeAt(
		final int index
	);

	/** Determines whether this Node contains an index, by comparing to bracket indices.
	 * @param index The given index to compare with the brackets.
	 * @return The distance to the index, which is positive if greater than the closing bracket, negative if less than the opening bracket, and zero if contained within.
	 */
	int compareIndex(
		final int index
	);

	/** Find an internal node that may contain the given index value.
	 * @param index The index relative to the bracket pairs.
	 * @return The BracketNode that contains the index, or null.
	 */
	@Nullable
	BracketNodeInterface findNodeContainingIndex(
		final int index
	);

}