# text_analysis_tool
CSC212 Data Structures

In this Java text analysis tool, the primary objective is to design and implement a robust text analysis tool centered around an Abstract Data Type (ADT) called ADTWordAnalysis. This ADT plays a pivotal role in efficiently storing words from a given text file in main memory, facilitating various word analytics operations.


The specified functionalities include:

1- Determining the total number of words in a text file

2- Counting the total number of unique words

3- Finding the occurrences of a specific word

4- Counting words with a given length

5- Displaying unique words along with their occurrences sorted by frequency from the most to the least

6- Presenting the locations of word occurrences in terms of line and word positions

7- Checking if two words appear adjacent to each other in the file, requiring at least one occurrence of both words for validation.


To achieve these tasks, the ADTWordAnalysis was designed with methods that efficiently handled word storage, counting, sorting, and searching operations. The implementation involved reading the text file, parsing it, and using appropriate data structures to store word occurrences. Data structures like ArrayLists and LinkedLists were utilized to store occurrence positions.


The graphical representation of the ADTWordAnalysis to illustrate its structure:



<img width="527" alt="Screenshot 2024-03-22 024802" src="https://github.com/Futoon2002/text_analysis_tool/assets/101240944/85c4c335-3f2f-434f-bff1-2d6b7d1f3122">

Description for the structure:

1- ArrayofDifferentLengths will be used to store words such that each index i of the array will contain a list of words that have length of i characters.

2- The list of Nodes with WordInformation type will contain information about each word such as the word itself, a list of occurrences of that word (LinkedList<WordOccurrence>occList), and the size of the occurrences list.

3- occList will contain the different occurrences of the same word in terms of which line and position it occurs in.
