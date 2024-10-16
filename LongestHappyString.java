/**
 * Problem: 1405. Longest Happy String
 * 
 * A string s is called happy if it satisfies the following conditions:
 * 
 * - s only contains the letters 'a', 'b', and 'c'.
 * - s does not contain any of "aaa", "bbb", or "ccc" as a substring.
 * - s contains at most a occurrences of the letter 'a'.
 * - s contains at most b occurrences of the letter 'b'.
 * - s contains at most c occurrences of the letter 'c'.
 * 
 * Given three integers a, b, and c, return the longest possible happy string.
 * If there are multiple longest happy strings, return any of them.
 * If there is no such string, return the empty string "".
 * 
 * A substring is a contiguous sequence of characters within a string.
 * 
 * Example 1:
 * Input: a = 1, b = 1, c = 7
 * Output: "ccaccbcc"
 * Explanation: "ccbccacc" would also be a correct answer.
 * 
 * Example 2:
 * Input: a = 7, b = 1, c = 0
 * Output: "aabaa"
 * Explanation: It is the only correct answer in this case.
 * 
 * Constraints:
 * - 0 <= a, b, c <= 100
 * - a + b + c > 0
 */

 import java.util.PriorityQueue;

 public class LongestHappyString {
     public String longestDiverseString(int a, int b, int c) {
         // Max heap to always get the character with the highest remaining count
         PriorityQueue<int[]> maxHeap = new PriorityQueue<>((x, y) -> y[0] - x[0]);
 
         // Add characters 'a', 'b', 'c' to the heap with their counts
         if (a > 0) maxHeap.offer(new int[]{a, 'a'});
         if (b > 0) maxHeap.offer(new int[]{b, 'b'});
         if (c > 0) maxHeap.offer(new int[]{c, 'c'});
 
         StringBuilder result = new StringBuilder();
 
         // While there are characters left to add
         while (!maxHeap.isEmpty()) {
             int[] first = maxHeap.poll();  // Get the character with the highest count
             
             // Check if we can add this character (i.e., avoid three consecutive same letters)
             if (result.length() >= 2 && result.charAt(result.length() - 1) == first[1] && result.charAt(result.length() - 2) == first[1]) {
                 if (maxHeap.isEmpty()) {
                     break;  // If there's no other character to add, stop
                 }
                 
                 int[] second = maxHeap.poll();  // Get the next highest count character
                 result.append((char) second[1]);  // Add it to the result
                 second[0]--;  // Decrease its count
                 
                 if (second[0] > 0) {
                     maxHeap.offer(second);  // Reinsert if there's still more of this character
                 }
                 
                 maxHeap.offer(first);  // Put the first character back into the heap for the next round
             } else {
                 result.append((char) first[1]);  // Add the highest count character to the result
                 first[0]--;  // Decrease its count
                 
                 if (first[0] > 0) {
                     maxHeap.offer(first);  // Reinsert if there's still more of this character
                 }
             }
         }
 
         return result.toString();  // Return the final result
     }
 
     // Main method to test the solution
     public static void main(String[] args) {
         LongestHappyString solution = new LongestHappyString();
         System.out.println(solution.longestDiverseString(1, 1, 7));  // Output: "ccaccbcc"
         System.out.println(solution.longestDiverseString(7, 1, 0));  // Output: "aabaa"
     }
 }
 

 //============================================================================================================
 /*
  
class Solution {
    public String longestDiverseString(int a, int b, int c) {
    int currA =0, currB = 0, currC = 0;
    int maxLen = a+b+c, i=0;
    StringBuilder sb = new StringBuilder(); 
    while(i<(maxLen))
    {
      if(currA!=2 && a>=b && a>=c ||  a>0 && (currB==2 || currC==2))
      {
      sb.append('a');
      currA++; 
      currB=0;
      currC=0;
      a--;
      }

      else if(currB!=2 && b>=a && b>=c ||  b>0 && (currA==2 || currC==2))
      {
      sb.append('b');
      currB++; 
      currA=0;
      currC=0;
      b--;
      }

     else if(currC!=2 && c>=a && c>=b ||  c>0 && (currA==2 || currB==2))
      {
      sb.append('c');
      currC++; 
      currA=0;
      currB=0;
      c--;
      }
     i++;
    }
      return sb.toString();
    }
}

  */