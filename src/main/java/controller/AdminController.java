package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import response.AdminResponse;
import service.IAdminService;

@Controller
public class AdminController {

    @Autowired
    private IAdminService adminService;

    private AdminResponse adminResponse;

    @GetMapping("/home")
    public String home() {
        return "index";
    }

    @GetMapping("/listaUsuarios")
    public String listaUsuarios(Model model) {
        adminResponse = adminService.listarUsuarios();
        if (adminResponse.isResultado()) {
            model.addAttribute("usuarios", adminResponse.getData());
            return "listaUsuarios";
        };
        return "redirect:/home";
    }

    @PostMapping("/excluir/${id}")
    public String excluir(@PathVariable int id) {
        adminResponse = adminService.excluirUsuario(id);
        if (adminResponse.isResultado()) {
            return "redirect:/listaUsuarios";
        }
        return "redirect:/home";
    }

    @GetMapping("/alterar/${id}")
    public String alterar(@PathVariable int id, Model model) {
        adminResponse = adminService.obterUsuario(id);
        model.addAttribute("usuario", adminResponse.getData());
        return "alterar";
    }


}
