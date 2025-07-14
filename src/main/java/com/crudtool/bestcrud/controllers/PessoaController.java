package com.crudtool.bestcrud.controllers;

import com.crudtool.bestcrud.models.Pessoa;
import com.crudtool.bestcrud.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

  /*
    @GetMapping("/create")
    public String salvar(Pessoa pessoa){
        pessoaRepository.save(pessoa);
        return "redirect:/pessoas";
    }*/

     //CREATE
    @PostMapping("/create")
    public ModelAndView salvar(Pessoa pessoa){
        pessoaRepository.save(pessoa);

        ModelAndView andView = new ModelAndView("pessoas/index");
        Iterable<Pessoa> it = pessoaRepository.findAll();
                                     //pessoas é o objeto que vai para o HTML
        andView.addObject("pessoas", it);

        return andView;
    }

    //ENTRA NO CREATE
    @GetMapping("/create")
    public String showCreatePage(Model model){
        Pessoa pessoa = new Pessoa();
        model.addAttribute("pessoa", pessoa);
        return "pessoas/create";
    }

    //LISTAGEM
    @GetMapping             //esse /pessoa é da pasta pessoa = templates/pessoa
    public ModelAndView pessoas(){
        ModelAndView andView = new ModelAndView("pessoas/index");
        Iterable<Pessoa> it = pessoaRepository.findAll();
                                     //pessoas é o objeto que vai para o HTML
        andView.addObject("pessoas", it);
        return andView;
    }

}
