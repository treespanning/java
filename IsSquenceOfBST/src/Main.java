import java.util.Scanner;

public class Main {
    public static boolean VerifySquenceOfBSTCore(int[]sequence,int start,int end){
        if(start>=end){
            return true;
        }
        int root=sequence[end];
        int i=0;
        while(i<end&&sequence[i]<root){
            i++;
        }
        for(int j=i;j<end;j++){
            if(sequence[j]<root){
                return false;
            }
        }
        return VerifySquenceOfBSTCore(sequence,0,i-1)&& VerifySquenceOfBSTCore(sequence,i,end-1);
    }
    public static boolean VerifySquenceOfBST(int [] sequence) {
        if(sequence.length==0){
            return false;
        }
        return VerifySquenceOfBSTCore(sequence,0,sequence.length-1);
    }

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int[]array=new int[20];
        for(int i=0;i<20;i++){
            array[i]=scanner.nextInt();
        }
        System.out.println(VerifySquenceOfBST(array));
    }
}