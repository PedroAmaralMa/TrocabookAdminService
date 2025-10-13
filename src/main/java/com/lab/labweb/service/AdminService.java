package com.lab.labweb.service;

import com.lab.labweb.model.DTO.AdminDTO;
import com.lab.labweb.model.DTO.LogAdminDTO;
import com.lab.labweb.model.DTO.UsuarioDTO;
import com.lab.labweb.model.Admin;
import com.lab.labweb.model.Dashboard;
import com.lab.labweb.model.LogAdmin;
import com.lab.labweb.repository.AdminRepository;
import com.lab.labweb.repository.DashboardRepository;
import com.lab.labweb.repository.LogAdminRepository;
import com.lab.labweb.response.AdminResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Implementação concreta da interface {@link IAdminService}.
 *
 * <p>Esta classe contém as regras de negócio relacionadas aos administradores da plataforma,
 * incluindo cadastro, autenticação, visualização de dashboards, gerenciamento de usuários
 * e exportação de dados em formato Excel.</p>
 */
@Service
public class AdminService implements IAdminService {

    final AdminRepository adminRepository;
    final DashboardRepository dashboardRepository;
    final LogAdminRepository logAdminRepository;
    private final IDadosService api;

    /**
     * Construtor para injeção de dependências.
     *
     * @param adminRepository repositório de administradores
     * @param dashboardRepository repositório de dashboards
     * @param logAdminRepository repositório de logs administrativos
     * @param api serviço externo para manipulação de dados de usuários
     */
    @Autowired
    AdminService(AdminRepository adminRepository, DashboardRepository dashboardRepository,
                 LogAdminRepository logAdminRepository, IDadosService api) {
        this.adminRepository = adminRepository;
        this.dashboardRepository = dashboardRepository;
        this.logAdminRepository = logAdminRepository;
        this.api = api;
    }

    /**
     * Requisito: "Administrador pode se cadastrar".
     * <p>Cadastra um novo administrador na base de dados.</p>
     *
     * @param adminDto objeto com os dados do administrador (nome, email e senha)
     * @return {@link AdminResponse} indicando o resultado da operação
     */
    @Override
    public AdminResponse cadastro(AdminDTO adminDto) {
        try {
            Admin admin = new Admin();
            admin.setNome(adminDto.getNome());
            admin.setSenha(adminDto.getSenha());
            admin.setEmail(adminDto.getEmail());
            adminRepository.save(admin);
            return new AdminResponse(true, "Admin cadastrado com sucesso", admin);
        } catch (Exception e) {
            return new AdminResponse(false, "Erro no cadastro: " + e.getMessage(), null);
        }
    }

    /**
     * Requisito: "Administrador pode logar".
     * <p>Realiza a autenticação do administrador com base no email e senha.</p>
     *
     * @param email email do administrador
     * @param senha senha do administrador
     * @return {@link AdminResponse} com os dados do administrador autenticado, se válido
     */
    @Override
    public AdminResponse loginAdmin(String email, String senha) {
        try {
            AdminDTO adminResposta = new AdminDTO();
            Admin admin = adminRepository.findByEmailAndSenha(email, senha);
            if (admin != null) {
                adminResposta.setId(admin.getId());
                adminResposta.setNome(admin.getNome());
                return new AdminResponse(true, "Login realizado com sucesso", adminResposta);
            }
            return new AdminResponse(false, "Credenciais inválidas", null);
        } catch (Exception e) {
            return new AdminResponse(false, "Erro no login: " + e.getMessage(), null);
        }
    }

    /**
     * Busca e retorna um administrador pelo seu identificador.
     *
     * @param id identificador único do administrador
     * @return objeto {@link Admin} correspondente
     * @throws RuntimeException caso o administrador não seja encontrado
     */
    @Override
    public Admin obterAdminPorId(int id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin não encontrado"));
    }

    /**
     * Requisito: "Administrador visualiza dados agregados da plataforma".
     * <p>Obtém a lista de dashboards ordenada pela data de criação mais recente.</p>
     *
     * @return {@link AdminResponse} contendo os dados do dashboard
     */
    @Override
    public AdminResponse obterDashboard() {
        try {
            List<Dashboard> listaDashboard = dashboardRepository.findAllByOrderByDataCriacaoDesc();
            return new AdminResponse(true, "Dashboard obtido", listaDashboard);
        } catch (Exception e) {
            return new AdminResponse(false, "Erro ao obter dashboard: " + e.getMessage(), null);
        }
    }

    /**
     * Requisito: "Administrador pode excluir usuários".
     * <p>Exclui um usuário do sistema e registra a operação no log administrativo.</p>
     *
     * @param logAdmin objeto contendo as informações do log e do usuário excluído
     * @return {@link AdminResponse} indicando o resultado da exclusão
     */
    @Override
    public AdminResponse excluirUsuario(LogAdminDTO logAdmin) {
        try {
            api.deletarUsuario(logAdmin.getCdUsuario());
            salvarOperacao(logAdmin);
            return new AdminResponse(true, "Usuário excluído com sucesso", null);
        } catch (Exception e) {
            return new AdminResponse(false, "Erro ao excluir usuário: " + e.getMessage(), null);
        }
    }

