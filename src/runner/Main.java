package runner;

import algorithm.HuffmanEncoder;
import models.Node;
import utils.FileIO;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String inputFilePath = "input.txt";
        String outputFilePath = "compressed_data.bin";
        HuffmanEncoder encoder = new HuffmanEncoder();

        try {
            System.out.println("Reading file: " + inputFilePath + "...");
            String textToCompress = FileIO.readTextFile(inputFilePath);

            //Calculate Frequencies & Build Tree
            Map<Character, Integer> frequencies = encoder.calculateFrequencies(textToCompress);
            Node root = encoder.buildTree(frequencies);

            encoder.generateCodes(root, "");

            //Compress the String
            System.out.println("Compressing data using Huffman Tree...");
            String compressedString = encoder.compress(textToCompress);

            // Write to Physical Binary File
            FileIO.writeCompressedFile(compressedString, outputFilePath);

            // Display Storage Savings
            long originalSize = FileIO.getFileSize(inputFilePath);
            long compressedSize = FileIO.getFileSize(outputFilePath);
            double spaceSaved = 100.0 - (((double) compressedSize / originalSize) * 100);

            System.out.println("\n--- COMPRESSION RESULTS ---");
            System.out.println("Original File Size:   " + originalSize + " bytes");
            System.out.println("Compressed File Size: " + compressedSize + " bytes");
            System.out.printf("Storage Space Saved:  %.2f%%\n", spaceSaved);
            System.out.println("Output saved to:      " + outputFilePath);

            System.out.println("\nReading compressed binary from disk...");
            String readBinary = FileIO.readCompressedFile(outputFilePath, compressedString.length());
            String decompressedText = encoder.decompress(readBinary, root);
            System.out.println("Decompression successful: " + textToCompress.equals(decompressedText));

        } catch (Exception e) {
            System.err.println("Error processing files: " + e.getMessage());
        }
    }
}