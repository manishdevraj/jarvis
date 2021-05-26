# Jarvis
 Competitive coding questions
> This guide help you practice and understand the coding programming questions in a competitive interviews and exams

## <a name='toc'>Problem patterns</a>
- [ ] [Sliding window](#slidingwindow)
- [ ] [Two pointers](#twopointer)
- [ ] [Fast and slow pointer](#fastandslow)
- [ ] [Intervals](#intervals)
- [ ] [Cycling sort](#cyclingsort)
- [ ] [Reverse in place problem](#reverseinplace)
- [ ] [Breath first search](#bfs)
- [ ] [Depth first search](#dfs)
- [ ] [Heap](#heap)
- [ ] [Subset](#subset)
- [ ] [Binary search](#binarysearch)
- [ ] [Bit XOR](#bitxor)
- [ ] [Top K element](#topk)
- [ ] [K-way merge](#kwaymerge)
- [ ] [Bounded Knapsack (0/1)](#boundedknapsack)
- [ ] [Fibonacci](#fibonacci)
- [ ] [Longest Palindrome Subsequence or Substring](#longestpalindrome)
- [ ] [Longest Common Subsequence or Substring](#longestcommon)
- [ ] [Unbounded Knapsack](#unboundedknapsack)
- [ ] [Topology sort](#topologysort)
- [ ] [Tries](#tries)
- [ ] [Greedy algorithms](#greedy)

### [[⬆]](#toc) <a name='slidingwindow'>Sliding window problem</a>

It typically deals with trying to find contiguous subarray, sub list with some defined window of size 
K or at times trying to find the window.
E.g. could be - 
* Find a maximum subarray sum with a size K
* Replace 0's with 1's in an array where K replacements are allowed, find maximum consecutive 1's 
* Select maximum fruits of K type from an array and find maximum fruits that can be collected

### [[⬆]](#toc) <a name='twopointer'>Two pointer problem</a>

Problem deals in finding a sum or arranging array in certain fashion when array is unsorted.
E.g. could be -
* Dutch national flag problem (sort the colors in an array without using extra space)
* Find two number sum, triplet sum, quadruplet sum with target sum or min/max value
* Perform sub array sorted needed to sort entire array 
* Calculate sorted squares of integers in a sorted array
* Find maximum water that can be trapped between pillar in an array


### [[⬆]](#toc) <a name='fastandslow'>Fast and slow pointer problem</a>

In these problem statements there usually a single linked list and we are required to find a cycle or cycle length 
or middle of a linked list.

* Happy number
* Find a cycle in a linked list
* Find if linked list is a palindrome or not


### [[⬆]](#toc) <a name='intervals'>Interval problem</a>

Interval problems where we have an upper and lower bound of an interval and we need to find an overlapping or insert or 
a non-overlapping intervals in a 2D array or pair in a single array

It predominately uses min/max heaps to control the slots or at times needs to merge and flatten the intervals.

* Find possible meeting time between two calendars
* Minimum meeting rooms needed for a set of conferences
* Find a free time between employees


### [[⬆]](#toc) <a name='cyclingsort'>Cycling sort problem</a>

Unsorted array in range 1…. n, find missing element

### [[⬆]](#toc) <a name='reverseinplace'>Reverse in place problem</a>

Set of problem statements where we are required to either merge or re-arrange a linked in certain way, 
find duplicate or remove Nth value from end Or reverse linked list from middle or from two ranged index values.

It heavily uses fast slow pointer technique but additional complexity arround reversing linked list and 
maintaining links between nodes.

* Add two numbers like arithmetic equation 
* Merge two linked list
* Swap node pairs or from certain indices
* Reverse linked list from certain indices
* Remove duplicates
* Remove node from X location in linked list

### [[⬆]](#toc) <a name='bfs'>Breath first search problem</a>

Level by level order with O(w) complexity. (Use queues with queue size as a level order iteration)

### [[⬆]](#toc) <a name='dfs'>Depth first search problem</a>

Track while traversing and use recursion with O(h) complexity. (Use stack) Path, path sum, path sequence.

### [[⬆]](#toc) <a name='heap'>Heap problem</a>

Find smallest or largest in some part of collection (Works with sliding window and intervals)

### [[⬆]](#toc) <a name='subset'>Subset problem</a>

Find permutation or combination. (Use BFS)

### [[⬆]](#toc) <a name='binarysearch'>Binary search problem</a>

Array, list, matrix. search something. Bitonic, shifted search, rotate search, find ceiling or floor.

### [[⬆]](#toc) <a name='bitxor'>Bit XOR problem</a>

Bitwise XOR based bit manipulations

### [[⬆]](#toc) <a name='topk'>Top K element problem</a>

Find top, small, frequent element (Use Heap)

### [[⬆]](#toc) <a name='kwaymerge'>K-way merge problem</a>

K-way merge, min and max, sorted list, array and matrix (Use heap)

### [[⬆]](#toc) <a name='boundedknapsack'>Bounded Knapsack (0/1)</a>

Refer to DP Knapsack problem using weight, capacity and items problems

### [[⬆]](#toc) <a name='fibonacci'>Fibonacci problem</a>

Fibonacci problems, also refer DP version of it

### [[⬆]](#toc) <a name='longestpalindrome'>Longest Palindrome Subsequence or Substring problem</a>

```
if str (start) == str (end)
    //If last and first characters of str are same
    dp[start][end] = 2 + dp[start+1][end-1] 
else
    // refer to matrix to understand better
    dp[start][end] = max(dp[start+1][end], dp[start][end-1]) 
```

### [[⬆]](#toc) <a name='longestcommon'>Longest Common Subsequence or Substring problem</a>

```
if str (i) == str (i)
    //If last and first characters of str are same use cross diagonal value
    dp[i][j] = 1 + dp[i-1][j-1]
else
    // refer to matrix to understand better
    dp[i][j] = max(dp[i-1][j], dp[i][j-1])
```

### [[⬆]](#toc) <a name='unboundedknapsack'>Unbounded Knapsack problem</a>

Capacity, item and weight with unlimited quantity. Refer to DP Knapsack

### [[⬆]](#toc) <a name='topologysort'>Topology sort problem</a>

Jobs and dependencies of the job. Linear ordering with dependencies. Order things when they are interdependent kind of problems.

### [[⬆]](#toc) <a name='greedy'>Greedy algorithms</a>

Algorithms with greedy approach and using data structure suitabe to get to the solution
* Find a possible events that can be attended in a conference
* Partition a pattern of string such that each partition has at most single character