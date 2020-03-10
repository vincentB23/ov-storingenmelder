package be.vb.storingenmelder.dto.disturbance.outgoing;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class DisturbanceOutDto {
    private String title;
    private String description;
    private String lineDirection;
    private int lineNumber;
    private String lineNumberPublic;
    private String lineDescription;
    private String lineTransportType;
    private String lineServiceType;
    private String provinceName;
}
