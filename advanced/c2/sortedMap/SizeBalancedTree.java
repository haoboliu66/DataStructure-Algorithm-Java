package advanced.c2.sortedMap;


/**
 * @author andy-liu
 * @date 2020/6/20 - 12:37 AM
 */
public class SizeBalancedTree {


    public static class SBTNode<K, V> {
        K key;
        V value;
        public SBTNode<K, V> left;
        public SBTNode<K, V> right;
        int size;

        public SBTNode(K key, V value) {
            this.key = key;
            this.value = value;
            size = 1;
        }

    }

    public static class SizeBalancedTreeMap<K extends Comparable<K>, V> {
        private SBTNode<K, V> root;

        public int size() {
            return root == null ? 0 : root.size;
        }

        public boolean containsKey(K key) {
            if (key == null) {
                throw new RuntimeException("invalid parameter");
            }
            SBTNode<K, V> lastNode = findLastIndex(key);
            //找到的节点不为空才能证明找到了对应的key
            return lastNode != null && key.compareTo(lastNode.key) == 0;
        }


        public V get(K key) {
            if (key == null) {
                throw new RuntimeException("invalid parameter.");
            }
            SBTNode<K, V> lastNode = findLastIndex(key);
            if (lastNode == null || key.compareTo(lastNode.key) != 0) {
                return null;
            }
            return lastNode.value;
        }

        private SBTNode<K, V> findLastIndex(K key) {
            SBTNode<K, V> pre = null;
            SBTNode<K, V> cur = root;
            while (cur != null) {
                pre = cur;
                if (key.compareTo(cur.key) == 0) {
                    break;
                } else if (key.compareTo(cur.key) < 0) {
                    cur = cur.left;
                } else {
                    cur = cur.right;
                }
            }
            //如果key没有找到, 返回最近的不为null的节点; 仅当root为null,返回值是null
            return pre;
        }

        //返回调整后的头结点
        private SBTNode<K, V> rightRotate(SBTNode<K, V> cur) {
            SBTNode<K, V> leftNode = cur.left;
            cur.left = leftNode.right;
            leftNode.right = cur;
            //交换后要更新size信息
            leftNode.size = cur.size; //当前的头结点是leftNode, 直接接过原来头结点cur的size
            cur.size = 1 + (cur.left != null ? cur.left.size : 0) + (cur.right != null ? cur.right.size : 0);
            return leftNode;
        }


        private SBTNode<K, V> leftRotate(SBTNode<K, V> cur) {
            SBTNode<K, V> rightNode = cur.right;
            cur.right = rightNode.left;
            rightNode.left = cur;
            rightNode.size = cur.size;
            cur.size = 1 + (cur.left != null ? cur.left.size : 0) + (cur.right != null ? cur.right.size : 0);
            return rightNode;
        }


        public void put(K key, V value) {
            if (key == null) {
                throw new RuntimeException("invalid parameter");
            }
            SBTNode<K, V> lastNode = findLastIndex(key);
            if (lastNode != null && key.compareTo(lastNode.key) == 0) {
                // 查到已存在的key, 是修改操作
                lastNode.value = value;
            } else {
                // 添加操作, add递归调用
                root = add(root, key, value);
            }
        }


        private SBTNode<K, V> add(SBTNode<K, V> cur, K key, V value) {
            if (cur == null) {
                return new SBTNode<>(key, value);
            } else {
                cur.size++;
                if (key.compareTo(cur.key) < 0) {
                    cur.left = add(cur.left, key, value);
                } else {
                    cur.right = add(cur.right, key, value);
                }
            }
            return maintain(cur);
        }


