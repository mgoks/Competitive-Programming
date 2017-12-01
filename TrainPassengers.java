/**
Train Passengers, Kattis
*/

import java.util.Scanner;

public class TrainPassengers {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int cap = sc.nextInt();
        int numStations = sc.nextInt();
        int tempFill = 0;
        int leavers;
        int enters;
        int stayers;
        boolean possible = true;
        for(int i=0; i<numStations; i++){
            leavers = sc.nextInt();
            enters = sc.nextInt();
            stayers = sc.nextInt();
            if(tempFill - leavers < 0){
                possible = false;
                break;
            }else{
                tempFill -= leavers;
            }
            if(tempFill+enters > cap){
                possible = false;
                break;
            }else{
                tempFill += enters;
            }
            if(stayers > 0 && tempFill != cap){
                possible = false;
                break;
            }
            
        }
        if (tempFill != 0) {
            possible = false;
        }
        if(possible){
            System.out.println("possible");
        }else{
            System.out.println("impossible");
        }
        
        sc.close();
    }
}
