
import java.util.Scanner;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ForkJoinPool;


public class MyMergeSort extends RecursiveAction{ //

    public static void main(String[] args) {
        
        Scanner s = new Scanner(System.in);
        System.err.println("Loading file.");
        
        while(s.hasNextLine()) {
            //TODO:read in the file from stdin , run mergesort, and time it.
            String[] sNums = s.nextLine().split(" ");
            int len = sNums.length;
            int z;
            int[] nums = new int[len];
            int[] nums2 = new int[len];

            long startTime;
            long endTime;
            long duration;

            for(int x = 0; x < len; x++){
                z = Integer.parseInt(sNums[x]);
                nums[x] = z;
                nums2[x] = z;
            }

            startTime = System.nanoTime();
            mergeSort(nums, len);
            endTime = System.nanoTime();

            duration = endTime - startTime;
            System.out.println("Regular: " + duration);


            MyMergeSort MMS = new MyMergeSort(nums2, nums.length);
            
            startTime = System.nanoTime();
            MMS.compute();
            endTime = System.nanoTime();

            duration = endTime - startTime;
            System.out.println("Compute: " + duration);
        }

        s.close();
    }

    public int[] a;
    public int n;

    public MyMergeSort(int[] a, int n) {
        // store the merge variables or the mergeSort variables
        this.a = a;
        this.n = n;
    }

    
    public void compute(){
        if (n < 2) {
            return;
        }

        int mid = n / 2;
        int[] l = new int[mid];
        int[] r = new int[n - mid];
        
        
        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
        }

        invokeAll(new MyMergeSort(l, mid), new MyMergeSort(r, n - mid));
        
        merge(a, l, r, mid, n - mid);
    }
    

    public static void mergeSort(int[] a, int n) {

        if (n < 2) {
            return;
        }

        int mid = n / 2;
        int[] l = new int[mid];
        int[] r = new int[n - mid];
        
        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
        }
    
        mergeSort(l, mid);
        mergeSort(r, n - mid);
        
        merge(a, l, r, mid, n - mid);
    }

    

    public static void merge(int[] a, int[] l, int[] r, int left, int right) {
	
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < left && j < right) {

            if (l[i] <= r[j]) {
                a[k++] = l[i++];
            }

            else {
            a[k++] = r[j++];
            }

        }

        while (i < left) {
            a[k++] = l[i++];
        }

        while (j < right) {
            a[k++] = r[j++];
        }

    }

}