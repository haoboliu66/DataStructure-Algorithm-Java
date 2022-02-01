# Class1

题目1

绳子覆盖问题





题目2

给定一个文件目录的路径，写一个函数统计这个目录下所有的文件数量并返回, 隐藏文件也算，但是文件夹不算

DFS或BFS



题目3

给定一个非负整数num，

如何不用循环语句，

返回>=num，并且离num最近的，2的某次方



HashMap源码

```java
   /**
     * Returns a power of two size for the given target capacity.
     */
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
```





题目4

一个数组中只有两种字符'G'和'B’，

可以所有的G都放在左侧，所有的B都放在右侧

或者所有的B都放在左侧，所有的G都放在右侧

但是只能在相邻字符之间进行交换操作，两种方案选其一, 返回至少需要交换几次





题目5

给定一个二维数组matrix，

你可以从任何位置出发，走向上下左右四个方向

返回能走出来的最长的递增链长度





题目6

给定两个非负数组x和hp，长度都是N，再给定一个正数range

x有序，x[i]表示i号怪兽在x轴上的位置；hp[i]表示i号怪兽的血量

range表示法师如果站在x位置，用AOE技能打到的范围是：

[x-range,x+range]，被打到的每只怪兽损失1点血量

返回要把所有怪兽血量清空，至少需要释放多少次AOE技能？



线段树





题目7

给定一个数组arr，你可以在每个数字之前决定+或者-

但是必须所有数字都参与

再给定一个数target，请问最后算出target的方法数是多少？



优化点1: 把所有负数变成正数, 对结果不影响

优化点2: 如果target > 数组累加和sum, 结果一定是0

优化点3: taget和sum奇偶性不一样, 结果一定是0

优化点4: 转换成背包问题, 二维表规模小一半

优化点5: 二维表的空间压缩







# Class2

## 1.Most Profit Assigning Work

题目1

给定数组hard和money，长度都为N

hard[i]表示i号的难度， money[i]表示i号工作的收入

给定数组ability，长度都为M，ability[j]表示j号人的能力

每一号工作，都可以提供无数的岗位，难度和收入都一样

但是人的能力必须>=这份工作的难度，才能上班

返回一个长度为M的数组ans，ans[j]表示j号人能获得的最好收入



## 2.可乐问题

题目2

贩卖机只支持硬币支付，且收退都只支持10 ，50，100三种面额

一次购买只能出一瓶可乐，且投钱和找零都遵循优先使用大钱的原则

需要购买的可乐数量是m，

其中手头拥有的10、50、100的数量分别为a、b、c

可乐的价格是x(x是10的倍数)

