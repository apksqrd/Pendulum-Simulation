public class MultipleDoublePendulums extends SingleBasePivotPendulumSystem {
    public MultipleDoublePendulums(final double g,
            final int numPendulums, final double startTheta, final double differencePer) {
        super(g);
        basePivot = new Pivot(0, this);
        for (int i = 0; i < numPendulums; i++) {
            basePivot.addBottomRod(new DoubleRodTop(1, startTheta + ((double) i) * differencePer, this, 1));
        }
    }

    public MultipleDoublePendulums() {
        this(9.81, 100, 2.3, ((double) 1) / ((double) 10000));
    }
}
