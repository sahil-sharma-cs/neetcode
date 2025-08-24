# https://neetcode.io/problems/top-k-elements-in-list?list=blind75


#solution 1: use bucket sort
/*
1. again count the frequency of number and put it in a map
2. fill a resulting array with -1 first and then put the frequency number in index of array, 
for eg if 3 occurs 5 times it will be at 5th index of array.
3. return k elements from the last as the max frequency will be at last 
Note: you need to add the array size as n+1 :

nums = [7,7,7]
n = 3
frequency of 7 = 3

Needs to go into buckets[3]

That’s why array size must be 3 + 1 = 4
*/    
class Solution {
   public static int[] topKFrequent(int[] nums, int target) {

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {

            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + 1);
            } else {
                map.put(nums[i], 1);
            }
        }

        //array of lists
        //Index 0 → null
        //Index 1 → [10]
        //Index 2 → [20, 30]
        //Index 3 → null
        List<Integer>[] arr = new List[nums.length + 1];

        for (Map.Entry<Integer, Integer> m : map.entrySet()) {
            int key = m.getKey();
            int value = m.getValue();
            if(arr[value] == null) {
                arr[value] = new ArrayList<>();
            }
            arr[value].add(key);
        }

        ArrayList<Integer> rArr = new ArrayList<>();

        for (int i = arr.length-1; i>=0 && target!=0; i--) {

            if(arr[i]!=null) {
                for(int j = 0; j<arr[i].size(); j++) {
                    rArr.add(arr[i].get(j));
                    target--;
                }
            }
        }

        return rArr.stream().mapToInt(i->i).toArray();
    }

    public static void main(String[] args) {
        System.out.println(topKFrequent(new int[]{1,2,2,3,3,3}, 2));
    }

    /* solution 2: basically put frequency of numbers in a map and the iterate that map target (k) times so that you have 
    added k most occuring numbers in the resulting array
    */  
    public static int[] topKFrequent(int[] nums, int target) {
    
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<nums.length; i++) {
      
            if(map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + 1);
            } else {
                map.put(nums[i], 1);
            }
        }
      
        ArrayList<Integer> rArr = new ArrayList();
      
      
        while(target>0) {
            int maxIndex = -1;
            int maxNum = -1;
            for(Map.Entry<Integer, Integer> m : map.entrySet()) {
                if(m.getValue()>maxNum) {
                    maxNum = Math.max(m.getValue(), maxNum);
                    maxIndex = m.getKey();
                }
      
            }
            rArr.add(maxIndex);
            map.remove(maxIndex);
            target--;
        }
        return rArr.stream().mapToInt(Integer::intValue).toArray();
    }

}
