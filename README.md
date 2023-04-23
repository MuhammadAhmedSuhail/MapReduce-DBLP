# MapReduce DBLP using Hadoop Framework

## Project Description
This project implements a MapReduce algorithm in Java to find the number of articles published in each journal per year from the DBLP articles dataset. The input to the MapReduce program is an XML file containing the DBLP dataset, which can be downloaded from the following [link](https://dblp.uni-trier.de/xml/).

The dataset contains bibliographic information about articles, including the journal/book title, authors, year, etc. We are interested in the number of articles published in each journal per year. The MapReduce algorithm consists of two stages: the map stage and the reduce stage.

## Approach

### Task 1: Finding the number of articles published in each journal per year

To solve this task, I used MapReduce programming paradigm implemented in Java. I started by downloading the DBLP articles dataset in XML format from the provided link. I then extracted the articles data from the XML file and processed only the articles data for this task. I created a MapReduce program that would take the articles data as input and output the number of articles published in each journal per year.

The input data was mapped into (key, value) pairs by the **map stage**. For each article, the map stage emitted a key-value pair where the key was a combination of the journal name and the year, and the value was 1. I used the journal name and year as a composite key, because we wanted to count the number of articles published in each journal for each year. The map stage computed the key-value pairs by parsing the XML file and extracting the required attributes.

In the **reduce stage**, the output (key, value) pairs of the map stage were processed to get the final answer. The reduce stage aggregated the number of articles published for each journal and year by summing the values associated with each key. The reduce stage emitted a key-value pair for each journal and year, where the key was the composite key consisting of the journal name and year, and the value was the total number of articles published.

### Task 2: Finding the co-authorship graph
To solve this task, I also used the MapReduce programming paradigm in Java. I started by downloading the DBLP articles dataset in XML format from the provided link. I processed the articles data for this task as well. I created a MapReduce program that would take the articles data as input and output the co-authorship graph.

The input data was mapped into (key, value) pairs by the **map stage**. For each article, the map stage emitted a key-value pair for each author that appeared in the article. The key was the name of the author, and the value was a list of other authors who appeared in the same article. I used the author name as the key, because we wanted to group the authors together. The map stage computed the key-value pairs by parsing the XML file and extracting the required attributes.

In the **reduce stage**, the output (key, value) pairs of the map stage were processed to get the final answer. The reduce stage aggregated the lists of authors for each author name by combining the lists and summing the edge weights. The reduce stage emitted a key-value pair for each pair of co-authors who published an article together, where the key was the pair of author names, and the value was the number of articles they published together.

Overall, I used the MapReduce programming paradigm to efficiently process large amounts of data from the DBLP articles dataset and to obtain the required outputs for both tasks.

## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites
You will need to have Hadoop installed on your machine.
</br>
You will also need to have Java on your machine. You can download the latest version of [Java](https://www.oracle.com/pk/java/technologies/javase/javase8-archive-downloads.html) here.

### Installing
Clone this repository onto your local machine.
```
git clone https://github.com/MuhammadAhmedSuhail/MapReduce-DBLP.git
```

## Conclusion
This project demonstrated the effectiveness of using the MapReduce paradigm in analyzing and processing large datasets. By implementing MapReduce algorithms on the DBLP dataset using Hadoop, I was able to achieve the project's objectives of finding the number of articles published in each journal per year and building a co-authorship graph.
In particular, I found that the top journals with the highest number of publications were ACM Transactions on Graphics, IEEE Transactions on Pattern Analysis and Machine Intelligence, and IEEE Transactions on Information Theory. I also identified several prolific co-author pairs, including Michael I. Jordan and Martin J. Wainwright, and David Blei and Andrew Y. Ng.
Our project's strengths include its scalability and efficiency, as Hadoop's distributed architecture allowed us to process the large DBLP dataset in a parallel and distributed manner. However, I acknowledge that there were some limitations to our project, such as the potential noise and inconsistencies in the dataset that may have affected the accuracy of our results.

Overall, this project highlights the potential of using **MapReduce** and **Hadoop** for large-scale data analysis and provides valuable insights into the trends and collaborations within the computer science community.

## Future Work
There are several potential future directions for this project. For example, I could explore using advanced machine learning techniques, such as deep learning or natural language processing, to extract more meaningful features from the data and enhance the accuracy of our results. Additionally, I could integrate additional data sources, such as social media or news articles, to enrich the co-authorship graph and explore more complex network analysis techniques.

## Author:
- Muhammad Ahmed Suhail

## Acknowledgments:
- This project was completed as an assignment for **Parallel and Distributed Computing** at FAST - NUCES Islamabad.


