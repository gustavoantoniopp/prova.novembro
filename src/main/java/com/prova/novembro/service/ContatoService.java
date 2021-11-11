package com.prova.novembro.service;

import com.prova.novembro.config.ModelMapperConfig;
import com.prova.novembro.dto.ContatoDto;
import com.prova.novembro.form.ContatoForm;
import com.prova.novembro.model.Contato;
import com.prova.novembro.repository.ContatoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContatoService {

    private ContatoRepository contatoRepository;
    private ModelMapperConfig modelMapper;

    Logger logger = LoggerFactory.getLogger(ContatoService.class);

    @Autowired
    public ContatoService(ContatoRepository contatoRepository, ModelMapperConfig modelMapper) {
        this.contatoRepository = contatoRepository;
        this.modelMapper = modelMapper;
    }

    public ContatoDto create(ContatoForm contatoForm){
        if (contatoRepository.findByEmail(contatoForm.getEmail()).isPresent()) {
            logger.error("Email already exists {}", contatoForm.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email já cadastrado");
        }
        if (contatoRepository.findByPhone(contatoForm.getPhone()).isPresent()) {
            logger.error("Phone already exists {}", contatoForm.getPhone());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Telefone já cadastrado");
        }
        return ContatoDto.from(contatoRepository.save(Contato.from(contatoForm)));
    }
    public ContatoDto update(Long id, ContatoForm contatoForm){
        Contato contatoFound = contatoRepository.findById(id).orElseThrow(() -> {
            logger.error("Id not found {}", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        });
        modelMapper.modelMapper().map(contatoForm, contatoFound);
        return ContatoDto.from(contatoRepository.save(contatoFound));
    }
    public void delete(Long id){
        Contato contato = contatoRepository.findById(id).orElseThrow(() -> {
            logger.error("Id not found {}", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        });
        contatoRepository.delete(contato);
    }
    public List<ContatoDto> findAll(){
        List<Contato> contatos = contatoRepository.findAll();
        return contatos.stream().map(ContatoDto::from).collect(Collectors.toList());
    }
    public ContatoDto findById(Long id){
        return ContatoDto.from(contatoRepository.findById(id).orElseThrow(() -> {
            logger.error("id not found {}", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "id não encontrado");
        }));
    }
}
