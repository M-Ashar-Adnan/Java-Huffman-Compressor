package models;

public class Node implements Comparable<Node> {
    public char character;
    public int frequency;
    public Node left, right;

    // Constructor for leaf nodes (actual characters)
    public Node(char character, int frequency) {
        this.character = character;
        this.frequency = frequency;
    }

    // Constructor for internal nodes (combined frequencies)
    public Node(int frequency, Node left, Node right) {
        this.character = '\0';
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.frequency, other.frequency);
    }
}