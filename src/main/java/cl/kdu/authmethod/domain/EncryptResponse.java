package cl.kdu.authmethod.domain;

/**
 * Created by cgonzalezr on 25-03-21.
 */
public class EncryptResponse {

    private String idf;

    public EncryptResponse(){
    }

    public EncryptResponse(String idf) {
        this.idf = idf;
    }

    /**
     * @return the idf
     */
    public String getIdf() {
        return idf;
    }

    /**
     * @param idf the idf to set
     */
    public void setIdf(String idf) {
        this.idf = idf;
    }

    @Override
    public String toString() {
        return "QRCodeEMVDatapowerReqRes{" + "idf=" + idf + '}';
    }
}
