package com.kaesar.saude.domain.documento;

import com.kaesar.saude.domain.beneficiario.Beneficiario;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DocumentoRequestDTO {

    @NotNull
    private Long numero;

    @NotNull
    private TipoDocumento tipoDocumento;

    private String descricao;

    public Documento criaEntidade(Beneficiario beneficiario) {

        Documento documento = new Documento();

        documento.setNumero(this.numero);
        documento.setTipoDocumento(this.tipoDocumento);
        documento.setDescricao(this.descricao);
        documento.setDataInclusao(LocalDateTime.now());
        documento.setDataAtualizacao(LocalDateTime.now());
        documento.setBeneficiario(beneficiario);

        return documento;
    }

    public void atualizaEntidade(Documento documento) {

        if(this.numero != null)
            documento.setNumero(this.numero);

        if(this.tipoDocumento != null)
            documento.setTipoDocumento(this.tipoDocumento);

        if(this.descricao != null)
            documento.setDescricao(this.descricao);

        documento.setDataAtualizacao(LocalDateTime.now());
    }
}