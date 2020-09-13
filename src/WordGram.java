
/**
 * A WordGram represents a sequence of strings
 * just as a String represents a sequence of characters
 * 
 * @author Cate Schick
 *
 */
public class WordGram {
	
	private String[] myWords;   
	private String myToString;  // cached string
	private int myHash;         // cached hash value

	/**
	 * Create WordGram by creating instance variable myWords and copying
	 * size strings from source starting at index start
	 * @param source is array of strings from which copying occurs
	 * @param start starting index in source for strings to be copied
	 * @param size the number of strings copied
	 */
	public WordGram(String[] source, int start, int size) {
		myWords = new String[size];
		myToString = myWords.toString();
		myHash = myToString.hashCode();

		// TODO: initialize all instance variables
		// copy array
		System.arraycopy(source, start, myWords, 0, size);
	}

	/**
	 * Return string at specific index in this WordGram
	 * @param index in range [0..length() ) for string 
	 * @return string at index
	 */
	public String wordAt(int index) {
		if (index < 0 || index >= myWords.length) {
			throw new IndexOutOfBoundsException("bad index in wordAt "+index);
		}
		return myWords[index];
	}

	/**
	 * Returns the length of myWords
	 * @return
	 */
	public int length(){
		// TODO: change this
		return myWords.length;
	}

	/**
	 * Return true when parameter passed is a WordGram object with the same
	 * strings in the same order as this object
	 * @param o
	 * @return
	 */
	@Override
	public boolean equals(Object o) {
		if (! (o instanceof WordGram) || o == null){
			return false;
		}
		// TODO: Complete this method
		// if o is a wordgram, which it has to be to get here
		// in the code, it can be written as:
		WordGram other = (WordGram) o;

		// the arrays need to have the same length
		if (this.length() != other.length()) {
			return false;
		}

		// the arrays need to have the same words
		for (int i = 0; i < this.length(); i++) {
			if (!this.wordAt(i).equals(other.wordAt(i))) {
				return false;
			}
			else {
				return true;
			}
		}

		return true;
	}

	/**
	 * returns an integer value based on all the strings
	 * @return
	 */
	@Override
	public int hashCode(){
		// TODO: complete this method: assign to myHash as needed
		return myHash;
	}
	

	/**
	 * Creates and returns a new WordGram object with k entries
	 * whose first k-1 entries are the same as the last k-1 entries
	 * @param last is last String of returned WordGram
	 * @return
	 */
	public WordGram shiftAdd(String last) {
		WordGram wg = new WordGram(myWords,0,myWords.length);
		// TODO: Complete this method
		String[] words = new String[this.length()];
		myWords = words;

		for (int i = 0; i < myWords.length-1; i++) {
			words[i] = this.wordAt(i+1);
		}

		words[words.length-1] = last;

		WordGram ret = new WordGram(words, 0, words.length);

		return ret;
	}

	@Override
	public String toString(){
		// TODO: Complete this method, assign to myToString as needed
		for (int i = 0; i < myWords.length; i++) {
			myToString = myToString + (myWords[i]);
			myToString = myToString + (" ");
		}
		return myToString;
	}
}
