package br.pro.hashi.ensino.desagil.aps.model;

public class DeMux extends Gate {
    private final NandGate nandTop1;
    private final NandGate nandBottom1;
    private final NandGate nandA;
    private final NandGate nandB;
    private final NandGate nandLeft;


    public DeMux() {
        super("DeMux", 2, 2);

        nandTop1 = new NandGate();
        nandA = new NandGate();
        nandBottom1 = new NandGate();
        nandB = new NandGate();
        nandLeft = new NandGate();

        nandA.connect(0, nandTop1);
        nandA.connect(1, nandTop1);

        nandB.connect(0, nandBottom1);
        nandB.connect(1, nandBottom1);

        nandTop1.connect(1, nandLeft);
    }


    @Override
    public boolean read(int outputPin) {
        if (outputPin != 0 && outputPin != 1) {
            throw new IndexOutOfBoundsException(outputPin);
        }
        switch (outputPin) {
            case 0:
                return nandA.read();
            case 1:
                return nandB.read();
        }
        return nandA.read();
    }


    @Override
    public void connect(int inputPin, SignalEmitter emitter) {
        switch (inputPin) {
            case 0:
                nandTop1.connect(0, emitter);
                nandBottom1.connect(1, emitter);
                break;
            case 1:
                nandBottom1.connect(0, emitter);
                nandLeft.connect(0, emitter);
                nandLeft.connect(1, emitter);
                break;
            default:
                throw new IndexOutOfBoundsException(inputPin);
        }
    }
}
