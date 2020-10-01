package model;

public class SismicSensor extends IntelligentDevice {
    private String id;
    private String state = "Normal";
    private boolean shake;

    public SismicSensor(String id) {
        this.id = id;
    }

    @Override
    public void alert(boolean check) {
        if(check){
            state = "Blinking...";
        } else {
            state = "Normal";
        }
    }

    public String getId() {
        return id;
    }

    public boolean isShake() {
        return shake;
    }

    public void setShake(boolean shake) {
        if(this.shake != shake){
            this.shake = shake;
            setChanged();
            notifyObservers();
        }
    }
}
