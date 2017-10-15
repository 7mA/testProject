package com.guo.demo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//废弃代码，有误

public class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList();
        Set<String> set = new HashSet();
        StringBuffer sb = new StringBuffer(s);
        int lpos = 0, rpos;
        if(sb.length() == 0){
            res.add(s);
            return res;
        }
        while(sb.charAt(lpos) != '('){
            if(sb.charAt(lpos) == ')') sb.deleteCharAt(lpos);
            else lpos++;
            if(lpos >= sb.length() || sb.toString().equals("")){
                res.add(sb.toString());
                return res;
            }
        }
        rpos = sb.length() - 1;
        while(sb.charAt(rpos) != ')'){
            if(sb.charAt(rpos) == '(') sb.deleteCharAt(rpos);
            rpos--;
            if(rpos < 0 || sb.toString().equals("")){
                res.add(sb.toString());
                return res;
            }
        }
        int size = sb.length();
        int lp = charCount(sb.toString(), '(');
        int rp = charCount(sb.toString(), ')');
        int del = Math.abs(lp - rp);
        if(del == 0){
            res.add(sb.toString());
            return res;
        }
        if(lp > rp){
            rip(sb, del, set, size, '(', 0);
        }else{
            rip(sb, del, set, size, ')', 0);
        }
        for(String str:set){
            res.add(str);
        }
        return res;
    }

    public int charCount(String str, char ch){
        int count = 0;
        while(str.indexOf(ch) != -1){
            count++;
            str = str.substring(str.indexOf(ch) + 1);
        }
        return count;
    }

    public void rip(StringBuffer s, int del, Set<String> set, int size, char p, int start){
        if(del == 0){
            set.add(s.toString());
            return;
        }
        if(start >= size - 1) return;
        System.out.println(start);
        System.out.println(s.toString());
        for(int j = start; j < size - 1; j++){
            if(s.charAt(j) == p){
                StringBuffer temp = s;
                rip(temp, del, set, temp.toString().length(), p, j + 1);
                temp.deleteCharAt(j);
                rip(temp, del - 1, set, temp.toString().length(), p, j);
            }
        }
    }
}
