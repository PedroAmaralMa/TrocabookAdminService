package com.lab.labweb.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Data
@Getter
@Setter
public class DashboardDTO {
    private Long totalUsuarios;
    private Long totalLivros;
    private Long totalNegociacao;
    private LocalDate dataCriacao;


}
