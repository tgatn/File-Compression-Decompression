package edu.ncsu.csc316.compression.manager;

import java.io.FileNotFoundException;

import edu.ncsu.csc316.compression.dsa.Algorithm;
import edu.ncsu.csc316.compression.dsa.DSAFactory;
import edu.ncsu.csc316.compression.dsa.DataStructure;
import edu.ncsu.csc316.compression.io.InputReader;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.Map.Entry;

/**
 * CompressionManager class holds methods to compress and decompress a given file
 * @author TungTran
 *
 */
public class CompressionManager {

	/** map of keys and their respective values */
	private Map<Integer, List<String>> map;

	/**
	 * Constructor of CompressionManager that sets a certain map, list and sorter type
	 * @param pathToInputFile input file to be compressed or decompressed
	 * @throws FileNotFoundException if file does not exist
	 */
	public CompressionManager(String pathToInputFile) throws FileNotFoundException {
		// Set the data structure and algorithm types first
		DSAFactory.setMapType(DataStructure.SKIPLIST);
		DSAFactory.setListType(DataStructure.ARRAYBASEDLIST);
		DSAFactory.setComparisonSorterType(Algorithm.QUICKSORT);
		DSAFactory.setNonComparisonSorterType(Algorithm.QUICKSORT);

		map = InputReader.readFile(pathToInputFile);
	}

	/**
	 * Compresses a given input file
	 * @return the compressed input file in a map
	 */
	public Map<Integer, List<String>> getCompressed() {
//		DSAFactory.setMapType(DataStructure.SKIPLIST);
//		DSAFactory.setListType(DataStructure.ARRAYBASEDLIST);
		// holds unique words in the inputMap
		
		
		
		Map<String, Integer> dictionary = DSAFactory.getMap(null);

		// index of dictionary
		int dictionaryIdx = 1;

		Map<Integer, List<String>> compressedMap = DSAFactory.getMap(null);

		// holds the integer of duplicate words
		Integer temp;
		
		//Map<Integer, List<String>> sorter = DSAFactory.getMap(null);
		/*
		for (Entry<Integer, List<String>> e : map.entrySet()) {
			sorter.put(e.getKey(), e.getValue());
		}
		*/

		for (Entry<Integer, List<String>> e : map.entrySet()) {
			// holds a list of words in the input map
			List<String> wordList = e.getValue();
			List<String> outputList = DSAFactory.getIndexedList();

			// process each list of words in the map
			int size = wordList.size();
			for (int i = 0; i < size; ++i) {
				temp = dictionary.get(wordList.get(i));
				// word is found in dictionary
				if (temp != null) {
					outputList.addLast(temp.toString());
				} else { // word is unique
					dictionary.put(wordList.get(i), dictionaryIdx++);
					outputList.addLast(wordList.get(i));
				}
			}
			compressedMap.put(e.getKey(), outputList);
		}
		
		return compressedMap;
	}

	/**
	 * Decompresses a given input file
	 * @return the decompressed input file in a map
	 */
	public Map<Integer, List<String>> getDecompressed() {
		// dictionary to hold words and their associated value
		// iterate through the each entry in input map
		// iterate through each word in input map
		// if a word in the input map matches a word in dictionary
		// add the word instead of the number
		// else just add that word
//
//		DSAFactory.setMapType(DataStructure.SKIPLIST);
//		DSAFactory.setListType(DataStructure.ARRAYBASEDLIST);
		// holds unique words in the inputMap
		Map<Integer, String> dictionary = DSAFactory.getMap(null);

		// index of dictionary
		int dictionaryIdx = 1;

		// iterates through the input map
		Iterable<Entry<Integer, List<String>>> E = map.entrySet();

		Map<Integer, List<String>> decompressedMap = DSAFactory.getMap(null);

//		Map<Integer, List<String>> sorter = DSAFactory.getMap(null);
//		for (Entry<Integer, List<String>> e : E) {
//			sorter.put(e.getKey(), e.getValue());
//		}
		
		for (Entry<Integer, List<String>> e : map.entrySet()) {
			// holds a list of words in the input map
			List<String> wordList = e.getValue();
			List<String> outputList = DSAFactory.getIndexedList();

			// process each list of words in the map
			int size = wordList.size();
			for (int i = 0; i < size; ++i) {

				try { // word is a number
					int compressWord = Integer.parseInt(wordList.get(i));
					outputList.addLast(dictionary.get(compressWord));
				} catch (Exception exception) { // word is unique
					dictionary.put(dictionaryIdx++, wordList.get(i));
					outputList.addLast(wordList.get(i));
				}
			}
			decompressedMap.put(e.getKey(), outputList);
		}

		return decompressedMap;
	}

}