package baekjoon.algorithm.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem14891 {
	static char[][] wheel = new char[4][8];
	static int rot_case_num;
	static int[][] rot_case;

	public static void main(String[] args) throws IOException {
		int answer = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 4; i++) {
			wheel[i] = br.readLine().toCharArray();
		}
		rot_case_num = Integer.parseInt(br.readLine());
		rot_case = new int[rot_case_num][2];
		for (int i = 0; i < rot_case_num; i++) {
			String[] input = br.readLine().split(" ");
			rot_case[i][0] = Integer.parseInt(input[0]) - 1;
			rot_case[i][1] = Integer.parseInt(input[1]);
		}
		for (int i = 0; i < rot_case_num; i++) {
			compare_wheel(rot_case[i][0], rot_case[i][1]);
		}
		for (int i = 0; i < wheel.length; i++) {
			answer += Character.getNumericValue(wheel[i][0]) * (int) Math.pow(2, i);
		}
		System.out.println(answer);

	}

	public static void compare_wheel(int pos, int dir) {
		l_check(pos, dir);
		r_check(pos, dir);
		rot_wheel(pos, dir);
	}

	public static void l_check(int pos, int dir) {
		if (pos == 0)
			return;
		if (wheel[pos][6] != wheel[pos - 1][2]) {
			l_check(pos - 1, dir * -1);
			rot_wheel(pos - 1, dir * -1);
		}
	}

	public static void r_check(int pos, int dir) {
		if (pos == 3)
			return;
		if (wheel[pos][2] != wheel[pos + 1][6]) {
			r_check(pos + 1, dir * -1);
			rot_wheel(pos + 1, dir * -1);
		}
	}

	public static void rot_wheel(int pos, int dir) {

		if (dir == 1) {
			char temp = wheel[pos][7];
			for (int i = 7; i > 0; i--) {
				wheel[pos][i] = wheel[pos][i - 1];
			}
			wheel[pos][0] = temp;
		} else if (dir == -1) {
			char temp = wheel[pos][0];
			for (int i = 0; i < 7; i++) {
				wheel[pos][i] = wheel[pos][i + 1];
			}
			wheel[pos][7] = temp;
		}

	}
}
