# Class1

## 1. Scramble String

https://src.main.java.leetcode.com/problems/scramble-string/



## 2. 

给定字符串str1和str2，求str1的子串中含有str2所有字符的最小子串长度

【举例】

str1="abcde"，str2="ac"

因为"abc"包含 str2 所有的字符，并且在满足这一条件的str1的所有子串中，"abc"是 最短的，返回3。

str1="12345"，str2="344" 最小包含子串不存在，返回0。





## 3. LFU

一个缓存结构需要实现如下功能

void set(int key, int value):加入或修改key对应的value

int get(int key):查询key对应的value值

但是缓存中最多放K条记录，如果新的第K+1条记录要加入，就需要根据策略删掉一条记录，然后才能把新记录加入。

这个策略为:

在缓存结构的K条记录中，哪一个key从进入缓存结构的时刻开始，被调用set或者get的次数最少，就删掉这个key的记录;

如果调用次数最少的key有多个，上次调用发生最早的key被删除。

这就是LFU缓存替换算法。实现这个结构，K作为参数给出。





## 4. Gas Station

N个加油站组成一个环形，给定两个长度都是N的非负数组 oil和dis(N>1)，oil[i]代表 第i个加油站存的油可以跑多少千米，dis[i]代表第i个加油站到环中下一个加油站相隔 多少千米。 假设你有一辆油箱足够大的车，初始时车里没有油。如果车从第i个加油站出发，最终 可以回到这个加油站，那么第i个加油站就算良好出发点，否则就不算。 请返回长度为N的boolean型数组res，res[i]代表第 i 个加油站是不是良好出发点。





# Class2



## 1.

给定一个路径数组 paths，表示一张图。paths[i]==j 代表城市 i 连向城市 j，如果 paths[i]==i， 则表示 i 城市是首都，一张图里只会有一个首都且图中除首都指向自己之 外不会有环。

例如， paths=[9,1,4,9,0,4,8,9,0,1]，

由数组表示的图可以知道，城市 1 是首都，所以距离为 0，离首都距离为 1 的城市只有城 市 9，离首都距离为 2 的城市有城市 0、3 和 7，离首都距离为 3 的城市有城市 4 和 8， 离首都 距离为 4 的城市有城市 2、5 和 6。所以距离为 0 的城市有 1 座，距离为 1 的 城市有 1 座，距离 为 2 的城市有 3 座，距离为 3 的城市有 2 座，距离为 4 的城市有

3 座。那么统计数组为nums=[1,1,3,2,3,0,0,0,0,0]，nums[i]==j 代表距离为 i 的城市有 j 座。要求实现一个 void 类型的函 数，输入一个路径数组 paths，直接在原数组上调整， 使之变为 nums 数组，即 paths=[9,1,4,9,0,4,8,9,0,1]经过这个函数处理后变成 [1,1,3,2,3,0,0,0,0,0]。

【要求】

如果 paths 长度为 N，请达到时间复杂度为 O(N)，额外空间复杂度为 O(1)。





## 2.

一群孩子做游戏，现在请你根据游戏得分来发糖果，要求如下: 1.每个孩子不管得分多少， 起码分到 1 个糖果。 2.任意两个相邻的孩子之间，得分较多的孩子必须拿多一些的糖果。 给定一个数组 arr 代表得分数组，请返回最少需要多少糖果。 例如:arr=[1,2,2]，糖果 分配为[1,2,1]，即可满足要求且数量最少，所以返回 4。

【进阶】

原题目中的两个规则不变，再加一条规则: 3.任意两个相邻的孩子之间如果得分一样，糖 果数必须相同。

给定一个数组 arr 代表得分数组，返回最少需要多少糖果。 例如:arr=[1,2,2]，糖果分 配为[1,2,2]，即可满足要求且数量最少，所以返回 5。

【要求】

arr 长度为 N，原题与进阶题都要求时间复杂度为 O(N)，额外空间复杂度为 O(1)。



## 3. Binary Camera

