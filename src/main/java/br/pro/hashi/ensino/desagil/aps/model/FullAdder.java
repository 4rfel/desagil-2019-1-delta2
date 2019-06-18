package br.pro.hashi.ensino.desagil.aps.model;

public class FullAdder extends Gate {
    private final NandGate nandLeftTop;
    private final NandGate nandLeftMiddle;
    private final NandGate nandLeftBot;
    private final NandGate nandRightMiddle;
    private final NandGate nandRightBot;
    private final NandGate nandOut;
    private final NandGate nandCarry;

    public FullAdder() {
        super("FullAdder", 3, 2);
        nandLeftTop = new NandGate();
        nandLeftMiddle = new NandGate();
        nandLeftBot = new NandGate();
        NandGate nandMiddle = new NandGate();
        nandRightMiddle = new NandGate();
        NandGate nandRightTop = new NandGate();
        nandRightBot = new NandGate();
        nandOut = new NandGate();
        nandCarry = new NandGate();

        nandOut.connect(0, nandRightTop);
        nandOut.connect(1, nandRightBot);

        nandRightTop.connect(0, nandMiddle);
        nandRightTop.connect(1, nandRightMiddle);

        nandRightBot.connect(0, nandRightMiddle);

        nandRightMiddle.connect(0, nandMiddle);

        nandMiddle.connect(0, nandLeftTop);
        nandMiddle.connect(1, nandLeftBot);

        nandLeftTop.connect(1, nandLeftMiddle);

        nandLeftBot.connect(0, nandLeftMiddle);

        nandCarry.connect(0, nandRightMiddle);
        nandCarry.connect(1, nandLeftMiddle);
    }


    @Override
    public boolean read(int outputPin) {
        switch (outputPin) {
            case 0:
                return nandOut.read();
            case 1:
                return nandCarry.read();
            default:
                throw new IndexOutOfBoundsException(outputPin);
        }
    }


    @Override
    public void connect(int inputPin, SignalEmitter emitter) {
        switch (inputPin) {
            case 0:
                nandLeftTop.connect(0, emitter);
                nandLeftMiddle.connect(0, emitter);
                break;
            case 1:
                nandLeftMiddle.connect(1, emitter);
                nandLeftBot.connect(1, emitter);
                break;
            case 2:
                nandRightMiddle.connect(1, emitter);
                nandRightBot.connect(0, emitter);
                break;
            default:
                throw new IndexOutOfBoundsException(inputPin);
        }
    }
}
