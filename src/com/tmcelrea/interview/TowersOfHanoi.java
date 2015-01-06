package com.tmcelrea.interview;

import java.util.ArrayList;
import java.util.List;

public class TowersOfHanoi {

    Integer size = 0;

    Integer step = 0;

    List<List<Integer>> towers = new ArrayList<List<Integer>>();
    List<Integer> left = new ArrayList<Integer>();
    List<Integer> right = new ArrayList<Integer>();
    List<Integer> spare = new ArrayList<Integer>();

    List<String> towerNames = new ArrayList<String>();

    public TowersOfHanoi(int size, String leftName, String spareName, String rightName) {
        this.reset(size, leftName, spareName, rightName);
    }

    private void reset(Integer size, String leftName, String spareName, String rightName) {
        this.size = size;
        this.towerNames.add(leftName);
        this.towerNames.add(spareName);
        this.towerNames.add(rightName);
        for(int i=size;i>0;i--) {
            left.add(i);
            right.add(0);
            spare.add(0);
        }
        towers.add(left);
        towers.add(spare);
        towers.add(right);
    }

    public static void main(String[] args) {
        TowersOfHanoi towers = new TowersOfHanoi(5, "A", "X" ,"B");
        towers.print();
        towers.solve(new Integer(towers.size.intValue()), towers.towerNames.get(0), towers.towerNames.get(1), towers.towerNames.get(2));
    }

    public void solve(Integer n, String from, String inter, String to) {
        this.step++;
        if (n == 1){
            System.out.println("\nDisk " + n + " from " + from + " to " + to);
            push(to, pop(from));
        }else {
            solve(n - 1, from, to, inter);
            System.out.println("\nDisk " + n + " from " + from + " to " + to);
            push(to, pop(from));
            solve(n - 1, inter, from, to);
        }
    }

    private Integer pop(String towerName) {
        List<Integer> tower = towers.get(towerNames.indexOf(towerName));
        Integer retval = 0;
        for(int i=0;i<tower.size();i++) {
            if((i==tower.size()-1) || (tower.get(i+1)==0)) {
                retval = tower.get(i);
                tower.set(i, 0);
                break;
            }
        }
        return retval;
    }

    private void push(String towerName, Integer disc) {
        List<Integer> tower = towers.get(towerNames.indexOf(towerName));
        for(int i=0;i<tower.size();i++) {
            if(tower.get(i)==0) {
                tower.set(i, disc);
                break;
            }
        }
        print();
    }

    public Boolean isFinished() {
        return right.get(size-1).intValue() == 1;
    }

    public void print() {
        System.out.println("");
        System.out.println("Step: " + step + " Finished?: " + isFinished().toString().toUpperCase());
        int x = 1;
        for(List<Integer> tower: towers) {
            System.out.print("||" + String.format("%1$-" + 5 + "s", towerNames.get(x-1)) + "|| ");
            for(Integer level: tower) {
                System.out.print("-"+level+"-");
            }
            x++;
            System.out.println("");
        }
    }

}
