# Java Huffman File Compressor

A simple and efficient Java program that shrinks the size of text files using the Huffman Coding algorithm.

## What It Does
This tool reads a standard text file (`.txt`), compresses it into raw binary data (`.bin`), and can decompress it back to the original text. By giving common characters (like 'e' or 'a') shorter binary codes and rare characters longer codes, it significantly reduces the overall file size, typically saving 40% to 50% of storage space.

## Features
- **Lossless Compression:** Shrinks file size without losing a single character of the original data.
- **Bitwise File I/O:** Packs binary '1's and '0's into actual physical bytes to achieve real-world storage savings on your hard drive.
- **Metrics Dashboard:** Calculates and displays the original file size, compressed file size, and the exact percentage of storage space saved.
- **Decompression Engine:** Perfectly reverses the compression process to restore and verify the original text.

## How to Run It
1. Create a text file named `input.txt` in the root folder of the project.
2. Paste any text (the more, the better!) into `input.txt` and save it.
3. Run the `Main.java` file from your IDE or terminal.
4. The program will generate a compressed `compressed_data.bin` file and print the storage savings directly in your console.

## How It Works Behind the Scenes
1. **Frequency Analysis:** It reads your text and counts exactly how many times each letter and symbol appears.
2. **Tree Building:** It uses a Priority Queue to build a mathematical tree, placing the most frequent characters at the top.
3. **Dictionary Generation:** It traces the tree to generate a unique binary dictionary, assigning the shortest codes to the most common letters.
4. **Encoding:** It translates your entire text into the new binary format and writes it to a `.bin` file using bit manipulation.