# Data Structure Project 2017
## Dictionary Application
A Dictionary is a book or electronic resource that lists the words of a language (typically in alphabetical order) and gives their meaning, or gives the equivalent words in a different language, often also providing information about pronunciation, origin, and usage. You have been asked to develop a Dictionary application that will allow users to manage English words, their meaning and validate simple sentences. Students will be provided with an initial dataset of words in a basic text file and will be expected to read the same into two (2) different appropriate and efficient data structures. The two data structures will be used throughout the application to benchmark and compare the efficiency of the operations performed on each. For each word, the user should store the following;

* The word,

* Its definition, and

* The part of speech (nouns, pronouns, verbs, adjectives, adverbs, conjunctions, prepositions, and interjections)

The dictionary should allow users to conduct the following operations:

1. Parse and load given dataset

  ..* A text or html file will be provided for students to parse and load data into the two selected data structures
  
  ..* Calculate and output how long it takes to load the data into both data structures
  
2. Sort the dataset

  ..* Use an efficient algorithm to arrange the words in ascending order
  
  ..* Calculate and output how long it takes to sort both algorithms
  
3. Search for a word
  *. Allow the user to enter a word and search for same in the both structures. If found, output the definition and part of speech and       the iteration/index where it was found. If not found output an appropriate message.
  
  *. Calculate and output how long it takes to find the word which both data structures.
  
iv. Add a new word to the list

  a. This option prompts the user for the word to be added, its definition and part of speech and then adds it in its correct position        in both data structures.
  
  b. The program should ensure that there are no duplicate words in the dictionary
  
  c. Calculate and output how long it takes to add the word with each data structure
  
v. Print all words in dataset

  a. The program should output all words, their definition and part of speech for each data structure.
  
  b. Calculate and output how long it takes to output all words with each data structures
  
vi. Validate a sentence

  a. Prompts the user to enter a sentence
  
  b. Parse the sentence to ensure all words exist in the dictionary
  
  c. If a word does not exist, ask the user if they would like to add it to the database of words
  
  d. Calculate and output how long it takes to validate the sentence using both data structures