给定一棵二叉树的头节点head，如果在某一个节点x上放置相机，那么x的父节点、x的所 有子节点以及x都可以被覆盖。返回如果要把所有数都覆盖，至少需要多少个相机。





## 4.

给定一个数组arr，给定一个正数k。选出3个不重叠的子数组，每个子数组长度都是k，返回最大的三子数组的最大和。









# Class3

## 1.

牛牛和15个朋友来玩打土豪分田地的游戏，牛牛决定让你来分田地，地主的田地可以看成是一个矩形，每个位置有一个价值。分割田地的方法是横竖各切三刀，分成 16 份，作为领导干部，牛牛总是会选择其中总 价值最小的一份田地， 作为牛牛最好的朋友，你希望牛牛取得的田地的价值和尽可能大，你知道这个值最大 可以是多少吗?

输入描述:

每个输入包含 1 个测试用例。每个测试用例的第一行包含两个整数 n 和 m(1 <= n, m <= 75)，表示田地 的大小，接下来的 n 行，每行包含 m 个 0-9 之间的数字，表示每块位置的价值。

输出描述: 输出一行表示牛牛所能取得的最大的价值。

输入例子: 4 4

3 3 3 2

3 2 3 3

3 3 3 2

2 3 2 3

输出例子: 2



## 2.

给定一个只含0和1二维数组matrix，第0行表示天花板。每个位置认为与上、下、左、右四个方向有粘性，比如：

matrix = 

1 0 0 1 0

1 0 0 1 1

1 1 0 1 1

1 0 0 0 0

0 0 1 1 0

注意到0行0列是1，然后能延伸出5个1的一片。同理0行3列也是1，也能延伸出5个1的一片。注意到4行2列是1，然后能延伸出2个1的一片。其中有两片1是粘在天花板上的，而4行2列延伸出来的这片，认为粘不住就掉下来了。

在给定一个二维数组bomb，表示炸弹的位置，比如：

bomb = 

2 0

1 3

1 4

0 3

第一枚炮弹在2行0列，该处的1直接被打碎，然后会有2个1掉下来。

第二枚炮弹在1行3列，该处的1直接被打碎，不会有1掉下来，因为这一片1还能粘在一起。

第三枚炮弹在1行4列，该处的1直接被打碎，然后会有2个1掉下来。

第四枚炮弹在0行3列，该处的1直接被打碎，不会有1掉下来，因为这一片1只剩这一个了。

根据matrix和bomb，返回结果[2,0,2,0]。



## 3.

给定一个数组arr，如果其中有两个集合的累加和相等，并且两个集合使用的数没有相容 的部分(也就是arr中某数不能同时进这个两个集合)，那么这两个集合叫作等累加和集 合对。返回等累加和集合对中，最大的累加和。

举例:

arr={1,2,3,6} {1,2}和{3}，是等累加和集合对 {1,2,3}和{6}，也是等累加和集合对 返回6



## 4.

给定一个字符串s，求s中有多少个字面值不相同的子序列。







# Class4

## 1.

给定一个整型数组 arr，数组中的每个值都为正数，表示完成一幅画作需要的时间，再 给定 一个整数 num，表示画匠的数量，每个画匠只能画连在一起的画作。所有的画家 并行工作，请 返回完成所有的画作需要的最少时间。

【举例】

arr=[3,1,4]，num=2。

最好的分配方式为第一个画匠画 3 和 1，所需时间为 4。第二个画匠画 4，所需时间 为 4。 因为并行工作，所以最少时间为 4。如果分配方式为第一个画匠画 3，所需时 间为 3。第二个画 匠画 1 和 4，所需的时间为 5。那么最少时间为 5，显然没有第一 种分配方式好。所以返回 4。

arr=[1,1,1,4,3]，num=3。

最好的分配方式为第一个画匠画前三个 1，所需时间为 3。第二个画匠画 4，所需时间 为 4。 第三个画匠画 3，所需时间为 3。返回 4。



## 2.

