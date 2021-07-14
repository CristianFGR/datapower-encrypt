package cl.kdu.authmethod.domain;

/**
 * Created by cgonzalezr on 04-03-21.
 */
public class QRCodeEMVCardInfo {

    private String pan;
    private String expirationDate;
    private String cvv;

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
}
