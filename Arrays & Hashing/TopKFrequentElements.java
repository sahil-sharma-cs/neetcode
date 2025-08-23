# https://neetcode.io/problems/top-k-elements-in-list?list=blind75

/* solution 1: basically put frequency of numbers in a map and the iterate that map target (k) times so that you have 
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

#solution 2: use bucket sort
