package com.example.api_my.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.api_my.dao.UsuarioDao;
import com.example.api_my.dto.Usuario;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioDao dao;

    @GetMapping(value = "/home")
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("home");
        List<Usuario> userList = new ArrayList<>();
        mv.addObject("usuarios", userList);
        return mv;
    }

    @GetMapping(value = "/cadastrar")
    public ModelAndView cadastrar() {
        ModelAndView mv = new ModelAndView("cadastrar");
        mv.addObject("usuarios", dao.getUsuarios());
        return mv;
    }

    @PostMapping(value = "/cadastrar")
    public ModelAndView salvarUsuario(@ModelAttribute Usuario usuario) {
        dao.salvar(usuario);
        return cadastrar();
    }

    @GetMapping(value = "/listaUsuario")
    public ModelAndView listaUsuario() {
        ModelAndView mv = new ModelAndView("listaUsuario");
        mv.addObject("usuarios", dao.getUsuarios());
        return mv;
    }

    @GetMapping(value = "/deletar/{id}")
    public ModelAndView deletar(@PathVariable("id") int id) {
        dao.deletar(id);
        return cadastrar();
    }

    @GetMapping(value = "/editar/{id}")
    public ModelAndView editar(@PathVariable("id") int id) {
        Usuario usuario = dao.getUsuarioById(id);
        ModelAndView mv = new ModelAndView("editar");
        mv.addObject("usuario", usuario);
        return mv;
    }

    @PostMapping(value = "/editar/{id}")
    public ModelAndView salvarEdicao(@PathVariable("id") int id, @ModelAttribute Usuario usuario) {
        usuario.setId(id);
        dao.editar(usuario);
        return cadastrar();
    }

}
