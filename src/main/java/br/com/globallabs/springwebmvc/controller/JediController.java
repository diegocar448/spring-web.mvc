package br.com.globallabs.springwebmvc.controller;


import br.com.globallabs.springwebmvc.model.Jedi;
import br.com.globallabs.springwebmvc.repository.JediRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
public class JediController {

    // (Injeção de depedência) Spring criará uma instancia para essa classe
    @Autowired
    private JediRepository repository;


    @GetMapping("/jedi")
    public ModelAndView jedi(){
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jedi");
        //modelAndView.addObject("allJedi", new Jedi("Luke", "Sky"));


        modelAndView.addObject("allJedi", repository.getAllJedi());

        return modelAndView;
    }

    @GetMapping("/new-jedi")
    public ModelAndView newJedi(){
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("new-jedi");

        modelAndView.addObject("jedi", new Jedi());
        return modelAndView;
    }

    @PostMapping("/jedi")
    public String createJedi(@Valid @ModelAttribute Jedi jedi, BindingResult result, RedirectAttributes redirectAttributes){

        /* Caso tenha algum erro de validação redirecione de volta para o formulário */
        if(result.hasErrors())
        {
            return "new-jedi";
        }

        /* Manter persistência dos dados */
        repository.add(jedi);

        redirectAttributes.addFlashAttribute("message", "Jedi cadastrado com sucesso!");

        return "redirect:jedi";
    }
}