请计算出需要投入硬币次数？



 ![](https://raw.githubusercontent.com/haoboliu66/PicBed/master/img/20210501225515.png)



数据量 可乐的量 10^18^





coding技巧: a / x 向上取整 => (a + (x - 1) ) / x





## 3. 打印流结构设计

题目3

已知一个消息流会不断地吐出整数 1~N，

但不一定按照顺序依次吐出

如果上次打印的序号为i， 那么当i+1出现时

请打印 i+1 及其之后接收过的并且连续的所有数

直到1~N全部接收并打印完

请设计这种接收并打印的结构





## 4.司机分配

> 1029.Two City Scheduling
>
> https://src.main.java.leetcode.com/problems/two-city-scheduling/

题目4

现有司机N*2人，调度中心会将所有司机平分给A、B两个区域

第 i 个司机去A可得收入为income[i]，

第 i 个司机去B可得收入为income[i]，

返回所有调度方案中能使所有司机总收入最高的方案，是多少钱



暴力尝试递归可解



最优解贪心: 全部司机去A, 计算出每个司机的收益差, 然后看看哪5个司机改换门庭可以获得最多的收益(排序后累加收益差)





题目5

Leetcode 自由之路

https://src.main.java.leetcode.com/problems/freedom-trail/



## 6.数组部分排序

题目6

给定一个数组arr，只能对arr中的一个子数组排序，

但是想让arr整体都有序

返回满足这一设定的子数组中，最短的是多长



从左到右

从右到左

两次遍历找到2个下标, 此区间即为需要排序的最短范围



# Class3

## 1.最长无重复子串 (lc3)

2个指标一直维持

- 当前字符的上一次出现
- 前一个字符形成的瓶颈



## 2. 字符串摘要

2.只由小写字母（a~z）组成的一批字符串, 都放在字符类型的数组String[] arr中

如果其中某两个字符串所含有的字符种类完全一样, 就将两个字符串算作一类

比如：baacbba和bac就算作一类;  返回arr中有多少类？



因为字符的词频不影响结果, 可以使用一个int 32bit 标识一个数字



## 3. largest-1-bordered-square

给定一个只有0和1组成的二维数组

返回边框全是1的最大正方形面积

https://src.main.java.leetcode.com/problems/largest-1-bordered-square/



## 4. 比赛局数

给定一个数组arr，代表每个人的能力值。再给定一个非负数k

如果两个人能力差值正好为k，那么可以凑在一起比赛

一局比赛只有两个人

返回最多可以同时有多少场比赛



上课讲解: 排序 + 窗口

贪心: 先满足能力值小的凑比赛, 可以得到最优解 e.g 1 3 5 7 , k = 2



## 5. boats saving people

给定一个正数数组arr，代表若干人的体重

再给定一个正数limit，表示所有船共同拥有的载重量

每艘船最多坐两人，且不能超过载重

想让所有的人同时过河，并且用最好的分配方法让船尽量少

返回最少的船数



## 6. closest-subsequence-sum

最接近sum的子序列累加和问题

https://src.main.java.leetcode.com/problems/closest-subsequence-sum/



## 7. freedom-trail

https://src.main.java.leetcode.com/problems/freedom-trail/



## 8. 距离K的所有节点

863.All Nodes Distance K in Binary Tree https://src.main.java.leetcode.com/problems/all-nodes-distance-k-in-binary-tree/

给定三个参数：

二叉树的头节点head，树上某个节点target，正数K

从target开始，可以向上走或者向下走

返回与target的距离是K的所有节点



# Class4

## 1.数组范围查找

1.数组为{3, 2, 2, 3, 1}，查询为(0, 3, 2)

意思是在数组里下标0~3这个范围上，有几个2？答案返回2。

假设给你一个数组arr，

对这个数组的查询非常频繁，都给出来

请返回所有查询的结果



假设:

1的下标: 2,5,6

2的下标: 1,4,8

3的下标: 0,3,7

在有序的下标范围内进行二分查找



## 2. 子数组最大累加和

2.返回一个数组中，子数组最大累加和

Done



## 3. 子矩阵最大累加和

3.返回一个二维数组中, 子矩阵最大累加和





## 4. 子序列最大累加和

4.返回一个数组中，选择的数字不能相邻的情况下, 最大子序列累加和

Done





## 5. 分配Candy

5.原问题：https://src.main.java.leetcode.com/problems/candy/

进阶问题：在原问题的基础上，增加一个原则：

相邻的孩子间如果分数一样，分的糖果数必须一样

返回至少需要分多少糖





## 6.造种子数组

6.生成长度为size的达标数组，什么叫达标？

达标：对于任意的 i<k<j，满足 [i] + [j] != [k] * 2

给定一个正数size，返回长度为size的达标数组



## 7.字符串交错组成

https://src.main.java.leetcode.com/problems/interleaving-string/



dp [i] [j] => str1拿出 i 长度, str2拿出 j 长度, 能否组成str3的 i + j 长度

长度 -> 字符串的前缀长度



## 8.大楼轮廓线问题

https://src.main.java.leetcode.com/problems/the-skyline-problem/





# Class5

## 1. 根据先序遍历建树

1.已知一棵搜索二叉树上没有重复值的节点，

现在有一个数组arr，是这棵搜索二叉树先序遍历的结果

请根据arr生成整棵树并返回头节点





2.如果一个节点X，它左树结构和右树结构完全一样

那么我们说以X为头的树是相等树

给定一棵二叉树的头节点head

返回head整棵树上有多少棵相等子树





## 3.编辑距离

3.经典中的经典， 编辑距离问题





## 4.编辑距离/删除

4.给定两个字符串s1和s2，问s2最少删除多少字符可以成为s1的子串？

比如 s1 = "abcde"，s2 = "axbc"





# Class6

## 1-3 异或和 + Trie

1.数组中所有数都异或起来的结果，叫做异或和

给定一个数组arr，返回arr的最大子数组异或和





2.数组中所有数都异或起来的结果，叫做异或和

给定一个数组arr，想知道arr中哪两个数的异或结果最大

返回最大的异或结果

https://src.main.java.leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/



3.Leetcode 

https://src.main.java.leetcode.com/problems/maximum-xor-with-an-element-from-array/



## 4. 异或和 + 假设答案

4.数组中所有数都异或起来的结果，叫做异或和; 给定一个数组arr，可以任意切分成若干个不相交的子数组

其中一定存在一种最优方案，使得切出异或和为0的子数组最多

返回这个最多数量



## 5.Nim博弈

给定一个正数数组arr, 先手和后手每次可以选择在一个位置拿走若干值, 值要大于0，但是要小于该处的剩余

谁最先拿空arr，谁赢。根据arr，返回谁赢

> 谁最先把arr拿成[0,0,0,0,0.......], 谁就赢了

如果谁首先面对[0,0,0,0,0,0....]的情况, 谁就输了, 也就是说, 如果最开始数组的异或和不是0, 那么先手一定赢



Done



# Class7

## 1.两个数&最大

1.给定一个非负数组成的数组，长度一定大于1, 想知道数组中哪两个数&的结果最大

返回这个最大结果



遍历数组, 检查第30位, 如果只有1个数字第30位是1, 继续向下查第29位

如果有很多, 假设有20个, 就淘汰其他所有数字, 仅保留这20个, 然后在这20个里继续筛选



## 2.相机最小覆盖问题

https://src.main.java.leetcode.com/problems/binary-tree-cameras/





一定要保证: X节点往下的所有节点都被覆盖的情况, 再往上传递Info, 

X节点没被覆盖, 是可以靠递归上游的父节点补救的, 但是如果有X的某个子节点没被覆盖, 再往上传, 后续就无法补救了



- 普通二叉树递归套路 => 没有优化, 列举所有可能性

- 贪心进行优化



## 3.Max Gap

桶思维



## 4.Word Break







# Class8

## 1.Basic Calculator

从不带括号的计算(一个栈解决)  ===> 推演到带括号的计算(递归)





## 2.Container with most water

双指针, 谁小结算谁



## 3.Word Search

设定1: 可以走重复路



设定2: 不可以走重复路



## 4.SnakeGame







# Class9

## 1. Light Problem

给定一个数组arr，长度为N，arr中的值不是0就是1。arr[i]表示第i栈灯的状态，0代表灭灯，1代表亮灯

每一栈灯都有开关，但是按下i号灯的开关，会同时改变i-1、i、i+1栈灯的状态

问题一：如果N栈灯排成一条直线,请问最少按下多少次开关？

i为中间位置时，i号灯的开关能影响i-1、i和i+1

0号灯的开关只能影响0和1位置的灯

N-1号灯的开关只能影响N-2和N-1位置的灯



问题二：如果N栈灯排成一个圈,请问最少按下多少次开关,能让灯都亮起来

i为中间位置时，i号灯的开关能影响i-1、i和i+1

0号灯的开关能影响N-1、0和1位置的灯

N-1号灯的开关能影响N-2、N-1和0位置的灯



## 2. remove-invalid-parentheses

https://src.main.java.leetcode.com/problems/remove-invalid-parentheses/





## 3. LIS

https://src.main.java.leetcode.com/problems/longest-increasing-subsequence





## 4. russian-doll-envelopes

https://src.main.java.leetcode.com/problems/russian-doll-envelopes/





## 5. step sum

定义何为step sum？

比如680，680 + 68 + 6 = 754，680的step sum叫754

给定一个正数num，判断它是不是某个数的step sum



单调性 + 二分



# Class10

## 1. JumpGame

题目1

https://src.main.java.leetcode.com/problems/jump-game-ii/

题目2

## 2. top-k-frequent-words-ii

https://www.lintcode.com/problem/top-k-frequent-words-ii/

## 3. k-inverse-pairs-array

题目3

https://src.main.java.leetcode.com/problems/k-inverse-pairs-array/

## 4. convert-BST-to-sorted-doubly-linked-list

题目4

https://src.main.java.leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/

## 5. boolean-evaluation

题目5

https://src.main.java.leetcode-cn.com/problems/boolean-evaluation-lcci/

# Class11

## 根据dp表回溯收集答案

技巧: 动态规划根据dp表 收集答案

1.Minimum Insertion Steps to Make a String Palindrome



2.Palindrom Partition

一个字符串至少要切几刀能让切出来的子串都是回文串



# Class1

## 1. permutation-in-string

给定长度为m的字符串aim，以及一个长度为n的字符串str

问能否在str中找到一个长度为m的连续子串，

使得这个子串刚好由aim的m个字符组成，顺序无所谓，

返回任意满足条件的一个子串的起始位置，未找到返回-1

https://src.main.java.leetcode.com/problems/permutation-in-string/



窗口 + 哈希表



## 2. Median of two sorted array

https://src.main.java.leetcode.com/problems/median-of-two-sorted-arrays/

进阶问题：

在两个都有序的数组中找整体第K小的数

可以做到O(log(Min(M,N)))



1.两个等长数组 求二者的中位数    e.g. 两个长度为5的数组, 求第5小的数字



## 3. regular-expression-matching

https://src.main.java.leetcode.com/problems/regular-expression-matching/





## 4. longest-consecutive-sequence

https://src.main.java.leetcode.com/problems/longest-consecutive-sequence/







# Class13



## 1. Google面试题





## 2. scramble-string

https://src.main.java.leetcode.com/problems/scramble-string/



4个参数简化成3个参数



## 3. super-washing-machines

https://src.main.java.leetcode.com/problems/super-washing-machines/

贪心, 以每个位置进行计算, 然后找到所有位置中最大的瓶颈



## 4. bricks-falling-when-hit

https://src.main.java.leetcode.com/problems/bricks-falling-when-hit/



矩阵 (i, j) => 转化成某个编号, 使用一维数组表示这个矩阵







中国古代的历史故事“田忌赛马”是为大家所熟知的。话说齐王和田忌又要赛马了，他们各派出N匹马，每场比赛，输的一方将要给赢的一方200两黄金，如果是平局的话，双方都不必拿出钱。现在每匹马的速度值是固定而且已知的，而齐王出马也不管田忌的出马顺序。请问田忌该如何安排自己的马去对抗齐王的马，才能赢取最多的钱？







# Class14

## 1.Longest Valid Parentheses

给定一个只由左括号和右括号的字符串, 返回最长的有效括号子串的长度



## 2.子数组累加和小于等于K

请返回arr中，求个子数组的累加和，是<=K的并且是最大的, 返回这个最大的累加和



## 4. CBT节点个数

给定一个棵完全二叉树，返回这棵树的节点个数，

要求时间复杂度小于O(树的节点数)



## 6. first missing positive

https://src.main.java.leetcode.com/problems/first-missing-positive/

字节的问法: 缺失的大于K的最小正整数



如果数组上有1, 要放在0位置

如果有2, 放在1位置

以此类推



设置有效区, 有效区内每个位置i 放的值都是 i + 1

垃圾区, 废弃的值

垃圾区的位置R表示: 我预期收集到的有效范围之后的下一个数字R+1, 比如预期收集到1-6, 那么R就是7



## 5. recover-binary-search-tree

https://src.main.java.leetcode.com/problems/recover-binary-search-tree/



c4 - class7



## 3. 最大BST拓扑结构

从二叉树的某个节点x开始，往下子节点都要的，叫子树；

在二叉树上只要能连起来的任何结构，叫子拓扑结构；

返回二叉树上满足搜索二叉树性质的、最大子拓扑结构的节点数



定义一个概念: 拓扑贡献记录

针对某一个头的贡献值



然后把这些记录不断向父节点迁移





![](https://raw.githubusercontent.com/haoboliu66/PicBed/master/img/20210612122328.png)



![](https://raw.githubusercontent.com/haoboliu66/PicBed/master/img/20210612122625.png)







# Class15 - 股票问题1-6

## 1. 一次交易

https://src.main.java.leetcode.com/problems/best-time-to-buy-and-sell-stock/



## 2. 无限次交易

https://src.main.java.leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/

## 3. 两次交易

https://src.main.java.leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/

## 4. k次交易

https://src.main.java.leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/



## 5. 有cooldown

https://src.main.java.leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/



## 6. 有 transaction fee

https://src.main.java.leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/







# Class16

## 1. 背包 + 分治

题目1

给定一个有正、有负、有0的数组arr，

给定一个整数k，

返回arr的子集是否能累加出k

1）正常怎么做？   背包 暴力递归 -> 记忆化搜索 -> dp (dp的设计需要进行转化, 因为累加和不一定是从0开始)

2）如果arr中的数值很大，但是arr的长度不大，怎么做？ -> 分治 再 整合





## 2. 背包 + patch

题目2 - 最小不可组成和

给定一个正数数组arr, 返回arr的子集不能累加出的最小正数

1）正常怎么做？   dp

2）如果arr中肯定有1这个值，怎么做？  打patch问题



## 3. Min Patch

题目3

https://src.main.java.leetcode.com/problems/patching-array/

打patch问题



## 4. 约瑟夫环

题目5

约瑟夫环问题

给定一个链表头节点head，和一个正数m

从头开始，每次数到m就杀死当前节点

然后被杀节点的下一个节点从1开始重新数，

周而复始直到只剩一个节点，返回最后的节点

Leetcode :

https://src.main.java.leetcode-cn.com/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/



杀前 =  (杀后 + m - 1) % i + 1



## 5. Merge Record

题目5





# Class17

## 1. search-a-2d-matrix

题目1

给定一个每一行有序、每一列也有序，整体可能无序的二维数组

再给定一个数num，

返回二维数组中有没有num这个数

https://src.main.java.leetcode.com/problems/search-a-2d-matrix/

右上角开始



## 2. kth-smallest-element-in-a-sorted-matrix

题目2

给定一个每一行有序、每一列也有序，整体可能无序的二维数组

在给定一个正数k，返回二维数组中，最小的第k个数

https://src.main.java.leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/



 按照这个单调性, 满足大小关系, 直接根据单调性 (1)计算出找到了多少个数 (2) 找到离当前目前最近的值

左上角, 右下角的值 二分



## 4. distinct-subsequences

题目4

给定两个字符串S和T, 返回S的所有子序列中, 有多少个子序列的字面值等于T

https://src.main.java.leetcode.com/problems/distinct-subsequences/

样本行列对应

int dp 

0...i 对应 0...j



## 5. distinct-subsequences-ii

题目5

给定一个字符串Str

返回Str的所有子序列中有多少不同的字面值

https://src.main.java.leetcode.com/problems/distinct-subsequences-ii/

解题要点: 列出实际例子, 观看每次出现重复数字情况下, 重复集合出现的规律 

(1, 2, 3) 无重复的情况

all       新

把每次的新加入all



(1, 2, 1) 有重复的情况 

all再加入新的时候 会有重复的集合出现

如果当前字符是x, 那么重复的是上一次的x出现时的all中除了 空集之外的所有以x结尾的集合数量

x ........... x

第一个x: all = 100, 以x结尾的集合数量为70

第二个x: all = 上一次的all + 新 - 70



## 3. palindrome-pairs

题目3

https://src.main.java.leetcode.com/problems/palindrome-pairs/



字符串放到哈希表, 然后看每个字符串, 根据前缀截掉后, 去哈希表里找剩余的逆序; 再根据后缀截掉后, 去哈希表里找剩余的逆序

![](https://raw.githubusercontent.com/haoboliu66/PicBed/master/img/20210619132455.png)



查前缀是否是回文 O(K^2^)



题目扩展:

一个字符串,  判断0....i每个前缀是不是回文

Manacher - 回文半径







# Class 18

## 1. 判断汉诺塔最佳轨迹

> c4 - class8

题目1

给定一个数组arr，长度为N，arr中的值只有1，2，3三种

arr[i] == 1，代表汉诺塔问题中，从上往下第i个圆盘目前在左

arr[i] == 2，代表汉诺塔问题中，从上往下第i个圆盘目前在中

arr[i] == 3，代表汉诺塔问题中，从上往下第i个圆盘目前在右

那么arr整体就代表汉诺塔游戏过程中的一个状况

如果这个状况不是汉诺塔最优解运动过程中的状况，返回-1

如果这个状况是汉诺塔最优解运动过程中的状况，返回它是第几个状况

 

O(N)可以搞定

[ 3 1 1 2 3 1 1 ]



process(int[] arr, int level, int from, int to, int other)

最底层的圆盘 永远不会来到other上





## 2. Shortest Bridge

https://src.main.java.leetcode.com/problems/shortest-bridge/



2个辅助矩阵, 每个矩阵处理一个岛, 每个岛进行广播

岛上的点到自己的距离为1, 把一个岛的坐标都放入一个集合, 然后对这个集合中的点进行bfs的下一层, 距离标为2, 然后再进行下一层, 距离标记为3, 依次标记完所有点



二维坐标 变 一维坐标

 



## 3. 最大路径和

> c4 - class2

左上到右下, 右下再回到左上, 每个数字只能获得一遍, 返回最大路径和

https://www.nowcoder.com/questionTerminal/8ecfe02124674e908b2aae65aad4efdf





## 4. 两个有序数组相加的TopK

https://www.nowcoder.com/practice/7201cacf73e7495aa5f88b223bbbf6d1



二维boolean表变一维Set, 大根堆移除后, Set也可以移除, 省空间





# Class19

续18节

## 5. LRU





## 6. LFU





## 1.  数位DP

给定一个正数N，比如N = 13，在纸上把所有数都列出来如下：

1 2 3 4 5 6 7 8 9 10 11 12 13

可以数出1这个字符出现了6次

给定一个正数N，如果把1~N都列出来，

返回1这个字符出现的多少次



## 2. smallest-range-covering-elements-from-k-lists/

https://src.main.java.leetcode.com/problems/smallest-range-covering-elements-from-k-lists/

有序表, 维持size为K





## 3. 抽ABC卡牌问题

一张扑克有3个属性，每种属性有3种值（A、B、C）

比如"AAA"，第一个属性值A，第二个属性值A，第三个属性值A

比如"BCA"，第一个属性值B，第二个属性值C，第三个属性值A

给定一个字符串类型的数组cards[]，每一个字符串代表一张扑克

从中挑选三张扑克，一个属性达标的条件是：这个属性在三张扑克中全一样，或全不一样

挑选的三张扑克达标的要求是：每种属性都满足上面的条件

比如："ABC"、"CBC"、"BBC"

第一张第一个属性为"A"、第二张第一个属性为"C"、第三张第一个属性为"B"，全不一样

第一张第二个属性为"B"、第二张第二个属性为"B"、第三张第二个属性为"B"，全一样

第一张第三个属性为"C"、第二张第三个属性为"C"、第三张第三个属性为"C"，全一样

每种属性都满足在三张扑克中全一样，或全不一样，所以这三张扑克达标

返回在cards[]中任意挑选三张扑克，达标的方法数





# Class20

## 1. pre和in构建post

如果只给定一个二叉树前序遍历数组pre和中序遍历数组in，

能否不重建树，而直接生成这个二叉树的后序数组并返回

已知二叉树中没有重复值





## 2. Largest-Component-Size-by-Common-Factor

并查集





## 3. 完美洗牌问题







## 4. count-different-palindromic-subsequences

https://src.main.java.leetcode.com/problems/count-different-palindromic-subsequences/

范围尝试: 从L....R能搞出几个回文  dp[ L ] [ R ]







# Class21

## 线段树



树链剖分

给定数组father，大小为N，表示一共有N个节点

father[i] = j 表示点i的父亲是点j， father表示的树一定是一棵树而不是森林

给定数组values，大小为N，values[i]=v表示节点i的权值是v

实现如下4个方法，保证4个方法都很快！

1)让某个子树所有节点值加上v，入参：int head, int v

2)查询某个子树所有节点值的累加和，入参：int head

3)在树上从a到b的整条链上所有加上v，入参：int a, int b, int v

4)查询在树上从a到b的整条链上所有节点值的累加和，入参：int a, int b





