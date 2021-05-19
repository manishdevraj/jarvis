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


### [[⬆]](#toc) <a name='fastandslow'>Fast and slow pointer problem</a>

Cyclic array or list. Find cycle or middle elements

### [[⬆]](#toc) <a name='intervals'>Interval problem</a>

Overlapping intervals, merge intervals (use min max heaps)

### [[⬆]](#toc) <a name='cyclingsort'>Cycling sort problem</a>

Unsorted array in range 1…. n, find missing element

### [[⬆]](#toc) <a name='reverseinplace'>Reverse in place problem</a>

Swap, rotate, alternate reverse etc around linked list.

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