package edu.ncsu.csc316.compression.manager;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

/**
 * Test ReportManager class
 * @author TungTran
 *
 */
class ReportManagerTest {

	@Test
	void testCompress() {
		
		
		/** Valid test file */
		String validTestFile = "input/sampleCompress.txt";
		ReportManager manager = null;
		
		try {
			manager = new ReportManager("input/empty.txt");
		} catch (FileNotFoundException e) {
			assertEquals("", e.getMessage());
		}
		
		assertEquals("The provided input file has no text to compress.", manager.compress());
		
		try {
			manager = new ReportManager(validTestFile);
		} catch (FileNotFoundException e) {
			assertEquals("", e.getMessage());
		}
		assertEquals("Compressed Output {\n"
				+ "   Line 1:the dog and 1 cat 3 1 fox 3 1 snake\n"
				+ "   Line 2:3 1 fish 3 1 horse 3 1 2 3 1 4\n"
				+ "   Line 3:jumped over 1 lazy brown 2 for 1 red 5 cannot\n"
				+ "   Line 4:see that 1 4 was asleep 3 11 13 1 6\n"
				+ "   Line 5:I am not 1 5\n"
				+ "}", manager.compress());
		
	}
	
	@Test
	void testDecompress() {
		/** Valid test file */
		String validTestFile = "input/sampleDecompress.txt";
		ReportManager manager = null;
		
		try {
			manager = new ReportManager("input/empty.txt");
		} catch (FileNotFoundException e) {
			assertEquals("", e.getMessage());
		}
		
		assertEquals("The provided input file has no text to decompress.", manager.decompress());
		
		try {
			manager = new ReportManager(validTestFile);
		} catch (FileNotFoundException e) {
			assertEquals("", e.getMessage());
		}
		
		assertEquals("Decompressed Output {\n"
				+ "   Line 1:the dog and the cat and the fox and the snake\n"
				+ "   Line 2:and the fish and the horse and the dog and the cat\n"
				+ "   Line 3:jumped over the lazy brown dog for the red fox cannot\n"
				+ "   Line 4:see that the cat was asleep and lazy for the snake\n"
				+ "   Line 5:I am not the fox\n"
				+ "}", manager.decompress());
	}

}
