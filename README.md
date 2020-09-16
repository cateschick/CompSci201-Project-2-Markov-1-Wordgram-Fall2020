
P2: Markov Part I, Fall 2020

Background (Not necessary to complete assignment)
Markov processes are widely used in Computer Science and in analyzing different forms of data. Part II of this assignment offers an occasionally amusing look at text (it's more fun than counting words) by using a Markov process to generate random text based on a training text. When run in reverse (we won't do that in this assignment), it's possible to identify the source of an unknown text based on frequency of letters and words. This process can be used to identify SPAM or to ascertain if Bacon wrote Romeo and Juliet.

The most recent text-generation via statistical machine learning program is the OpenAI GPT-3, you can read more using Google, but that's a start.  If you're on Facebook, you can use the what-would-i-say FB (or Android) app, described here http://what-would-i-say.com/about.html as "Technically speaking, it trains a Markov Bot based on mixture model of bigram and unigram probabilities derived from your past post history."

You can also read about the so-called "Infinite Monkey Theorem" via its Wikipedia entry. This assignment has its roots in several places: a story named Inflexible Logic now found in pages 91-98 from Fantasia Mathematica (Google Books) and reprinted from a 1940 New Yorker story called by Russell Maloney. 
The true mathematical roots are from a 1948 monolog by Claude Shannon, A Mathematical Theory of Communication which discusses in detail the mathematics and intuition behind this assignment. This assignment has its roots in a Nifty Assignment designed by Joe Zachary from U. Utah, assignments from Princeton designed by Kevin Wayne and others, and the work done at Duke starting with Owen Astrachan and continuing with Jeff Forbes, Salman Azhar, and the UTAs from Compsci 201.
TL;DR
Fork and clone the repo. Run SimpleWordGramDriver to see that it runs, the output will be wrong, but you'll see that it runs as a Java program. Add your name as a comment at the top of the WordGram.java file, then push your changes to Git, so that you can have confidence that Git works. 

Implement the constructor and .toString method for WordGram. Run the driver program. Implement .hashCode, implement .equals, implement .shiftAdd in WordGram. Test using SimpleWordGramDriver AND using the JUnit tests in WordGramTest. If all tests pass, and the SimpleWordGramDriver output matches expected output, then answer the questions in the analysis section by running the WordGramBenchmark program several times as asked for. Submit program on Gradescope via GitLab, and submit analysis in the reflect for this assignment.

Overview of WordGram
Implement a class WordGram that represents a sequence of words or strings, just like a Java String represents a sequence of characters. As described below, implement the constructor and all stub-methods so you pass the provided tests and adhere to the design guidelines described below..

Just as the Java String class is an immutable sequence of characters, the WordGram class you implement will be an immutable sequence of strings. Immutable means that once a WordGram object has been created, it cannot be modified. You cannot change the contents of a WordGram object, however, you can create a new WordGram. Strings are also immutable.

The number of strings contained in a WordGram is sometimes called the order of the WordGram, and we sometimes call the WordGram an order-k WordGram, or a k-gram -- the term used in the Markov program you'll implement for Part II.  Some examples of order-3 WordGram objects include:

"cat"
"sleeping"
"nearby"

and

"chocolate"
"doughnuts"
"explode"

You'll construct a WordGram object by passing an array, a starting index, and the size (or order) of the WordGram. You'll store the strings in an array instance variable by copying them from the array passed to the constructor.
Implementing WordGram
You're given an implementation of WordGram.java with stub (unimplemented) methods and a stub constructor. See the screenshot from IntelliJ to the right that indicates the required methods, constructors, and the three private instance variables you'll create. In the WordGram class you get in the starter code these methods are not correct, as you can see if you run the JUnit tests in WordGramTest. You'll follow these general steps to provide a correct implementation.

Replace the stub/incomplete methods in WordGram with working versions. In particular, you should implement the following methods and constructor:
The constructor WordGram(String[] words, int index, int size)
toString()
hashCode()
equals(Object other)
length()
shiftAdd(String last)

For hashCode, equals, and toString, your implementations should conform to the specifications as given in the documentation for Object.

