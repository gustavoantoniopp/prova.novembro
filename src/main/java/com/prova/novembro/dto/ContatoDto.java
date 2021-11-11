package com.prova.novembro.dto;

import com.prova.novembro.model.Contato;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;


@Data
public class ContatoDto {

    private Long id;
    private String name;
    private String email;
    private String phone;

    public static ContatoDto from (Contato contato){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper.map(contato, ContatoDto.class);
    }

}
