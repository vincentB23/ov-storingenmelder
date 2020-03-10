package be.vb.storingenmelder.controller;

import be.vb.storingenmelder.domain.Line;
import be.vb.storingenmelder.dto.disturbance.outgoing.DisturbanceOutDto;
import be.vb.storingenmelder.services.DisturbanceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DisturbanceController {
    private final DisturbanceService disturbanceService;

    public DisturbanceController(DisturbanceService disturbanceService) {
        this.disturbanceService = disturbanceService;
    }

    @GetMapping("/disturbances")
    public List<DisturbanceOutDto> getAllDisturbances() {
        List<DisturbanceOutDto> disturbancesDto = new ArrayList<>();

        disturbanceService.getAllDisturbances().forEach(
                disturbance -> {
                    Line line = disturbance.getLine();
                    DisturbanceOutDto disturbanceOutDto = new DisturbanceOutDto(
                            disturbance.getTitle(),
                            disturbance.getDescription(),
                            disturbance.getLineDirection(),
                            line.getNumber(),
                            line.getNumberPublic(),
                            line.getDescription(),
                            line.getTransportType(),
                            line.getServiceType(),
                            line.getProvince().getName()
                    );
                    disturbancesDto.add(disturbanceOutDto);
                }
        );

        return disturbancesDto;
    }
}
