package com.kaesar.saude.domain.beneficiario;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kaesar.saude.domain.documento.DocumentoRequestDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class BeneficiarioRequestDTO {

    @NotBlank
    private String nome;

    @NotBlank
    private String telefone;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    private List<DocumentoRequestDTO> documentos = new ArrayList<>();

    public Beneficiario criaEntidade() {

        Beneficiario beneficiario = new Beneficiario();

        beneficiario.setNome(this.nome);
        beneficiario.setTelefone(this.telefone);
        beneficiario.setDataNascimento(this.dataNascimento);
        beneficiario.setDataInclusao(LocalDateTime.now());
        beneficiario.setDataAtualizacao(LocalDateTime.now());

        for(DocumentoRequestDTO documento : this.documentos) {
            beneficiario.adicionaDocumento(documento.criaEntidade(beneficiario));
        }

        return beneficiario;
    }

    public void atualizaEntidade(Beneficiario beneficiario) {

        if(this.nome != null)
            beneficiario.setNome(this.nome);

        if(this.telefone != null)
            beneficiario.setTelefone(this.telefone);

        if(this.dataNascimento != null)
            beneficiario.setDataNascimento(this.dataNascimento);

        beneficiario.setDataAtualizacao(LocalDateTime.now());
    }
}
