package cl.kdu.authmethod.domain;

/**
 * Created by cgonzalezr on 04-03-21.
 */
public class QRCodeEMVAuthInfo {

    private String cavv;
    private String eci;
    private String vci;

    public String getCavv() {
        return cavv;
    }

    public void setCavv(String cavv) {
        this.cavv = cavv;
    }

    public String getEci() {
        return eci;
    }

    public void setEci(String eci) {
        this.eci = eci;
    }

    public String getVci() {
        return vci;
    }

    public void setVci(String vci) {
        this.vci = vci;
    }
}
