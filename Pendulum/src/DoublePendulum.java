// import java.awt.Color;

// /**
// * equations from
// *
// https://web.mit.edu/jorloff/www/chaosTalk/double-pendulum/double-pendulum-en.html
// *
// * simple pendulum (mass on pivots, not rods)
// */
// public class DoublePendulum {
// private static final double g = 9.81;
// private static Pivot basePivot;
// private static Pivot pivot1, pivot2;
// private static Rod rod1, rod2;
// private static double prevFrameTime;

// public static void mainloop(double dTime) {
// // TODO: make this general and not just for 2 rods

// double t1 = rod1.getTheta(), t2 = rod2.getTheta(), m1 = pivot1.getMass(), m2
// = pivot2.getMass(),
// l1 = rod1.getLength(), l2 = rod2.getLength(), t1p2 =
// Math.pow(rod1.getThetaVel(), 2),
// t2p2 = Math.pow(rod2.getThetaVel(), 2);
// rod1.setThetaAcc((-g * (2 * m1 + m2) * Math.sin(t1) - m2 * g * Math.sin(t1 -
// 2 * t2)
// - 2 * Math.sin(t1 - t2) * m2 * (t2p2 * l2 + t1p2 * l1 * Math.cos(t1 - t2)))
// / (l1 * (2 * m1 + m2 - m2 * Math.cos(2 * t1 - 2 * t2))));
// rod2.setThetaAcc((2 * Math.sin(t1 - t2)
// * (t1p2 * l1 * (m1 + m2) + g * (m1 + m2) * Math.cos(t1)
// + t2p2 * l2 * m2 * Math.cos(t1 - t2)))
// / (l2 * (2 * m1 + m2 - m2 * Math.cos(2 * t1 - 2 * t2))));

// System.out.println("Other thing: " + (-g * (2 * m1 + m2) * Math.sin(t1) - m2
// * g * Math.sin(t1 - 2 * t2)
// - 2 * Math.sin(t1 - t2) * m2 * (t2p2 * l2 + t1p2 * l1 * Math.cos(t1 - t2))));
// System.out.println("T1p2: " + t1p2);
// System.out.println("Denom: " + (l1 * (2 * m1 + m2 - m2 * Math.cos(2 * t1 - 2
// * t2))));

// basePivot.updateThetaVelRecur(dTime);
// basePivot.updateThetaRecur(dTime);

// // draw
// StdDraw.clear();
// basePivot.drawRecur(0, 0);
// StdDraw.show();
// }

// public static void main(String[] args) {
// pivot2 = new Pivot(1, Color.MAGENTA);
// rod2 = new Rod(1, 2.5, Color.MAGENTA, pivot2);
// pivot1 = new Pivot(1, Color.MAGENTA, rod2);
// rod1 = new Rod(1, 2.5, Color.MAGENTA, pivot1);
// basePivot = new Pivot(5, Color.MAGENTA, rod1);

// StdDraw.enableDoubleBuffering();
// StdDraw.setYscale(2.5, -0.5);
// StdDraw.setXscale(-1.5, 1.5);

// prevFrameTime = ((double) System.currentTimeMillis()) / 1000;
// while (true) {
// double currTime = ((double) System.currentTimeMillis()) / 1000;
// System.out.println("-----------------------------");
// System.out.println("dT: " + (currTime - prevFrameTime));
// mainloop(currTime - prevFrameTime);
// prevFrameTime = currTime;
// }
// }
// }