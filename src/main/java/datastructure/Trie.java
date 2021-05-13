package datastructure;

import java.util.HashMap;
import java.util.Map;

public class Trie {

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String text) {
        TrieNode lastNode = root;
        lastNode = setChildWithText(text, lastNode);
        lastNode.setLast(true);
    }

    private TrieNode setChildWithText(String text, TrieNode lastNode) {
        for (int index = 0; index < text.length(); index++) {
            char oneWord = text.charAt(index);
            lastNode.setChild(oneWord);
            lastNode = lastNode.getChild(oneWord);
        }
        return lastNode;
    }

    public boolean contains(String text) {
        TrieNode lastNode = root;
        boolean isLastIndex = isLastIndex(text, lastNode);
        lastNode = getLastNodeAndSetParent(text, lastNode);

        return isLastIndex && lastNode.isLast();
    }

    private boolean isLastIndex(String text, TrieNode lastNode) {
        for (int index = 0; index < text.length(); index++) {
            char oneWord = text.charAt(index);
            if (!lastNode.hasChild(oneWord)) {
                return false;
            }
            lastNode = lastNode.getChild(oneWord);
        }
        return true;
    }

    private TrieNode getLastNodeAndSetParent(String text, TrieNode lastNode) {
        for (int index = 0; index < text.length(); index++) {
            char oneWord = text.charAt(index);
            if (lastNode.hasChild(oneWord)) {
                TrieNode parent = lastNode;
                lastNode = lastNode.getChild(oneWord);
                lastNode.setParent(parent);
                lastNode.setThisChar(oneWord);
            } else {
                break;
            }
        }
        return lastNode;
    }

    public void remove(String text) {
        if (contains(text)) {
            TrieNode lastNode = root;
            lastNode = getLastNodeAndSetParent(text, lastNode);
            removeLastNode(lastNode);
            removeCheck(lastNode);
        }

    }

    private void removeLastNode(TrieNode lastNode) {
        if (!lastNode.hasChildren()) {
            removeFromParent(lastNode);
        } else {
            lastNode.setLast(false);
        }
    }

    private void removeCheck(TrieNode lastNode) {
        if (!lastNode.hasChildren() && !lastNode.isLast()) {
            removeFromParent(lastNode);
        }
    }

    private void removeFromParent(TrieNode lastNode) {
        TrieNode parent = lastNode.getParent();
        parent.removeChild(lastNode.getThisChar());
    }

    public static class TrieNode {
        boolean isLast;
        TrieNode parent;
        Character thisChar;
        Map<Character, TrieNode> children;

        public TrieNode() {
            this.isLast = false;
            this.children = new HashMap<>();
        }

        public TrieNode getChild(Character oneChar) {
            return children.get(oneChar);
        }

        public void setChild(Character oneChar) {
            children.computeIfAbsent(oneChar, key -> new TrieNode());
        }

        public boolean isLast() {
            return isLast;
        }

        public void setLast(boolean isLast) {
            this.isLast = isLast;
        }

        public boolean hasChild(Character oneChar) {
            return children.containsKey(oneChar);
        }

        public int childSize() {
            return children != null ? children.size() : 0;
        }

        public void removeChild(Character oneChar) {
            children.remove(oneChar);
        }

        public boolean hasChildren() {
            return null != children && children.size() > 0;
        }

        public TrieNode getParent() {
            return parent;
        }

        public void setParent(TrieNode parent) {
            this.parent = parent;
        }

        public Character getThisChar() {
            return thisChar;
        }

        public void setThisChar(Character thisChar) {
            this.thisChar = thisChar;
        }
    }

    protected TrieNode getRoot() {
        return root;
    }

}
