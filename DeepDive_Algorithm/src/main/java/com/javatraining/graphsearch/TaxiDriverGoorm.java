package com.javatraining.graphsearch;

import java.io.*;
import java.util.*;

public class TaxiDriverGoorm {

    static int N, M , X, Y, Z;
    static int[][] NN;
    // 인접행렬 -> 움직이는 거리마다 수를 하나씩 늘린다
    static int [][] nn;
    static int [] a, b, c, d;

    // (a,b) 묶을 노드
    static class Node{
        int x, y;
        Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    // 상하좌우 좌표
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};

    static int bfs(int sx, int sy, int ex, int ey){
        // 큐선언
        Queue<Node> q = new LinkedList<>();

        // 인접 행렬 -1로 초기화
        nn = new int[N][N];
        for(int [] row : nn){
            // -1로 방문하지 않은 상태를 정의
            // 행을 기준으로 반복
            Arrays.fill(row, -1);
        }

        // 인접 행렬 시작위치를 넣는다. 시작 위치의 거리를 0이다.
        nn[sy][sx] = 0;
        // 큐에 시작 위치 넣기
        q.add(new Node(sy,sx));

        while(!q.isEmpty()){
            // node는 x,y로 넣었지만 좌표랑 인덱스는 다르기 때문에 x, y의 위치를 바꿔서 작성해야한다.
            Node cur = q.poll();
            int yy = cur.x;
            int xx = cur.y;

            // 상하좌우 좌표 비교
            for(int dir = 0; dir<4; dir++){
                int ny = yy + dx[dir];
                int nx = xx + dy[dir];

                if(nx >= 0 && ny >= 0 && nx < N && ny < N && nn[ny][nx] == -1 && NN[ny][nx] == 0){

                    nn[ny][nx] = nn[yy][xx]+1;
                    q.add(new Node(ny, nx));
                }
            }
        }

        return nn[ey][ex];
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        // 지도의 크기
        N = Integer.parseInt(input[0]);
        // 태운 손님의 수
        M = Integer.parseInt(input[1]);

        input = br.readLine().split(" ");
        // 기본 요금
        X = Integer.parseInt(input[0]);
        // 추가 요금
        Y = Integer.parseInt(input[1]);
        // 통행료
        Z = Integer.parseInt(input[2]);

        // 격자 모양 입력
        NN = new int[N][N];
        for(int i = 0; i<N; i++){
            input = br.readLine().split(" ");
            for(int j = 0; j<N; j++){
                NN[i][j] = Integer.parseInt(input[j]);
            }
        }

        a = new int[M];
        b = new int[M];
        c = new int[M];
        d = new int[M];
        // (a,b) 손님 승차 지점 , (c,d) 손님 하차 지점
        for(int i = 0; i<M; i++){
            input = br.readLine().split(" ");
            a[i] = Integer.parseInt(input[0]);
            b[i] = Integer.parseInt(input[1]);
            c[i] = Integer.parseInt(input[2]);
            d[i] = Integer.parseInt(input[3]);
        }

        // 인접 행렬 초기화
        nn = new int[N][N];

        // 총 요금
        int totalFee = 0;

        // 시작 위치 : 첫 손님
        int tx = a[0]-1;
        int ty = b[0]-1;

        for(int i = 0; i<M; i++){
            // 손님을 태우러가는 거리
            int toCustomer = bfs(tx, ty, a[i]-1, b[i]-1);

            // 손님을 태우고 가는 거리
            int work = bfs(a[i]-1, b[i]-1, c[i]-1, d[i]-1);
            tx = c[i]-1;
            ty = d[i]-1; // 이번 손님의 하차지점

            // 요금 계산
            if(work <= 5){
                totalFee += X;
            } else{
                totalFee += X + (work -5)*Y;
            }

            // 통행료 계산
            totalFee -= (toCustomer + work)*Z;

        }

        System.out.println(totalFee);
    }
}
