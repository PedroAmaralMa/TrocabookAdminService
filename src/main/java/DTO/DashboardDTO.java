package DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class DashboardDTO {
    private Long totalUsuarios;
    private Long totalLivros;
    private Long totalNegociacao;


}
