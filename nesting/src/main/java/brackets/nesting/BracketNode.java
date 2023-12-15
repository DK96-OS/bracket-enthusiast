package brackets.nesting;

import java.util.ArrayList;
import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/** A Node representing a Pair of Brackets.
 * Provides storage of nested Bracket Pairs.
 */
public final class BracketNode
    implements BracketNodeInterface {

    /** The index of the opening bracket.
     */
    public final int open;

    /** The index of the closing bracket.
     */
    public final int close;

    private ArrayList<BracketNode> mInternalNodes = null;

    /** Construct a Bracket Node.
     *  Ensures that the bracket index are non-negative.
     *  Ensures that the index increases.
     * @param open The index of the opening bracket
     * @param close The index of the closing bracket.
     */
    BracketNode(
        final int open,
        final int close
    ) {
        this.open = open;
        this.close = close;
        if (!(close > open && open > -1))
            throw new IllegalArgumentException(
                String.format(
                    "Invalid Bracket Pair: %s",
                    super.toString()
                )
            );
    }

    /** Add a new Node to the array of direct descendants.
     *  Ensures that the given values are nested.
     * @param open The index of the opening bracket
     * @param close The index of the closing bracket.
     * @return Whether the given node is nested, and was added.
     */
    boolean addNode(
        final int open,
        final int close
    ) {
        // Validate Nesting within this Node
        if (!(compareIndex(open) == 0 && compareIndex(close) == 0))
            return false;
        if (open == this.open || close == this.close)
            return false;
        // Initialize the Internal Node Array if necessary
        if (mInternalNodes == null) {
            mInternalNodes = new ArrayList<>(1);
            // Create and Add the new Node
            mInternalNodes.add(
                new BracketNode(open, close)
            );
            return true;
        } else {
            // Search Internal Nodes
            var node = findNodeContainingIndex(open);
            if (node == null) {
                // Create and Add the new Node
                mInternalNodes.add(
                    new BracketNode(open, close)
                );
            } else {
                // Add the Node in a Sub-Node
                return ((BracketNode) node)
                    .addNode(open, close);
            }
        }
        return true;
    }

    @Nonnull
    @Override
    public int[] getIndices() {
        return new int[]{open, close};
    }

    @Override
    @Nullable
    public BracketNodeInterface findNodeContainingIndex(
        final int index
    ) {
        // Binary Search
        int minIndex = 0;
        int maxIndex = countSubNodes() - 1;
        //
        while (minIndex <= maxIndex) {
            // Compute the midpoint
            final int searchIndex = minIndex + (maxIndex - minIndex) / 2;
            // Get the node at the midpoint
            final BracketNodeInterface node = getSubNodeAt(searchIndex);
            // Compare the Index with the Node
            final int comparisonResult = node.compareIndex(index);
            // The Node matches the index
            if (comparisonResult == 0) {
                // Found the Node! Check it's SubNodes
                return Objects.requireNonNullElse(
                    node.findNodeContainingIndex(index),
                    node
                );
            } else if (comparisonResult < 0) {
                // Node is below the position containing the index
                maxIndex = searchIndex - 1;
            } else {
                // Node is above the position containing the index
                minIndex = searchIndex + 1;
            }
        }
        return null;
    }

    @Nullable
    @Override
    public BracketNodeInterface getSubNodeAt(
        int index
    ) {
        if (mInternalNodes == null)
            return null;
        return mInternalNodes.get(index);
    }

    @Override
    public int compareIndex(
        final int index
    ) {
        // Less than, returns negative
        if (open > index)
            return index - open;
        // Greater than, positive
        if (close < index)
            return index - close;
        // When the index is contained, return 0
        return 0;
    }

    @Override
    public int countSubNodes() {
        if (mInternalNodes == null)
            return 0;
        return mInternalNodes.size();
    }

}