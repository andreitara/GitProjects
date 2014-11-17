/**
 * Created by Andrei on 11/16/2014.
 */
public class Calculator {

    private String name;
    private Ram ram;
    private Ecran ecran;
    private Processor processor;
    private HardDisk hardDisk;

    public Calculator(){}

    public Ram getRam() {
        return ram;
    }

    public void setRam(Ram ram) {
        this.ram = ram;
    }

    public Ecran getEcran() {
        return ecran;
    }

    public void setEcran(Ecran ecran) {
        this.ecran = ecran;
    }

    public HardDisk getHardDisk() {
        return hardDisk;
    }

    public void setHardDisk(HardDisk hardDisk) {
        this.hardDisk = hardDisk;
    }

    public Processor getProcessor() {
        return processor;
    }

    public void setProcessor(Processor processor) {
        this.processor = processor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
