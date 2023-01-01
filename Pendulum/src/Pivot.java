import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class Pivot extends Body {
    private final List<Rod> bottomRods;

    private final double mass;

    public Pivot(double mass, SingleBasePivotPendulumSystem physicalSystem, Rod... rods) {
        super(physicalSystem);
        bottomRods = new LinkedList<Rod>(Arrays.asList(rods));
        this.mass = mass;
    }

    public List<Rod> getBottomRods() {
        return bottomRods;
    }

    public void addBottomRod(Rod newRod) {
        bottomRods.add(newRod);
    }

    public double getMass() {
        return mass;
    }

    public void updateThetaRecur(double dTime) {
        for (Rod rod : bottomRods) {
            rod.updateThetaRecur(dTime);
        }
    }

    public void updateThetaVelRecur(double dTime) {
        for (Rod rod : bottomRods) {
            rod.updateThetaVelRecur(dTime);
        }
    }

    public void updateThetaAccRecur() {
        for (Rod rod : bottomRods) {
            try { // TODO: remove this later
                rod.updateThetaAccRecur();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public int getNumChildrenRods() {
        return bottomRods.size();
    }

    public boolean isLeaf() {
        return getNumChildrenRods() == 0;
    }
}