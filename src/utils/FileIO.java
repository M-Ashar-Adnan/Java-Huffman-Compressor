package utils;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileIO {

    // Reads a standard text file into a String
    public static String readTextFile(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }

    // Packs the "101011" string into raw bytes and writes to a .bin file
    public static void writeCompressedFile(String compressedText, String outputPath) throws IOException {
        int numOfBytes = (compressedText.length() + 7) / 8;
        byte[] bytes = new byte[numOfBytes];

        for (int i = 0; i < compressedText.length(); i++) {
            if (compressedText.charAt(i) == '1') {
                // Bitwise magic: shifts the 1 into the correct bit position within the byte
                bytes[i / 8] |= (1 << (7 - (i % 8)));
            }
        }

        try (FileOutputStream fos = new FileOutputStream(outputPath)) {
            fos.write(bytes);
        }
    }

    // Helper to check actual file size on the disk
    public static long getFileSize(String filePath) {
        File file = new File(filePath);
        return file.length();
    }

    public static String readCompressedFile(String filePath, int validBitLength) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(filePath));
        StringBuilder binaryString = new StringBuilder();
        for (byte b : bytes) {
            for (int i = 7; i >= 0; i--) {
                binaryString.append((b >> i) & 1);
            }
        }
        return binaryString.substring(0, validBitLength);
    }
}