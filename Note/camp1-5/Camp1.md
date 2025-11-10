# 1.滑动窗口

滑动窗口逻辑:   使用双向队列实现

窗口范围L...R  且  L <= R

R++每次有一个数字从右侧进入窗口

L++每次有一个数字从右侧移出窗口

窗口内最大值的更新结构: 永远保留下标大且值也大的, 相等也移除



**双端队列中值的含义: 如果此时, 我开始从左侧缩小窗口, 哪些数会成为窗口中的最大值**



复杂度分析:

​	每个数字只进一次队列, 只出一次队列,每次操作平均下来是O(1),整体滑动一遍是O(N)





题目一:

> https://src.main.java.com.hliu.leetcode.com/problems/sliding-window-maximum/

假设一个固定大小为W的窗口，依次划过arr, 返回每一次滑出状况的最大值

例如, arr = [4,3,5,4,3,3,6,7], W = 3

返回: [5,5,5,4,6,7]



找Max: Deque队列, 队列内从左到右按降序摆放, 左侧只放当前窗口内的最大值;

L++, R++ 不停滚动, 但是在移动窗口之前, 要判断L所在位置是不是队列的头(即当前窗口最大值所在位置)





题目二:

给定一个整型数组arr，和一个整数num, 某个arr中的子数组sub，如果想达标，必须满足：

sub中最大值 – sub中最小值 <= num, 返回arr中达标子数组的数量



两个Deque, 一个降序摆放, 左侧放窗口内的最大值; 一个升序摆放, 左侧放窗口内的最小值, 每次比较max - min

**优化点**: 

1. 如果一个短的子数组不达标, 没必要再向外扩了
2. 短子数组的max一定 <= 继续扩子数组的max;  短子数组的min一定 >= 继续扩子数组的min



**一个子数组达标, 那么它缩小范围必达标; 如果一个子数组不达标, 它扩大范围也一定不达标, 建立了单调性**



**优化思想**: 以每一个位置开头的子数组情况

所以要找以每一个数字开头的情况下, 达标的子数组数量



常见优化点:

1)数据状况

2)问题本身   看问题本身和求解范能不能建立单调性  (e.g. 滑动窗口 和 首尾指针)





题目三: Gas Station

返回从每个位置出发能否跑完一圈

