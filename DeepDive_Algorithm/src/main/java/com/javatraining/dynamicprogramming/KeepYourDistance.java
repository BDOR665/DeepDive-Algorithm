package com.javatraining.dynamicprogramming;

import java.io.*;

public class KeepYourDistance {

    static final int MOD = 100000007;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 테이블의 줄 수
        int N = Integer.parseInt(br.readLine());

		/*
		(0,0,0) - 1번
		(1,0,0) - 2번
		(0,1,0) - 3번
		(0,0,1) - 4번
		(1,0,1) - 5번
		*/

        // 앉을 수 있는 배치에 맞춰서 dp 테이블 생성
        int[][] dp = new int[N][5];

        // 첫 번째 줄은 모든 배치 가능
        for(int i=0; i<5; i++){
            dp[0][i] = 1;
        }

        // 두 번째 줄부터 그전 배치에 영향을 받아서 배치
        for(int i=1; i<N; i++){
            // 1번 배치를 사용하려면 그 전 배치가 가능한 경우
            dp[i][0] = (dp[i-1][0] + dp[i-1][1] + dp[i-1][2] + dp[i-1][3] + dp[i-1][4])%MOD;

            // 2번 배치를 사용하려면 그 전 배치가 가능한 경우
            dp[i][1] = (dp[i-1][0] + dp[i-1][2] + dp[i-1][3])%MOD;

            // 3번 배치를 사용하려면 그 전 배치가 가능한 경우
            dp[i][2] = (dp[i-1][0] + dp[i-1][1] + dp[i-1][3] + dp[i-1][4])%MOD;

            // 4번 배치를 사용하려면 그 전 배치가 가능한 경우
            dp[i][3] = (dp[i-1][0] + dp[i-1][1] + dp[i-1][2])%MOD;

            // 5번 배치를 사용하려면 그 전 배치가 가능한 경우
            dp[i][4] = (dp[i-1][0] + dp[i-1][2])%MOD;

        }

        int result = (dp[N-1][0] + dp[N-1][1] + dp[N-1][2] + dp[N-1][3] + dp[N-1][4])%MOD;

        System.out.println(result);
    }
}
