package StudentList;
public class KMP_algoritmus {

        public static boolean KMP(String Text,String Pattern)
        {
            int n = Text.length();
            int m = Pattern.length();
            int[] pr_table = prefix_szamitas(Pattern);
        /*for (int i=0;i<pr.length;i++) //kiíratás
        {
            System.out.println(pr[i]);
        }*/

            int q=0; //Az egyező karakterek száma
            boolean tartalmaz=false; //Mert nekünk elég 1 előfordulás
            int i=0;
            while (!tartalmaz && i<n){
                while (q > 0 && Text.charAt(i) != Pattern.charAt(q)) {
                    q = pr_table[q - 1];
                }

                if (Text.charAt(i) == Pattern.charAt(q)) {
                    q++; //Ha egyezik a 2 betű, akkor növeljük a leghosszabb egyezo mintat
                }

                if (q == m) {
                    tartalmaz=true; //kilépünk a ciklusból
                }
                i++;
            }
            return tartalmaz;
        }

        public static int[] prefix_szamitas(String P)
        {
            int []pi=new int[P.length()]; //prefix tábla
            pi[0]=0; //Az első elem mindig 0 a prefix táblában
            int k=0; //A leghosszabb prefix amit a mintában találtunk
            for (int i=1;i<P.length();i++)
            {
                while(k>0 && P.charAt(k)!=P.charAt(i)){
                    k=pi[k-1];
                }
                if (P.charAt(k)==P.charAt(i)) k++;
                pi[i]=k;
            }
            return pi;
        }
    }




