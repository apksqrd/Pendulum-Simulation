public class DoubleRodTop extends Rod {
    private final Rod botRod;
    private final Pivot botBotPivot;
    /*
     * TODO: change this
     */
    private final double const0, const1, const2, const3, const4, const5, const6, const7, const8, const9, const10,
            const11, const12, const13, l12, l22;

    public DoubleRodTop(double length, double theta, SingleBasePivotPendulumSystem physicalSystem,
            double pivotMass) {
        super(length, theta, physicalSystem, new Pivot(pivotMass, physicalSystem,
                new Rod(length, theta, physicalSystem,
                        new Pivot(pivotMass, physicalSystem))));
        botRod = botPivot.getBottomRods().get(0);
        botBotPivot = botRod.getBotPivot();

        double g = physicalSystem.g, m1 = botPivot.getMass(),
                m2 = botBotPivot.getMass(),
                l1 = getLength(), l2 = botRod.getLength();

        // for theta acceleration
        const1 = (2 * m1 + m2);
        const0 = -g * (const1);
        const2 = m2 * g;
        const3 = m1 + m2;
        const4 = l2 * m2;
        const5 = g * (const3);
        const6 = l1 * (const3);
        const7 = l2 * const1;
        const8 = m2 * l1;

        // for PE
        const9 = -(m1 + m2) * g * l1;
        const10 = -m2 * g * l2;

        // for KE
        const11 = 0.5 * m1 * Math.pow(l1, 2);
        const12 = 0.5 * m2;
        l12 = Math.pow(l1, 2); // l1 squared
        l22 = Math.pow(l2, 2); // l2 squared
        const13 = 2 * l1 * l2;
    }

    public void updateThetaAccRecur() {
        // from
        // https://web.mit.edu/jorloff/www/chaosTalk/double-pendulum/double-pendulum-en.html

        double t1 = getTheta(), t2 = botRod.getTheta(), t1p2 = Math.pow(getThetaVel(), 2),
                t2p2 = Math.pow(botRod.getThetaVel(), 2);

        setThetaAcc((const0 * Math.sin(t1) - const2 * Math.sin(t1 - 2 * t2)
                - 2 * Math.sin(t1 - t2) * (t2p2 * const4 + t1p2 * const8 * Math.cos(t1 - t2)))
                / (const7 - const4 * Math.cos(2 * t1 - 2 * t2)));
        // botRod.setThetaAcc((2 * Math.sin(t1 - t2)
        // * (t1p2 * l1 * (const3) + const5 * Math.cos(t1)
        // + t2p2 * const4 * Math.cos(t1 - t2)))
        // / (l2 * (const1 - m2 * Math.cos(2 * t1 - 2 * t2))));
        botRod.setThetaAcc((2 * Math.sin(t1 - t2)
                * (t1p2 * const6 + const5 * Math.cos(t1)
                        + t2p2 * const4 * Math.cos(t1 - t2)))
                / (const7 - const4 * Math.cos(2 * t1 - 2 * t2)));

        // System.out.println("Vars: T1p2: " + t1p2 + ", T2p2: " + t2p2);
        // System.out.println("Top rod: " + this);
        // System.out.println("Other thing: " + (-g * (2 * m1 + m2) * Math.sin(t1) - m2
        // * g * Math.sin(t1 - 2 * t2)
        // - 2 * Math.sin(t1 - t2) * m2 * (t2p2 * l2 + t1p2 * l1 * Math.cos(t1 - t2))));
        // System.out.println("Denom: " + (l1 * (2 * m1 + m2 - m2 * Math.cos(2 * t1 - 2
        // * t2))));

        System.out.println("Tot Energy: " + (getKeneticEnergy() + getPotentialEnergy()) + " KE: " + getKeneticEnergy()
                + " PE: " + getPotentialEnergy());
    }

    public double getPotentialEnergy() {
        // from https://scienceworld.wolfram.com/physics/DoublePendulum.html
        double t1 = getTheta(), t2 = botRod.getTheta();
        return const9 * Math.cos(t1) + const10 * Math.cos(t2);
    }

    public double getKeneticEnergy() {
        // also from https://scienceworld.wolfram.com/physics/DoublePendulum.html
        double t1 = getTheta(), t2 = botRod.getTheta(), t1p = getThetaVel(), t2p = botRod.getThetaVel(),
                t1p2 = Math.pow(t1p, 2), t2p2 = Math.pow(t2p, 2);
        return const11 * t1p2 + const12 * (l12 * t1p2 + l22 * t2p2 + const13 * t1p * t2p * Math.cos(t1 - t2));
    }
}