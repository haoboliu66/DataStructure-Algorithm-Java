package com.hliu.fundamental.tree.utils;

import com.hliu.fundamental.tree.TreeNode;

public class BinaryTreeUtils {

  public static TreeNode generateNode(String val) {
    if (val == null) {
      return null;
    }
    return new TreeNode(val);
  }
}
