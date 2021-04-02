package elements;

public class Flags {
    private int pos;
    private int start;
    private int sign;
    private int signAfterEqual;

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getSign() {
        return sign;
    }

    public void setSign(int sign) {
        this.sign = sign;
    }

    public int getSignAfterEqual() {
        return signAfterEqual;
    }

    public void setSignAfterEqual(int signAfterEqual) {
        this.signAfterEqual = signAfterEqual;
    }

    public Flags(int pos, int start, int sign, int signAfterEqual) {
        this.pos = pos;
        this.start = start;
        this.sign = sign;
        this.signAfterEqual = signAfterEqual;
    }

    public void enlargePos(){
        this.pos++;
    }

    public void movePos(int sign){
        this.sign = sign;
        this.start = this.pos + 1;
    }

    public int signResult(){
        return this.sign * this.signAfterEqual;
    }
}
