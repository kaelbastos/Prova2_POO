package model;

public class ColapseAlarm extends IntelligentDevice {
    private String id;
    private String state = "Normal";

    public ColapseAlarm(String id) {
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
}
