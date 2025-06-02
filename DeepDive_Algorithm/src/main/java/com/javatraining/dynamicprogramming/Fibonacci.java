package com.javatraining.dynamicprogramming;

import java.io.*;

public class Fibonacci {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] fibo = new long[N+1];

        // 첫 번째 / 두 번째 항일 경우 바로 출력
        if(N == 1 || N == 2){
            System.out.println(N-1);
            return;
        }

        fibo[0] = 0;
        fibo[1] = 0;
        fibo[2] = 1;

        // 모듈러(%) 연산은 누적합 도중에 계속 나누어줘야 에러가 나지 않는다
        for(int i = 3; i<N+1; i++){
            fibo[i]=(fibo[i-1]+fibo[i-2])%1000000007;
        }


        System.out.println(fibo[N]);
    }
}
