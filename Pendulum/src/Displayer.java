import java.awt.Color;

/**
 * This class can display a physical system at a given moment and the update
 * method must be called outside of this class every time an update is desired.
 * This is meant to be as generic as possible so make a subclass for something
 * visually cool.
 */
public class Displayer {
    /**
     * This is both for rod and pivots. The system is based off of
     * <code>java.awt.Font</code>'s way of storing italics and bold.
     * DISPLAY_ROOT only applicable to pivots, but it doesn't matter if
     * rodDisplayProcedure has display root or not
     */
    public static final int DISPLAY_LEAF = 1, DISPLAY_MIDDLE = 2, DISPLAY_ROOT = 4, DISPLAY_NONE = 0,
            DISPLAY_ALL = DISPLAY_ROOT + DISPLAY_MIDDLE + DISPLAY_LEAF;
    private final int pivotDisplayProcedure, rodDisplayProcedure;
    private final double displayRadius; // TODO: should I remove this later?
    private final SingleBasePivotPendulumSystem physicalSystem;
    /*
     * TODO: trails, webbed (2 adjacent are connected, like webbed feet (make better
     * name?))
     * 
     * Don't have a display frame rate because live displayer should only be
     * responsible for displaying a single moment, so the main runner or something
     * else should make this live.
     */

    public Displayer(SingleBasePivotPendulumSystem physicalSystem) {
        this(physicalSystem, DISPLAY_ALL, DISPLAY_LEAF, 0.01);
    }

    public Displayer(SingleBasePivotPendulumSystem physicalSystem, int pivotDisplayProcedure, int rodDisplayProcedure,
            double displayRadius) {
        this.physicalSystem = physicalSystem;
        this.pivotDisplayProcedure = pivotDisplayProcedure;
        this.rodDisplayProcedure = rodDisplayProcedure;
        this.displayRadius = displayRadius;

        initStdDraw();
    }

    public void updateDisplay() {
        // Right now, display is displayed in a dfs manner but maybe later that can be
        // changed based on a variable or st

        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(Color.MAGENTA);
        updateRootPivotDisplayRecur();
        StdDraw.show();
    }

    private void updateRootPivotDisplayRecur() {
        // TODO: all the display stuff are redundant (if statements and stddraw.xyz())

        if ((pivotDisplayProcedure & DISPLAY_ROOT) != 0) {
            StdDraw.filledCircle(0, 0, displayRadius);
        }

        for (Rod r : physicalSystem.basePivot.getBottomRods()) {
            updateRodDisplayRecur(r, 0, 0);
        }
    }

    private void updatePivotDisplayRecur(Pivot p, double x, double y) {
        if (p.isLeaf()) { // should i just use an && statement
            if ((pivotDisplayProcedure & DISPLAY_LEAF) != 0) {
                StdDraw.filledCircle(x, y, displayRadius);
            }
        } else { // should i use else if here?
            if ((pivotDisplayProcedure & DISPLAY_MIDDLE) != 0) {
                StdDraw.filledCircle(x, y, displayRadius);
            }
        }

        for (Rod r : p.getBottomRods()) {
            updateRodDisplayRecur(r, x, y);
        }
    }

    private void updateRodDisplayRecur(Rod r, double topX, double topY) {
        double botX = topX + r.getLength() * Math.sin(r.getTheta());
        double botY = topY + r.getLength() * Math.cos(r.getTheta()); // greater y means lower

        if (r.isLeaf()) {
            if ((rodDisplayProcedure & DISPLAY_LEAF) != 0) {
                StdDraw.line(topX, topY, botX, botY);
            }
        } else {
            if (((rodDisplayProcedure & DISPLAY_MIDDLE) != 0)) {
                StdDraw.line(topX, topY, botX, botY);
            }
        }

        updatePivotDisplayRecur(r.getBotPivot(), botX, botY);
    }

    private void initStdDraw() {
        StdDraw.enableDoubleBuffering();
        StdDraw.setYscale(2, -2);
        StdDraw.setXscale(-2, 2);
    }
}
