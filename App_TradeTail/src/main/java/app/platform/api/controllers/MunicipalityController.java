package app.platform.api.controllers;

import app.platform.models.Municipality_group.CCAA;
import app.platform.models.Municipality_group.Municipality;
import app.platform.models.Municipality_group.Province;
import app.platform.services.Municipality.MunicipalityService;
import app.platform.services.impl.Municipality.CCAAServiceImpl;
import app.platform.services.impl.Municipality.MunicipalityServiceImpl;
import app.platform.services.impl.Municipality.ProvinceServiceImpl;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.JsonNode;
import com.twilio.http.Request;
import com.twilio.twiml.messaging.Body;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/address")
public class MunicipalityController {

    @Autowired
    private CCAAServiceImpl ccaaService;

    @Autowired
    private ProvinceServiceImpl provinceService;

    @Autowired
    private MunicipalityServiceImpl municipalityService;


    @GetMapping("/CCAAs")
    public ResponseEntity<?> getCcass() {
        List<CCAA> CCAAs = new ArrayList<>(ccaaService.findAllCCAA());
        return new ResponseEntity<>(new ArrayList<>(CCAAs), HttpStatus.OK);
    }

    @GetMapping("/provinces")
    public ResponseEntity<?> getProvinces() {
        List<Province> provinces = new ArrayList<>(provinceService.findAllProvinces());
        return new ResponseEntity<>(new ArrayList<>(provinces), HttpStatus.OK);
    }

    @GetMapping("/municipalities")
    public ResponseEntity<?> getMunicipalities() {
        List<Municipality> municipalities = new ArrayList<>(municipalityService.getAll());
        return new ResponseEntity<>(new ArrayList<>(municipalities), HttpStatus.OK);
    }

    @PostMapping("/provinces/CCAA")
    public ResponseEntity<?> getProvincesByCCAA(@RequestBody JsonNode object) {

        Long id = object.get("id").asLong();

        if (id == null) {
            return new ResponseEntity<>("ID not found or null", HttpStatus.NOT_FOUND);
        }

        List<Province> provinces = new ArrayList<>(provinceService.findProvinceByCcaa_Id(id));

        return new ResponseEntity<>(new ArrayList<>(provinces), HttpStatus.OK);
    }

    @PostMapping("/municipalities/Province")
    public ResponseEntity<?> getMunicipalitiesByProvinces(@RequestBody JsonNode object) {

        Long id = object.get("id").asLong();

        if (id == null) {
            return new ResponseEntity<>("ID not found or null", HttpStatus.NOT_FOUND);
        }

        List<Municipality> municipalities = new ArrayList<>(municipalityService.findMunicipalitiesByProvince_Id(id));

        return new ResponseEntity<>(new ArrayList<>(municipalities), HttpStatus.OK);
    }


}

