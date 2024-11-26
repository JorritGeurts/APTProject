package fact.it.politiekeparitijen.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class partijResponse {

    private String partijNaam;
    private String adres;
    private String postCode;
    private String stad;
    private boolean coalitieVlaams;
    private boolean coalitieFederaal;
}
