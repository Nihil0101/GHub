package proj_krieg;

import java.util.Comparator;

public class Commander implements Comparable<Commander>{
    
    //String armyName;
    int armyNr;
    String armyCommander;
    int w1 ,w2, w3;
    int l1, l2, l3;
    
    
    // armijos nr ir vardas imami iš duomenų eilutės
    public Commander(String data) {
        int an = data.indexOf(';');
        //armyName = data.substring(0, an);
        armyNr = Integer.parseInt(data.substring(0, an));    
        armyCommander = data.substring(an+1);
        
    }     
    
    int victories(){ return 1*w1 + 10*w2 + 100*w3; } //s = 1 b=10 o=100
    
    @Override
    public int compareTo(Commander c) {
        return armyNr - c.armyNr;
    } 
//    public int compareTo(Commander c) { //jeigu naudot string armijų nr
//        if(c == null)
//        {
//            return -1;
//        }
//        int len1 = armyName.length();
//        int len2 = c.armyName.length();
//        int lim = Math.min(len1, len2);
//        char v1[] = armyName.toCharArray();
//        char v2[] = c.armyName.toCharArray();
//
//        int k = 0;
//        while (k < lim) {
//            char c1 = v1[k];
//            char c2 = v2[k];
//            if (c1 != c2) {
//                return c1 - c2;
//            }
//            k++;
//        }
//        return len1 - len2;
//    }    
    public static Comparator<Commander> byVictories = new Comparator<Commander>(){
        @Override
        public int compare(Commander c1, Commander c2) {
            return c2.victories() - c1.victories();
        }
    };
}

