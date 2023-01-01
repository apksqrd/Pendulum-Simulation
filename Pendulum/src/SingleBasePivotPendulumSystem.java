/**
 * The only reason this exists is because I don't want to rewrite everything and
 * it is actually kind of beneficial because I might want to add another
 * physical system subclass later.
 */
public abstract class SingleBasePivotPendulumSystem { // TODO: make this extend something else
    /**
     * 1 means real time, 0.5 is slower, 2 is
     * faster
     */
    public final double g;
    protected Pivot basePivot; // not final because subclasses should set this

    public SingleBasePivotPendulumSystem(double g) {
        this.g = g;
    }

    public void simulateNextFrame(double dTime) {
        basePivot.updateThetaAccRecur();
        basePivot.updateThetaVelRecur(dTime);
        basePivot.updateThetaRecur(dTime);
    }
}