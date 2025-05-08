//package com.javatraining.datastructure;
//
//public class TheBackOfMyHeadFeelsHot {
//
//    import java.io.*;
//import java.util.*;
//
//    public class Main {
//
//        public static void main(String[] args) throws IOException {
//            // 입력 처리를 위한 BufferedReader
//            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//            StringBuilder sb = new StringBuilder(); // 출력 효율을 위한 StringBuilder
//
//            // N 입력 받기
//            int N = Integer.parseInt(br.readLine());
//
//            // 봉우리 높이 배열 h
//            int[] h = new int[N];
//            String[] input = br.readLine().split(" ");
//            for (int i = 0; i < N; i++) {
//                h[i] = Integer.parseInt(input[i]);
//            }
//
//            // 스택 선언
//            Stack<Integer> stk = new Stack<>();
//
//            // 각 신선에 대해 봉우리 탐색 및 출력
//            for (int i = 0; i < N; i++) {
//                // 현재 스택에 남아 있는 신선들의 수를 출력
//                sb.append(stk.size()).append(" ");
//
//                // 스택에 남아 있는 신선들의 봉우리 높이가 감소하는 형태가 되도록
//                // 현재 신선의 봉우리 높이보다 낮거나 같은 모든 신선들을 pop
//                while (!stk.isEmpty() && h[stk.peek()] <= h[i]) {
//                    stk.pop();
//                }
//
//                // 현재 신선을 스택에 추가
//                stk.push(i);
//            }
//
//            // 결과 출력
//            System.out.println(sb.toString().trim());
//        }
//    }
//}
