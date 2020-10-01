package model;

public class SismicSensorWithAlarm extends IntelligentDevice {
    private String id;
    private String state = "Normal";
    private double shakeRead;
    private double safetyLimit = 0.4;

    public SismicSensorWithAlarm(String id) {
        this.id = id;
    }

    @Override
    public void alert(boolean check) {
        if(check){
            state = "Alert sound on...";
        } else {
            state = "Normal";
        }
    }

    public String getId() {
        return id;
    }


    public double getShakeRead() {
        return shakeRead;
    }

    public void setShakeRead(double shakeRead) {
        if(this.shakeRead >= shakeRead)
        this.shakeRead = shakeRead;
        setChanged();
        notifyObservers();
    }

    public double getSafetyLimit() {
        return safetyLimit;
    }

    public void setSafetyLimit(float safetyLimit) {
        this.safetyLimit = safetyLimit;
        setChanged();
        notifyObservers();
    }
}
