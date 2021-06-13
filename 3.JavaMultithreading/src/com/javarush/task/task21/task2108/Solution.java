package com.javarush.task.task21.task2108;

/* 
Клонирование растений
*/

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Tree tree = new Tree("willow", new String[]{"s1", "s2", "s3", "s4"});
        Tree clone = null;
        try {
            clone = (Tree) tree.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        System.out.println(tree);
        System.out.println(clone);

        System.out.println(tree.branches);
        System.out.println(clone.branches);

        System.out.println("is trees the same: " + (tree == clone));
        System.out.println("is trees equal: " + (tree.equals(clone)));
        System.out.println("is trees brunches the same: " + (tree.branches == clone.branches));
        System.out.println("is trees brunches equal: " + (Arrays.equals(tree.branches, clone.branches)));
        System.out.println("is trees names the same: " + (tree.getName() == clone.getName()));

    }

    public static class Plant {
        private String name;

        public Plant(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public static class Tree extends Plant implements Cloneable{
        private String[] branches;

        @Override
        protected Object clone() throws CloneNotSupportedException {
            String[] br = new String[getBranches().length];
            for (int i = 0; i < getBranches().length; i++) {
                br[i] = getBranches()[i];
                //System.out.println(getBranches()[i]);
            }
            Tree nT = new Tree(getName(),br);
            return nT;
        }

        public Tree(String name, String[] branches) {
            super(name);
            this.branches = branches;
        }

        public String[] getBranches() {
            return branches;
        }
    }
}
