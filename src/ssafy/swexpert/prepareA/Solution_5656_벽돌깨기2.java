package ssafy.swexpert.prepareA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_5656_벽돌깨기2 {
	static class Point {
		int r, c, cnt;

		public Point(int r, int c, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}

	}

	static int N, W, H, min;
	static int[] dr = { -1, 1, 0, 0 }, dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int iT = Integer.parseInt(br.readLine());

		for (int t = 1; t <= iT; t++) {
			StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(stk.nextToken());
			W = Integer.parseInt(stk.nextToken());
			H = Integer.parseInt(stk.nextToken());

			int[][] map = new int[H][W];
			for (int r = 0; r < H; r++) {
				stk = new StringTokenizer(br.readLine(), " ");
				for (int c = 0; c < W; c++) {
					map[r][c] = Integer.parseInt(stk.nextToken());
				}
			}

			min = Integer.MAX_VALUE;
			go(0, map);
			System.out.println("#" + t + " " + min);
		}
	}

	/*
	 * count : 던져진 구슬의 개수 map : 이전 구슬까지의 2차원 배열
	 */
	private static boolean go(int count, int[][] map) {
		int result = getRemain(map);

		if (result == 0) {
			min = 0;
			return true;
		}

		if (count == N) {
			// 남아있는 벽돌의 개수 구하여 최소값 갱신
			if (min > result)
				min = result;
			return false;
		}

		int[][] newMap = new int[H][W];

		for (int c = 0; c < W; c++) {
			int r = 0;
			while (r < H && map[r][c] == 0)
				++r;

			if (r == H)// 벽돌이 없음.
				continue;
			// 이전 구슬 상태로 배열 복사하여 초기화
			copy(map, newMap);
			// 터트리기
			boom(newMap, r, c);
			// 벽돌 내리기
			down(newMap);
			// 다음 구슬처리
			if (go(count + 1, newMap)) {
				return true;
			}

		}
		return false;
	}

	private static void boom(int[][] map, int r, int c) {
		Queue<Point> queue = new LinkedList<>();
		queue.offer(new Point(r, c, map[r][c]));
		map[r][c] = 0; // 방문처리겸 벽돌깨기

		while (!queue.isEmpty()) {
			Point p = queue.poll();
			if (p.cnt == 1)
				continue;

			for (int d = 0; d < 4; d++) {
				int nr = p.r, nc = p.c;
				for (int k = 1; k < p.cnt; k++) {
					nr += dr[d];
					nc += dc[d];

					if (nr >= 0 && nr < H && nc >= 0 && nc < W && map[nr][nc] != 0) {
						queue.offer(new Point(nr, nc, map[nr][nc]));
						map[nr][nc] = 0;
					}
				}
			}
		}

	}

	private static void down(int[][] map) {
		for (int c = 0; c < W; c++) { // 열을 고정하고 실행
			ArrayList<Integer> list = new ArrayList<Integer>();
			int r;
			for (r = H - 1; r >= map.length; r--) {
				if (map[r][c] > 0) {
					list.add(map[r][c]);
					map[r][c] = 0;
				}

			}
			r = H;
			for (int b : list) {
				map[--r][c] = b;
			}
		}

	}

	private static void copy(int[][] map, int[][] newMap) {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				newMap[i][j] = map[i][j];
			}
		}

	}

	private static int getRemain(int[][] map) {
		int count = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (map[i][j] > 0)
					count++;
			}
		}
		return count;
	}
}
