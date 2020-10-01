package model;

import java.util.Objects;
import java.util.Observable;

public abstract class IntelligentDevice extends Observable {
    private String id;
    private String state = "Normal";

    public abstract void alert(boolean check);

    public String getId() {
        return id;
    }

    public String getState(){
        return state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntelligentDevice that = (IntelligentDevice) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
