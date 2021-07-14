package cl.kdu.authmethod.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class QRCodeEMVAuthorizeRequest implements Serializable {

    private static final long serialVersionUID = 7521754330383379416L;

    private String walletUUID;
    private String tbkUser;
    private String paymentTypeCode;
    private String paymentInstrumentsName;
    private Integer sharesNumber;
    private QRCodeEMVAuthInfo authInformation;
    private QRCodeEMVCardInfo cardInformation;

    @JsonProperty(value = "walletUUID")
    public String getWalletUUID() {
        return walletUUID;
    }

    public void setWalletUUID(String walletUUID) {
        this.walletUUID = walletUUID;
    }

    @JsonProperty(value = "tbkUser")
    public String getTbkUser() {
        return tbkUser;
    }

    public void setTbkUser(String tbkUser) {
        this.tbkUser = tbkUser;
    }

    @JsonProperty(value = "authInformation")
    public QRCodeEMVAuthInfo getAuthInformation() {
        return authInformation;
    }

    public void setAuthInformation(QRCodeEMVAuthInfo authInformation) {
        this.authInformation = authInformation;
    }

    @JsonProperty(value = "cardInformation")
    public QRCodeEMVCardInfo getCardInformation() {
        return cardInformation;
    }

    public void setCardInformation(QRCodeEMVCardInfo cardInformation) {
        this.cardInformation = cardInformation;
    }

    @JsonProperty(value = "paymentTypeCode")
    public String getPaymentTypeCode() {
        return paymentTypeCode;
    }

    public void setPaymentTypeCode(String paymentTypeCode) {
        this.paymentTypeCode = paymentTypeCode;
    }

    @JsonProperty(value = "paymentInstrumentsName")
    public String getPaymentInstrumentsName() {
        return paymentInstrumentsName;
    }

    public void setPaymentInstrumentsName(String paymentInstrumentsName) {
        this.paymentInstrumentsName = paymentInstrumentsName;
    }

    @JsonProperty(value = "sharesNumber")
    public Integer getSharesNumber() {
        return sharesNumber;
    }

    public void setSharesNumber(Integer sharesNumber) {
        this.sharesNumber = sharesNumber;
    }
}
