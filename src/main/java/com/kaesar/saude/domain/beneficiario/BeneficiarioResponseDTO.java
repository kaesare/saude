package com.kaesar.saude.domain.beneficiario;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kaesar.saude.domain.documento.Documento;
import com.kaesar.saude.domain.documento.DocumentoResponseDTO;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class BeneficiarioResponseDTO {

    private Long id;

    private String nome;

    private String telefone;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataInclusao;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataAtualizacao;

    private List<DocumentoResponseDTO> documentos = new ArrayList<>();

    public BeneficiarioResponseDTO(Beneficiario beneficiario) {
        this.id = beneficiario.getId();
        this.nome = beneficiario.getNome();
        this.telefone = beneficiario.getTelefone();
        this.dataNascimento = beneficiario.getDataNascimento();
        this.dataInclusao = beneficiario.getDataInclusao();
        this.dataAtualizacao = beneficiario.getDataAtualizacao();
        this.adicionaDocumentos(beneficiario.getDocumentos());
    }

    static public List<BeneficiarioResponseDTO> converteBeneficiariosEntidadeDTO(List<Beneficiario> beneficiarios) {

        List<BeneficiarioResponseDTO> listaBeneficiariosDTO = new ArrayList<>();

        for(Beneficiario beneficiario : beneficiarios) {
            listaBeneficiariosDTO.add(new BeneficiarioResponseDTO(beneficiario));
        }

        return listaBeneficiariosDTO;
    }

    private void adicionaDocumentos(List<Documento> documentosEntidade) {
        for(Documento documento : documentosEntidade) {
            this.documentos.add(new DocumentoResponseDTO(documento));
        }
    }
}
