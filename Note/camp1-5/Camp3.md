# Class1

# 经典面试题目(一)

## 1.绳子覆盖点 问题

1.给定一个有序数组arr，从左到右依次表示X轴上从左往右点的位置, 给定一个正整数K，返回如果有一根长度为K的绳子，最多能盖住几个点, 绳子的边缘点碰到X轴上的点，也算盖住



## 2.括号问题

2.括号有效配对是指：

1）任何一个左括号都能找到和其正确配对的右括号

2）任何一个右括号都能找到和其正确配对的左括号

有效的：  (()) ()()  (()()) 等

无效的：   (()  )(   等

问题一：怎么判断一个括号字符串有效？

问题二：如果一个括号字符串无效，返回至少填几个字符能让其整体有效



## 3.最长括号有效子串长度(DP)

3.括号有效配对是指:

1）任何一个左括号都能找到和其正确配对的右括号

2）任何一个右括号都能找到和其正确配对的左括号

返回一个括号字符串中，最长的括号有效子串的长度 (DP)



## 4.左红右绿涂染 (R/G)

4.有一些排成一行的正方形。每个正方形已经被染成红色或者绿色。现在可以选择任意一个正方形然后用这两种颜色的任意一种进行染色,这个正方形的颜色将 会被覆盖。目标是在完成染色之后,每个红色R都比每个绿色G距离最左侧近。 返回最少需要涂染几个正方形。

如样例所示: s = RGRGR 我们涂染之后变成RRRGG满足要求了,涂染的个数为2,没有比这个更好的涂染方案。



要求每个R都比G离左侧近, 那么就是要分割数组, 找到左侧最少G,右侧最少R的划分情况

如果我们能站在每个[i]时, 都知道[i]左右有几个G,右侧有几个R的话, 那么就能找到这个分割点;

在此为分割点的情况下, 需要涂染的正方形数最少

**(1)预处理结构**: 两个数组       [0...i]一共有几个G,  [i....N-1]一共有几个R

**(2)一个变量, 一个数组**

**(3)两个变量**



## 5.矩阵选出面积最大正方形

5.给定一个N*N的矩阵matrix，只有0和1两种值，返回边框全是1的最大正方形的边长长度。

例如:

01111

01001

01001

01111

01011 

其中边框全是1的最大正方形的大小为4*4，所以返回4。

> 与leetcode中的题不是完全相同, 该题是仅要求边长为1, lc要求内部也全为1

> lc 85. Maximal Rectangle

> lc 221. Maximal Square

**思路:**

长方形: 随机选一个点O(N^2), 再随机选一个点O(N^2), 总共O(N^4)

正方形:随机选一个点O(N^2), 没办法再随机选一个点, 只能通过扩充边长来找正方形, 所以要找边长为1...N的不同情况,边长可能性O(N), 所以总共O(N^3)

如果要找正方形, 总复杂度至少是O(N^3)



![](https://raw.githubusercontent.com/haoboliu66/PicBed/master/img/20210110154454.png)



把N^2个点扩充到4N^2个点, 长方形数量级的复杂度依然还是O(N^4)





如何检查正方形边框是否都为1, 如果采用**(1)遍历**的方式, 复杂度还要额外再加一阶, 可以采用**(2)预处理辅助数组**方式;

两个辅助数组: 

right[i] [j],   i, j点右侧有几个连续的1

down[i] [j],  i, j点下方有几个连续的1







 # 经典面试题(二)

## - 数据量猜解法 -

可以由数据规模推出大概需要一个什么复杂度的算法

10的8次方到9次方的数据量可以实现算法通过(Java 3-4s, C和C++ 1-2s)

如果数据量为10^6, O(N^2)一定没戏, 所以至少需要O(NlogN)的解法



处理规模为N的数据, 机器指令条数不超过10的8次方



## 6.种子构造数组

6.给定一个正整数M，请构造出一个长度为M的数组arr，要求

对任意的i、j、k三个位置，如果i<j<k，都有arr[i] + arr[k] != 2*arr[j]

返回构造出的arr

(需要硬记)



假设有[a, b, c]满足条件, 那么可以变换出第a个奇数, 第b个奇数, 第c个奇数也满足条件; 变成偶数同理;

因此, [1,5,3] 可以变成 左侧奇数 + 右侧偶数的组合:  [1, 9, 5, 2, 10, 6] 该新数组一定满足题目要求

因为单从一侧选, 必满足要求; 从两侧选, 相加一定是奇数, 不可能等于中间数字 * 2



对于任意的正整数M, 都可以进行分治  e.g M = 15, 需要长度为8的种子, 长度为8的种子需要长度为4的种子, 长度为4的种子需要长度为2的种子, 长度为2的种子需要长度为1的种子, 长度为1的种子就是数组[ 1 ]



## 7.二叉树路径和三连

7.给定一个二叉树的头节点head，路径的规定有以下三种不同的规定:

1）路径必须是头节点出发，到叶节点为止，返回最大路径和

2）路径可以从任何节点出发，但必须往下走到达任何节点，返回最大路径和

3）路径可以从任何节点出发，到任何节点，返回最大路径和



# Class2

## 1.有序数组中search

1.在行也有序、列也有序的二维数组中，找num，找到返回true，否则false

从右上角开始找, 每次决定向左或者向右

> LeetCode 240, 74 两道题代码一样



## 2.Packing Machine(贪心)

2.有n个打包机器从左到右一字排开，上方有一个自动装置会抓取一批放物品到每个打 包机上，放到每个机器上的这些物品数量有多有少，由于物品数量不相同，需要工人 将每个机器上的物品进行移动从而到达物品数量相等才能打包。每个物品重量太大、 每次只能搬一个物品进行移动，为了省力，只在相邻的机器上移动。请计算在搬动最 小轮数的前提下，使每个机器上的物品数量相等。如果不能使每个机器上的物品相同， 返回-1。 例如[1,0,5]表示有3个机器，每个机器上分别有1、0、5个物品，经过这些轮后: 

第一轮:1  0 <- 5 => 1 1 4 第二轮:1 <- 1 <- 4 => 2 1 3 第三轮:2  1 <- 3 => 2 2 2 

移动了3轮，每个机器上的物品相等，所以返回3

例如[2,2,3]表示有3个机器，每个机器上分别有2、2、3个物品， 这些物品不管怎么移动，都不能使三个机器上物品数量相等，返回-1

> lc 517. Super Washing Machines

**思路:**

(1)如果总物品数 % 总机器数 != 0, 意味着无法均摊, 直接返回-1

先求出每个位置应该最终分配的数量 N = sum / size

在每个[i]位置求解: 达成平衡需要的轮数, **四种情况**:

假设 i 位置左边 a 台机器, 右边 b 台机器: 根据前缀和分别求出 i 位置左边和右边目前的衣服总数量

再计算出左边和右边分别需要处理的衣服数量:  因为我们已经知道最后要剩下的数量是a * N, 所以要处理的数量就可以由总数减去 a * N得到, 四种情况: 

左 > 0, 右 > 0, 至少需要的轮数是 Math.max( |左|, |右| )

左 > 0, 右 < 0, 至少需要的轮数是 Math.max( |左|, |右| )

左 < 0, 右 > 0, 至少需要的轮数是 Math.max( |左|, |右| ) 前三种情况都是两边的abs取max

左 < 0, 右 < 0, 至少需要的轮数是 |左| + |右| , 第四种情况是因为 i 要向两遍扔, 但是一次只能扔一侧, 所以是两边的绝对值相加

贪心: 所有位置中, 最大的瓶颈就是答案



## 3. 左Max - 右Max绝对值最大

3.给定一个数组arr长度为N，你可以把任意长度大于0且小于N的前缀作为左部分，剩下的 作为右部分。

但是每种划分下都有左部分的最大值和右部分的最大值，请返回最大的， 左部分最大值减去右部分最大值的绝对值。



因为左右两侧必须都有值, 所以 N 个数有 N - 1 种分割

思路流程: 找到全局的max值, 0位置和 N-1 位置谁的值小, 就用max减去该位置的值就是答案

单调性: 分割范围增大, max值只会变大, 不会变小



情况一:  max被划分进左边, 我们需要让右边的max尽可能的小, 才能得到结果最大, 只有单独N-1位置数时右侧的max是最小的

情况二:  max被划分进右边, 同理



# 经典面试题(三)

## 4. Trapping Water(一维)

4.给定一个数组arr，已知其中所有的值都是非负的，将这个数组看作一个容器， 请返回容器能装多少水

比如，arr = {3，1，2，5，2，4}，根据值画出的直方图就是容器形状，该容 器可以装下5格水

再比如，arr = {4，5，1，3，2}，该容器可以装下2格水

> lc 42. Trapping Rain Water

**思路:**

方法一:Brute Force: 到每一个位置i 遍历两侧, 找最大

方法二:数组预处理, 求出前缀最大值数组, 和后缀最大值数组, 这样在遍历过程中就可以知道两侧的最大值, 复杂度O(N)

方法三: 双指针, 省去预处理数组 (类似于题目二packing machine中的单调性)

0和N-1位置不可能有水量, 所以L指针从1开始, R指针从N-2开始, 用两个变量leftMax和rightMax分别记录已经走过位置的最大值

1.当leftMax < rightMax时, 虽然右侧还有没看过的位置, 但右侧的最大值一定不会小于当前的rightMax, 所以左侧的高度就是瓶颈, 可以结算L位置的水量(leftMax - arr[i] )

2.同理, 当leftMax > rightMax时, 右侧的高度是瓶颈, 可以结算右侧的水量

3.当leftMax == rightMax时, 可以一起结算



## 5. Trapping Water(二维)

5.如果给你一个二维数组，每一个值表示这一块地形的高度，

求整块地形能装下多少水。

**思路流程:**

- 小根堆
- 存储Node对象为num, row, col;
- boolean类型二维数组isEnter[][], 检查row, col这个位置是否进过堆;
- 全局max

1.先把四周边缘一圈数字生成对象加入小根堆, 在isEnter中注册(true表示进过, false表示没进过)

1.小根堆弹出堆顶num, 更新全局max

2.根据num的row, col把他上下左右的邻居arr[i][j]全加入堆, 在放的时候, 结算水量,水量累加上max - arr[i] [j] (只有大于0才累加)



minHeap决定了是从矩阵边缘的薄弱处开始依次结算

小根堆最先弹出的值是边缘处最矮的点, 以该值为瓶颈的一片湖, 可以结算水量

**复杂度**最差情况是O(N * M * log(K))    K是小根堆的规模, 可以接近N*M



## 6. 二元组、三元组问题

6.给定一个有序数组arr，给定一个正数aim

1）返回累加和为aim的，所有不同二元组

2）返回累加和为aim的，所有不同三元组

- 二元组

因为有序, 所以可以用双指针, 三种情况:

(1) [L] + [R] < aim; R--

(2) [L] + [R] > aim; L++

(3) [L] + [R] == aim; 可以L++ 或者 R-- , 当L和L-1位置的值不相等 或 R 和 R+1的值不相等, 才收集答案

- 三元组

三元组就是固定一个数字, 然后其他部分求二元组(修改二元组的方法, 指定L和R范围)

但是需要注意去重, 在只有在[i] != [i-1]的时候才收集答案

最优解, 复杂度O(N^2)



## 7.求第k小的数值对

7.长度为N的数组arr，一定可以组成N^2个数值对。

例如arr = [3,1,2]，数值对有(3,3) (3,1) (3,2) (1,3) (1,1) (1,2) (2,3) (2,1) (2,2)，也就是任意两个数都有数值对，而且自己和自己也算数值对。

数值对怎么排序？规定，第一维数据从小到大，第一维数据一样的，第二维数组也从小到大。所以上面的数值对排序的结果为：

(1,1)(1,2)(1,3)(2,1)(2,2)(2,3)(3,1)(3,2)(3,3)

给定一个数组arr，和整数k，返回第k小的数值对。

**流程:**

1.先找到第一个数字是哪个, 记为a

2.遍历数组, 找到小于a的数字有几个, 等于a的数字有N个

3.k - a * 



# 经典面试题(四)

# Class3

## 1.Choose Work

> 826.Most Profit Assigning Work

1.每种工作有难度和报酬，规定如下

class Job {

public int money;// 该工作的报酬

public int hard; // 该工作的难度

}

给定一个Job类型的数组jobarr，表示所有岗位，每个岗位都可以提供任意份工作

选工作的标准是在难度不超过自身能力值的情况下，选择报酬最高的岗位

给定一个int类型的数组arr，表示所有人的能力

返回int类型的数组，表示每个人按照标准选工作后所能获得的最高报酬



思路:

按照hard排序, hard相同的按money排序M;

对于hard相同的, 仅保留money最大的; 而且要保证hard增加, money也是增加的; 把满足条件的工作放入有序表;

对于arr数组, 找小于等于他能力最近的key (floorKey)



## 2.背包问题

2.背包容量为w, 一共有n袋零食, 第i袋零食体积为v[i], 总体积不超过背包容量的情况下，

一共有多少种零食放法？(总体积为0也算一种放法)。

1 <= n <= 30;  0 <= v[i] <= 2 * 10^ 9;  1 <= w <= 2 * 10^ 9



普通背包问题: dp[i] [j]含义: 0 ... i 自由选择, 要求体积必须严格累加成 j 的情况下, 方法数是多少

该问题: 分治, 并指定左右的整合逻辑



## 3.矩阵最小路径和

>  空间压缩技巧

3.给定一个二维数组matrix，其中每个数都是正数，要求从左上到右下

每一步只能向右或者向下，沿途经过的数字要累加起来

最后请返回最小的路径和 (空间压缩技巧)

> lc 64. Minimum Path Sum



## 4.最长公共子序列

>  空间压缩技巧

4.给定两个字符串str1和str2, 求两个字符的最长公共子序列(subsequence) 

> lc 1143.Longest Common Subsequence

> lc 583.Delete Operation for Two Strings
> 可以转换成 求最长子序列

一个样本做行, 一个样本做列的模型, 经验是: 根据某个位置开头或结尾分析



## 5.最长公共子串

>  空间压缩技巧

5.给定两个字符串str1和str2, 求两个字符串的最长公共子串(substring)    

时间复杂度O(M*N), 空间复杂度可以优化成O(1)

**二维表不是最优解;  最优解法是后缀数组, 复杂度可以做到O(M+N)**



脑海中构建的这张表可以用有限几个变量填出来, (宏观调度)从右上角开始, 向左滑, 滑到0,开始向下走, 每到一个点, 走右对角线填值, 即可构建出这张表



## 6.词频最大的前K个字符串

6.给定一个由字符串组成的数组String[] strs，给定一个正数K, 返回词频最大的前K个字符串，假设结果是唯一的

解法1: 小根堆, 容量保持K

解法2: 快排改写, 求第K大的元素

> lc 347. Top K Frequent Elements

> lc 692. Top K Frequent Words





## 7.手写堆Top Record

7.请实现如下结构：

TopRecord{

public TopRecord(int K) : 构造时事先指定好K的大小，构造后就固定不变了

public void add(String str) :  向该结构中加入一个字符串，可以重复加入

public List<String> top() : 返回之前加入的所有字符串中，词频最大的K个

}

要求:

add方法，复杂度O(log K);

top方法，复杂度O(K)

可重复加入 ==> 词频变化 ==> 需要自动调整堆 ==> 手写堆





# 经典面试题(五)

# Class4

## 1.打印目录结构

1.给你一个字符串类型的数组arr，譬如: String[] arr = { "b\st", "d\", "a\d\e", "a\b\c" }; 

把这些路径中蕴含的目录结构给打印出来，子目录直接列在父目录下面，并比父目录向右进两格，就像这样:

a

 b

  c

 d

  e

b

 st

d

同一级的需要按字母顺序排列不能乱。

**流程:**

构造Trie, 深度优先遍历打印





## 2.BST转双向链表

2.双向链表节点结构和二叉树节点结构是一样的，如果你把last认为是left，next认为是next的话。

给定一个搜索二叉树的头节点head，请转化成一条有序的双向链表，并返回链表的头节点。

- 中序遍历收集节点, 取出来依次连接



- 二叉树递归套路 -  

class Info{

​	Node start;

​	Node end;

}







## 3.二叉树先序、中序数组，求后序数组

3.已知一棵二叉树中没有重复节点，并且给定了这棵树的中序遍历数组和先序遍历 数组，返回后序遍历数组。

比如给定:

int[] pre = { 1, 2, 4, 5, 3, 6, 7 };

int[] in = { 4, 2, 5, 1, 6, 3, 7 }; 返回:

{4,5,2,6,7,3,1}





## 4. LIS

4.最长递增子序列问题的O(N*logN)的解法 (LIS)    必须严格递增

> lc 300. Longest Increasing Subsequence

解法1: O(N^2^)

一维dp   dp[i] => 子序列必须以i位置结尾, 形成的最长递增子序列是多少

到了每个i位置, 往前看一边所有数字, 根据dp的值进行叠加

解法2: O(N*logN)

dp[i]含义维持不变

end[i]: 长度为i+1结尾的最长递增子序列的最小结尾  e.g. end[0] = 3表示, 长度为1的最长递增子序列的最小结尾是3





## 5. 俄罗斯套娃问题(LIS的应用)

5.每个信封都有长和宽两个维度的数据，A信封如果想套在B信封里面，A信封必须在长和宽上都小于B信封才行。

如果给你一批信封，返回最大的嵌套层数  (最长递增子序列LIS的应用)

> lc 354. Russian Doll Envelopes

数组排序, 长度按递增排, 长度相同的, 高度按递减排

[7,6] [10,5] [1,6] [1,8] [3,7] [3,10] [7,9] [8,8] [10,9]

[1,8] [1,6] [3,10] [3,7] [7,9] [7,6] [8,8] [10,9] [10,5]

[8, 6, 10, 7, 9, 6, 8, 9, 5] 求最长递增子序列

在这个数组中, 排在我前面的, 长度一定 <= 我; 如果长度相等的话, 我把高度大于我的排到了我前面, 所以在求LIS的过程不会受到干扰



### LIS附加题

> 视频讲解: 高频全讲(二十)

题目1:

一个数组, 只能做2种操作, 把一个数字往前放, 或者往后放, 需要至少几次操作, 可以让整个数组变成不降序



题目2:

![](https://raw.githubusercontent.com/haoboliu66/PicBed/master/img/20210607181252.png)

数组中数字代表人的身高, 想让数组中的数字形成先单调上升, 后单调下降的趋势, 问一个数组中最多能有多少个数人满足这个要求?

以每个 i 位置计算, 从左往右求LIS, 再从右往左求LIS. 看看哪个 i 位置两个的LIS长度累加和 - 1 最大, 就是答案



加强版LIS

> 673.Number of Longest Increasing Subsequence

https://src.main.java.leetcode.com/problems/number-of-longest-increasing-subsequence/





## 6. 子数组最大累加和

6.给定一个数组arr，返回子数组的最大累加和

> lc 53. Maximum Subarray



## 7.子矩阵最大累加和

7.给定一个int矩阵，返回子矩阵的最大累加和





# 经典面试题(六)

# Class5

## 1.  字符串编辑cost

1.给定两个字符串str1和str2，再给定三个整数ic、dc和rc，分别代表插入、删 除和替换一个字符的代价，返回将str1编辑成str2的最小代价。

【举例】

str1="abc"，str2="adc"，ic=5，dc=3，rc=2 从"abc"编辑成"adc"，把'b'替换成'd'是代价最小的，所以返回2 

str1="abc"，str2="adc"，ic=5，dc=3，rc=100 从"abc"编辑成"adc"，先删除'b'，然后插入'd'是代价最小的，所以返回8

str1="abc"，str2="abc"，ic=5，dc=3，rc=2 不用编辑了，本来就是一样的字符串，所以返回0



## 2. 字符串编辑距离

2.给定两个字符串s1和s2，问s2最少删除多少字符可以成为s1的子串？

比如 s1 = "abcde"，s2 = "axbc" 返回1。s2删掉'x'就是s1的子串了。





## 3. 求完全二叉树节点的个数

3.求完全二叉树节点的个数, 要求时间复杂度低于O(N)

- 计算总level -> 一直向左子树走

- 根据右树的最左节点延伸到的层数, 进行递归计算





# 经典面试题(七)

## 4.LRU

4.LRU内存替换算法的实现

> lc 146. LRU Cache



## 5. Word Ladder

5.给定两个字符串，记为start和to，再给定一个字符串列表list，list中一定包含to list中没有重复字符串，所有的字符串都是小写的。

规定: start每次只能改变一个字符，最终的目标是彻底变成to，但是每次变成的新字符串必须在list 中存在。

请返回所有最短的变换路径。

【举例】 

start="abc",end="cab",list={"cab","acc","cbc","ccc","cac","cbb","aab","abb"}

转换路径的方法有很多种，但所有最短的转换路径如下: 

abc -> abb -> aab -> cab

abc -> abb -> cbb -> cab

abc -> cbc -> cac -> cab

abc -> cbc -> cbb -> cab





## 6. Max Revenue - 图

输入参数：活动之间的依赖图，每个活动花费的天数、收益

规定：如果选择参加某个活动，必须要根据依赖图做到最后一个活动

再给定一个int[] arr，表示主播们依次拥有的天数，

返回一个int[] ans，表示主播们能获得的最大收益





# Class6

## 1.最大子数组异或和

1.一个数组的异或和是指数组中所有的数异或在一起的结果

给定一个数组arr，求最大子数组异或和。

> 421.Maximum XOR of Two Numbers in an Array  简单版本, 与此题不同但类似

> 1707.Maximum XOR With an Element From Array  困难版本

根据子数组累加和的思想传统: 我们要逐个计算以 i 位置结尾的子数组的累加和

假设 0...17的累加和是1000, 往前找最小的前缀和, 假设是-7, 那么最大的子数组累加和就是1007

但是由于异或计算的性质: operand的大小不能决定计算结果的大小 => operand的大小和计算结果没有单调性

所以如何才能找到一个合适的 前缀异或和 , 让两者 异或 之后结果最大呢？

假设 0...4的异或和是0110, 我们想要寻找的一个 前缀异或和 最好是1001, 这样异或起来的结果才能最大, 由此可以把 前缀异或和 的二进制形式 组织成Trie  => 即便没有完全符合要求的1001, 优先把 异或结果的高位 变1, 也可以得到最优结果 



对于符号位的贪心策略是不同的: 需要找到相同的符号位



所以前缀树需要实现2个功能:

- 添加一个数字, 把数字的二进制形式加入前缀树
- 给定一个数字num, 在前缀树上找到跟num进行异或计算能得到的最大值

 

### 1_1 最多的XOR为0的子数组

数组中所有数都异或起来的结果，叫做异或和

给定一个数组arr，可以任意切分成若干个不相交的子数组, 其中一定存在一种最优方案，使得切出异或和为0的子数组最多

返回这个最多数量

> 假设答案法





## 2.逻辑运算符组合

2.给定一个只由 0(假)、1(真)、&(逻辑与)、|(逻辑或)和^(异或)五种字符组成 的字符串express，再给定一个布尔值 desired。返回express能有多少种组合 方式，可以达到desired的结果。

【举例】

express="1^0|0|1"，desired=false

只有 1^((0|0)|1)和 1^(0|(0|1))的组合可以得到 false，返回 2。 express="1"，desired=false

无组合则可以得到false，返回0





## 3. Jump Game

3.给出一组正整数arr，你从第0个数向最后一个数，

每个数的值表示你从这个位置可以向右跳跃的最大长度

计算如何以最少的跳跃次数跳到最后一个数。

> 45.Jump Game II





## 经典面试题(九)

## 4.两数组累加和最大前K个

4.给定两个有序数组arr1和arr2，再给定一个正数K

求两个数累加和最大的前K个，两个数必须分别来自arr1和arr2



大根堆 + 二维表



## 5. 切分子数组累加和相等

5.给定一个正数数组arr，返回该数组能不能分成4个部分，并且每个部分的累加和相等，切分位置的数不要。

例如:

arr=[3, 2, 4, 1, 4, 9, 5, 10, 1, 2, 2] 返回true

三个切割点下标为2, 5, 7. 切出的四个子数组为[3,2], [1,4], [5], [1,2,2]，

累加和都是5



## 6. 字符串交错组成

6.给定三个字符串str1、str2和aim，如果aim包含且仅包含来自str1和str2的所有字符， 而且在aim中属于str1的字符之间保持原来在str1中的顺序，属于str2的字符之间保持 原来在str2中的顺序，那么称aim是str1和str2的交错组成。实现一个函数，判断aim是 否是str1和str2交错组成

【举例】 str1="AB"，str2="12"。那么"AB12"、"A1B2"、"A12B"、"1A2B"和"1AB2"等都是 str1 和 str2 的 交错组成





# Class7

## 1.Subarray Sort

1.给定一个无序数组arr，如果只能再一个子数组上排序

返回如果让arr整体有序，需要排序的最短子数组长度



流程:

记录左边部分的max, 

如果左边max > cur, 画×; 否则画√, 记录最右一个画×的位置

记录右边部分min, 

如果右边min < cur, 画×; 否则画√, 记录最左一个画×的位置



从左往右看, 找到第一个位置i, 右侧的所有值都大于前面的max, 说明这个i位置的右侧都是有序的; 如果排序的话, [i]位置往后的值都不需要为左侧的max让位置; 同理:

从右往左看, 找到第一个位置j, 左侧的所有值都小于右边的min, 说明这个j位置的左侧都是有序的; 如果排序的话, [j]位置往左的值都不需要为右侧的min让位置



1 2 6 5 4 3 8 9





## 2.最小不可组成和

2.给定一个正数数组 arr，其中所有的值都为整数，以下是最小不可组成和的概念:

把 arr 每个子集内的所有元素加起来会出现很多值，其中最小的记为 min，最大的记为max 在区间[min,max]上，如果有数不可以被arr某一个子集相加得到，那么其中最小的那个数是arr 的最小不可组成和 在区间[min,max]上，如果所有的数都可以被arr的某一个子集相加得到，那么max+1是arr的最 小不可组成和

请写函数返回正数数组 arr 的最小不可组成和。

【举例】

arr=[3,2,5]。子集{2}相加产生 2 为 min，子集{3,2,5}相加产生 10 为 max。在区间[2,10] 上，4、 6 和 9 不能被任何子集相加得到，其中 4 是 arr 的最小不可组成和。 arr=[1,2,4]。子集{1}相加产生 1 为 min，子集{1,2,4}相加产生 7 为 max。在区间[1,7]上， 任何数都可以被子集相加得到，所以 8 是 arr 的最小不可组成和。



思路: 找到min和max, 然后转换成在min-max范围上的背包问题



【进阶】

如果已知正数数组 arr 中肯定有 1 这个数，是否能更快地得到最小不可组成和?

思路: 数组先排序, 用一个变量range, 记录累加和可以得到从 1...range 范围内的数字



## 3. MinPatches

> 330.Patching Array https://src.main.java.leetcode.com/problems/patching-array/

3.给定一个有序的正数数组arr和一个正数range，如果可以自由选择arr中的数字，想累加得 到 1~range 范围上所有的数，返回arr最少还缺几个数。

【举例】

arr = {1,2,3,7}，range = 15

想累加得到 1~15 范围上所有的数，arr 还缺 14 这个数，所以返回1 

arr = {1,5,7}，range = 15

想累加得到 1~15 范围上所有的数，arr 还缺 2 和 4，所以返回2



[1-60]   61



思路: 

先假设如果我们没有数组, 如何得到最少的patch

所有要补充的的数字序列是 [1, 2, 4, 8, 16, 32.........]



那么如果这时有数组, 我们要保证make the most of 数组中的值, 所以对于每一个数组中的值n, 我们要保证完成1~n-1这个小目标, 这时就可以最经济地利用这个数字n



类似于上一题, 对于i位置的a, 我们要实现 1 -> a-1, 如果前面无法实现, 就添数字

range从0开始, 因为第一个满足的范围必须要是[ 1, 1 ]才可以, 所以一开始要先满足[ 0, 0 ]的范围

如果第一个数字不是1, 那么一定需要补上1, 然后再往下进行



=> 跳到了题目六



# 经典面试题(十)

## 4. Largest-Component-Size-by-Common-Factor

https://src.main.java.leetcode.com/problems/largest-component-size-by-common-factor/

4.一个数组中，如果两个数的最小公共因子有大于大1，则认为这两个数之间有通路, 返回数组中，有多少个独立的域

> UnionFind

普通想法: O(N^2^)的调度, 每2个数字都求gcd, 然后union



如何判断一个数字X是不是质数

> 假如一个数N是合数，它有一个约数a, a × b = N
> 则a、b两个数中必有一个大于或等于根号N，一个小于或等于根号N。
> 因此，只要小于或等于根号N的数（1除外）不能整除N，则N一定是素数。



当 x % 2 != 0, x/2往后不用验了

当 x % 3 != 0, x/3往后不用验了

当 x % 4 != 0, x/4往后不用验了

如果 x % 根号x !=0, 根号x往后不用验了



找到x所有的因子, 每次算出一对, 查到根号x即可

如果2个数字有相同的因子, 就进行union;

e.g   20的因子有 1, 20   2, 10   4, 5 一共6个因子, 当其他数字有这些因子的时候, 就和20进行union (因子1不进行计算)

复杂度变成 O(n * 根号value)



所以看菜下饭: 看哪个复杂度能过





## 5. 删除多余字符,字典序最小

https://src.main.java.leetcode.com/problems/remove-duplicate-letters/

5.给定一个全是小写字母的字符串str，删除多余字符，使得每种字符只保留一个，并让 最终结果字符串的字典序最小

【举例】

str = "acbc"，删掉第一个'c'，得到"abc"，是所有结果字符串中字典序最小的。

str = "dbcacbca"，删掉第一个'b'、第一个'c'、第二个'c'、第二个'a'，得到"dabc"， 是所有结 果字符串中字典序最小的。



思路:

如何删除字符和如何挑选每一类字符各1个是等价的



第一个字符一定在划线的范围内挑选, 而且要挑选字典序最小的字符

- 如果再往后一个字符, 把b划进范围, 意味着可以选b, 那么后面就没有d了

![](https://raw.githubusercontent.com/haoboliu66/PicBed/master/img/20210506173619.png)

第一个字符要选择字典序最小的字符, 然后这个字符左侧的部分, 在字符右侧删除所有该字符, 然后重复上述的词频统计, 划线, 选字符的过程

![](https://raw.githubusercontent.com/haoboliu66/PicBed/master/img/20210506174349.png)



复杂度分析:

如果字符串有k种字符, 每趟过一遍选出一个字符, 要挑k次, 复杂度O(k * N)





## 6. Longest Substring Without Repeating Characters

6.在一个字符串中找到没有重复字符子串中最长的长度。

例如:

abcabcbb没有重复字符的最长子串是abc，长度为3 

bbbbb，答案是b，长度为1

pwwkew，答案是wke，长度是3

要求:答案必须是子串，"pwke" 是一个子字符序列但不是一个子字符串。

> lc 3. Longest Substring Without Repeating Characters



2个限制条件

- 当前字符可以延伸的最大长度
- 前一个字符pre可以延伸的最大长度



## 7. Max Points On A Line

7.给定两个数组arrx和arry，长度都为N。代表二维平面上有N个点，第i个点的x 坐标和y坐标分别为arrx[i]和arry[i]，返回求一条直线最多能穿过多少个点?

> lc 149. Max Points on a Line

考虑的种类:  共点,  共x, 共y, 相同斜率

gcd(m, n) => n == 0? m : gcd(n, m % n);





跳到了怪兽Money问题



# Class8

## 1.一种消息接收并打印的结构设计

1.已知一个消息流会不断地吐出整数 1~N，但不一定按照顺序吐出。如果上次打印的数为 i， 那么当 i+1 出现时，请打印 i+1 及其之后接收过的并且连续的所有数，直到 1~N 全部接收 并打印完，请设计这种接收并打印的结构。

初始时默认i ==0



headMap + tailMap + 等待的编号waitPoint

一个Map + waitPoint也可解



## 2.普通币+纪念币组合

2.现有n1+n2种面值的硬币，其中前n1种为普通币，可以取任意枚，后n2种为纪念币， 每种最多只能取一枚，每种硬币有一个面值，问能用多少种方法拼出m的面值?



两种面值硬币分别进行DP, 然后进行整合 => 类似分治 + 整合



## 3.数位DP

3.给定一个正数N，表示你在纸上写下1~N所有的数字

返回在书写的过程中，一共写下了多少个1





# 经典面试题(十)

## 4.怪兽Money问题

4.int[] d，d[i]：i号怪兽的能力

int[] p，p[i]：i号怪兽要求的钱

开始时你的能力是0，你的目标是从0号怪兽开始，通过所有的怪兽。

如果你当前的能力，小于i号怪兽的能力，你必须付出p[i]的钱，贿赂这个怪兽，然后怪兽就会加入你，他的能力直接累加到你的能力上；如果你当前的能力，大于等于i号怪兽的能力，你可以选择直接通过，你的能力并不会下降，你也可以选择贿赂这个怪兽，然后怪兽就会加入你，他的能力直接累加到你的能力上。

返回通过所有的怪兽，需要花的最小钱数。





# 经典面试题(十二)

## 5.可整合数组

5.先给出可整合数组的定义:如果一个数组在排序之后，每相邻两个数差的绝对值 都为 1， 则该数组为可整合数组。例如，[5,3,4,6,2]排序之后为[2,3,4,5,6]， 符合每相邻两个数差的绝对值 都为 1，所以这个数组为可整合数组。 给定一个整型数组 arr，请返回其中最大可整合子数组的长度。例如， [5,5,3,2,6,4,3]的最大 可整合子数组为[5,3,2,6,4]，所以返回 5。



改写可整合数组定义: 

- 数组中无重复值

- max - min == 个数 - 1

两个循环枚举子数组, 然后进行判断即可



讲了卡特兰数, 然后跳转至 ===> Camp4 第2节 股票问题三连





## 6. Minimum Insertion Steps to Make a String Palindrome

6.给定一个字符串，如果可以在任意位置添加字符，最少添加几个能让字符串整体都是回文串

> lc.1312

范围尝试



# 卡特兰数 (Catalan Number)

![](https://raw.githubusercontent.com/haoboliu66/PicBed/master/img/20210403193533.png)



![image-20210403193559213](/Users/liuhaobo/Library/Application Support/typora-user-images/image-20210403193559213.png)



结论: 在数学上, 所有的整数和所有的偶数 数量一样多

原因: 每个整数乘2都是一个偶数; 每个偶数除2, 都是一个整数, 因此整数和偶数建立了双向的一对一映射关系, 所以数量一样多

> The Infinite Hotel

两个集合等势(equinumerous) => 两个集合的数量一样多



题目:

1.给N个左括号和N个右括号, 问有多少种合法匹配的情况？

合法的隐藏含义: 每一个位置向前看, 任意的前缀中, 左括号数量 >= 右括号数量



如果我们可以找到不合法的情况数, 用总的情况 减去 不合法情况, 就得到了合法的情况

总数量: C(2n, n)  => 2n个位置, 选出n个位置放左括号

任何一个不合法情况, 一定存在一个最初的前缀, 使得 右括号数量 > 左括号数量



> 如果能在2个集合A,B中, 找到A->B的一对一映射关系和B->A的一对一映射关系, 就可以确定A集合和B集合的规模相等

根据集合映射关系, 进行不合法的情况的推断: 在某个不合法前缀中, 当 右括号数量 - 左括号数量 = 1, 剩余部分一定有左括号 - 右括号 = 1; 

此时将剩余部分左右反转, 即可得到剩余部分右括号数量 - 左括号数量 = 1, 所以整体 右括号 - 左括号 = 2, 即有 n+1个右括号, n - 1个左括号, 结果是C(2n, n + 1) / C(2n, n - 1)





2.一个栈, 给N个数字, 依次入栈出栈, 给出出入栈的所有可能组合

思路: 入栈的数量要不小于出栈的数量



3.有N个0, N个1, 自由组合, 任何前缀上都要保证0个数量不比1少, 问达标结果有多少个?

思路: 类似第1题(左右括号匹配问题)



卡特兰数: 在任意前缀上, A操作的次数一定 >= B操作的次数





4.有N个二叉树节点, 问有多少种不同的二叉树构成方式?

命中第一个公式

![](https://raw.githubusercontent.com/haoboliu66/PicBed/master/img/20210403222340.png)

![](https://raw.githubusercontent.com/haoboliu66/PicBed/master/img/20210403222402.png)



Takeaway:

1. 公式1、公式2
2. 左右括号模型
3. 通项公式 => 符合公式1
