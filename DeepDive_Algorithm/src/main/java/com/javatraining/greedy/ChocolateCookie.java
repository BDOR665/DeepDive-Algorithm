package com.javatraining.greedy;

import java.io.*;
import java.util.*;

public class ChocolateCookie {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 쿠키의 개수
        int N = Integer.parseInt(br.readLine());

        // i번 쿠기의 맛있는 정도
        String[] input = br.readLine().split(" ");
        int [] A = new int[N+1];
        for(int i = 1; i<N+1; i++){
            A[i] = Integer.parseInt(input[i-1]);
        }

        // (Ai, i) 쌍을 리스트에 저장
        List<int[]> cookies = new ArrayList<>();
        for(int i=1; i<N+1; i++){
            cookies.add(new int[] {A[i], i});
        }

        // 구름이가 먹은 쿠키의 맛있는 정도의 곱을 최대화 하려면 0이 최대한 되지 않게 작성해야한다.
        // 즉, 맛있는 정도가 낮은 정도를 먼저 먹는다
        // 작은 것부터 정렬한다
        Collections.sort(cookies, Comparator.comparingInt(o->o[0]));

        // 작은 것부터 먹어도 0이 되는 경우는 사전순으로 제일 앞서는 순서를 출력한다
        for(int i=0; i<N; i++){
            if(cookies.get(i)[0] - i <= 0){
                for(int j=1; j<N+1; j++){
                    System.out.print(j+" ");
                }
                return;
            }
        }

        // 0보다 큰 경우에는 정렬된 인덱스 순서대로 출력
        for(int[] cookie : cookies){
            System.out.print(cookie[1] + " ");
        }
    }
}
