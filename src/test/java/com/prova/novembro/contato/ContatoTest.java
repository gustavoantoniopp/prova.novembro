package com.prova.novembro.contato;

import com.prova.novembro.form.ContatoForm;
import com.prova.novembro.model.Contato;
import com.prova.novembro.repository.ContatoRepository;
import org.junit.jupiter.api.Test;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
public class ContatoTest {

    @Autowired
    private ContatoRepository contatoRepository;

    @Test
    @Rollback(false)
    public void TestEmail(){
        ContatoForm contatoForm = new ContatoForm("Gustavo", "gustavo@gmail", "999");
        contatoRepository.save(Contato.from(contatoForm));

        ContatoForm contatoForm1 = new ContatoForm("Gustavo", "gustavo@outlook", "888");

        if (contatoRepository.findByEmail(contatoForm1.getEmail()).isPresent()) {

            contatoForm1 = null;
            ResponseEntity.internalServerError();

        } else {
            contatoRepository.save(Contato.from(contatoForm1));
        }

        Assert.assertEquals( contatoRepository.findById(2L).get().getEmail(), contatoForm1.getEmail() );
    }

    @Test
    @Rollback(false)
    public void TestPhone(){
        ContatoForm contatoForm = new ContatoForm("Gustavo", "gustavo@gmail", "998");
        contatoRepository.save(Contato.from(contatoForm));

        ContatoForm contatoForm1 = new ContatoForm("Gustavo", "gustavo@hotmail", "996");

        if (contatoRepository.findByPhone(contatoForm1.getPhone()).isPresent()) {

            contatoForm1 = null;
            ResponseEntity.internalServerError();

        } else {
            contatoRepository.save(Contato.from(contatoForm1));
        }

        Assert.assertEquals( contatoRepository.findById(2L).get().getPhone(), contatoForm1.getPhone() );
    }
}