Test these methods using the JUnit tests in WordGramTest. 
WordGram Constructors and Methods
As you're implementing code for the first three methods: constructor, .toString(), and .hashCode() you can use the program SimpleWordGramBenchmark to test what you've done, in addition to the JUnit tests described later in this document. The output of the program should be different than when run after first cloning.

After implementing the constructor and two methods the first line of the output should be as shown below, after implementing .shiftAdd you'll get the second line of output as well.

gram = Computer Science is fun, length = 4, hash = 52791914
gram = Science is fun sometimes, length = 4, hash = 1248130903
(1) Implement the Constructor
The constructor for WordGram:

    public WordGram(String[] source, int start, int size)

should store size strings from the array source, starting at index start (of source) into a private String array instance variable myWords of the WordGram class. The array myWords should contain exactly size strings. There are three instance variables in WordGram:

     private String[] myWords;
    private String myToString;
    private int myHash;

You must give each of these instance variables a value in the constructor. Instance variable values given to myToString and myHash  will change when you implement the methods .toString() and .hashCode(), respectively.

The constructor of a WordGram takes an array of strings as a parameter and copies size of these, starting at index start, into the instance variable myWords. For example, suppose parameter words is the array below, with "this" at index 0.

"this"
"is"
"a"
"test"
"of"
"the"
"code"

The call new WordGram(words,3,4) should create this array myWords since the starting index is the second parameter, 3, and the size is the third parameter, 4.

"test"
"of"
"the"
"code"
(2) Implement and override method toString()
The toString() method should return a printable String representing all the strings stored in the WordGram. This should be a single String storing each of the values in instance variable myWords separated by a space. You can do this using the static String.join method with a first parameter of a single-space: " " and the second parameter the instance variable myWords. 

Don't recompute this String each time toString is called -- store the String in instance variable myToString. For full credit your code must only call String.join the first time .toString() is called and will then use the value stored in myToString on subsequent calls. This is because once we obtain the String representation of this WordGram object, it cannot change (WordGram is immutable), so there’s no need to recompute it when toString() is called again. You can compare the initial value of myToString to see if you need to assign a value to it, e.g., its length would be zero if initialized to "" in the constructor.
(3) Implement and override method  hashCode()
The hashCode() method should return an int value based on all the strings in instance variable myWords. A simple and efficient way to calculate a hash value is to call this.toString() and to use the hash-value of the resultant String created and returned by this.toString() -- you should use this method in calculating hash values for WordGram objects.

Don't recompute the hash value each time hashCode is called -- it can't change since WordGram objects are immutable. For full credit you'll only call .toString().hashCode() the first time WordGram.hashCode() is called, your code will store this value in myHash, and use the stored value on subsequent calls.
(4) Implement and override method equals()
The equals() method should return true when the parameter passed is a WordGram object with the same strings in the same order as this object. Your code should test the object parameter with the instanceof operator to see if the parameter is a WordGram. You're given code that makes this test and returns false when the parameter is not a WordGram object.

If the parameter other is a WordGram object, it can be cast to a WordGram, e.g., like this:

WordGram wg = (WordGram) other;

Then the strings in the array myWords of wg can be compared to this object's strings in this.myWords. Note that WordGram objects of different lengths cannot be equal.
(5) Implement the method length()

The length() method should return the order or size of the WordGram -- this is the number of words stored in the instance variable myWords.
(6) Implement the method shiftAdd()
The shiftAdd() method should create and return a new WordGram object with k entries (where k is the order of this WordGram) whose first k-1 entries are the same as the last k-1 entries of this WordGram, and whose last entry is the parameter last. 

For example, if WordGram w is {"apple", "pear", "cherry"} then the method call w.shiftAdd("lemon") should return a new WordGram {"pear", "cherry", "lemon"}.

The call w.shiftAdd(string) is meant to be an analog of the call s.substring(1).concat(char) for a String object s.  


We will map total points you earn to scores as follows. Note that 19-22 is some kind of A, so 19 will be an A-, 20 and 21 will be an A, 22 will be an A+. We will record the letter grade as your grade for this assignment.

19-22: A
15-18: B
11-14: C
5-10:   D

