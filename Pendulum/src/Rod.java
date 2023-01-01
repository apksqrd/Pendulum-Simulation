class Rod extends Body {
    protected final double length; // not possible but want give access to subclass but not same package
    protected final Pivot botPivot;

    protected double theta, thetaVel = 0, thetaAcc = 0;

    public Rod(double length, double theta, SingleBasePivotPendulumSystem physicalSystem,
            Pivot bottomPivot) {
        super(physicalSystem);
        this.length = length;
        this.theta = theta;
        this.botPivot = bottomPivot;
    }

    public Rod(double length, double theta, SingleBasePivotPendulumSystem physicalSystem,
            double pivotMass) {
        this(length, theta, physicalSystem, new Pivot(pivotMass, physicalSystem));
    }

    public Pivot getBotPivot() {
        return botPivot;
    }

    public double getLength() {
        return length;
    }

    public double getTheta() {
        return theta;
    }

    public double getThetaVel() {
        return thetaVel;
    }

    public void setThetaAcc(double thetaAcc) {
        this.thetaAcc = thetaAcc;
    }

    @Override
    public String toString() {
        return "Rod: " + System.identityHashCode(this) + "\nTheta: " + theta + " Vel: " + thetaVel + " Acc: "
                + thetaAcc;
    }

    public void updateThetaRecur(double dTime) {
        theta += thetaVel * dTime;
        botPivot.updateThetaRecur(dTime);
    }

    public void updateThetaVelRecur(double dTime) {
        thetaVel += thetaAcc * dTime;
        botPivot.updateThetaVelRecur(dTime);
    }

    public void updateThetaAccRecur() throws Exception {
        throw new Exception("E");
    }

    public boolean isLeaf() {
        return botPivot.isLeaf();
    }
}