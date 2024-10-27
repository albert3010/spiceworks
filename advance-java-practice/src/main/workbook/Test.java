import java.util.*;

public class Test {
    public static void main(String[] args) {
        System.out.println("test");

//        Queue queue = Queue.getQueueInstance();
//        Producer producer = new Producer(queue);
//
//        Consumer consumer1 = new Consumer("c1");
//        Consumer consumer2 = new Consumer("c2");
//        Consumer consumer3 = new Consumer("c3");
//
//        queue.subscriber(consumer1);
//
//
//        queue.subscriber(consumer2);
//        queue.subscriber(consumer3);
//
//        producer.produceMessage("m1");
//        producer.produceMessage("m2");
//        producer.produceMessage("m3");
//        producer.produceMessage("m4");
        int [] aa = {-1,0,0,1,1,1};
        //System.out.println(stringSequence("abc"));
//        System.out.println(minOperations(aa));
//        System.out.println(getPrimes(1000000));
//        System.out.println(findSubtreeSizes(aa, "abaabc"));
        System.out.println(lengthAfterTransformations("jqktcurgdvlibczdsvnsg", 7517));
    }

    static public int[] findSubtreeSizes(int[] parent, String s) {

        Map<Integer, List<Integer>> tree = new HashMap<>();
        int n = parent.length;
        int [] size =new int[n];
        for(int i =1; i< n ;i++){
            int curr = i;
            char c = s.charAt(i);

            while(curr!=-1){
                int newPrt = parent[curr];
                if(newPrt ==-1) break;
                if(s.charAt(newPrt) == c){
                    parent[i] = newPrt;
                    break;
                }else {
                    curr = newPrt;
                }
            }
        }
        for(int i =1; i< n ;i++){
            int par = parent[i];
            tree.putIfAbsent(par, new ArrayList<>());
            tree.get(par).add(i);
        }
        dfsHelper(0, tree, size);
        return size;
    }
    static int dfsHelper(int node,    Map<Integer, List<Integer>> tree,  int [] size){
        int count =1;
        for(int child  : tree.getOrDefault(node, new ArrayList<>())){
            count+=dfsHelper(child, tree, size);
        }
        size[node] = count;
        return count;
    }
    static public int lengthAfterTransformations(String s, int t) {
        int [] count = new int[26];
        for(char c : s.toCharArray()){
            count[c-'a']++;
        }
        int mod = 1000000000 + 7;
        for(int i=1; i<=t;i++){
            int [] countTmp = new int[26];
            for(int d =0; d< 25 ; d++){
                if(count[d]>0){
                    countTmp[d+1] = count[d]%mod;
                }
            }
            if(count[25]>0){
                countTmp[0]  = count[25]%mod;
                countTmp[1] = (countTmp[1]+count[25])%mod;
            }
            count = countTmp;
        }
        int ans = 0;
        long x =0;
        for(int i =0; i< 26 ; i++){
            int tt = (count[i])%mod;
            ans = (tt+ans)%mod;
            x = (x+count[i])%mod;
        }
        System.out.println(x);
        return ans;
    }

    static List<Integer> getPrimes(int k){
        int l = (int)Math.sqrt(k)+1;
        boolean [] prime = new boolean[l+1];
        for(int i =3; i<=l ; i+=2){
            for(int j =i*i ; j<=l ; j+=i){
                prime[j] = true;
            }
        }
        List<Integer> primes = new ArrayList<>();
        for(int i=3; i< l ;i+=2){
            if(!prime[i]){
                System.out.println(i);
                primes.add(i);
            }
        }
        return primes;
    }
    static public int minOperations(int[] nums) {
        int minOperation =0;
        int n = nums.length;

        for (int i = 1; i < n; i++) {
            if(nums[i-1]> nums[i]){
                int x = updateLeft(i, nums);
                if(x==0) return -1;
                minOperation+=x;
            }
            if(nums[i-1]> nums[i]){
                int k = isPossible(nums[i]);
                if(k==0) return -1;
                nums[i] = k;
                minOperation++;
            }
            if(nums[i-1]> nums[i]){
                return -1;
            }
        }
        return minOperation;

    }
   static int  updateLeft(int i , int [] nums){
        int t =0;
        while (i>0){
            int curr = nums[i];
            if(nums[i-1] > curr){
                int k = isPossible(nums[i-1]);
                if(k==0 || k>curr) return 0;
                t++;
                nums[i-1] = k;
            }
            i--;
        }
        return t;

    }
    static int isPossible(int num ){
        int v = (int)Math.sqrt(num)+1;
        for (int i = 2; i <=v; i++) {
            if(num%i==0){
                return i;
            }
        }
        return 0;
    }



    public int numberOfSubstrings(String s, int k) {
        int [] count = new int[26];
        int n = s.length();
        int i =0;
        int prev =0;
        int ans =0;
        while (i<n){
            char c = s.charAt(i);
            count[c-'a']++;
            while (prev<=i && check(count, k)){
                ans+= n-i;
                count[prev-'a']--;
                prev++;
            }
            i++;

        }
        return ans;

    }
    boolean check(  int [] count,  int k){
        for (int i =0; i< 26;i++){
            if (count[i]>=k) return true;
        }
        return false;
    }

    static public List<String> stringSequence(String target) {
        List<String> ans = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int i =0;
        for (char c : target.toCharArray()){
            sb.append("a");
            updateList('a', sb, ans, i);
            i++;
        }
        return ans;

    }
    static void updateList(char c, StringBuilder sb, List<String> ans, int i){
        int k = c-'a';
        int l = sb.charAt(i)-'a';
        ans.add("a");
        System.out.println();
        while (k!=l){
            k = (k+1)%26;
            char cc = (char)(k+'a');
            sb.deleteCharAt(i);
            sb.append(cc);
            ans.add(sb.toString());
        }
    }
}
