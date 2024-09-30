import org.junit.Assert;
import org.junit.Test;
import test.BusJourney;
import test.JourneyRepo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestRed {
    @Test
    public void addBusJourneyTest(){
        BusJourney busJourney1 = new BusJourney(1, LocalDate.now(), LocalTime.of(9, 0),  LocalTime.of(18, 0), "BLR", "HLD", 100, "");
        BusJourney busJourney2 = new BusJourney(2, LocalDate.now().plusDays(1), LocalTime.of(9, 0),  LocalTime.of(18, 0), "BLR", "HLD", 100, "");

        JourneyRepo journeyRepo = new JourneyRepo();
        journeyRepo.addJourney(busJourney1);
        journeyRepo.addJourney(busJourney2);

        // empty

        List<BusJourney> busJourneyList = journeyRepo.searchJourney(LocalDate.now(), "BLR", "HLD", 10);
        Assert.assertEquals(1, busJourneyList.size());
        Assert.assertEquals(1, busJourneyList.get(0).getBusId());

        List<BusJourney> busJourneyList2 = journeyRepo.searchJourney(LocalDate.now().plusDays(1), "BLR", "HLD", 10);
        Assert.assertEquals(1, busJourneyList2.size());
        Assert.assertEquals(2, busJourneyList2.get(0).getBusId());

        List<BusJourney> busJourneyList3 = journeyRepo.searchJourney(LocalDate.now().plusDays(1), "BLR", "HLD", 110);
        Assert.assertTrue(busJourneyList3.isEmpty());

        List<BusJourney> busJourneyList4 = journeyRepo.searchJourney(LocalDate.now().plusDays(1), "BLR", "HLD", 0);
        Assert.assertTrue(busJourneyList3.isEmpty());
    }

    public static void main(String[] args) {
//        System.out.println(kthCharacter(5));
        char[][] board = {
                {'*', '*', '*', '*'},
                {'-', '-', '*', '-'},
                {'*', '*', '*', '-'},
                {'-', '#', '-', '#'},
                {'-', '#', '-', '#'}
        };
        System.out.println(minimumObstaclesToRemove(board));
//        System.out.println(countOfSubstrings("ieaouqqieaouqq", 1));

//        String[] codes = new String[]{"0211","639"};
//        String[] ans = solution("18114539",codes);
//        for(String s: ans){
//            System.out.println(s);
//        }

    }
    static String[] solution(String panel, String[] codes){
        List<String> list = new ArrayList<>();
        for( String s : codes){
            list.addAll(find(panel,s));
        }

        return list.toArray(new String[0]);

    }

    public static List<String> find(String panel, String code){
        int n = code.length();
        int len = panel.length();
        List<String> list = new ArrayList<>();
        for(int i=2;i<n;i++){
            String s =code.substring(0,i);
            String pattern = code.substring(i);
            int index = Integer.parseInt(s);
            if(len < index){
                list.add("not found");
            }else{
                list.add(checkPatter(panel,pattern,index));
            }

        }

        return list;


    }

    public static String checkPatter(String panel, String pattern, int index){
        int j = 0;
        for(int i=index;i<panel.length() && j < pattern.length(); i++){
            if(panel.charAt(i) != pattern.charAt(j)){
                return "not found";
            }
            j++;
        }

        return pattern;
    }
    public static int countOfSubstrings(String word, int k) {
        int l = word.length();
        int j =0;
        int i=0;
        int [] right = new int[l];
        int contIndex =l;
        for(int x =l-1; x>=0 ; x--) {
            right[x] = contIndex;
            if (!isVowel(word.charAt(x))) {
                contIndex = x;
            }

        }
        int ans =0;
        int [] cache = new int[26];
        while (j<=i && i<l) {
            char c = word.charAt(i);
            cache[c-'a']++;
            while (j<=i && i<l && isKMore(cache, k)){
                char prev = word.charAt(j);
                cache[prev-'a']--;
                j++;
            }
            while (j<=i && i<l && isValid(cache, k)){
                int rightIndex = right[i];
                ans+=rightIndex -i;
                char prev = word.charAt(j);
                cache[prev-'a']--;
                j++;
            }
         i++;
        }
        System.out.println();
        return ans;
    }
    static boolean isVowel(char c){
        return (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c== 'u' );
    }

    public static int countOfSubstrings2(String word, int k) {
        int l = word.length();
        int ans =0;
        for(int i =0; i<l;i++){
            int [] cache = new int[26];
            for(int j =i; j< l ;j++){
                char c = word.charAt(j);
                cache[c-'a']++;
                if(isValid(cache, k)){
                    ans++;
                }
            }
        }
        return ans;
    }

    public static int minimumObstaclesToRemove(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        int bottomRow = -1;
        for (int i = m-1; i >=0 && bottomRow==-1 ; i--) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '*') {
                    bottomRow = i;
                    break;
                }
            }
        }
        int distanceToMove = m-bottomRow-1;
        int minRemove=0;
        System.out.println(distanceToMove);
        for (int j = 0; j < n; j++) {
            minRemove+=getMinToRemoveForCol(board, j, distanceToMove, m);
        }
        return minRemove;
    }

    static int getMinToRemoveForCol(char[][] board, int j, int dis, int m){
        int maxDis =0;
        int count=0;
        for(int i =0; i< m ; i++){
            if (board[i][j] == '*') {
                maxDis = Math.max(maxDis, i+dis);
            }
            if (board[i][j] == '#') {
                if(i<= maxDis){
                    count++;
                }
            }
        }
        return count;
    }


    static boolean isValid(int cache[] , int k){
        Set<Integer> vw = new HashSet<>();
        int cnt =0;
        for(int i =0; i< 26 ; i++){
            char c = (char)('a'+i);
            if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c== 'u' ){
                if(cache[i]>0)
                    vw.add(i);
            }else {
                cnt+=cache[i];
            }
        }
        return cnt==k && vw.size()==5;
    }
    static boolean isKMore(int cache[] , int k){
        Set<Integer> vw = new HashSet<>();
        int cnt =0;
        for(int i =0; i< 26 ; i++){
            char c = (char)('a'+i);
            if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c== 'u' ){
                if(cache[i]>0)
                    vw.add(i);
            }else {
                cnt+=cache[i];
            }
        }
        return cnt>k;
    }

    public static char kthCharacter(int k) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        k--;
        while (k>0){
            int l = list.size();
            for( int i =0;i<l;i++){
                int j = list.get(i);
                int x = (j+1)%26;
                list.add(x);
                k--;
                if(k==0) return (char)('a'+x);
            }
        }
        return 'a';
    }


}