![](https://raw.githubusercontent.com/haoboliu66/PicBed/master/img/20210728182137.png)

加工出一个数组 gas[i] - cost[i]

**如果中途累加和掉到了0以下, 表示该点出发跑不完**





![](https://raw.githubusercontent.com/haoboliu66/PicBed/master/img/20210728182458.png)

划定长度为6的窗口





题目四:

arr是货币数组, 值都是正数。再给定一个正数aim, 每个值都认为是一张货币, 返回组成aim的最少货币数

注意: 因为是求最少货币数, 所以每张货币认为是相同或者不同就不重要了

















# 2.单调栈

单调栈解决的问题: 求一个数组左边离它最近比他小的, 和右边离它最近比他小的



题目一:

**算法原型**: 

**给定一个数组arr[3,2,1,7,0,4,5,6], 求每一个数字左边最近的比它小的数,和右边最近比它小的数**





题目二:

给定一个只包含正数的数组arr，arr中任何一个子数组sub，

一定都可以算出(sub累加和 ) * (sub中的最小值)是什么，

那么所有子数组中，这个值最大是多少？



思路: 以每个i位置为最小值, 向两侧扩, 用单调栈找到能扩的最大范围, 然后根据前缀和数组, 求出该子数组累加和, 然后求max;

因为是正数arr, 所以子数组数组长度和累加和具有单调性



可以忽略数字相等的情况, 前面的都会算错, 但最后一个数字会把它的情况算对

 ![](https://raw.githubusercontent.com/haoboliu66/PicBed/master/img/20210727225742.png)



题目三:

> https://src.main.java.com.hliu.leetcode.com/problems/largest-rectangle-in-histogram/

给定一个非负数组arr，代表直方图, 返回直方图的最大长方形面积



思路: 必须以[i]位置的值作为长方形的高, 找到[i]位置两侧比它小的位置, 就是所形成的长方形的限制边界





题目四:

> https://src.main.java.com.hliu.leetcode.com/problems/maximal-rectangle/

给定一个二维数组matrix，其中的值不是0就是1，

返回全部由1组成的最大子矩形，内部有多少个1



N*N的矩阵, 如果要枚举所有子矩阵, 复杂度O(N^4^)



最优解O(N^2^)

思路:  压缩矩阵 变成成求解直方图形成的最大矩形面积, 类似上一题

以矩阵的每行 作为地基

![](https://raw.githubusercontent.com/haoboliu66/PicBed/master/img/20210727234224.png)

![](https://raw.githubusercontent.com/haoboliu66/PicBed/master/img/20210727235329.png)



N行, 每行玩一次单调栈求解





题目五:

> https://leetcode.com/problems/count-submatrices-with-all-ones

给定一个二维数组matrix，其中的值不是0就是1，

返回全部由1组成的子矩形数量



思路: 也是考虑以每行做底, 能形成几个全是1的矩形



![](https://raw.githubusercontent.com/haoboliu66/PicBed/master/img/20210728112112.png)



考虑相等情况下的舍弃问题, 让后面的相等的值的最后一个找到这片连通区域, 统一计算

![](https://raw.githubusercontent.com/haoboliu66/PicBed/master/img/20210728112016.png)



题目六:

给定一个数组, 返回所有子数组中最小值的累加和

> 907.Sum of Subarray Minimums



以i位置为最小值的子数组的范围, 在这个范围上可以累积多少累加和

有相等值的时候, 要设置右边相等值的位置到不了;

2个辅助数组: 左侧严格小于[i]的位置, 右侧小于等于[i]的位置





**思维传统: 以[i]位置的数作为某个标准, 求一个答案, 把每个[i]位置的答案求出来, 就可以得到最后的答案**





# 3.类斐波那契数列递推式

斐波那契暴力解: 类似展开一颗二叉树, 高度为N, 每个节点都要过一遍, 所以复杂度O(2^N)

斐波那契数列用矩阵的乘法解, 可以做到复杂度O(logN)

**所有没有条件转移的严格递推式, 都有logN的解**



严格递推式最后到n-x  就是x阶 => 矩阵的规模就是x平方, 最后的矩阵就要乘上n-x方 



![](https://raw.githubusercontent.com/haoboliu66/PicBed/master/img/20210629210640.png)





![](https://raw.githubusercontent.com/haoboliu66/PicBed/master/img/20210506120402.png)



由前几项可以推出这个矩阵的值

<img src="https://raw.githubusercontent.com/haoboliu66/PicBed/master/img/20201102122529.png" style="zoom:50%;" />

![](https://raw.githubusercontent.com/haoboliu66/PicBed/master/img/20210506122451.png)



  

所以矩阵的 n-2次方的计算复杂度就决定了该算法的复杂度

> 如何计算一个数字的N次方尽量快

> 10 ^ 75

> 75 == 1 0 0 1 0 1 1

<img src="https://raw.githubusercontent.com/haoboliu66/PicBed/master/img/20201102123617.png" style="zoom:50%;" />

75的二进制有几位, 就决定计算几次, 就决定了最终的复杂度



斐波那契数列的系数矩阵:

[1,1]

[1,0]

同样作用于矩阵乘法, 只不过矩阵乘法的**初始变量res**是对角线都为1的二维数组, 单位矩阵就是矩阵中的1

![](https://raw.githubusercontent.com/haoboliu66/PicBed/master/img/20201102125724.png)



## 斐波那契递推式推广:

任何严格的递推式都可以做到O(logN)的解



Example:奶牛问题

母牛每年产一只小牛, 小牛长到第三年才能帮着再生小牛, 问 第N年 牛的数量

![](https://raw.githubusercontent.com/haoboliu66/PicBed/master/img/20201101183323.png)

F(N) = F(N-1) + F(N-3) ==> F(N) = F(N-1) + 0 * F(N-2) + F(N-3)

三阶递推式, 转化为三阶的矩阵乘法

![](https://raw.githubusercontent.com/haoboliu66/PicBed/master/img/20201101183554.png)





## 推广题目

1.斐波那契数列矩阵乘法方式的实现



2.一个人可以一次往上迈1个台阶，也可以迈2个台阶, 返回这个人迈上N级台阶的方法数



3.第一年农场有1只成熟的母牛A，往后的每年：

1）每一只成熟的母牛都会生一只母牛

2）每一只新出生的母牛都在出生的第三年成熟

3）每一只母牛永远不会死

返回N年后牛的数量



4.给定一个数N，想象只由0和1两种字符，组成的所有长度为N的字符串

如果某个字符串,任何0字符的左边都有1紧挨着,认为这个字符串达标, 返回有多少达标的字符串?

N=1,  0(False), 1

N=2,  00(False), 01(False), 10, 11

N=3,  000(False), 001(False), 010(False), 011(False), 100(False), 101, 110, 111

N=4, .........

**如果暴力生成所有字符串并检查, 复杂度O(2^N * N)**



**设计递归函数f(i), 还有i的长度要填, 每个位置可以填0 或填1, 但是在最左侧一定有个1(潜台词), e.g N=8, 调f(7)**

**推出是斐波那契数列递推式**

<img src="https://raw.githubusercontent.com/haoboliu66/PicBed/master/img/20201101200143.png" alt="50" style="zoom:50%;" />

![](https://raw.githubusercontent.com/haoboliu66/PicBed/master/img/20201101200211.png)







5.一个N * 2的区域, 给定1 * 2的瓷砖, 把该区域贴满, 有多少种不同的方案

<img src="https://raw.githubusercontent.com/haoboliu66/PicBed/master/img/20201101200542.png" alt="50" style="zoom:50%;" />

同样推出是类斐波那契数列递推式



# 4.蓄水池算法

## 解决的问题:

假设有一个源源吐出不同球的机器，只有装下10个球的袋子，每一个吐出的球，决定: 要么放入袋子，要么永远扔掉, 如何做到机器吐出每一个球之后，所有吐出的球都等概率被放进袋子里?

**算法流程:** 

1.先把1-10号球全放入袋子

2.随机函数f(i), 等概率返回1~i中的一个数字, 假设到了k号球, 如何决策留不留它:

​	用10/k的概率决定这个球要不要入袋子; 调用f(k), 如果返回1~10, 那么就决定让它入袋子; 此时需要从袋子中丢掉一个球, 也要等概率丢掉一个球, 所以再调用f(10), 决定扔几号球



e.g

17号球处理完后, 看看3号球还在袋子中的概率P是多少?

11号球到来, 3号被淘汰的概率是 11号被选中进袋子(10/11) * 3号球被选中淘汰(1/10), 结果是1/11, 所以3号球还留在袋子中的概率是 10/11, 依次推算下去, 17号球处理后, 3号在还在袋子中的概率P=10/17

<img src="https://raw.githubusercontent.com/haoboliu66/PicBed/master/img/20201102154529.png" alt="50" style="zoom:50%;" />

类似的, 再举例17号球处理后,13号球还在袋子中的概率P

13号球还在袋子中的概率:  13号被选中入袋的概率(10/13) * 14号球不把他淘汰的概率(1 - 10/14 * 1/10) ==> 10/14, 依次推算下去, 17号球处理后, 13号还在袋子里的概率P=10/17

<img src="https://raw.githubusercontent.com/haoboliu66/PicBed/master/img/20201102154647.png" alt="50" style="zoom:50%;" />

由以上两个例子, 可以推断出, 每个球在袋子里的概率都是均等的



## 应用举例

10亿+用户

全球服务器大抽奖, 选出100个幸运观众, 当天登录过只算一个名额, 重复登录不多算



暴力方法: 所有名单拿到手, 去重, 再抽取100个发布

蓄水池算法: 一台服务器留100个位置, 需要统计**2个条件**:

**1.检查是不是首次登录; 2.第一次登录的话,是全球第几个登录**

先把前1-100个人收集到袋子里, 第k个, 直接以100/k的概率处理, 再决定是否把这个人放入袋子, 再踢掉另一个人







# 附加问题

## 随机函数生成问题

**1. f()函数可以等概率生成1~7的数字, 只能用这个随机机制, 生成一个g()函数, 要求g()函数等概率返回1~10**

f()函数如果返回7, 重新调用, 返回123就映射成0, 返回456就映射成1, 这样就得到了等概率返回0和1的函数

等概率返回1~10, 相当于 ===> 等概率返回0~9, 再加1

看0~9这个范围需要几个二进制位来解决, 是4位, 所以用刚生成的等概率返回0和1的函数, 扔4次, 4个二进制位可以等概率生成0~15之间的数字, 如果产生了10~15, 重新扔



**2. f函数等概率生成7~13, 要求等概率生成17~56**

也是用f函数先加工01源, 然后17~56等价于0~39, 需要6个二进制位, 如果扔出40以上的数字, 就重新扔



**3. 假设f函数不等概率返回0和1, 0是P, 1是1-P, 加工出g函数, 要求等概率返回0和1**

f函数有00, 01, 10, 11   4种可能的结果, 如果扔出了00和11就重新来, 01和10是等概率返回, 01映射成0, 10映射成1, 就可以 以等概率P*(1-P)得到0和1, 就加工出了g函数



如何判断一个范围需要几个二进制表示?

```java
// range是范围右边界
int num = 1;
while ((1 << num) - 1 < range) {
	num++;
}
```





# 5.KMP

解决问题: str1是不是str2的子串, 类似indexOf()方法

int kmp (s, match)



暴力方法: 用每个开头往后去匹配目标字符串

复杂度O(N*M)



![](https://raw.githubusercontent.com/haoboliu66/PicBed/master/img/20210709203358.png)

## 算法流程

i位置前面的字符串pre的最大相等的前缀长度和后缀长度 (该长度不能等于pre的长度)

a b c a b c (k)

根据每个位置, 规定一个指标

对match字符串建立每个位置的指标: 任何字符串0位置指标都是-1,    1位置都是0



假设str的i位置对应match的0位置, 一路向后匹配, 直到X和Y匹配失败

![](https://raw.githubusercontent.com/haoboliu66/PicBed/master/img/20210709205037.png)

原理1: 当图中X和Y位置不相等时, 他们前面的匹配过的一段肯定是相等的, 所以找到Y位置的指标(C == B), 对应到C位置, 这样就可以直接直接比较C的后一个位置和X, 因为A和C一定是相等的, 无需重新比较







原理2: 为什么选择j的位置开始匹配match, 因为i 到 j之间不可能有字符能匹配出整个match

- 假设存在K, K往后可以匹配出整个match, 因为X和Y不等,那么可以得出K到X这段长度一定是match的一个前缀, 而且K到X这段长度又是match的一个后缀(因为X和Y之前的部分要一路都相等, 原理1中内容), 所以发现了一个矛盾: Y的相等前后缀长度现在变成了K到X这段长度, 大于了预先算出的Y的指标长度, 所以K位置往后不能匹配出整个match



想象match字符串有一个指针, 根据nextArray根据当前位置的指标不断向前跳



![](https://raw.githubusercontent.com/haoboliu66/PicBed/master/img/20210709223315.png)

例子:

当match中12位置的k和上面对应的5无法匹配时, 找到k对应的指标(相等前后缀), 

k的指标(在next数组中的值)为5表示: 相等前后缀长度为5, 对应下标为0-4 

因为5前面一段的和k前面一段字符必然相等, 再把match中的这段后缀对应到前缀, 无需再重新比较这段字符, 就相当于从match的下标为5的位置开始继续和str进行比较,  这中间包含了match字符串向前跳的过程, 从12跳到5

所以next数组有两个含义: 1. 最长的相等前后缀的长度; 2.如果指针要向前跳, 应该跳到哪个index(表示匹配失败, 下一个位置去哪)

当match跳回到开头, 说明str当前的这个位置无法匹配, 要去下一个位置继续匹配



## 复杂度分析

O(N)

根据不同分支推断, while循环的发生次数不可能超过2N次, 所以复杂度是O(N)

![](https://raw.githubusercontent.com/haoboliu66/PicBed/master/img/20210709224913.png)



## next数组的求法

i位置的信息和它本身的字符是没有关系的





![](https://raw.githubusercontent.com/haoboliu66/PicBed/master/img/20210709225531.png)

先看i - 1位置, 假设i - 1的信息是7, 然后看?的位置, 如果?的位置也是b, 那么i位置信息就是8



![](https://raw.githubusercontent.com/haoboliu66/PicBed/master/img/20210709230547.png)

如果第一次匹配没匹配上, 就再往前跳



cn初始化为0的原因: 因为我要从2位置开始进行计算, 是0位置的字符和 i - 1位置的字符比较



++cn, 是为了 i + 1的位置进行比较, 到i+1的时候, 当前i位置 要进行比较的下标 就是 cn+1



### 复杂度证明 - 类似

![](https://raw.githubusercontent.com/haoboliu66/PicBed/master/img/20210709232203.png)









对于 i 位置, 要不断向前比较 i - 1位置的数字和 i - 1位置前缀 j 的下一个位置(j + 1)进行比较, 如果相等,那i位置的指标就是i - 1位置的指标 +1, 如果一直比到j + 1的位置到了1也不相等, 那最后就只能比较 i - 1位置和0位置, 如果也不相等, 那么 i 位置的指标就是0



题目:

1.判断两个字符串是否互为旋转词  (eg. 123456  561234)

str1 + str2,  判断str2是不是str1的子串



2.给定两棵二叉树的头节点head1和head2,

想知道head1中是否有某个子树的结构和head2完全一样



先序方式序列化, 然后处理成字符串; 然后2个字符串进行KMP比较



序列化直接转成字符串可能会产生歧义:

只补一个underscore无法消除歧义, 前后各补一个可以

<img src="https://raw.githubusercontent.com/haoboliu66/PicBed/master/img/20201115102521.png" style="zoom:50%;" />





# 6.Manacher

解决问题: 最长回文子串长度问题



**Manacher算法核心:**

1）理解**回文半径数组**

2）理解所有中心的回文最右边界R，和取得R时的中心点C

3）理解  L…(i`)…C…(i)…R 的结构，以及根据i’回文长度进行的状况划分

4）每一种情况划分，都可以加速求解i回文半径的过程



## 4种情况:



第一种情况: i 在 R 外, 直接暴力扩展验证



第二种情况: i 在 R 内

此时形成R的回文中心为C

做 i 关于 C 的对称点 i'

(1) i' 的回文区域完全在[L...R]内,  i的回文半径就等于 i'的回文半径

![](https://raw.githubusercontent.com/haoboliu66/PicBed/master/img/20210629221512.png)

情况(1)的证明:

![](https://raw.githubusercontent.com/haoboliu66/PicBed/master/img/20210629221938.png)



(2) i' 的回文区域左侧突破了L,  i的回文半径就等于 i到R 的长度

![](https://raw.githubusercontent.com/haoboliu66/PicBed/master/img/20210629222433.png)



情况(2)的证明:

![](https://raw.githubusercontent.com/haoboliu66/PicBed/master/img/20210629222549.png)





(3)  i' 的回文区域左侧刚好与L重合,  i的回文半径至少有 i到R 的长度, 外侧的部分仍然要暴力扩展验证

![](https://raw.githubusercontent.com/haoboliu66/PicBed/master/img/20210629222834.png)









![](https://raw.githubusercontent.com/haoboliu66/PicBed/master/img/20210629222951.png)









题目:

1.一个字符串, 只能在字符串后面添加字符, 让整体都变回文串, 至少需要加几个字符

> https://src.main.java.com.hliu.leetcode.com/problems/shortest-palindrome/

求必须包含最后一个字符的最长回文子串, 前面的部分逆序填到后面



2.如何获取对应的最长回文子串?

记录处理串的结尾end,  (end - 1) / 2就对应回原串的结尾, 子串的长度也已知, 就可以找出最长回文子串了





# 7.bfprt

解决问题: 无序数组中求第K大 或 第K小  要求: 时间复杂度O(N)



**方法一: 快排改写**

改写快排的流程:

(1)随机选出一个数pivot

(2)Dutch flag problem划分, 求出等于区的range

(3)根据index的位置, 判断是否命中等于区, 再决定向哪一侧进行递归



**方法二: bfprt**

bfprt流程:

(1)随机选出一个数pivot,      **bfprt对于怎么选出这一个数比较讲究**

- a. 数字分组, 5个数字一组, 一共N/5个组
- b. 把每个组内进行排序, 并取出每个组内中位数, 组成mArr  O(N)
- c. bfprt(mArr, mArr.length / 2), 调用bfprt取出mArr中的中位数m

(2)Dutch flag problem根据取到的m划分, 求出等于区的range

(3)根据index的位置, 判断是否命中等于区, 再决定向哪一侧进行递归



**复杂度分析:**

假设进左侧的递归, 问小于M的数最多有几个(因为估算复杂度要看最差的情况, 所以要看递归问题的最大规模) ===> 等同于推算: 大于等于m的数至少有几个?

arr分组后有N/5组, 每个组中的中位数组成的数组mArr[]规模是N/5, 因为m是mArr[]的中位数, 所以在N/5规模的这个mArr数组中, 有N/10的数字是>=m的

m在自己size是5的组里还至少有2个数字比他大的, 所以整个arr数组中至少有3N/10的规模是>=m的, 所以至多有7N/10的规模是< m的.

左右侧同理, 规模最大是7N/10

<img src="https://raw.githubusercontent.com/haoboliu66/PicBed/master/img/20201115154822.png" style="zoom:50%;" />



题目:

1.在无序数组中求第K小的数

1）改写快排的方法

2）bfprt算法



2.在一个数组中找到前10小的数字, 返回一个数组;

找到第10小, 然后遍历数组填10个返回





3.给定一个无序数组arr, 给定一个正数k, 返回top k个最大的数

不同时间复杂度三个方法:

O(N*logN)

O(N + K*logN) : 数组建堆, 从下往上建堆, 复杂度可以O(N)实现, 大根堆弹出K个, 就可以达到这个复杂度

O(N + K*logK) : O(N)实现数组收集k个值, 然后针对这个k长度的数组, 进行降序排序 



# 8. Morris遍历

实现遍历二叉树, 时间复杂度O(N), 额外空间复杂度O(1)



**Morris遍历流程**:

假设来到当前节点cur，开始时cur来到头节点位置

1）如果cur没有左孩子，cur向右移动(cur = cur.right)

2）如果cur有左孩子，找到左子树上最右的节点mostRight：

​	 a. 如果mostRight的右指针指向空，让其指向cur，

​	 	然后cur向左移动(cur = cur.left)

​	 b. 如果mostRight的右指针指向cur，让其指向null，

​		 然后cur向右移动(cur = cur.right)

3）cur == null时遍历停止



**Morris遍历实质**:

建立一种机制: 对于没有左子树的节点只到达一次,  对于有左子树的节点会到达两次



Morris序的前序:

来到节点第一次就打印

![](https://raw.githubusercontent.com/haoboliu66/PicBed/master/img/20210709233304.png)



Morris序的中序:

对于能回到自己两次的节点(有左树的节点), 第二次打印, 对于只能到自己一次的节点, 直接打印

打印时机其实就是当cur要往右树移动的时候: 因为只能到一次的节点是直接往右树移动, 有左树的节点第二次回到自己之后(先把mostRight指针置空)也是往右树移动; 

![](https://raw.githubusercontent.com/haoboliu66/PicBed/master/img/20210709233317.png)

Morris序的后序:  (用右边界分解整棵树, 涉及反转链表)

只追踪第二次回到自己的节点, 当这个节点第二次回到自己时, 逆序打印整个左树的右边界; 所有过程完成后, 单独逆序打印整棵树的右边界

![](https://raw.githubusercontent.com/haoboliu66/PicBed/master/img/20210709233341.png)







复杂度分析: 每条有边界最多走两次, 不会超过2N规模



# 9. Segment Tree























额外题 Q: 一个数组, 将奇数放在奇数位上 或 偶数放在偶数位上, 或全放好

就看N-1位置的数

两个指针a, b	一个走奇数位, 一个走偶数位

如果N-1位置是奇数, 发货到奇数指针, 奇数指针后移

如果N-1位置是偶数, 发货到偶数指针, 偶数指针后移

直到a, b中有一个越界, 说明都搞定了





















































































