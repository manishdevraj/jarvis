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
- [ ] [Recursion and Backtracking](#recursion) 
- [ ] [Binary search tree](#bst)
- [ ] [Binary search](#binarysearch)
- [ ] [Bit XOR](#bitxor)
- [ ] [Top K element](#topk)
- [ ] [K-way merge](#kwaymerge)
- [ ] [Knapsack](#knapsack)
- [ ] [Fibonacci](#fibonacci)
- [ ] [Longest Palindrome Subsequence or Substring](#longestpalindrome)
- [ ] [Longest Common Subsequence or Substring](#longestcommon)
- [ ] [Topology sort](#topologysort)
- [ ] [Tries](#tries)
- [ ] [Greedy algorithms](#greedy)

### [[⬆]](#toc) <a name='slidingwindow'>Sliding window problem</a>

[source](src/main/java/org/javainaction/slidingwindow)

It typically deals with trying to find contiguous subarray, sub list with some defined window of size 
K or at times trying to find the window.
E.g. could be - 
* Find a maximum subarray sum with a size K
* Replace 0's with 1's in an array where K replacements are allowed, find maximum consecutive 1's 
* Select maximum fruits of K type from an array and find maximum fruits that can be collected
* Non constructible change of coins
* Buy and sell stocks
* Sliding window maximum
* Sliding window median
* Longest substring with k distict characters

### [[⬆]](#toc) <a name='twopointer'>Two pointer problem</a>

[source](src/main/java/org/javainaction/twopointers)

Problem deals in finding a sum or arranging array in certain fashion when array is unsorted.
E.g. could be -
* Dutch national flag problem (sort the colors in an array without using extra space)
* Find two number sum, triplet sum, quadruplet sum with target sum or min/max value
* Perform sub array sorted needed to sort entire array 
* Calculate sorted squares of integers in a sorted array
* Find maximum water that can be trapped between pillar in an array
* Find peak in an array
* Find how many boards needed to save people
* Backspace compare two strings


### [[⬆]](#toc) <a name='fastandslow'>Fast and slow pointer problem</a>

[source](src/main/java/org/javainaction/fastslow)

In these problem statements there usually a single linked list and we are required to find a cycle or cycle length 
or middle of a linked list.

* Happy number
* Find a cycle in a linked list
* Find if linked list is a palindrome or not
* Find middle of the linked list
* Rearrange linked list in a certain fashion

### [[⬆]](#toc) <a name='intervals'>Interval problem</a>

[source](src/main/java/org/javainaction/interval)

Interval problems where we have an upper and lower bound of an interval and we need to find an overlapping or insert or 
a non-overlapping intervals in a 2D array or pair in a single array

It predominately uses min/max heaps to control the slots or at times needs to merge and flatten the intervals.

* Find possible meeting time between two calendars
* Minimum meeting rooms needed for a set of conferences
* Find a free time between employees
* Min laptops needed for rental

### [[⬆]](#toc) <a name='cyclingsort'>Cycling sort problem</a>

[source](src/main/java/org/javainaction/cyclicsort)

Unsorted array in range 1.... n range, we need to find either duplicate or missing element. Range usually is large.

* Find one or all missing numbers
* Find one or all duplicate numbers
* Find kth missing positive number
* Find corrupt number

### [[⬆]](#toc) <a name='reverseinplace'>Reverse in place problem</a>

[source](src/main/java/org/javainaction/linkedlist)

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

[source](src/main/java/org/javainaction/bt/bfs)

Level by level order with O(w) complexity.
These problems largely deal with handling tree that demands traversal by level. We are required to use iterative solutions
using queue where queue size acts as a level order during iteration.

* Connect all level order siblings
* Find a tree path in zig-zag manner
* Right side view of tree
* Get level order average/sum etc.
* Find level order successor element
* Maximum binary tree depth

### [[⬆]](#toc) <a name='dfs'>Depth first search problem</a>

[source](src/main/java/org/javainaction/bt/dfs)

Track while traversing and use recursion with O(h) complexity.
These problems largely deal with handling tree that demands traversal to its depth. We are required to use recursive
solutions or use stack in case of iterative solutions.

* Find binary tree diameter
* Find branch sum of a binary tree
* Invert a given binary tree
* Find maximum or path sum of given binary tree
* Get the node depth of a tree
* Get lowest common ancestor 

### [[⬆]](#toc) <a name='heap'>Heap problem</a>

[source](src/main/java/org/javainaction/heap)

These problems typically deal with finding either smallest or largest in a collection. 
Large part of problems also works with sliding window or interval problems.

* Connect ropes while maintaining minimum cost
* Frequency sort in ascending or descending order
* Kth closest element to origin
* Kth smallest or largest number in a sorted array
* Kth smallest or largest number in a sorted matrix
* Find maximum CPU load
* Find minimum roms needed for a conference
* Find Nth ugly number
* Find median (sliding window median or median from stream too)
* Rearrange strings with K distance apart

### [[⬆]](#toc) <a name='recursion'>Recursion and Backtracking problem</a>

[source](src/main/java/org/javainaction/recursion)

These problems demand we break problem into smaller units and perform DFS or recursion on remaining equation.
They also deal with cases when solving linearly is not feasible and we need to backtrack solution multiple times.

* Find all permutations of characters or numbers
* Find all possible combination of characters or numbers
* Find powerset of list
* Find phone number mnemonics
* Solve sudoku
* How many ways to climb staircase
* Find all subsets or subsets with duplicates
* Find unique BST trees
* Find next permutation
* Find possible matching tags or parenthesis that can be created

### [[⬆]](#toc) <a name='bst'>Binary search tree</a>

[source](src/main/java/org/javainaction/bst)

These problems hint towards performing search to improve efficiency while finding certain elements. 
Most of the time they could be a sorted arrays in either direction.

* BST traversal
* Find smallest or largest value in BST
* Find closes value in BST
* Find lowest common ancestor
* Construct a minimum height BST
* Validate if it's a valid BST

### [[⬆]](#toc) <a name='binarysearch'>Binary search problem</a>

[source](src/main/java/org/javainaction/binarysearch)

These problems hint towards performing search to improve efficiency while finding certain elements.
Most of the time they could be a sorted arrays in either direction.

* Find minimum value in a sorted and rotated array
* Find range of given element in a sorted array
* Find first bad version
* Are two BST same without constructing BST
* Search in the Bitonic array
* Search in a sorted matrix
* Search in an infinite sorted array
* Search in a rotated array
* Find ugly number given a, b, c
* Find the day when the revenue milestones achieved
* Find ceiling of a number

### [[⬆]](#toc) <a name='bitxor'>Bit XOR problem</a>


Bitwise XOR based bit manipulations

### [[⬆]](#toc) <a name='topk'>Top K element problem</a>

These are added flavor to heap problems where we need to find certain top elements. The top elements could be anything -
unique elements, least unique, max occurring chars or least occurring characters.

* Rearrange string without having same chars at adjacent 
* Minimum distinct elements in array after K removals
* Top K frequent numbers or words
* Rearrange string with K distance apart
* Frequency sort to achieve something either to minimize cost or gain more length

### [[⬆]](#toc) <a name='kwaymerge'>K-way merge problem</a>

In this problem type we are required to merge K sorted array/list/matrix to find a range of elements or min/max.
This also uses heap to maintain min or max elements at the top

* Merge K sorted list/array
* K smallest number in M sorted lists
* K smallest number in a sorted matrix
* Find smallest range in a matrix that includes at least 2 elements for either side


### [[⬆]](#toc) <a name='knapsack'>Knapsack</a>

We are given set of items with their weight and a capacity, aim is to fit the items that fall under give capacity.
In some situation weight in implicit and w need to create on out of given problem.

* Min number of ways to make coins to match value
* Find minimum cost while cutting rod with given cutting price
* Find partition set with given value of equal partitions or K equal partitions
* Find minimum cost to cut a stick

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

### [[⬆]](#toc) <a name='topologysort'>Topology sort problem</a>

Jobs and dependencies of the job. Linear ordering with dependencies. Order things when they are interdependent kind of problems.

### [[⬆]](#toc) <a name='greedy'>Greedy algorithms</a>

Algorithms with greedy approach and using data structure suitabe to get to the solution
* Find a possible events that can be attended in a conference
* Partition a pattern of string such that each partition has at most single character