# Class22

1.





## 2. 三个不重合子数组累加和

https://src.main.java.leetcode.com/problems/maximum-sum-of-3-non-overlapping-subarrays/



基于 最大子数组累加和 问题

dp[i]  =>  0...i 上的累加和最大的子数组



2个辅助数组, 固定中间的一个数组, 在两头找2个数组



## 3.可见山峰对问题

一个不含有负数的数组可以代表一圈环形山，每个位置的值代表山的高度

比如， {3,1,2,4,5}、{4,5,3,1,2}或{1,2,4,5,3}都代表同样结构的环形山

山峰A和山峰B能够相互看见的条件为: 

1.如果A和B是同一座山，认为不能相互看见

2.如果A和B是不同的山，并且在环中相邻，认为可以相互看见

3.如果A和B是不同的山，并且在环中不相邻，假设两座山高度的最小值为min。

​    1)如果A通过顺时针方向到B的途中没有高度比min大的山峰，认为A和B可以相互看见

​    2)如果A通过逆时针方向到B的途中没有高度比min大的山峰，认为A和B可以相互看见

两个方向只要有一个能看见，就算A和B可以相互看见

给定一个不含有负数且没有重复值的数组 arr，请返回有多少对山峰能够相互看见。



进阶问题

给定一个不含有负数但可能含有重复值的数组arr，返回有多少对山峰能够相互看见。