        private SBTNode<K, V> maintain(SBTNode<K, V> cur) {
            if (cur == null) {
                return null;
            }
            //rotate调整结束后, 子节点发生变化的节点递归调用maintain进行调整
            if (cur.left != null && cur.left.left != null && cur.right != null && cur.left.left.size > cur.right.size) {
                // LL型
                cur = rightRotate(cur);
                cur.right = maintain(cur.right);
                cur = maintain(cur);
            } else if (cur.left != null && cur.left.right != null && cur.right != null && cur.left.right.size > cur.right.size) {
                // LR型
                cur.left = leftRotate(cur.left);
                cur = rightRotate(cur);
                cur.left = maintain(cur.left);
                cur.right = maintain(cur.right);
                cur = maintain(cur);
            } else if (cur.right != null && cur.right.right != null && cur.left != null && cur.right.right.size > cur.left.size) {
                // RR型
                cur = leftRotate(cur);
                cur.left = maintain(cur.left);
                cur = maintain(cur);
            } else if (cur.right != null && cur.right.left != null && cur.left != null && cur.right.left.size > cur.left.size) {
                // RL型
                cur.right = rightRotate(cur.right);
                cur = leftRotate(cur);
                cur.left = maintain(cur.left);
                cur.right = maintain(cur.right);
                cur = maintain(cur);
            }
            return cur;
        }

        public void remove(K key) {
            if (key == null) {
                throw new RuntimeException("invalid parameter");
            }
            if (containsKey(key)) {
                root = delete(root, key);
            }
        }

        // 在cur这棵树上，删掉key所代表的节点
        // 返回cur这棵树的新头部
        private SBTNode<K, V> delete(SBTNode<K, V> cur, K key) {
            cur.size--;
            if (key.compareTo(cur.key) > 0) {
                cur.right = delete(cur.right, key);
            } else if (key.compareTo(cur.key) < 0) {
                cur.left = delete(cur.left, key);
            } else {
                if (cur.left == null && cur.right == null) {
                    cur = null;
                } else if (cur.left != null && cur.right == null) {
                    cur = cur.left;
                } else if (cur.left == null && cur.right != null) {
                    cur = cur.right;
                } else {
                    //有左有右
                    SBTNode<K, V> pre = null;
                    SBTNode<K, V> des = cur.right;
                    des.size--;
                    while (des.left != null) {
                        pre = des;
                        des = des.left;
                        des.size--; //des要移出整条边, 所以沿途size--
                    }
                    if (pre != null) {
                        pre.left = des.right;
                        des.right = cur.right;
                    }
                    des.left = cur.left; //cur.left一定不为空 是前提
                    //des.right == null的情况是, 要删除节点的right是他的后继节点,
                    //且该后继节点无right, 所以当后继节点替了他的位置之后, 右树为null
                    des.size = des.left.size + (des.right != null ? des.right.size : 0) + 1;
                    cur = des;
                }
            }
            // 可以决定是否在每次删除节点之后维持平衡
            // cur = maintain(cur);
            return cur;
        }

        public K firstKey() {
            if (root == null) {
                return null;
            }
            SBTNode<K, V> cur = root;
            while (cur.left != null) {
                cur = cur.left;
            }
            return cur.key;
        }

        public K lastKey() {
            if (root == null) {
                return null;
            }
            SBTNode<K, V> cur = root;
            while (cur.right != null) {
                cur = cur.right;
            }
            return cur.key;
        }


        // <= key
        public K floorKey(K key) {
            SBTNode<K, V> ans = null;
            SBTNode<K, V> cur = root;
            while (cur != null) {
                if (key.compareTo(cur.key) == 0) {
                    ans = cur;
                    break;
                } else if (key.compareTo(cur.key) < 0) {
                    cur = cur.left;
                } else {
                    ans = cur;
                    cur = cur.right;
                }
            }

            return ans != null ? ans.key : null;
        }

        // >= key
        public K ceilingKey(K key) {
            SBTNode<K, V> ans = null;
            SBTNode<K, V> cur = root;
            while (cur != null) {
                if (key.compareTo(cur.key) == 0) {
                    ans = cur;
                    break;
                } else if (key.compareTo(cur.key) < 0) {
                    ans = cur;
                    cur = cur.left;
                } else {
                    cur = cur.right;
                }
            }
            return ans != null ? ans.key : null;
        }


    }


}
