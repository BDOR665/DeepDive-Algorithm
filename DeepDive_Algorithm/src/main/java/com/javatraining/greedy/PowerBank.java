package com.javatraining.greedy;

import java.io.*;
import java.util.*;

public class PowerBank {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        // X 타입 충전기로만 충전이 가능한 보조 배터리의 개수
        int A = Integer.parseInt(input[0]);
        // Y 타입 충전기로만 충전이 가능한 보조 배터리의 개수
        int B = Integer.parseInt(input[1]);
        // X 타입,Y 타입 충전기로 충전이 가능한 보조 배터리의 개수
        int C = Integer.parseInt(input[2]);

        // 마트에서 판매하는 충전기의 개수
        int N = Integer.parseInt(br.readLine());

        // 충전기 가격과 타입 저장할 배열
        List<int[]> chargers = new ArrayList<>();
        // c = 충전기의 가격 t = 0 은 X 타입, t = 1 은 Y 타입
        for(int i=0; i<N; i++){
            input = br.readLine().split(" ");
            int c = Integer.parseInt(input[0]);
            int t = Integer.parseInt(input[1]);
            chargers.add(new int[]{c,t});
        }

        // 가격 기준으로 정렬
        Comparator<int[]> byPrice = (a, b) -> Integer.compare(a[0], b[0]);
        Collections.sort(chargers, byPrice);

        // 보조 배터리 개수
        int count = 0;
        // 충전기 가격의 합
        long total = 0;

        // 가장 저렴한 충전기 부터 구매
        for(int[] charger : chargers){
            int price = charger[0];
            int tpye = charger[1];

            // X 타입 충전기
            if(tpye == 0){
                // A가 남아있는 경우
                if(A > 0){
                    count ++;
                    total += price;
                    A--;
                    // C가 남아있는 경우
                } else if (C > 0){
                    count ++;
                    total += price;
                    C--;
                }
                // Y 타입 충전기
            } else{
                // B가 남아있는 경우
                if(B > 0){
                    count++;
                    total+= price;
                    B--;
                    // C가 남아있는 경우
                } else if (C>0){
                    count++;
                    total+=price;
                    C--;
                }
            }
        }

        // 동시에 충전할 수 있는 보조 배터리의 개수 / 충전기의 가격의 합
        System.out.println(count+" "+total);
    }
}
