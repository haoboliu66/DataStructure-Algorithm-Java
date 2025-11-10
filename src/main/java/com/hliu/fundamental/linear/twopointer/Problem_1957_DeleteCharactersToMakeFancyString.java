package com.hliu.fundamental.linear.twopointer;

public class Problem_1957_DeleteCharactersToMakeFancyString {

  // two pointer without extra space, even better, cleaner code
  public String makeFancyString3(String s) {
    char[] str = s.toCharArray();
    int count = 0;
    char curr = str[0];
    int writeIndex = 0;
    for (int i = 0; i < str.length; i++) {
      if (str[i] == curr) {
        count++;
        if (count < 3) {   // if count < 3, write and move to the next index
          str[writeIndex++] = str[i];
        }
        // if count >= 3, do nothing, just move to the next index
        // e.g. abbbbbbcc, we won't write until we reach the first c
      } else {
        // xxxy
        curr = str[i];
        count = 1;
        str[writeIndex++] = str[i];
      }
    }
    return new String(str, 0, writeIndex);
  }

  // two pointer without extra space
  public String makeFancyString2(String s) {
    char[] str = s.toCharArray();
    int count = 0;
    char curr = str[0];
    int writeIndex = 0;
    for (int i = 0; i < str.length; i++) {
      if (str[i] == curr) {
        count++;
        if (count < 3) {   // if count < 3, write and move to the next index
          str[writeIndex++] = str[i];
        } else {
          // if count == 3, keep moving until see a different char
          while (i < str.length && str[i] == curr) {
            i++;
          }
          if (i >= str.length) {
            break;
          }
          // we see a different char with a valid index [i]
          str[writeIndex++] = str[i];
          curr = str[i];  // set curr to the new char
          count = 1;  // reset count
        }
      } else {
        // xxxy
        curr = str[i];
        count = 1;
        str[writeIndex++] = str[i];
      }
    }
    return new String(str, 0, writeIndex);
  }


  public String makeFancyString1(String s) {
    char[] str = s.toCharArray();
    int count = 0;
    char curr = str[0];
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < str.length; i++) {
      if (str[i] == curr) {
        count++;
        if (count < 3) {   // if count < 3, append and move to the next index
          sb.append(str[i]);
        } else {
          // if count == 3, keep moving until see a different char
          while (i < str.length && str[i] == curr) {
            i++;
          }
          if (i >= str.length) {
            break;
          }
          // we see a different char with a valid index [i]
          sb.append(str[i]);
          curr = str[i];  // set curr to the new char
          count = 1;  // reset count
        }
      } else {
        // xxxy
        curr = str[i];
        count = 1;
        sb.append(str[i]);
      }
    }
    return sb.toString();
  }


  public String makeFancyString(String s) {
    char[] str = s.toCharArray();
    int count = 0;
    char curr = str[0];
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < str.length; ) {
      if (str[i] == curr) {
        count++;
        if (count < 3) {   // if count < 3, append and move to the next index
          sb.append(str[i++]);
        } else {
          // if count == 3, keep moving until see a different char
          while (i < str.length && str[i] == curr) {
            i++;
          }
          if (i >= str.length) {
            break;
          }
          // we see a different char with a valid index [i]
          curr = str[i];  // set curr to the new char
          count = 0;  // reset count
        }
      } else {
        curr = str[i];
        count = 1;
        sb.append(str[i++]);
      }
    }
    return sb.toString();
  }

}
