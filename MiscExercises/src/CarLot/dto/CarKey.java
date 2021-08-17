package CarLot.dto;

public class CarKey {
    private String VIN;
    private boolean laserCut;

    public String getVIN() {
        return VIN;
    }

    public boolean isLaserCut() {
        return laserCut;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public void setLaserCut(boolean laserCut) {
        this.laserCut = laserCut;
    }

    // plus getters, setters & appropriate constructors
}
