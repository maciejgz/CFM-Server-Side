package pl.mg.cfm.springtest;

public class ConstructorObject {
    private int version;

    public ConstructorObject(int version) {
        this.setVersion(version);
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
