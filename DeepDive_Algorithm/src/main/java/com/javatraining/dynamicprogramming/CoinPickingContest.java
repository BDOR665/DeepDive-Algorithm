package com.javatraining.dynamicprogramming;

import java.io.*;

public class CoinPickingContest {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 동전 개수
        int N = Integer.parseInt(br.readLine());
        // 동전의 가치
        String[] input = br.readLine().split(" ");
        int[] C = new int[N];
        for(int i = 0; i<N; i++){
            C[i] = Integer.parseInt(input[i]);
        }

        // 배열을 생성해야 각 i 번째에서 끝나는 최댓값, 각 위치의 결과 역추적 가능하다
        long[] dp = new long[N];
        // 첫 번째 동전 초기화
        dp[0] = C[0];

        for(int i = 1; i<N; i++){
            // 이전까지 연속합에 지금 값을 더한 것이 크면 그대로 더하고 음수가 되면 0 으로 만든 뒤 현재 값을 더한다
            dp[i] = Math.max(dp[i-1],0) + C[i];
        }

        // 모든 i 에 대해 dp 값 중 최대 값을 찾는다
        // 합이 음수인 경우는 상금을 얻지 못하므로 0을 출력하도록 한다
        long result = 0;
        for(int i = 0; i<N; i++){
            result = Math.max(dp[i],result);
        }
        System.out.println(result);
    }
}
