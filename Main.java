//java code

import java.io.*;
import java.util.*;


public class Main{

    static int D;
    static int I;
    static int S;
    static int V;
    static int F;
    static int countTrue = 0;
    static int numberOfLights;
    static ArrayList<Integer> B = new ArrayList<>();
    static ArrayList<Integer> E = new ArrayList<>();
    static ArrayList<Integer> L = new ArrayList<>();
    static ArrayList<String> streetName = new ArrayList<>();
    static ArrayList<ArrayList<String>> streetPath = new ArrayList<>();
    static String in_files[] = {"a", "b", "c", "d", "e", "f"};

    public static void main(String[] args) throws Exception {
        for(int i = 0; i < in_files.length; i++){
            System.out.println("Run for file " + in_files[i]);
            dataInput(in_files[i] + ".txt");
            computeIntersections();
        }
    }

    public static void dataInput(String filename) throws Exception{
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String str = br.readLine();
        String ar[] = str.split(" ");
        
        D = Integer.valueOf(ar[0]);
        I = Integer.valueOf(ar[1]);
        S = Integer.valueOf(ar[2]);
        V = Integer.valueOf(ar[3]);
        F = Integer.valueOf(ar[4]);

        int idx_S = 0;
        while(idx_S++ < S){
            str = br.readLine();
            ar = str.split(" ");
            B.add(Integer.valueOf(ar[0]));
            E.add(Integer.valueOf(ar[1]));
            streetName.add(ar[2]);
            L.add(Integer.valueOf(ar[3]));
        }
        int idx_V = 0;
        while(idx_V++ < V){
            str = br.readLine();
            ar = str.split(" ");
            int k = Integer.valueOf(ar[0]);
            ArrayList<String> temp = new ArrayList<>();
            for(int i = 1; i <= k; i++){
                temp.add(ar[i]);
            }
            streetPath.add(temp);
        }
        br.close();
    }
    
    public static void computeIntersections(){
        boolean[] is_visited = new boolean[I];
        for(int j = 0; j < streetPath.size(); j++){
            ArrayList<String> temp = new ArrayList<>();
            temp = streetPath.get(j);
            for(int i = 0; i < streetPath.get(j).size()-1; i++){
                
                int idx_1 = streetName.indexOf(temp.get(i));
                int idx_2 = streetName.indexOf(temp.get(i+1));
                if(B.get(idx_1) == E.get(idx_2)){
                    is_visited[B.get(idx_1)] = true;
                }else if(B.get(idx_2) == E.get(idx_1)){
                    is_visited[B.get(idx_2)] = true;
                }
            }
        }   

        for(int i = 0; i < is_visited.length; i++) if(is_visited[i]) countTrue++;
        numberOfLights = countTrue;
        System.out.println(numberOfLights);

    }

    public static void dataOutput(String filename) throws Exception{
        FileWriter fw = new FileWriter(filename + ".out");
    }
}