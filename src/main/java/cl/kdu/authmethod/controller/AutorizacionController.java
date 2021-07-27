package cl.kdu.authmethod.controller;

import cl.kdu.authmethod.domain.EncryptResponse;
import cl.kdu.authmethod.domain.QRCodeEMVAuthorizeRequest;
import cl.kdu.authmethod.util.DataPowerUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by cgonzalezr on 25-03-21.
 */
@RestController
@RequestMapping("/authorization")
public class AutorizacionController {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private DataPowerUtil dataPowerUtil;

    @ApiOperation(value = "Servicio de encryptacion request para autorizacion", response = EncryptResponse.class)
    @PostMapping(value = "/encrypt")
    public ResponseEntity<EncryptResponse> encryptarData(@RequestBody QRCodeEMVAuthorizeRequest qrCodeEMVAuthorizeRequest,
                                                         @ApiParam(value = "valor de la llave",
                                                                 required = true,
                                                                 defaultValue = "CzczExArD2kHUBVFdUBIP2VJGBlJf1YCYjIdDWogHCk=")
                                                         @RequestHeader("DP-KEY") final String dpKey) throws JsonProcessingException {
        String jsonStr = mapper.writeValueAsString(qrCodeEMVAuthorizeRequest);
        return new ResponseEntity<>(new EncryptResponse(dataPowerUtil.encrypt(jsonStr, dpKey)), HttpStatus.OK);
    }

    @ApiOperation(value = "Servicio que desencrypta request para autorizacion", response = QRCodeEMVAuthorizeRequest.class)
    @PostMapping(value = "/decrypt")
    public ResponseEntity<QRCodeEMVAuthorizeRequest> decryptData(@RequestBody EncryptResponse encryptResponse,
                                                                 @ApiParam(value = "valor de la llave",
                                                                         required = true,
                                                                         defaultValue = "CzczExArD2kHUBVFdUBIP2VJGBlJf1YCYjIdDWogHCk=")
                                                                 @RequestHeader("DP-KEY") final String dpKey) throws JsonProcessingException {
        QRCodeEMVAuthorizeRequest authRequest = mapper.readValue(dataPowerUtil.decrypt(encryptResponse.getIdf(), dpKey), QRCodeEMVAuthorizeRequest.class);
        return new ResponseEntity<>(authRequest, HttpStatus.OK);
    }

    @ApiOperation(value = "Servicio de encryptacion request para objeto generico", response = Object.class)
    @PostMapping(value = "/generic-encrypt")
    public ResponseEntity<EncryptResponse> encryptarDataGenerica(@RequestBody Object genericObject,
                                                                 @ApiParam(value = "valor de la llave",
                                                                         required = true,
                                                                         defaultValue = "CzczExArD2kHUBVFdUBIP2VJGBlJf1YCYjIdDWogHCk=")
                                                                 @RequestHeader("DP-KEY") final String dpKey) throws JsonProcessingException {
        String jsonStr = mapper.writeValueAsString(genericObject);
        return new ResponseEntity<>(new EncryptResponse(dataPowerUtil.encrypt(jsonStr, dpKey)), HttpStatus.OK);
    }

    @ApiOperation(value = "Servicio que desencrypta request para objeto generico", response = Object.class)
    @PostMapping(value = "/generic-decrypt")
    public ResponseEntity<Object> decryptDataGenerica(@RequestBody EncryptResponse encryptResponse,
                                                      @ApiParam(value = "valor de la llave",
                                                              required = true,
                                                              defaultValue = "CzczExArD2kHUBVFdUBIP2VJGBlJf1YCYjIdDWogHCk=")
                                                      @RequestHeader("DP-KEY") final String dpKey) throws JsonProcessingException {
        Object authRequest = mapper.readValue(dataPowerUtil.decrypt(encryptResponse.getIdf(), dpKey), Object.class);
        return new ResponseEntity<>(authRequest, HttpStatus.OK);
    }
}
