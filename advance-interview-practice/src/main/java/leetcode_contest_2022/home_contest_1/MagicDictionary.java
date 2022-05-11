package leetcode_contest_2022.home_contest_1;

class MagicDictionary {
    class Node{
        char c;
        Node [] childs;
        boolean isWord;
        Node(char c){
            this.c =c;
            childs = new Node[26];
            this.isWord = false;
        }
        Node(){
            childs = new Node[26];
            this.isWord = false;
        }
    }
    Node root;

    public MagicDictionary() {
        root = new Node();
    }

    public void buildDict(String[] dictionary) {
        for(String word: dictionary){
            int l = word.length();
            updateTrieNodes(root, 0, word, l);
        }
    }
    void updateTrieNodes(Node root, int i, String word, int l){
        if(i==l) return;
        char c = word.charAt(i);
        int k = c-'a';
        Node nd = new Node(c);
        if(root.childs[k]!=null){
            nd = root.childs[k];
        }
        if(i==l-1){
            nd.isWord = true;
        }
        root.childs[k] = nd;
        updateTrieNodes(nd, i+1, word, l);
    }

    public boolean search(String searchWord) {
        int l = searchWord.length();
        return removeAndSearch(root, 0, searchWord, l, 1);
    }
    boolean removeAndSearch(Node root, int i, String searchWord, int l, int k){

        if(k<0) return false;
        if(i==l) {
            System.out.println("found call  dd "+k+"  "+ i);
            if(root.isWord && k==0)
                return true;
            return false;
        }
        System.out.println("found call  rr "+root.c+"  "+ i);
        for(Node nd: root.childs){
            if(nd!=null){
                System.out.println("found call  cc "+searchWord.charAt(i)+"  "+ nd.c);
                if(searchWord.charAt(i)!=nd.c){
                    System.out.println("found call  ff "+k+"  "+ nd.c);
                    if(removeAndSearch(nd, i+1, searchWord, l, k-1))
                        return true;
                }else{
                    if(removeAndSearch(nd, i+1, searchWord, l, k)){
                        return true;
                    }
                }

            }
        }
        return false;
    }
    public static void main(String[] args) {
        MagicDictionary obj = new MagicDictionary();
        String [] dictionary = {"ha", "he"};
        obj.buildDict(dictionary);
        boolean param_2 = obj.search("he");
    }
}

