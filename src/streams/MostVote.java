package streams;

import java.util.* ;
import java.util.function.Function;
import java.util.stream.* ;
import java.io.*;
import java.util.ArrayList;

/*You have been given an array/list of “VOTES” which contains the name of the candidates where each entry represents the name of the candidate who got the vote.

You are supposed to find the name of the candidate who received the maximum number of votes. If there is a tie, then print the lexicographically smaller name.

Detailed explanation ( Input/output format, Notes, Images )
Constraints :
        1 <= T <= 50
        1 <= ‘N’ <= 10^3
        1 <= |NAME| <= 20

Where ‘N’ is the number of votes cast and |NAME| denotes the length of the candidate’s name.

Time Limit: 1 sec
Sample Input 1 :
        2
        4
John
        Tim
Marry
        John
2
Rahul
        Ankur
Sample output 1 :
John
        Ankur
Explanation For Sample intput 1 :
For the first test case, “John” has received the maximum number of votes (2 votes).

For the second test case, both “Rahul” and “Ankur” has received one vote each since “Ankur” is lexicographically smaller than “Rahul”, print “Ankur”.
Sample Input 2 :
        2
        1
Arya
2
Atul
        Atul
Sample output 2 :
Arya
        Atul
Explanation For Sample intput 2 :
For the first test case, “Arya” is the only candidate in the election who has received the maximum number of votes.

For the second test case, “Atul” has received all the votes*/
public class MostVote {
    
    public static String getWinner(ArrayList< String > votes) {
        // Write your code here.
        return votes.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Comparator.<Map.Entry<String, Long>>comparingLong(Map.Entry::getValue).reversed().thenComparing(Map.Entry::getKey))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }
}
