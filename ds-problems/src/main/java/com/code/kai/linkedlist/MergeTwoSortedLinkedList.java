package com.code.kai.linkedlist;

/**
 * @author Mohan Sharma Created on 30/06/17.
 */
public class MergeTwoSortedLinkedList {

    public static long nChooseK(long n, long k){
        if(n < k)
            return 0;
        if(k == 0 || k == n)
            return 1;
        return nChooseK(n-1,k-1)+ nChooseK(n-1,k);
    }

    public static void main(String[] args) {
        System.out.println(nChooseK(100, 2));
    }
}
