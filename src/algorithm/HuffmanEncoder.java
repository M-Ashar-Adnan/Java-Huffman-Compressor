package algorithm;

import models.Node;
import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.Map;

public class HuffmanEncoder {
    public Map<Character, String> huffmanCodes = new HashMap<>();

    public Map<Character, Integer> calculateFrequencies(String text) {
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : text.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }
        return freqMap;
    }

    public Node buildTree(Map<Character, Integer> frequencies) {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        for (Map.Entry<Character, Integer> entry : frequencies.entrySet()) {
            pq.add(new Node(entry.getKey(), entry.getValue()));
        }

        while (pq.size() > 1) {
            Node left = pq.poll();
            Node right = pq.poll();
            Node parent = new Node(left.frequency + right.frequency, left, right);
            pq.add(parent);
        }
        return pq.poll(); // Returns the root of the tree
    }

    public void generateCodes(Node root, String code) {
        if (root == null) return;

        // If it's a leaf node, it contains a character
        if (root.left == null && root.right == null) {
            huffmanCodes.put(root.character, code);
        }

        generateCodes(root.left, code + "0");
        generateCodes(root.right, code + "1");
    }

    public String compress(String text) {
        StringBuilder compressed = new StringBuilder();
        for (char c : text.toCharArray()) {
            compressed.append(huffmanCodes.get(c));
        }
        return compressed.toString();
    }

    public String decompress(String compressedBinary, Node root) {
        StringBuilder decoded = new StringBuilder();
        Node current = root;
        for (int i = 0; i < compressedBinary.length(); i++) {
            current = compressedBinary.charAt(i) == '0' ? current.left : current.right;
            if (current.left == null && current.right == null) {
                decoded.append(current.character);
                current = root;
            }
        }
        return decoded.toString();
    }
}