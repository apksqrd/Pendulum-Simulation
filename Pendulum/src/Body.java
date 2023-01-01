public abstract class Body { // TODO: make this an interface
    protected final SingleBasePivotPendulumSystem physicalSystem;

    protected Body(SingleBasePivotPendulumSystem physicalSystem) {
        this.physicalSystem = physicalSystem;
    }
}