
/*
You should use the statndard input/output

in order to receive a score properly.

Do not use file input and output

Please be very careful. 
*/

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
   As the name of the class should be Solution , using Solution.java as the filename is recommended.
   In any case, you can execute your program by running 'java Solution' command.
 */
class ScoreInfo {
	private double left_inclination;
	private double right_inclination;
	private int score;
	private int[] s = { 13, 4, 18, 1, 20, 5, 12, 9, 14, 11, 8, 16, 7, 19, 3, 17, 2, 15, 10, 6 };

	public ScoreInfo(int i, int degree) {
		score = s[i];
		right_inclination = Math.tan(getRadian(degree));
		left_inclination = Math.tan(getRadian(degree + 18));
	}

	public double getRadian(int n) {
		return n * (Math.PI / 180);
	}

	public int[] getS() {
		return s;
	}

	public double getLeft_inclination() {
		return left_inclination;
	}

	public void setLeft_inclination(double left_inclination) {
		this.left_inclination = left_inclination;
	}

	public double getRight_inclination() {
		return right_inclination;
	}

	public void setRight_inclination(double right_inclination) {
		this.right_inclination = right_inclination;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}

class Solution {
	static int Answer;

	public static void getScore(int x, int y, int[] point_board, int index) {
		ScoreInfo i = new ScoreInfo(0, 0);
		int[] temp = i.getS();
		double d = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));

		if (d > point_board[1] && d < point_board[2])
			Answer += temp[index] * 3;
		else if (d > point_board[3] && d < point_board[4])
			Answer += temp[index] * 2;
		else
			Answer += temp[index];
	}

	public static void main(String args[]) throws Exception {
		/*
		 * The method below means that the program will read from input.txt,
		 * instead of standard(keyboard) input. To test your program, you may
		 * save input data in input.txt file, and call below method to read from
		 * the file when using nextInt() method. You may remove the comment
		 * symbols(//) in the below statement and use it. But before submission,
		 * you must remove the freopen function or rewrite comment symbols(//).
		 */

		/*
		 * Make new scanner from standard input System.in, and read data.
		 */
		Scanner sc = new Scanner(System.in);
		// Scanner sc = new Scanner(new FileInputStream("input.txt"));

		int T = sc.nextInt();

		for (int test_case = 0; test_case < T; test_case++) {

			Answer = 0;
			int degree = 9;
			/////////////////////////////////////////////////////////////////////////////////////////////
			/*
			 * Implement your algorithm here. The answer to the case will be
			 * stored in variable Answer.
			 */
			/////////////////////////////////////////////////////////////////////////////////////////////
			int[] point_board = new int[5];

			for (int i = 0; i < 5; i++)
				point_board[i] = sc.nextInt();

			List<ScoreInfo> si = new ArrayList<ScoreInfo>();
			for (int i = 0; i < 20; i++) {
				si.add(new ScoreInfo(i, degree));
				degree += 18;
			}

			int dart = sc.nextInt();

			for (int i = 0; i < dart; i++) {
				int x = sc.nextInt();
				int y = sc.nextInt();
				double point = 0;

				// 아예 밖으로 나감
				if (Math.pow(x, 2) + Math.pow(y, 2) > Math.pow(point_board[4], 2)) continue;
				// Bull
				if (Math.pow(x, 2) + Math.pow(y, 2) < Math.pow(point_board[0], 2))
					Answer += 50;
				else if (x == 0) {
					if (y > 0) {
						if (y > point_board[1] && y < point_board[2])
							Answer += 20 * 3;
						else if (y > point_board[3] && y < point_board[4])
							Answer += 20 * 2;
						else
							Answer += 20;
					}
					else {
						if ((-1 * y) > point_board[1] && (-1 * y) < point_board[2])
							Answer += 3 * 3;
						else if ((-1 * y) > point_board[3] && (-1 * y) < point_board[4])
							Answer += 3 * 2;
						else
							Answer += 3;
					}
				} else if (y == 0) {
					if (x > 0) {
						if (x > point_board[1] && x < point_board[2])
							Answer += 6 * 3;
						else if (y > point_board[3] && y < point_board[4])
							Answer += 6 * 2;
						else
							Answer += 6;
					}
					else {
						if ((-1 * x) > point_board[1] && (-1 * x) < point_board[2])
							Answer += 11 * 3;
						else if ((-1 * x) > point_board[3] && (-1 * x) < point_board[4])
							Answer += 11 * 2;
						else
							Answer += 11;
					}
				}
				// 나머지
				else {
					point = (double) y / x;

					// 1사분면
					if (x > 0 && y > 0) {
						for (int j = 0; j < 5; j++) {
							if (si.get(0).getRight_inclination() > point) {
								getScore(x, y, point_board, 19);
								break;
							} else if (si.get(3).getLeft_inclination() < point) {
								getScore(x, y, point_board, 4);
								break;
							} else if (si.get(j).getRight_inclination() < point
									&& si.get(j).getLeft_inclination() > point) {
								getScore(x, y, point_board, j);
								break;
							}
						}
					}
					// 2사분면
					if (x < 0 && y > 0) {
						for (int j = 5; j < 10; j++) {
							if (si.get(5).getRight_inclination() > point) {
								getScore(x, y, point_board, 4);
								break;
							} else if (si.get(8).getLeft_inclination() < point) {
								getScore(x, y, point_board, 9);
								break;
							} else if (si.get(j).getRight_inclination() < point
									&& si.get(j).getLeft_inclination() > point) {
								getScore(x, y, point_board, j);
								break;
							}
						}
					}
					// 3사분면
					if (x < 0 && y < 0) {
						for (int j = 10; j < 15; j++) {
							if (si.get(10).getRight_inclination() > point) {
								getScore(x, y, point_board, 9);
								break;
							} else if (si.get(13).getLeft_inclination() < point) {
								getScore(x, y, point_board, 14);
								break;
							} else if (si.get(j).getRight_inclination() < point
									&& si.get(j).getLeft_inclination() > point) {
								getScore(x, y, point_board, j);
								break;
							}
						}
					}
					// 4사분면
					if (x > 0 && y < 0) {
						for (int j = 15; j < 20; j++) {
							if (si.get(15).getRight_inclination() > point) {
								getScore(x, y, point_board, 14);
								break;
							} else if (si.get(18).getLeft_inclination() < point) {
								getScore(x, y, point_board, 19);
								break;
							} else if (si.get(j).getRight_inclination() < point
									&& si.get(j).getLeft_inclination() > point) {
								getScore(x, y, point_board, j);
								break;
							}
						}
					}
				}
			}
			// Print the answer to standard output(screen).
			System.out.println("Case #" + (test_case + 1));
			System.out.println(Answer);
		}
	}
}