一座大楼有 0~N 层，地面算作第 0 层，最高的一层为第 N 层。已知棋子从第 0 层掉落肯定 不会摔碎，从第 i 层掉落可能会摔碎，也可能不会摔碎(1≤i≤N)。给定整数 N 作为楼层数， 再给定整数 K 作为棋子数，返 回如果想找到棋子不会摔碎的最高层数，即使在最差的情况下扔 的最少次数。一次只能扔一个棋子。

【举例】

N=10，K=1。

返回 10。因为只有 1 棵棋子，所以不得不从第 1 层开始一直试到第 10 层，在最差的情况 下，即第 10 层 是不会摔坏的最高层，最少也要扔 10 次。

N=3，K=2。

返回 2。先在 2 层扔 1 棵棋子，如果碎了，试第 1 层，如果没碎，试第 3 层。 N=105，K=2

返回 14。

第一个棋子先在 14 层扔，碎了则用仅存的一个棋子试 1~13。 若没碎，第一个棋子继续在 27 层扔，碎了则 用仅存的一个棋子试 15~26。 若没碎，第一个棋子继续在 39 层扔，碎了则用仅存的一个棋子试 28~38。 若 没碎，第一个棋子继续在 50 层扔，碎了则用仅存的一个棋子试 40~49。 若没碎，第一个棋子继续在 60 层扔， 碎了则用仅存的一个棋子试 51~59。 若没碎，第一个棋子继续在 69 层扔，碎了则用仅存的一个棋子试 61~68。 若没碎，第一个棋子继续在 77 层扔，碎了则用仅存的一个棋子试 70~76。 若没碎，第一个棋子继续在 84 层 扔，碎了则用仅存的一个棋子试 78~83。 若没碎，第一个棋子继续在 90 层扔，碎了则用仅存的一个棋子试 85~89。 若没碎，第一个棋子继续在 95 层扔，碎了则用仅存的一个棋子试 91~94。 若没碎，第一个棋子继续 在 99 层扔，碎了则用仅存的一个棋子试 96~98。 若没碎，第一个棋子继续在 102 层扔，碎了则用仅存的一 个棋子试 100、101。 若没碎，第一个棋子继续在 104 层扔，碎了则用仅存的一个棋子试 103。 若没碎，第 一个棋子继续在 105 层扔，若到这一步还没碎，那么 105 便是结果。



## 3.

一条直线上有居民点，邮局只能建在居民点上。给定一个有序正数数组arr，每个值表示 居民点的一维坐标，再给定一个正数 num，表示邮局数量。选择num个居民点建立num个 邮局，使所有的居民点到最近邮局的总距离最短，返回最短的总距离

【举例】

arr=[1,2,3,4,5,1000]，num=2。

第一个邮局建立在 3 位置，第二个邮局建立在 1000 位置。那么 1 位置到邮局的距离 为 2， 2 位置到邮局距离为 1，3 位置到邮局的距离为 0，4 位置到邮局的距离为 1， 5 位置到邮局的距 离为 2，1000 位置到邮局的距离为 0。这种方案下的总距离为 6， 其他任何方案的总距离都不会 比该方案的总距离更短，所以返回6



## 4.

给定整数power，给定一个数组arr，给定一个数组reverse。

含义如下：

arr的长度一定是2的power次方，

reverse中的每个值一定都在0~power范围。

例如power = 2, arr = {3, 1, 4, 2}，reverse = {0, 1, 0, 2}

任何一个在前的数字可以和任何一个在后的数组，构成一对数。

可能是升序关系、相等关系或者降序关系。



arr开始时有如下的降序对：(3,1)、(3,2)、(4,2)，一共3个。

接下来根据reverse对arr进行调整：

reverse[0] = 0, 表示在arr中，划分每1(2的0次方)个数一组，然后每个小组内部逆序，

那么arr变成[3,1,4,2]，此时有3个逆序对

 reverse[1] = 1, 表示在arr中，划分每2(2的1次方)个数一组，然后每个小组内部逆序，

那么arr变成[1,3,2,4]，此时有1个逆序对

