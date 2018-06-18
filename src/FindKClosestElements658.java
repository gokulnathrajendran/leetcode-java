/**
 * Given a sorted array, two integers k and x, find the k closest elements to x
 * in the array. The result should also be sorted in ascending order. If there
 * is a tie, the smaller elements are always preferred.
 * 
 * Example 1:
 * Input: [1,2,3,4,5], k=4, x=3
 * Output: [1,2,3,4]
 * 
 * Example 2:
 * Input: [1,2,3,4,5], k=4, x=-1
 * Output: [1,2,3,4]
 * 
 * Note:
 * The value k is positive and will always be smaller than the length of the sorted array.
 * Length of the given array is positive and will not exceed 104
 * Absolute value of elements in the array and x will not exceed 104
 * 
 * UPDATE (2017/9/19):
 * The arr parameter had been changed to an array of integers (instead of a
 * list of integers). Please reload the code definition to get the latest
 * changes.
 */


public class FindKClosestElements658 {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int pos = binarySearch(arr, x, 0, arr.length-1);
        pos = updatePos(arr, x, pos);
        LinkedList<Integer> res = new LinkedList<>();
        int left = pos - 1;
        int right = pos;
        int i = 0;
        while (i < k) {
            if (left >= 0 && right < arr.length) {
                if (Math.abs(arr[left] - x) <= Math.abs(arr[right] - x)) {
                    res.addFirst(arr[left--]);
                } else {
                    res.addLast(arr[right++]);
                }
            } else if (left >= 0) {
                res.addFirst(arr[left--]);
            } else {
                res.addLast(arr[right++]);
            }
            i++;
        }
        return res;
    }

    public int binarySearch(int[] arr, int x, int lo, int hi) {
        if (lo == hi) return lo;
        if (hi - lo == 1) return hi;
        int mid = (lo + hi) / 2;
        if (arr[mid] == x) return mid;
        else if (arr[mid] > x) {
            return binarySearch(arr, x, lo, mid);
        } else {
            return binarySearch(arr, x, mid, hi);
        }
    }
    
    private int updatePos(int[] arr, int x, int pos) {
        if (arr[pos] == x || pos == 0) return pos;
        return (Math.abs(arr[pos-1]-x) <= Math.abs(arr[pos]-x)) ? pos - 1 : pos;
    }
  
    // /**
    //  * https://leetcode.com/problems/find-k-closest-elements/solution/
    //  */
    // public List<Integer> findClosestElements2(List<Integer> arr, int k, int x) {
    //     Collections.sort(arr, (a,b) -> a == b ? a - b : Math.abs(a-x) - Math.abs(b-x));
    //     arr = arr.subList(0, k);
    //     Collections.sort(arr);
    //     return arr;
    // }


    /**
     * https://leetcode.com/problems/find-k-closest-elements/discuss/106430/Updated-Java-Solution
     */
    public List<Integer> findClosestElements3(int[] arr, int k, int x) {
        int lo = 0;
        int hi = arr.length - k;
        int mid;
        while(lo < hi){
            mid = lo + (hi - lo) / 2;
            if(x - arr[mid] > arr[mid+k] - x)
                lo = mid + 1;
            else
                hi = mid;
        }

        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < k; i++)
            list.add(arr[lo+i]);
        return list;
    }

}