/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bixamon.gerenciamento.controller;

import com.bixamon.gerenciamento.model.FuncionarioBean;
import com.bixamon.gerenciamento.service.FuncionarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Aluno
 */
@Controller
public class FuncionarioController {
    @Autowired
    private FuncionarioService service;
    
    @GetMapping("/funcionarios")
    public String getFuncionarios(Model model) {
        List<FuncionarioBean> lista = service.lerTodos();
        model.addAttribute("lista", lista);
        return "funcionarios";       
    }
  
    @GetMapping("/perfil")
    public String perfil(@RequestParam int id, Model model) {
        FuncionarioBean funcionario = service.buscaPorId(id);
        model.addAttribute("funcionario", funcionario);
        return "perfil";
    }
    
    @PostMapping("/salvar")
    public String salvarDados(@ModelAttribute FuncionarioBean funcionario) {
    service.editar(funcionario);
    return "redirect:/funcionarios";
    }
    
}
