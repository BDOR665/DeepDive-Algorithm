package com.javatraining.greedy;

import java.io.*;
import java.util.*;

/*
미사일의 비행 시간들이 얼마나 겹쳐 있는가를 계산
- 그중 가장 많이 겹치는 구간의 개수만큼 빼주면 부스터로 단축 가능한 시간
*/

public class MissileLaunch {

    // 유클리드 거리계산
    static long euclid(long x, long y){
        return (x*x + y*y) *2;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 미사일의 개수
        int N = Integer.parseInt(br.readLine());

        // 외계인의 좌표(X,Y), i번 미사일이 발사되는 시각 T
        long[] X = new long[N];
        long[] Y = new long[N];
        long[] T = new long[N];
        for(int i = 0; i<N; i++){
            String[] input = br.readLine().split(" ");
            X[i] = Long.parseLong(input[0]);
            Y[i] = Long.parseLong(input[1]);
            T[i] = Long.parseLong(input[2]);
        }

        // 각 미사일의 도착 시각 계산
        long[] MAT = new long[N];
        for(int i=0; i<N; i++){
            MAT[i] = T[i] + euclid(X[i], Y[i]);
        }

        PriorityQueue<long[]> pq = new PriorityQueue<>(new Comparator<long[]>(){
            // (시간 , 타입)
            @Override
            public int compare(long[] a, long[] b){
                if(a[0] != b[0]){
                    // 시간 순서대로 정렬
                    return Long.compare(a[0], b[0]);
                } else{
                    // 시간이 같을 경우 도착 시간이 우선
                    return Long.compare(a[1], b[1]);
                }

            }
        });

        // 시작 시간(T[i]) 과 도착 시간(MAT[i])을 우선순위 큐에 넣기
        for(int i = 0; i<N; i++){
            // 시작 시각은 1로 표시
            pq.add(new long[]{T[i], 1});
            // 도착 시각은 0으로 표시
            pq.add(new long[]{MAT[i], 0});
        }

        int count = 0;
        int result = 0;

        // 힙에서 시작과 시각과 도착 시각을 처리
        // 겹치는 개수 세기
        while (!pq.isEmpty()){
            long[] current = pq.poll();
            long time = current[0];
            long type = current[1];
            // 시작 시각
            // 새로운 미사일 발사됨 -> 공중에 떠 있는 미사일 수 +1
            if(type == 1){
                ++ count;
                // 도착 시각
                // 미사일 도착 -> 공중에 떠 있는 미사일 수 -1
            } else{
                -- count;
            }
            // 현재 활성화 중인 미사일 개수 중 최대값
            result = Math.max(result, count);
        }

        // 총 시간에서 겹치는 구간의 개수를 빼준다
        long m = 0;
        for(int i = 0; i<N; i++){
            // 각 미사일의 비행 시간 누적
            m += MAT[i] - T[i];
        }
        // 최대 겹친 구산 수만큼 줄였다고 가정
        m -= result;

        System.out.print(m);

    }
}
