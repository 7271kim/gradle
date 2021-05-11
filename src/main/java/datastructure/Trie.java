package datastructure;

import java.util.HashMap;
import java.util.Map;

public class Trie {

	private TrieNode root;

	public Trie() {
		root = new TrieNode();
	}

	public void insert(String text) {
		TrieNode thisNode = root;

		for (int index = 0; index < text.length(); index++) {
			char oneWord = text.charAt(index);
			thisNode.setChild(oneWord);
			thisNode = thisNode.getChild(oneWord);
		}

		thisNode.setLast(true);
	}

	public boolean contains(String text) {
		boolean result = true;
		TrieNode thisNode = root;
		for (int index = 0; index < text.length(); index++) {
			char oneWord = text.charAt(index);
			if (thisNode.hasChild(oneWord)) {
				thisNode = thisNode.getChild(oneWord);
			} else {
				result = false;
				break;
			}
		}

		return result && thisNode.isLast();
	}

	public void remove(String text) {}

	public static class TrieNode {
		boolean isLast;
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

	}

	protected TrieNode getRoot() {
		return root;
	}

}
