# 排序算法(Sorting algorithm)
tags: [Java,算法,排序]

&emsp;&emsp;[@Saisimon](http://blog.saisimon.net/)

&emsp;&emsp;顾名思义就是将一串数据依照特定排序方式进行排列的一种算法。排序算法各式各样，各有各的特定使用场景，常用的简单排序有：归并排序、快速排序、堆排序、插入排序、希尔排序、基数排序、冒泡排序、选择排序、桶排序等

* 常见排序算法的复杂度与稳定性

|   名称   |    最优     |     平均      |      最差     |   空间   | 稳定 | 方式 |
|----------|-------------|---------------|---------------|----------|------|------|
| [归并排序](#mergeSort) | $O(n*log n)$ | $O(n*log n)$   | $O(n*log n)$   | $O(n)$     | Yes  | 合并 |
| [快速排序](#quickSort) | $O(n*log n)$ | $O(n*log n)$   | $O(n^2)$        | $O(log n)$ | No   | 分割 |
| [堆排序](#heapSort)   | $O(n*log n)$ | $O(n*log n)$   | $O(n*log n)$   | $O(1)$     | No   | 选择 |
| [插入排序](#insertSort) | $O(n)$        | $O(n^2)$        | $O(n^2)$        | $O(1)$     | Yes  | 插入 |
| [希尔排序](#shellSort) | $O(n)$      | $O(n*log^2 n)$ | $O(n*log^2 n)$ | $O(1)$     | No   | 插入 |
| [冒泡排序](#bubbleSort) | $O(n)$        | $O(n^2)$        | $O(n^2)$        | $O(1)$     | Yes  | 交换 |
| [选择排序](#selectSort) | $O(n^2)$     | $O(n^2)$        | $O(n^2)$        | $O(1)$     | Yes  | 选择 |
| [基数排序](#radixSort) | -           | $O(n*k)$     | O($n*k$)     | $O(n+k)$ | Yes  | -    |
| [桶排序](#bucketSort)   | -           | $O(n+k)$        | $O(n^2*k)$     | $O(n*k)$  | Yes  | -    |

<small>注：稳定表示相同的数据在排序前后在相对位置一致，例如 3，2，1，3 中第一个3在排序后应该也是第一个 3</small>

## <a id="mergeSort"></a>[归并排序](https://zh.wikipedia.org/wiki/%E5%BD%92%E5%B9%B6%E6%8E%92%E5%BA%8F)([Merge sort](https://en.wikipedia.org/wiki/Merge_sort))
&emsp;&emsp;归并排序运用了一种分治的方法，将一个复杂的问题进行分成两个或两个以上的子问题，直到将问题变成一个可以用简单的方式解决，然后将子问题的解进行合并，最终得到原问题的答案。归并排序即先递归分割待排序数据，然后再进行合并操作。

* 复杂度与稳定性

|     复杂度     |   复杂度    |
|----------------|-------------|
| 最差时间复杂度 | $O(n*log n)$ |
| 最优时间复杂度 | $O(n*log n)$ | 
| 平均时间复杂度 | $O(n*log n)$ |
| 最差空间复杂度 | $O(n)$        |
|     稳定       |     Yes     |
       

* 算法伪代码
``` c
# split in half
m = n / 2

# recursive sorts
sort a[1..m]
sort a[m+1..n]

# merge sorted sub-arrays using temp array
b = copy of a[1..m]
i = 1, j = m+1, k = 1
while i <= m and j <= n,
    a[k++] = (a[j] < b[i]) ? a[j++] : b[i++]
    → invariant: a[1..k] in final position
while i <= m,
    a[k++] = b[i++]
    → invariant: a[1..k] in final position
```

* 可视化归并排序算法
![image](https://upload.wikimedia.org/wikipedia/commons/c/cc/Merge-sort-example-300px.gif)
<small>图片来自wikipedia</small>

* java实现
``` java
/**
 * 归并排序
 * 先递归再合并
 * 
 * @param a 待排序数组
 */
public static void mergeSort(int[] a) {
	if (a == null) {
		new IllegalArgumentException("参数错误");
	}
	mergeSort(a, 0, a.length - 1);
}

public static void mergeSort(int[] a, int start, int end) {
	if (a == null || start < 0 || end >= a.length) {
		new IllegalArgumentException("参数错误");
	}
	if (start >= end) {
		return;
	}
	// 找中值
	int middle = (start + end) / 2;
	// 递归调用
	mergeSort(a, start, middle);
	mergeSort(a, middle + 1, end);
	// 合并
	sorts(a, start, middle, end);
}

private static void sorts(int[] a, int start, int middle, int end) {
	if (a == null || start < 0 || end >= a.length) {
		new IllegalArgumentException("参数错误");
	}
	int len = end - start + 1;
	// 如果只有两个元素，直接比较
	if (len == 2) {
		if (a[start] > a[end]) {
			int temp = a[start];
			a[start] = a[end];
			a[end] = temp;
		}
		return;
	}
	// 临时数组，用于存储比较之后的元素
	// a = [1,3,5,2,4,6] -> [1,3,5] + [2,4,6]
	// [1,3,5]  [2,4,6]
	//  -        -  
	// [1 < 2] -> c = [1,0,0,0,0,0]
	// [2 < 3] -> c = [1,2,0,0,0,0]
	//  ...
	// [6]     -> c = [1,2,3,4,5,6]
	int[] c = new int[len];
	int k = start;
	int j = middle + 1;
	for (int i = 0; i < len; i++) {
		if (k > middle) {
			//只剩第二个数组中的元素
			c[i] = a[j];
			j++;
			continue;
		}
		if (j > end) {
			//只剩第一个数组中的元素
			c[i] = a[k];
			k++;
			continue;
		}
		if (a[k] <= a[j]) {
			c[i] = a[k];
			k++;
		} else {
			c[i] = a[j];
			j++;
		}
	}
	// 将c数组中的元素填充回a数组
	for (int i = 0; i < len; i++) {
		a[start + i] = c[i];
	}
}
```

<br/>
<br/>

## <a id="quickSort"></a>[快速排序](https://zh.wikipedia.org/wiki/%E5%BF%AB%E9%80%9F%E6%8E%92%E5%BA%8F)([Quick sort](https://en.wikipedia.org/wiki/Quicksort))
&emsp;&emsp;快速排序是一种比较排序，以某个基准(pivot)为界限，进行划分，然后进行交换，所以又被称为划分交换排序（partition-exchange sort）

* 复杂度与稳定性

|     类型       |     值      |
|----------------|-------------|
| 最差时间复杂度 | $O(n^2)$      |
| 最优时间复杂度 | $O(n*log n)$ | 
| 平均时间复杂度 | $O(n*log n)$ |
| 最差空间复杂度 | $O(n)$       |
|     稳定       |     Yes     |

### 分成两部分进行排序

* 2-way 快速排序的步骤

&emsp;&emsp;快速排序使用分治法（Divide and conquer）策略来把一个序列（list）分为两个子序列（sub-lists）。

&emsp;&emsp;1. 从数列中挑出一个元素，称为"基准"（pivot），
&emsp;&emsp;2. 重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。在这个分区结束之后，该基准就处于数列的中间位置。这个称为分区（partition）操作。
&emsp;&emsp;3. 递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序。
      
&emsp;&emsp;![image](http://ww3.sinaimg.cn/mw690/7c2c72d3gw1f4xgrozyptj20m80ci0tw.jpg)

* 算法伪代码

``` c
# choose pivot
swap a[1,rand(1,n)]

# 2-way partition
k = 1
for i = 2:n, if a[i] < a[1], swap a[++k,i]
swap a[1,k]
→ invariant: a[1..k-1] < a[k] <= a[k+1..n]

# recursive sorts
sort a[1..k-1]
sort a[k+1,n]
```

* 可视化快速排序算法
![image](https://upload.wikimedia.org/wikipedia/commons/6/6a/Sorting_quicksort_anim.gif)
<small>图片来自wikipedia</small>

* java实现

``` java
/**
 * 快速排序 (分两部分)
 * 分成两部分，再递归调用
 * 
 * @param a 待排序数组
 */
public static void quickSort(int[] a) {
	if (a == null) {
		new IllegalArgumentException("参数错误");
	}
	quickSort(a, 0, a.length - 1);
}

public static void quickSort(int[] a, int start, int end) {
	if (a == null || start < 0 || end >= a.length) {
		new IllegalArgumentException("参数错误");
	}
	// 分段，找到idx,使得idx前面的值都小于k，idx后面的值都大于k
	int idx = partition(a, start, end);
	// 递归调用
	if (idx > start) {
		quickSort(a, start, idx - 1);
	}
	// 递归调用
	if (idx < end) {
		quickSort(a, idx + 1, end);
	}
}

private static int partition(int[] a, int start, int end) {
	if (a == null) {
		new IllegalArgumentException("参数错误");
	}
	// 首部
	int i = start;
	// 尾部
	int j = end;
	// 比较值
	int k = a[start];
	while (j > i) {
		// 从末尾开始和k进行比较，保证当前末尾的值都大于等于k
		while (j > i && a[j] >= k) {
			j--;
		}
		// 当当前末尾的小于k时，交换当前首部和当前尾部的值
		if (j > i) {
			a[i] = a[j];
			i++;
		}
		// 再从当前首部开始和k进行比较，保证当前首部的值都小于等于k
		while (j > i && a[i] <= k) {
			i++;
		}
		// 当当前首部的值大于k时，交换当前尾部和当前首部的值
		if (j > i) {
			a[j] = a[i];
			j--;
		}
	}
	// 首尾相遇，当前首部的值为比较值
	a[i] = k;
	// 返回当前比较值所在位置，用于递归调用，这是i前面的值都小于k，i后面的值都大于k
	return i;
}
```

### 分成三个部分进行排序(3 Way Partition)
* 3-way 快速排序的步骤
1. 依旧从数列中挑出一个元素，称为"基准"（pivot），
2. 基准左边的数据都小于基准，右边的数据都大于基准，其他的为与基准相等的数据
3. 递归调用，把小于基准值元素的子数列和大于基准值元素的子数列排序。
![image](http://ww2.sinaimg.cn/mw690/7c2c72d3gw1f4xgqsi3lbj20m80cht9x.jpg)

* 算法伪代码

``` c
# choose pivot
swap a[n,rand(1,n)]

# 3-way partition
i = 1, k = 1, p = n
while i < p,
  if a[i] < a[n], swap a[i++,k++]
  else if a[i] == a[n], swap a[i,--p]
  else i++
end
→ invariant: a[p..n] all equal
→ invariant: a[1..k-1] < a[p..n] < a[k..p-1]

# move pivots to center
m = min(p-k,n-p+1)
swap a[k..k+m-1,n-m+1..n]

# recursive sorts
sort a[1..k-1]
sort a[n-p+k+1,n]
```

* java实现

``` java
/**
 * 快速排序(分三部分)
 * 分成三个部分，再递归调用
 * 
 * @param a 待排序数组
 */
public static void quickSort3(int[] a) {
	if (a == null) {
		new IllegalArgumentException("参数错误");
	}
	quickSort3(a, 0, a.length - 1);
}

public static void quickSort3(int[] a, int start, int end) {
	if (a == null || start < 0 || end >= a.length) {
		new IllegalArgumentException("参数错误");
	}
	// 当首大于等于尾，直接返回
	if (start >= end) {
		return;
	}
	// 低位
	int low = start;
	// 高位
	int high = end;
	// 初始下标
	int i = start + 1;
	// 比较值
	int k = a[start];
	// 分三段 (小于|等于|大于)
	// k左边的元素都小于k，k右边的元素都大于k，中间为和k相等的元素
	while (i <= high) {
		if (a[i] < k) {
			swap(a, i, low);
			i++;
			low++;
 		} else if (a[i] > k) {
 			swap(a, i, high);
 			high--;
		} else {
			i++;
		}
	}
	// 小于部分递归调用
	quickSort3(a, start, low - 1);
	// 大于部分递归调用
	quickSort3(a, high + 1, end);
}

/**
 * 交换指定数组中的两个元素的位置
 * 
 * @param a 数组
 * @param i 第一个元素的下标
 * @param j 第二个元素的下标
 */
public static void swap(int[] a, int i, int j) {
	if (a == null || i < 0 || j >= a.length) {
		new IllegalArgumentException("参数错误");
	}
	int temp = a[i];
	a[i] = a[j];
	a[j] = temp;
}
```

<br/>
<br/>

## <a id="heapSort"></a>[堆排序](https://zh.wikipedia.org/wiki/%E5%A0%86%E6%8E%92%E5%BA%8F)([Heap sort](https://en.wikipedia.org/wiki/Heapsort))
&emsp;&emsp;堆排序（Heapsort）是利用堆这种数据结构所设计的一种排序算法，堆又是一种二叉树，关于二叉树可以参考[Wiki](https://zh.wikipedia.org/wiki/%E4%BA%8C%E5%8F%89%E6%A0%91)。

* 复杂度与稳定性

|     类型       |     值      |
|----------------|-------------|
| 最差时间复杂度 | $O(n*log n)$ |
| 最优时间复杂度 | $O(n*log n)$ | 
| 平均时间复杂度 | $O(n*log n)$ |
| 最差空间复杂度 | $O(1)$        |
|     稳定       |     No      |


* 堆排序的步骤
1. 最大堆调整（Max_Heapify）：将堆的末端子节点作调整，使得子节点永远小于父节点
2. 创建最大堆（Build_Max_Heap）：将堆所有数据重新排序
3. 堆排序（HeapSort）：移除位在第一个数据的根节点，并做最大堆调整的递归运算

* 算法伪代码

``` c
# heapify
for i = n/2:1, sink(a,i,n)
→ invariant: a[1,n] in heap order

# sortdown
for i = 1:n,
    swap a[1,n-i+1]
    sink(a,1,n-i)
    → invariant: a[n-i+1,n] in final position
end

# sink from i in a[1..n]
function sink(a,i,n):
    # {lc,rc,mc} = {left,right,max} child index
    lc = 2*i
    if lc > n, return # no children
    rc = lc + 1
    mc = (rc > n) ? lc : (a[lc] > a[rc]) ? lc : rc
    if a[i] >= a[mc], return # heap ordered
    swap a[i,mc]
    sink(a,mc,n)
```

* 可视化堆排序算法
![image](https://upload.wikimedia.org/wikipedia/commons/4/4d/Heapsort-example.gif)
<small>图片来自wikipedia</small>

* java实现

``` java
/**
 * 堆排序
 * 利用堆的特性进行排序
 * 
 * @param a 待排序数组
 */
public static void heapSort(int[] a) {
	if (a == null) {
		new IllegalArgumentException("参数错误");
	}
	heapSort(a, 0, a.length - 1);
}

public static void heapSort(int[] a, int start, int end) {
	if (a == null || start < 0 || end >= a.length) {
		new IllegalArgumentException("参数错误");
	}
	// 当首大于等于尾，直接返回
	if (start >= end) {
		return;
	}
	int i = end - start;
	while (i > 0) {
		// 当前元素的父节点
		int parent = (i - 1) / 2;
		// 如果左边元素与当前元素同属一个父节点，比较三个元素，最大的放入父节点
		if ((i - 2) / 2 == parent) {
			if (a[i - 1] > a[parent]) {
				swap(a, i - 1, parent);
			}
			if (a[i] > a[parent]) {
				swap(a, i, parent);
			}
			i = i - 2;
		} else {
			if (a[i] > a[parent]) {
				swap(a, i, parent);
			}
			i--;
		}
	}
	// 交换首尾元素
	swap(a, start, end);
	// 递归调用
	heapSort(a, start, --end);
}
```

<br/>
<br/>

## <a id="insertSort"></a>[插入排序](https://zh.wikipedia.org/wiki/%E6%8F%92%E5%85%A5%E6%8E%92%E5%BA%8F)([Insertion sort](https://en.wikipedia.org/wiki/Insertion_sort))
&emsp;&emsp;插入排序是一种非常直观的排序方式，从第一个元素开始，从后往前比较，然后进行交换操作，直到整个数据遍历完成。这种排序方式只需用到O(1)的额外空间来处理存放临时数据，但是需要不断的把前面的序列进行移位，来插入下一个元素。差不多已经排好序的数据集合使用插入排序的效率更高，这就产生了插入排序的改进方法--希尔排序

* 复杂度与稳定性

|     类型       |     值      |
|----------------|-------------|
| 最差时间复杂度 | $O(n^2)$      |
| 最优时间复杂度 | $O(n)$        | 
| 平均时间复杂度 | $O(n^2)$      |
| 最差空间复杂度 | $O(1)$        |
|     稳定       |     Yes     |


* 插入排序操作的实现步骤
1.从第一个元素开始，该元素可以认为已经被排序
2.取出下一个元素，在已经排序的元素序列中从后向前扫描
3.如果该元素（已排序）大于新元素，将该元素移到下一位置
4.重复步骤3，直到找到已排序的元素小于或者等于新元素的位置
5.将新元素插入到该位置后
6.重复步骤2~5

* 算法伪代码

``` c
for i = 2:n,
    for (k = i; k > 1 and a[k] < a[k-1]; k--) 
        swap a[k,k-1]
    → invariant: a[1..i] is sorted
end
```

* 可视化插入排序算法
![image](https://upload.wikimedia.org/wikipedia/commons/0/0f/Insertion-sort-example-300px.gif)
<small>图片来自wikipedia</small>

* java 实现

``` java
/**
 * 插入排序
 * 遍历数组，比较大小，插入元素
 * 
 * @param a 待排序数组
 */
public static void insertSort(int[] a) {
	if (a == null) {
		new IllegalArgumentException("参数错误");
	}
	insertSort(a, 0, a.length - 1);
}

public static void insertSort(int[] a, int start, int end) {
	if (a == null || start < 0 || end >= a.length) {
		new IllegalArgumentException("参数错误");
	}
	// 遍历数组
	for (int i = start + 1; i <= end; i++) {
		int k = a[i];
		// 当前位置的值与k进行比较，如果大于k，就交换
		for (int j = i - 1; j >= 0 && a[j] > k; j--) {
			a[j + 1] = a[j];
			a[j] = k;
		}
	}
}
```

<br/>
<br/>

## <a id="shellSort"></a>[希尔排序](https://zh.wikipedia.org/wiki/%E5%B8%8C%E5%B0%94%E6%8E%92%E5%BA%8F)([Shell sort](https://en.wikipedia.org/wiki/Shell_sort))
&emsp;&emsp;希尔排序是插入排序的一个改进版本，利用了插入排序在对几乎排好序的数据集合的排序效率为O(n)性质，对数据集合进行分组，然后进行插入排序，指定分组的间隔为1为止，但是希尔排序变成了不稳定的排序方法。

* 复杂度与稳定性

|     类型       |      值       |
|----------------|---------------|
| 最差时间复杂度 | $O(n*log^2 n)$ |
| 最优时间复杂度 | $O(n)$          | 
| 平均时间复杂度 | $O(n*log^2 n)$ |
| 最差空间复杂度 | $O(1)$          |
|     稳定       |      No       |


* 算法伪代码

``` c
h = 1
while h < n, h = 3*h + 1
while h > 0,
    h = h / 3
    for k = 1:h, insertion sort a[k:h:n]
    → invariant: each h-sub-array is sorted
end
```

* 排序实例

1. 待排序数据集合
    ```bash
    1,29,18,25,13,20,4,15,9,21,11,3,10,8,27,31,22,7
    ```
2. 对其进行步长为7的分组
    ```bash
    01,29,18,25,13,20,04
    15,09,21,11,03,10,08
    27,31,22,07
    ```
3. 对每一列进行插入排序
    ```bash
    01,09,18,07,03,10,04
    15,29,21,11,13,20,08
    27,31,22,25
    ```
4. 得到第一次排序结果
    ```bash
    1,9,18,7,3,10,4,15,29,21,11,13,20,8,27,31,22,25
    ```
5. 再对其进行步长为4的分组
    ```bash
    01,09,18,07
    03,10,04,15
    29,21,11,13
    20,08,27,31
    22,25
    ```
6. 再对每一列进行插入排序
    ```bash
    01,08,04,07
    03,09,11,13
    20,10,18,15
    22,21,27,31
    29,25
    ```
7. 得到第二次排序结果
    ```bash
    1,8,4,7,3,9,11,13,20,10,18,15,22,21,27,31,29,25
    ```
8. 最后进行步长为1的插入排序，得到最终排好序的结果
    ```bash
    1,3,4,7,8,9,10,11,13,15,18,20,21,22,25,27,29,31
    ```
    
&emsp;&emsp;根据上面的实例可以看出步长的选择对排序的效率有很大的影响。最后步长为1时就变成了普通的插入排序。一般选用的步长公式为3*i+1，即1,4,7,10...

* java 实现

``` java
/**
 * 希尔排序
 * 先按指定规则分组，然后组内排序，直到间隔为1
 * 
 * @param a 带排序数组
 */
public static void shellSort(int[] a) {
	if (a == null) {
		new IllegalArgumentException("参数错误");
	}
	shellSort(a, 0, a.length - 1);
}

public static void shellSort(int[] a, int start, int end) {
	if (a == null || start < 0 || end >= a.length) {
		new IllegalArgumentException("参数错误");
	}
	// 间隔数量
	int gap = 1;
	// 间隔数量取 gap = gap * 3 + 1， 1,4,13,40...
	while (gap < (end - start + 1) / 3) {
		gap = gap * 3 + 1;
	}
	// 每个间隔的值组成一个组，然后组内做插入排序，直到间隔为1
	// 1 6 2 9 -1 2 7 1 3 2 5
	// |________|_______|
	//   |________|_______|
	//     |________|_______|
	//       |________|
	for (; gap > 0; gap /= 3) {
		//插入排序
		for (int i = start + gap; i <= end; i += gap) {
			int k = a[i];
			for (int j = i - gap; j >= 0 && a[j] > k; j -= gap) {
				a[j + gap] = a[j];
				a[j] = k;
			}
		}
	}
}
```

<br/>
<br/>

## <a id="bubbleSort"></a>[冒泡排序](https://zh.wikipedia.org/wiki/%E5%86%92%E6%B3%A1%E6%8E%92%E5%BA%8F)([Bubble sort](https://en.wikipedia.org/wiki/Bubble_sort))
&emsp;&emsp;冒泡排序也是一种简单直观的比较排序算法。每次比较两个元素，如果顺序不正确，则交换位置，直到所有元素没有再需要交换为止，也就是说该数列已经排序完成。

* 复杂度与稳定性

|     类型       |      值       |
|----------------|---------------|
| 最差时间复杂度 | $O(n^2)$        |
| 最优时间复杂度 | $O(n)$          | 
| 平均时间复杂度 | $O(n^2)$        |
| 最差空间复杂度 | $O(1)$          |
|     稳定       |      Yes      |


* 冒泡排序的实现步骤
1. 比较相邻的元素。如果第一个比第二个大，就交换他们两个。
2. 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。这步做完后，最后的元素会是最大的数。
3. 针对所有的元素重复以上的步骤，除了最后一个。
4. 持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。

* 算法伪代码

``` c
for i = 1:n,
    swapped = false
    for j = n:i+1, 
        if a[j] < a[j-1], 
            swap a[j,j-1]
            swapped = true
    → invariant: a[1..i] in final position
    break if not swapped
end
```

* 可视化冒泡排序算法
![image](https://upload.wikimedia.org/wikipedia/commons/c/c8/Bubble-sort-example-300px.gif)
<small>图片来自wikipedia</small>

* java 实现

``` java
/**
 * 冒泡排序
 * 遍历数组，比较大小，最大值放最后
 * 
 * @param a 待排序数组
 */
public static void bubbleSort(int[] a) {
	if (a == null) {
		new IllegalArgumentException("参数错误");
	}
	bubbleSort(a, 0, a.length - 1);
}

public static void bubbleSort(int[] a, int start, int end) {
	if (a == null || start < 0 || end >= a.length) {
		new IllegalArgumentException("参数错误");
	}
	// 遍历数组
	for (int i = start; i <= end; i++) {
		// 当前区域最大值下标
		int maxIdx = start;
		// 当前区域的最后位置			
		int last = end - i + start;
		// 遍历选出当前区域最大值下标
		for (int j = start + 1; j <= last; j++) {
			if (a[maxIdx] <= a[j]) {
				maxIdx = j;
			}
		}
		// 交换最大值和尾元素
		if (maxIdx != last) {
			swap(a, maxIdx, last);
		}
	}
}
```

<br/>
<br/>

## <a id="selectSort">[选择排序](https://zh.wikipedia.org/wiki/%E9%81%B8%E6%93%87%E6%8E%92%E5%BA%8F)([Selection sort](https://en.wikipedia.org/wiki/Selection_sort))</a>
&emsp;&emsp;选择排序也是一个非常简单明了的比较排序算法。首先遍历待排序的数列，找出最小的元素，与数列第一个元素交换，然后从第二个元素开始再遍历找出最小的元素，以此类推，直到最后一个元素，这时数列就已经排好序了。

* 复杂度与稳定性

|     类型       |      值       |
|----------------|---------------|
| 最差时间复杂度 | $O(n^2)$        |
| 最优时间复杂度 | $O(n^2)$       | 
| 平均时间复杂度 | $O(n^2)$        |
| 最差空间复杂度 | $O(1)$          |
|     稳定       |      Yes      |


* 算法伪代码

``` c
for i = 1:n,
    k = i
    for j = i+1:n, if a[j] < a[k], k = j
    → invariant: a[k] smallest of a[i..n]
    swap a[i,k]
    → invariant: a[1..i] in final position
end
```

* 可视化选择排序算法
![image](https://upload.wikimedia.org/wikipedia/commons/9/94/Selection-Sort-Animation.gif)
<small>图片来自wikipedia</small>

* java 实现

``` java
/**
 * 选择排序
 * 遍历数组，比较大小，最小值放前面
 * 
 * @param a 待排序数组
 */
public static void selectSort(int[] a) {
	if (a == null) {
		new IllegalArgumentException("参数错误");
	}
	selectSort(a, 0, a.length - 1);
}

public static void selectSort(int[] a, int start, int end) {
	if (a == null || start < 0 || end >= a.length) {
		new IllegalArgumentException("参数错误");
	}
	// 遍历数组
	for (int i = start; i <= end; i++) {
		// 当前区域最小值的下标
		int minIdx = i;
		// 遍历选出当前区域最小值的下标
		for (int j = i + 1; j <= end; j++) {
			if (a[minIdx] >= a[j]) {
				minIdx = j;
			}
		}
		// 交换最小值和当前元素
		if (minIdx != i) {
			swap(a, minIdx, i);
		}
	}
}
```

<br/>
<br/>

## <a id="radixSort"></a>[基数排序](https://zh.wikipedia.org/wiki/%E5%9F%BA%E6%95%B0%E6%8E%92%E5%BA%8F)([Radix sort](https://en.wikipedia.org/wiki/Radix_sort))
&emsp;&emsp;基数排序是一种非比较的排序算法，用于对整数进行排序。原理是将数据集合的每一个数据按位数进行比较，位数不足的用0补足，从最低位开始进行依次进行排序，直到排完所有位数。

* 复杂度与稳定性

|     类型       |      值       |
|----------------|---------------|
| 最差时间复杂度 | $O(n*k)$       | 
| 平均时间复杂度 | $O(n*k)$       |
| 最差空间复杂度 | $O(n+k)$        |
|     稳定       |      Yes      |

<small>注：k为数字位数</small>

* 排序实例
1.待排序数据集合
    ```bash
    71,2,103,28,19,15,127,391,56,27,49,42,8,11,194
    ```
2.从个位开始比较
    ```bash
    0 : 
    1 : 11,71,391
    2 : 2,42
    3 : 103
    4 : 194
    5 : 15
    6 : 56
    7 : 27,127
    8 : 8,28
    9 : 19,49
    ```
3.得到第一次比较结果
    ```bash
    11,71,391,2,42,103,194,15,56,27,127,8,28,19,49
    ```
4.在对十位进行比较
    ```bash
    0 : 2,8,103
    1 : 11,15,19
    2 : 27,28,127
    3 : 
    4 : 42,49
    5 : 56
    6 : 
    7 : 71
    8 : 
    9 : 194,391
    ```
5.得到第二次比较结果
    ```bash
    2,8,103,11,15,19,27,28,127,42,49,56,71,194,391
    ```
6.再对百位进行比较
    ```bash
    0 : 2,8,11,15,19,27,28,42,49,56,71
    1 : 103,127,194
    2 : 
    3 : 391
    4 : 
    5 : 
    6 : 
    7 : 
    8 : 
    9 : 
    ```
7.最后得到最终排序后的结果
    ```bash
    2,8,11,15,19,27,28,42,49,56,71,103,127,194,391
    ```
    
* java 实现

``` java
/**
 * 基数排序
 * 按照位数来排序，先从个位一直到最大值得最大位
 * 
 * @param a 待排序数组
 */
public static void radixSort(int[] a) {
	if (a == null) {
		new IllegalArgumentException("参数错误");
	}
	radixSort(a, 0, a.length - 1);
}

public static void radixSort(int[] a, int start, int end) {
	if (a == null || start < 0 || end >= a.length) {
		new IllegalArgumentException("参数错误");
	}
	// 数组最大值
	int max = a[start];
	// 遍历选出最大值
	for (int i = start + 1; i <= end; i++) {
		if (a[i] > max) {
			max = a[i];
		}
	}
	// 最大值得位数，即循环多少次
	int loop = 0;
	while(max != 0) {
		max /= 10;
		loop++;
	}
	// 基数数组，从-9 ~ 9
	int[] radix = new int[19];
	// 临时存储中间数组
	int[] temp = new int[end - start + 1];
	int mod = 1;
	for (int i = 1; i <= loop; i++) {
		// 基数数组初始化或者还原
		for (int j = 0; j < radix.length; j++) {
			radix[j] = 0;
		}
		// 根据当前位数的值对应于基数数组中下标，进行累加
		for (int j = end; j >= start; j--) {
			//当前位数的值， 123 -> 个位为3，十位为2 ...
			int idx = (a[j] / mod) % 10;
			// 转为基数数组下标
			idx += 9;
			// 累计
			radix[idx]++;
		}
		// 对基数数组中的值进行累计，对应于a中的下标
		for (int j = 1; j < radix.length; j++) {
			radix[j] += radix[j - 1];
		}
		// 根据当前位数的值对应的基数数组的值填充进temp数组中
		for (int j = end; j >= start; j--) {
			int idx = (a[j] / mod) % 10;
			idx += 9;
			temp[radix[idx] - 1] = a[j];
			radix[idx]--;
		}
		// 将temp中的值存入a数组中
		for (int j = 0; j < temp.length; j++) {
			a[start + j] = temp[j];
		}
		mod *= 10;
	}
}
```

<br/>
<br/>

## <a id="bucketSort"></a>[桶排序](https://zh.wikipedia.org/wiki/%E6%A1%B6%E6%8E%92%E5%BA%8F)([Bucket sort](https://en.wikipedia.org/wiki/Bucket_sort))
&emsp;&emsp;桶排序不是一个比较排序，其原理是将元素放入特定的某个桶中，然后在桶中进行排序。

* 复杂度与稳定性

|     类型       |      值       |
|----------------|---------------|
| 最差时间复杂度 | $O(n^2*k)$     | 
| 平均时间复杂度 | $O(n+k)$        |
| 最差空间复杂度 | $O(n*k)$       |
|     稳定       |      Yes      |


* 桶排序实现步骤
1. 设置一个定量的数组当作空桶子。
2. 寻访序列，并且把项目一个一个放到对应的桶子去。
3. 对每个不是空的桶子进行排序。
4. 从不是空的桶子里把项目再放回原来的序列中。

* 排序实例
1. 待排序数据集合
    ```bash
    1,29,18,25,13,20,4,15,9,21,11,3,10,8,27,31,22,7
    ```
2. 分成若干个桶，将数据集合按照大小存入特定的桶内
    ```bash
    0-4 : 1,4,3
    5-9 : 9,8,7
    10-14 : 13,11,10
    15-19 : 18,15
    20-24 : 20,21,22
    25-29 : 29,25,27
    30-34 : 31
    ```
3. 在每个桶内进行排序操作
    ```bash
    0-4 : 1,3,4
    5-9 : 7,8,9
    10-14 : 10,11,13
    15-19 : 15,18
    20-24 : 20,21,22
    25-29 : 25,27,29
    30-34 : 31
    ```
4. 依次取出桶内的数据，得到最终排好序的数据集合
    ```bash
    1,3,4,7,8,9,10,11,13,15,18,20,21,22,25,27,29,31
    ```

* 算法伪代码

```c
function bucket-sort(array, n) is
  buckets ← new array of n empty lists
  for i = 0 to (length(array)-1) do
    insert array[i] into buckets[msbits(array[i], k)]
  for i = 0 to n - 1 do
    next-sort(buckets[i])
  return the concatenation of buckets[0], ..., buckets[n-1]
```

* java 实现

``` java
/**
 * 桶排序
 * 根据桶的大小，按顺序放入对应的桶中，最后依次取出桶中元素
 * 
 * @param a 待排序数组
 */
public static void bucketSort(int[] a) {
	if (a == null) {
		new IllegalArgumentException("参数错误");
	}
	bucketSort(a, 0, a.length - 1);
}

public static void bucketSort(int[] a, int start, int end) {
	if (a == null || start < 0 || end >= a.length) {
		new IllegalArgumentException("参数错误");
	}
	// 默认一个桶的大小
	int bucketSize = 5;
	// 数组中最大元素
	int max = a[start];
	// 数组中最小元素
	int min = a[start];
	// 遍历得到最大最小值
	for (int i = start + 1; i <= end; i++) {
		if (a[i] > max) {
			max = a[i];
		}
		if (a[i] < min) {
			min = a[i];
		}
	}
	// 桶的数量
	int count = (max - min) / bucketSize + 1;
	// 存储桶的二维数组
	int[][] temp = new int[count][];
	// 遍历待排序数组，往桶中插入元素
	for (int i = start; i <= end; i++) {
		// 桶下标
		int idx = (a[i] - min) / bucketSize;
		// 桶中的数组c
		int[] c = temp[idx];
		// 插入a[i]元素之后的数组
		int[] d;
		// c数组没有元素时，d[0]=a[i]
		if (c == null) {
			d = new int[1];
			d[0] = a[i];
		} else {
			// d数组比c数组多一个元素
			d = new int[c.length + 1];
			// 是否已经插入a[i]元素
			boolean flag = false;
			// c数组中插入a[i]元素得到d数组
			for (int j = 0; j < d.length; j++) {
				// 没有插入的情况下，c数组中的元素大于或等于待插入元素是或者j的值已经为d中最后一个元素时，d[j]=a[i]
				if (!flag && (j == c.length || c[j] >= a[i])) {
					d[j] = a[i];
					flag = true;
				} else {
					// 待插入元素已经插入时
					if (flag) {
						d[j] = c[j - 1];
					} else {
						d[j] = c[j];
					}
				}
			}
		}
		// 桶中数组更新为d数组
		temp[idx] = d;
	}
	int idx = 0;
	// 顺序填充
	for (int i = 0; i < temp.length; i++) {
		// 当前桶中无元素
		if (temp[i] == null) {
			continue;
		}
		for (int j = 0; j < temp[i].length; j++) {
			a[start + idx] = temp[i][j];
			idx++;
		}
	}
}
```

##小结
&emsp;&emsp;常见的排序算法差不多就是上面的几种排序算法：归并排序、快速排序、堆排序、插入排序、希尔排序、基数排序、冒泡排序、选择排序、桶排序，其实排序算法还有很多，二叉查找树排序(Binary tree sort)、[奇偶排序](https://zh.wikipedia.org/wiki/%E5%A5%87%E5%81%B6%E6%8E%92%E5%BA%8F)([Odd–even sort](https://en.wikipedia.org/wiki/Odd%E2%80%93even_sort))、鸡尾酒排序(Cocktail shaker sort)、,还有一些混合排序算法，例如[内省排序](https://zh.wikipedia.org/wiki/%E5%86%85%E7%9C%81%E6%8E%92%E5%BA%8F)([Introsort](https://en.wikipedia.org/wiki/Introsort)),将快速排序和堆排序结合使用，[Tim排序](https://en.wikipedia.org/wiki/Timsort)，将归并排序和插入排序结合一起使用。Java在1.7之前的数组排序，数据量小的时候使用快速排序，数据量大了使用归并排序，在1.7以后对于基本类型使用改良的双基数快速排序，对象使用Tim排序。要想学习Tim排序可以参考[这篇译文](http://blog.kongfy.com/2012/10/%E8%AF%91%E7%90%86%E8%A7%A3timsort-%E7%AC%AC%E4%B8%80%E9%83%A8%E5%88%86%EF%BC%9A%E9%80%82%E5%BA%94%E6%80%A7%E5%BD%92%E5%B9%B6%E6%8E%92%E5%BA%8Fadaptive-mergesort/)。当然还有一些脑洞很大的算法，譬如：bogo排序--将数列随机排列，检查是否排好，没排好的话，继续随机排列。睡眠排序--数列中每一个元素对应一个线程，元素的值对应休眠的时间，根据线程唤醒的先后次序进行排序。排序看上去是个简单的问题，但是确有着大量的研究，有用的新算法仍在不断的被发明。

## 参考资料
* [Sorting algorithm](https://en.wikipedia.org/wiki/Sorting_algorithm )
* [排序算法](https://zh.wikipedia.org/wiki/%E6%8E%92%E5%BA%8F%E7%AE%97%E6%B3%95)
* [常见排序算法](http://bubkoo.com/2014/01/17/sort-algorithm/archives )
* [可视化排序](http://zh.visualgo.net/sorting)
* [排序算法动画](http://www.sorting-algorithms.com)
