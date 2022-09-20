package com.sameen.study;

import java.util.Arrays;
import java.util.HashMap;

public class Main {

  public static void main(String[] args) {
    candy(new int[]{1, 0, 2});
  }

  public static int candy(int[] ratings) {
    if (ratings.length == 1) {
      return 1;
    }
    int[] res = new int[ratings.length];
    res[0] = 1;
    for (int i = 1; i < res.length; i++) {
      res[i] = ratings[i] > ratings[i - 1] ? res[i-1] + 1 : 1;
    }
    int count=0;
    for (int i = res.length - 2; i >= 0; i--) {
      if (ratings[i] > ratings[i + 1] && res[i] <= res[i + 1]) {
        res[i] = res[i + 1] + 1;
      }
      count+=res[i];
    }
    return count+res[res.length-1];
  }

}
