package br.com.globallabs.springwebmvc.repository;

import java.util.ArrayList;
import java.util.List;

import br.com.globallabs.springwebmvc.model.Jedi;
import org.springframework.stereotype.Repository;


@Repository
public class JediRepository {

    private List<Jedi> jedi;

    public JediRepository() {
        jedi = new ArrayList<>();
        jedi.add(new Jedi("Luke", "Sky"));
    }

    public List<Jedi> getAllJedi(){
        return this.jedi;
    }

    public void add(Jedi jedi) {
        this.jedi.add(jedi);
    }
}
