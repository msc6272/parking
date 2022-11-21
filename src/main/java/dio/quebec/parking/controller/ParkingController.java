package dio.quebec.parking.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import dio.quebec.parking.controller.dto.ParkingCreateDTO;
import dio.quebec.parking.controller.dto.ParkingDTO;
import dio.quebec.parking.controller.mapper.ParkingMapper;
import dio.quebec.parking.model.Parking;
import dio.quebec.parking.service.ParkingService;

@RestController
@RequestMapping("/parking")
@Api(tags = "Parking Controller")
public class ParkingController {
    private final ParkingMapper parkingMapper;
    private final ParkingService parkingService;
    //Ao invés de usar @Autowired antes da linha acima, as novas versões do Spring Boot recomendam
    //usar um construtor (injeção de dependência por construtor)
    public ParkingController(ParkingService parkingService, ParkingMapper parkingMapper) {
        this.parkingService = parkingService;
        this.parkingMapper = parkingMapper;
    }

    @GetMapping
    @ApiOperation("Find all parkings")
    public ResponseEntity<List<ParkingDTO>> findAll() {
        List<Parking> parkingList = parkingService.findAll();
        List<ParkingDTO> result = parkingMapper.toParkingDTOList(parkingList);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingDTO> findById(@PathVariable String id) {
        Parking parking = parkingService.findById(id);
        ParkingDTO result = parkingMapper.toParkingDTO(parking);
        return ResponseEntity.ok(result);
    }

    //A definição da função não deveria ser public "ResponseEntity<String> delete ..."?
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        parkingService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<ParkingDTO> create(@RequestBody ParkingCreateDTO dto) {
        var parkingCreate = parkingMapper.toParkingCreate(dto);
        var parking = parkingService.create(parkingCreate);
        var result = parkingMapper.toParkingDTO(parking);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    //Parece que a ordem em que os campos são exibidos no Swagger é de acordo com o nome da variável
    //passada para o método. No método abaixo, 'id' vem antes de 'parkingCreateDTO'
    @PutMapping("/{id}")
    public ResponseEntity<ParkingDTO> update(@PathVariable String id, @RequestBody ParkingCreateDTO parkingCreateDTO) {
        var parkingUpdate = parkingMapper.toParkingCreate(parkingCreateDTO);
        var parking = parkingService.update(id, parkingUpdate);
        /* var result = parkingMapper.toParkingDTO(parking);
        return ResponseEntity.status(HttpStatus.OK).body(result); */
        return ResponseEntity.ok(parkingMapper.toParkingDTO(parking));
    }

    @PostMapping("/{id}")
    public ResponseEntity<ParkingDTO> checkout(@PathVariable String id) {
        Parking parking = parkingService.checkout(id);
        var result = parkingMapper.toParkingDTO(parking);
        return ResponseEntity.status(HttpStatus.OK).body(result);
        // return ResponseEntity.ok(parkingMapper.toParkingDTO(parking));
    }
}
