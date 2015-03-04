package io.github.sing1ee.autocomplete.tst;

/**
 * Created by zhangcheng on 15/3/4.
 * Ternary Search Tree Node
 */
public class TernaryNode {

    public TernaryNode left, mid, right;
    public char label;
    public boolean end = false;

    public TernaryNode(TernaryNode left, TernaryNode mid, TernaryNode right, char label, boolean end) {
        this.left = left;
        this.mid = mid;
        this.right = right;
        this.label = label;
        this.end = end;
    }

    public TernaryNode(char label) {
        this.label = label;
    }

    public TernaryNode(char label, boolean end) {
        this.label = label;
        this.end = end;
    }

    public TernaryNode() {
    }
}
