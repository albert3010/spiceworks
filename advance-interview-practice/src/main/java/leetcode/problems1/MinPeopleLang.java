package leetcode.problems1;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.*;

public class MinPeopleLang {
    //        List<List<Integer>> books = Lists.newArrayList();
//        books.add(Lists.newArrayList(1,1));
//        int arr[][] = { {2,7,9},{3,6,1},{7,4,2} };


    private class node {

        node(int max, int sum) {
            this.max = max;
            this.sum = sum;
        }

        int max;
        int sum;
    }

    @Test
    public void smallestSufficientTeamTest() {
//        String req_skills[] = {"java", "nodejs", "reactjs"};
//
//        List<List<String>> people = Lists.newArrayList();
//        people.add(Lists.newArrayList("java"));
//        people.add(Lists.newArrayList("nodejs"));
//        people.add(Lists.newArrayList("reactjs", "nodejs"));
//
        String req_skills[] = {"algorithms","math","java","reactjs","csharp","aws"};

        List<List<String>> people = Lists.newArrayList();
        people.add(Lists.newArrayList("algorithms","math","java"));
        people.add(Lists.newArrayList("algorithms","math","reactjs"));
        people.add(Lists.newArrayList("java","csharp","aws"));
        people.add(Lists.newArrayList("reactjs","csharp"));
        people.add(Lists.newArrayList("csharp","math"));
        people.add(Lists.newArrayList("aws","java"));
        System.out.println(smallestSufficientTeam(req_skills, people));
    }

    private List<Integer> smallestSufficientTeam(String[] req_skills, List<List<String>> people) {

        HashMap<String, Boolean> skills = new HashMap<>();
        List<Integer> result = new ArrayList<>();

        int lastValidIndex = req_skills.length;
        for (int i = 0; i < req_skills.length; i++) {

            skills.put(req_skills[i], true);
        }

        while (lastValidIndex >= 0) {

            int n = people.size();

            int maxCountIndex = -1;
            int maxCount = 0;
            for (int i = 0; i < n; i++) {

                List<String> pskill = people.get(i);
                int count = 0;

                for (int j = 0; j < pskill.size(); j++) {
                    boolean avail = checkAvailFromRemaining(pskill.get(j), skills);
                    if (avail) {
                        count++;
                    }
                }
                if (maxCount < count) {
                    maxCountIndex = i;
                }
            }

            lastValidIndex = maxCountIndex;

            if (maxCountIndex >= 0) {
                UpdateSkillMapByRemovingFoundSkills(skills, maxCountIndex, people);
            }
            if (maxCountIndex >= 0) {
                result.add(lastValidIndex );
            }
        }

        return result;
    }

    void UpdateSkillMapByRemovingFoundSkills(HashMap<String, Boolean> skills, int maxCountIndex, List<List<String>> people) {
        List<String> pskill = people.get(maxCountIndex);
        int n = pskill.size();
        for (int i = 0; i < n; i++) {
            if (skills.get(pskill.get(i))) {
                skills.put(pskill.get(i), false);
            }
        }
    }

    boolean checkAvailFromRemaining(String pskill, HashMap<String, Boolean> skills) {
        return skills.get(pskill);
    }

}