O(N)

单调递减栈



## 4.tallest-billboard

https://src.main.java.leetcode.com/problems/tallest-billboard/



找到2个不重合的子集且累加和一样大, 并且要形成的累加和最大



使用哈希表 K-V, 每次遇到一个新数字, 生成所有可能的新集合, 记录2个集合差值和2个集合中较小的一个累加和

两个哈希表交替进行





## 5.trapping-rain-water

https://src.main.java.leetcode.com/problems/trapping-rain-water/



17...... 5 ..... 23      可以累积(17 -5)水

9 ........ 17 ......23   可以累积0个水



## 6.trapping-rain-water2

https://src.main.java.leetcode.com/problems/trapping-rain-water-ii/





# Class 23

1.

给定数组father大小为N，表示一共有N个节点

father[i] = j 表示点i的父亲是点j， father表示的树一定是一棵树而不是森林

queries是二维数组，大小为M*2，每一个长度为2的数组都表示一条查询

[4,9], 表示想查询4和9之间的最低公共祖先… 

[3,7], 表示想查询3和7之间的最低公共祖先…

tree和queries里面的所有值，都一定在0~N-1之间

返回一个数组ans，大小为M，ans[i]表示第i条查询的答案





2.

给定一个数组arr，长度为N

从中间切一刀，保证左部分和右部分都有数字，一共有N-1种切法

