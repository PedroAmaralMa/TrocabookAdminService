package com.lab.labweb.controller;

import com.lab.labweb.DTO.UsuarioDTO;
import com.lab.labweb.model.Admin;
import com.lab.labweb.response.AdminResponse;
import com.lab.labweb.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private IAdminService adminService;

    private AdminResponse adminResponse;

    private List<UsuarioDTO> lista;

    @GetMapping("/home")
    public String home() {
        return "index";
    }

    @GetMapping("/cadastro")
    public String cadastro(Model model){
        model.addAttribute("admin", new Admin());
        return "cadastro";

    }

    @PostMapping("/cadastrar")
    public String cadastrar(Admin admin){
        adminResponse = adminService.cadastro(admin.getNome(), admin.getSenha(), admin.getEmail());
        if (adminResponse.isResultado()) {
            return "redirect:/login";
        }
        return "cadastro";

    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/logar")
    public String logar(@RequestParam("email") String email, @RequestParam("senha") String senha){
        adminResponse = adminService.loginAdmin(email, senha);
        if (adminResponse.isResultado()) {
            return "redirect:/index";
        }
        return "login";
    }

    @GetMapping("/listaUsuarios")
    public String listaUsuarios(Model model) {
        adminResponse = adminService.listarUsuarios();
        lista = (List<UsuarioDTO>) adminResponse.getData();
        if (adminResponse.isResultado()) {
            model.addAttribute("usuarios", lista);
            return "listaUsuarios";
        }
        return "redirect:/home";
    }

    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable int id) {
        adminResponse = adminService.excluirUsuario(id, lista);
        
        if (adminResponse.isResultado()) {
            return "redirect:/listaUsuarios";
        }
        return "redirect:/home";
    }

    @GetMapping("/alterar/{id}")
    public String alterar(@PathVariable int id, Model model) {
        adminResponse = adminService.obterUsuario(id, lista);
        model.addAttribute("usuario", adminResponse.getData());
        return "alterar";
    }

    @PutMapping("/alterar/{id}")
    public String alterar(@PathVariable int id, UsuarioDTO usuario) {
        adminResponse = adminService.alterarUsuario(id, usuario, lista);
        return "redirect:/listaUsuarios";
    }


}