reverse[2] = 0, 表示在arr中，划分每1(2的0次方)个数一组，然后每个小组内部逆序，

那么arr变成[1,3,2,4]，此时有1个逆序对。

reverse[3] = 2, 表示在arr中，划分每4(2的2次方)个数一组，然后每个小组内部逆序，

那么arr变成[4,2,3,1]，此时有4个逆序对。

所以返回[3,1,1,4]，表示每次调整之后的逆序对数量。

输入数据状况：

power的范围[0,20]

arr长度范围[1,10的7次方]

reverse长度范围[1,10的6次方]











# Class5



## 1.

给定字符串数组words，其中所有字符串都不同，如果words[i]+words[j]是回文串就记 录(i,j)，找到所有记录并返回

例子一:

输入：["abcd","dcba","lls","s","sssll"]

输出：[[0,1],[1,0],[3,2],[2,4]]

解释：输出的每一组数组，两个下标代表字符串拼接在一起，都是回文串

abcddcba、 dcbaabcd 、 slls 、 llssssll






## 2.

给定无序数组 arr，返回其中最长的连续序列的长度

【举例】 

arr=[100,4,200,1,3,2]，最长的连续序列为[1,2,3,4]，所以返回4。





## 3.

public class Query {

  public Node o1;

  public Node o2;

  public Query(Node o1, Node o2) { 

​    this.o1 = o1;

​    this.o2 = o2; }

}



一个 Query 类的实例表示一条查询语句，表示想要查询 o1 节点和 o2 节点的最近公共 祖先 节点。

给定一棵二叉树的头节点 head，并给定所有的查询语句，即一个 Query 类型的数组 Query[] ques，请返回Node类型的数组Node[] ans，ans[i]代表ques[i]这条查询的答案， 即ques[i].o1和 ques[i].o2 的最近公共祖先。

【要求】

如果二叉树的节点数为 N，查询语句的条数为 M，整个处理过程的时间复杂度要求达到 O(N+M)。



## 4.

给定一个二维数组matrix，其中的值不是0就是1，

其中，

内部全是1的所有子矩阵中，含有最多1的子矩阵中，含有几个1？









# Class6



## 1.

https://src.main.java.leetcode.com/problems/remove-boxes/



## 2.

https://src.main.java.leetcode.com/problems/strange-printer/



## 3.

https://src.main.java.leetcode.com/problems/smallest-range-covering-elements-from-k-lists/





## 4.

整型数组arr长度为n(3 <= n <= 10^4)，最初每个数字是<=200的正数且满足如下条件： 

\1. arr[0] <= arr[1]

2.arr[n-1] <= arr[n-2]

\3. arr[i] <= max(arr[i-1], arr[i+1])

但是在arr有些数字丢失了，比如k位置的数字之前是正数，

丢失之后k位置的数字为0。

请你根据上述条件， 计算可能有多少种不同的arr可以满足以上条件。

比如 [6,0,9] 只有还原成 [6,9,9]满足全部三个条件，所以返回1种。







# Class7



## 1. TSP问题 

有N个城市，任何两个城市之间的都有距离，任何一座城市到自己的距离都为0。所有点到点的距 离都存在一个N*N的二维数组matrix里，也就是整张图由邻接矩阵表示。现要求一旅行商从k城市 出发必须经过每一个城市且只在一个城市逗留一次，最后回到出发的k城，返回总距离最短的路的 距离。参数给定一个matrix，给定k。





## 2. 贴瓷砖问题

你有无限的1*2的砖块，要铺满2*N的区域，不同的铺法有多少种? 

你有无限的1*2的砖块，要铺满M*N的区域，不同的铺法有多少种?





# Class8 - 后缀数组

后缀数组

介绍用DC3算法生成后缀数组的流程





给两个长度分别为M和N的整型数组nums1和nums2，其中每个值都不大于9，再给定一个正数K。 你可以在nums1和nums2中挑选数字，要求一共挑选K个，并且要从左到右挑。返回所有可能的结果中，代表最大数字的结果。