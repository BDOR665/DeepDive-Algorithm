package com.javatraining.graphsearch;

import java.io.*;
import java.util.*;

public class SmallNode1 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        // 노드의 개수
        int N = Integer.parseInt(input[0]);
        // 간선의 개수
        int M = Integer.parseInt(input[1]);
        // 시작 노드의 번호
        int K = Integer.parseInt(input[2]);

        // 그래프 초기화
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int i = 1; i<N+1; i++){
            graph.put(i, new ArrayList<>());
        }

        // 간선 입력
        for(int i = 0; i<M; i++){
            input = br.readLine().split(" ");
            int s = Integer.parseInt(input[0]);
            int e = Integer.parseInt(input[1]);

            // 양방향 처리
            graph.get(s).add(e);
            graph.get(e).add(s);
        }

        // 현재 연결된 노드 중 가장 작은 노드로 이동해야 하므로 정렬 필요
        for(int i : graph.keySet()){
            Collections.sort(graph.get(i));
        }

        // 방문 배열 선언
        boolean[] visited = new boolean[N+1];
        Queue<Integer> q = new LinkedList<>();
        // 큐에 시작 노드 넣기
        q.add(K);

        // 방문한 노드의 개수
        int count = 1;
        // 마지막 노드 번호
        int nodeNum = K;

        visited[K] = true;
        while(!q.isEmpty()){
            int current = q.poll();
            // 마지막 노드 업데이트
            nodeNum = current;

            // 이동했는지 여부체크
            boolean moved = false;
            for(int next : graph.get(current)){
                if(!visited[next]){
                    visited[next] = true;
                    // 이동할 노드 넣기
                    q.offer(next);
                    count ++;
                    moved = true;
                    // 가장 번호가 작은 노드로 하나만 이동하므로 break
                    break;
                }
            }
            // 더 이상 이동할 노드가 없음녀 반복 종료
            if(!moved){
                break;
            }
        }
        System.out.println(count + " " + nodeNum);
    }
}
