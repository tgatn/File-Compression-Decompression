package edu.ncsu.csc316.compression.manager;

import java.io.FileNotFoundException;

import edu.ncsu.csc316.compression.dsa.DSAFactory;
import edu.ncsu.csc316.compression.dsa.DataStructure;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.Map.Entry;

/**
 * ReportManager holds methods that print out the compress and decompress file in a string format
 * @author TungTran
 *
 */
public class ReportManager {

	/** amount of spaces in an indent */
    private static final String INDENT = "   ";
    
    /** instance of manager used to compress and decompress a file */
    private CompressionManager manager;

    /**
     * Constructor of ReportManager which intializes a CompressionManager
     * @param pathToInputFile is text file to be compressed or decompressed
     * @throws FileNotFoundException if file cannot be found
     */
    public ReportManager(String pathToInputFile) throws FileNotFoundException {
        manager = new CompressionManager(pathToInputFile);
    }

    /**
     * Gives a string representation of the compressed file input
     * @return a string representation of the compressed file input
     */
    public String compress() {
    	Map<Integer, List<String>> compressedMap = DSAFactory.getMap(null);
//    	DSAFactory.setMapType(DataStructure.SKIPLIST);
//		DSAFactory.setListType(DataStructure.ARRAYBASEDLIST);
    	
    	
        compressedMap = manager.getCompressed();
        
        if (compressedMap.size() == 0) {
        	return "The provided input file has no text to compress.";
        }
        
        StringBuilder buf = new StringBuilder();
		buf.append("Compressed Output {\n");
		int count = 1;
		for (Entry<Integer, List<String>> e : compressedMap.entrySet()) {
			buf.append(INDENT + "Line " + count++ + ": ");
			for (int i = 0; i < e.getValue().size(); ++i) {
				buf.append(e.getValue().get(i));
				if (i != e.getValue().size() - 1) {
					buf.append(" ");
				}
			}
			buf.append("\n");
		}
		buf.append("}");
		
		return buf.toString();
    }
    
    /**
     * Gives a string representation of the decompressed file input
     * @return a string representation of the decompressed file input
     */
    public String decompress() {
    	Map<Integer, List<String>> decompressedMap = DSAFactory.getMap(null);
        decompressedMap = manager.getDecompressed();
        if (decompressedMap.size() == 0) {
        	return "The provided input file has no text to compress.";
        }
        StringBuffer buf = new StringBuffer();
        buf.append("Decompressed Output {\n");
        int count = 1;
		for (Entry<Integer, List<String>> e : decompressedMap.entrySet()) {
			buf.append(INDENT + "Line " + count++ + ": ");
			for (int i = 0; i < e.getValue().size(); ++i) {
				buf.append(e.getValue().get(i));
				if (i != e.getValue().size() - 1) {
					buf.append(" ");
				}
			}
			buf.append("\n");
		}
		buf.append("}");
		
		return buf.toString();
    }
}