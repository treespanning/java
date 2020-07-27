import java.util.ArrayList;
public class Main {
    public static ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> list=new ArrayList<>();
        if(k>input.length||input==null||k<0){
            return list;
        }
        for(int i=0;i<input.length-1;i++){
            for(int j=i+1;j<input.length;j++){
                if(input[i]>input[j]){
                    int temp=input[i];
                    input[i]=input[j];
                    input[j]=temp;
                }
            }
        }
        for(int i=0;i<k;i++){
            list.add(input[i]);
        }
        return list;
    }
    public static void main(String[] args) {
        int[]array={4,5,1,6,2,7,3,8};
        int k=4;
        System.out.println(GetLeastNumbers_Solution(array,k));
    }
}
