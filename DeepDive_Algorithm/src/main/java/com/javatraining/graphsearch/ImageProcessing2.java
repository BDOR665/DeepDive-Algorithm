package com.javatraining.graphsearch;

import java.io.*;
import java.util.*;

public class ImageProcessing2 {

    /* 다른 방법 */

    static int N, M;
    static String[] S = new String[150];
    static boolean[][] visited = new boolean[150][150];

    // dfs
    static int dfs(int x, int y){
        if(visited[x][y] || S[x].charAt(y) == '.'){
            return 0;
        }

        // 현재 위치의 픽셀 개수
        int current = 1;

        // 시작 위치 방문 처리
        visited[x][y] = true;

        // 위쪽 탐색
        if (x-1 >= 0){
            current += dfs(x-1, y);
        }

        // 아래쪽 탐색
        if (x+1 < M){
            current += dfs(x+1, y);
        }

        // 왼쪽 탐색
        if (y-1 >= 0){
            current += dfs(x, y-1);
        }

        // 오른쪽 탐색
        if (y+1 < N){
            current += dfs(x, y+1);
        }

        return current;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        // 사진의 가로 크기
        N = Integer.parseInt(input[0]);
        // 사진의 세로 크기
        M = Integer.parseInt(input[1]);

        // 마스킹 픽셀 문자열
        for(int i = 0; i<M; i++){
            S[i] = br.readLine();
        }

        // 결과 저장 리스트
        List<Integer> sizes = new ArrayList<>();

        for(int i = 0; i<M; i++){
            for(int j = 0; j<N; j++){
                // 방문하지 않고 처리가 된 픽셀인 경우 탐색
                if(!visited[i][j] && S[i].charAt(j) == '#'){
                    int size = dfs(i,j);
                    sizes.add(size);
                }
            }
        }

        // 물체 개수
        int count = sizes.size();
        int maxSize = Collections.max(sizes);

        System.out.println(count);
        System.out.println(maxSize);
    }
}