如此多的切法中，每一种都有:

绝对值(左部分最大值 – 右部分最大值)

返回最大的绝对值是多少





## 3.可整合数组

定义什么是可整合数组：

一个数组排完序之后，除了最左侧的数外，有arr[i] = arr[i-1]+1

则称这个数组为可整合数组

比如{5,1,2,4,3}、{6,2,3,1,5,4}都是可整合数组

返回arr中最长可整合子数组的长度





## 4.超级水王问题

扩展1：摩尔投票

扩展2：给定一个正数K，返回所有出现次数>N/K的数





## 5.

https://src.main.java.leetcode.com/problems/minimum-cost-to-merge-stones/





# Class24









# Class25

## 1. IP to CIDR

 



## 2. Three Sum









## 3. Max Points On A Line





## 4.Gas Station







# Class26



题目1

Leetcode 题目 : 

https://src.main.java.leetcode.com/problems/smallest-range-covering-elements-from-k-lists/



有三个有序数组，分别在三个数组中挑出3个数，x、y、z

返回 |x-y| + |y-z| + |z-x|最小是多少？

![](https://raw.githubusercontent.com/haoboliu66/PicBed/master/img/20210731115938.png)



就是求 a 到 c 的距离的2倍, 就是求 a和c的距离怎样大





题目2 

https://src.main.java.leetcode.com/problems/word-search-ii/



题目3

https://src.main.java.leetcode.com/problems/expression-add-operators/

c4 - class1



题目4

https://src.main.java.leetcode.com/problems/word-ladder-ii/

生成邻居表

生成距离表, 根据距离指导dfs的前进路径

dfs生成结果





# Class27

题目1

每一个项目都有三个数，[a,b,c]表示项目a和b乐队参演，花费为c

给定很多个项目int[][] programs

每一个乐队可能在多个项目里都出现了，但是只能挑一次

nums是可以挑选的项目数量，所以一定会有nums*2只乐队被挑选出来

返回一共挑nums轮(也就意味着一定请到所有的乐队)，最少花费是多少？



nums < 9, programs长度小于500，每组测试乐队的全部数量一定是nums*2，且标号一定是0 ~ nums*2-1





题目2

https://src.main.java.leetcode.com/problems/rabbits-in-forest/

企鹅厂每年都会发文化衫，文化衫有很多种颜色，厂庆的时候，企鹅们都需要穿文化衫来拍照

一次采访中，记者随机遇到的企鹅，企鹅会告诉记者还有多少企鹅跟他穿一个颜色的文化衫

我们将这些回答放在 answers 数组里，返回鹅厂中企鹅的最少数量。

输入: answers = [1]

一个人回答，还有 1 个人跟他穿一样颜色的文化衫，所以最少是 2个人，

输出 2

输入: answers = [1, 1, 2]

输出: 5





 