    /**
     * Requisito: "Administrador pode alterar dados de usuários".
     * <p>Atualiza as informações de um usuário e registra a operação no log administrativo.</p>
     *
     * @param loginAdmin informações do administrador e da operação realizada
     * @param idUsuario identificador do usuário a ser alterado
     * @param usuarioDTO novos dados do usuário
     * @return {@link AdminResponse} com o resultado da atualização
     */
    @Override
    public AdminResponse alterarUsuario(LogAdminDTO loginAdmin, int idUsuario, UsuarioDTO usuarioDTO) {
        try {
            UsuarioDTO usuarioAtualizado = api.atualizarUsuario(idUsuario, usuarioDTO);
            salvarOperacao(loginAdmin);
            return new AdminResponse(true, "Usuário atualizado", usuarioAtualizado);
        } catch (Exception e) {
            return new AdminResponse(false, "Erro ao alterar usuário: " + e.getMessage(), null);
        }
    }

    /**
     * Requisito: "Administrador pode exportar dados da plataforma em Excel".
     * <p>Gera um arquivo Excel com informações dos dashboards registrados, incluindo
     * total de usuários, livros, negociações e data de criação.</p>
     *
     * @return array de bytes representando o arquivo Excel gerado
     * @throws Exception caso ocorra erro durante a geração
     */
    @Override
    public byte[] gerarExcel() throws Exception {
        AdminResponse adminResponse = this.obterDashboard();
        if (!adminResponse.isResultado()) {
            throw new Exception("Não foi possível obter dashboards");
        }

        @SuppressWarnings("unchecked")
        List<Dashboard> dashboards = (List<Dashboard>) adminResponse.getData();

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Dashboards");

            // --- Estilos ---
            // Cabeçalho
            CellStyle headerStyle = workbook.createCellStyle();
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.WHITE.getIndex());
            headerStyle.setFont(headerFont);
            headerStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setAlignment(HorizontalAlignment.CENTER);
            headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setBorderTop(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderRight(BorderStyle.THIN);

            // Estilos para linhas de dados
            CellStyle rowStyle = workbook.createCellStyle();
            rowStyle.setAlignment(HorizontalAlignment.CENTER);
            rowStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            rowStyle.setBorderBottom(BorderStyle.THIN);
            rowStyle.setBorderTop(BorderStyle.THIN);
            rowStyle.setBorderLeft(BorderStyle.THIN);
            rowStyle.setBorderRight(BorderStyle.THIN);

            // Estilo para data
            CellStyle dateStyle = workbook.createCellStyle();
            CreationHelper createHelper = workbook.getCreationHelper();
            dateStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/MM/yyyy"));
            dateStyle.setAlignment(HorizontalAlignment.CENTER);
            dateStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            dateStyle.setBorderBottom(BorderStyle.THIN);
            dateStyle.setBorderTop(BorderStyle.THIN);
            dateStyle.setBorderLeft(BorderStyle.THIN);
            dateStyle.setBorderRight(BorderStyle.THIN);

            // --- Cabeçalho ---
            int colunaInicial = 2;
            Row header = sheet.createRow(0);
            String[] colunas = {"Total Usuários", "Total Livros", "Total Negociações", "Data de Criação"};
            for (int i = 0; i < colunas.length; i++) {
                Cell cell = header.createCell(i + colunaInicial);
                cell.setCellValue(colunas[i]);
                cell.setCellStyle(headerStyle);
            }

            // --- Linhas de dados ---
            int rowIdx = 1;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            for (Dashboard dashboard : dashboards) {
                Row row = sheet.createRow(rowIdx);

                Cell cell0 = row.createCell(colunaInicial);
                cell0.setCellValue(dashboard.getTotalUsuarios());
                cell0.setCellStyle(rowStyle);

                Cell cell1 = row.createCell(colunaInicial + 1);
                cell1.setCellValue(dashboard.getTotalLivros());
                cell1.setCellStyle(rowStyle);

                Cell cell2 = row.createCell(colunaInicial + 2);
                cell2.setCellValue(dashboard.getTotalNegociacao());
                cell2.setCellStyle(rowStyle);

                Cell cell3 = row.createCell(colunaInicial + 3);
                cell3.setCellValue(dashboard.getDataCriacao().format(formatter));
                cell3.setCellStyle(dateStyle);

                rowIdx++;
            }

            // Ajustar largura das colunas
            for (int i = 0; i < 4; i++) {
                sheet.autoSizeColumn(i + colunaInicial);
            }

            // --- Escrever em array de bytes ---
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);
            return out.toByteArray();
        }
    }

    /**
     * Registra no banco de dados as operações administrativas realizadas,
     * como exclusões e alterações de usuários.
     *
     * @param logAdmin objeto contendo informações da operação e do administrador responsável
     */
    public void salvarOperacao(LogAdminDTO logAdmin) {
        LogAdmin novoLogAdmin = new LogAdmin();
        novoLogAdmin.setOperacao(LogAdmin.Operacao.valueOf(logAdmin.getOperacao()));
        novoLogAdmin.setAdminResponsavel(obterAdminPorId(logAdmin.getAdminResponsavelId()));
        novoLogAdmin.setCd_usuario(logAdmin.getCdUsuario());
        novoLogAdmin.setDataOperacao(LocalDate.now());
        logAdminRepository.save(novoLogAdmin);
    }
}
