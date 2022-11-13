package edu.ncsu.csc316.compression.manager;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;


/**
 * Test CompressionManager Class
 * @author TungTran
 *
 */
class CompressionManagerTest {

	@Test
	void testGetCompressed() {
		/** Valid test file */
		String validTestFile = "input/sampleCompress.txt";
		CompressionManager manager = null;
		try {
			manager = new CompressionManager(validTestFile);
		} catch (FileNotFoundException e) {
			assertEquals("", e.getMessage());
		}

		// Test all 5 lines after compression
		String output = "";

		// line 1
		for (int i = 0; i < manager.getCompressed().get(1).size(); ++i) {
			output += manager.getCompressed().get(1).get(i) + " ";
		}
		
		assertEquals(manager.getCompressed().get(1).get(6), "1");
		assertEquals("the dog and 1 cat 3 1 fox 3 1 snake ", output);

		// line 2
		output = "";
		for (int i = 0; i < manager.getCompressed().get(2).size(); ++i) {
			output += manager.getCompressed().get(2).get(i) + " ";
		}
		assertEquals("3 1 fish 3 1 horse 3 1 2 3 1 4 ", output);

		// line 3
		output = "";
		for (int i = 0; i < manager.getCompressed().get(3).size(); ++i) {
			output += manager.getCompressed().get(3).get(i) + " ";
		}
		assertEquals("jumped over 1 lazy brown 2 for 1 red 5 cannot ", output);

		// line 4
		output = "";
		for (int i = 0; i < manager.getCompressed().get(4).size(); ++i) {
			output += manager.getCompressed().get(4).get(i) + " ";
		}
		assertEquals("see that 1 4 was asleep 3 11 13 1 6 ", output);

		// line 5
		output = "";
		for (int i = 0; i < manager.getCompressed().get(5).size(); ++i) {
			output += manager.getCompressed().get(5).get(i) + " ";
		}
		assertEquals("I am not 1 5 ", output);

		assertThrows(FileNotFoundException.class, () -> new CompressionManager("invalid"));
	}

	@Test
	void testGetDecompressed() {
		/** Valid test file */
		String validTestFile = "input/sampleDecompress.txt";
		CompressionManager manager = null;
		try {
			manager = new CompressionManager(validTestFile);
		} catch (FileNotFoundException e) {
			assertEquals("", e.getMessage());
		}

		// Test all 5 lines after compression
		String output = "";

		// line 1
		for (int i = 0; i < manager.getDecompressed().get(1).size(); ++i) {
			output += manager.getDecompressed().get(1).get(i) + " ";
		}
		assertEquals("the dog and the cat and the fox and the snake ", output);

		// line 2
		output = "";
		for (int i = 0; i < manager.getDecompressed().get(2).size(); ++i) {
			output += manager.getDecompressed().get(2).get(i) + " ";
		}
		assertEquals("and the fish and the horse and the dog and the cat ", output);

		// line 3
		output = "";
		for (int i = 0; i < manager.getDecompressed().get(3).size(); ++i) {
			output += manager.getDecompressed().get(3).get(i) + " ";
		}
		assertEquals("jumped over the lazy brown dog for the red fox cannot ", output);

		// line 4
		output = "";
		for (int i = 0; i < manager.getDecompressed().get(4).size(); ++i) {
			output += manager.getDecompressed().get(4).get(i) + " ";
		}
		assertEquals("see that the cat was asleep and lazy for the snake ", output);

		// line 5
		output = "";
		for (int i = 0; i < manager.getDecompressed().get(5).size(); ++i) {
			output += manager.getDecompressed().get(5).get(i) + " ";
		}
		assertEquals("I am not the fox ", output);

		assertThrows(FileNotFoundException.class, () -> new CompressionManager("invalid"));
	}

}
