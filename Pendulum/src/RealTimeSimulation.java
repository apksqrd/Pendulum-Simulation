public class RealTimeSimulation implements Runnable {
    public RealTimeSimulation() {
        this((double) ((double) 1 / (double) 60), false, false, new MultipleDoublePendulums(), -1);
    }

    private final double timeFactor;
    private final boolean isRealTime;
    private final SingleBasePivotPendulumSystem physicalSystem;
    private final Displayer displayer;
    private double prevFrameTime;
    private int frameCount = 0;
    private final int numFramesToDo; // -1 means infinite
    private final boolean shouldSave;

    public RealTimeSimulation(double timeFactor, boolean isRealTime, boolean shouldSave,
            SingleBasePivotPendulumSystem physicSystem,
            int numFramesToDo) {
        this(timeFactor, isRealTime, shouldSave, physicSystem, new Displayer(physicSystem), numFramesToDo);
    }

    public RealTimeSimulation(double timeFactor, boolean isRealTime, boolean shouldSave,
            SingleBasePivotPendulumSystem physicalSystem,
            Displayer displayer, int numFramesToDo) {
        this.timeFactor = timeFactor;
        this.isRealTime = isRealTime;
        this.shouldSave = shouldSave;
        this.physicalSystem = physicalSystem;
        this.displayer = displayer;
        this.numFramesToDo = numFramesToDo;
    }

    @Override
    public void run() {
        startMainloop();
    }

    private void mainloop(double dTime) {
        System.out.println("-----------------------------");
        System.out.println("DTime: " + dTime);

        // logic
        physicalSystem.simulateNextFrame(dTime);

        // draw
        displayer.updateDisplay();

        if (shouldSave) {
            StdDraw.save("images/" + frameCount + ".PNG");
        }
    }

    private final void startMainloop() {
        prevFrameTime = ((double) System.currentTimeMillis()) / 1000;
        while ((frameCount < numFramesToDo) || (numFramesToDo == -1)) {
            double currTime = ((double) System.currentTimeMillis()) / 1000;
            if (isRealTime) {
                mainloop(timeFactor * (currTime - prevFrameTime));
            } else {
                mainloop(timeFactor);
            }
            prevFrameTime = currTime;
            frameCount++;
        }
    }
}
