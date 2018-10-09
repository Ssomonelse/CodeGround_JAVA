import java.util.Scanner;

class Coordinate {
   private int x;
   private int y;

   public Coordinate(int x, int y) {
      this.x = x;
      this.y = y;
   }

   public int getX() {
      return this.x;
   }

   public int getY() {
      return this.y;
   }

   public int getAngle() {
      double angle = Math.atan2((double) y, (double) x) * 180 / Math.PI + 9;
      if (angle < 0)
         angle += 360;
      return (int) angle / 18;
   }
}

class Board {
   private int[] area;
   private int[] score = { 6, 13, 4, 18, 1, 20, 5, 12, 9, 14, 11, 8, 16, 7, 19, 3, 17, 2, 15, 10 };

   public Board() {
      area = new int[5];
   }

//   public int getAnswer() {
//      return this.Answer;
//   }

   public void inputArea(int area, int index) {
      this.area[index] = area;
   }

   public int getLength() {
      return area.length;
   }

   public int runGame(Coordinate c, int cLength) {
      int Answer = 0;
      int x = c.getX();
      int y = c.getY();
      int r = x * x + y * y;
      double sqrtR = Math.sqrt((double) r);
      if (sqrtR < area[0])
         Answer = 50;
      else if (sqrtR > area[4])
         Answer = 0;
      else if (area[1] < sqrtR && sqrtR < area[2])
         Answer = score[c.getAngle()] * 3;
      else if (area[3] < sqrtR && sqrtR < area[4])
         Answer = score[c.getAngle()] * 2;
      else
         Answer = score[c.getAngle()];
      return Answer;
   }
}

public class CodeGround04 {

   public static void main(String args[]) throws Exception {

      Scanner sc = new Scanner(System.in);

      int T = sc.nextInt();
      int Answer = 0;
      for (int test_case = 0; test_case < T; test_case++) {
         Board board = new Board();
         for (int i = 0; i < board.getLength(); i++) {
            int area = sc.nextInt();
            board.inputArea(area, i);
         }
         int dart = sc.nextInt();
         Coordinate[] c = new Coordinate[dart];
         for (int i = 0; i < dart; i++) {
            int x, y;
            x = sc.nextInt();
            y = sc.nextInt();
            c[i] = new Coordinate(x, y);
            Answer += board.runGame(c[i], dart);
            System.out.println(Answer);
         }
         System.out.println("Case #" + (test_case + 1));
         System.out.println(Answer);
      }
   }
}