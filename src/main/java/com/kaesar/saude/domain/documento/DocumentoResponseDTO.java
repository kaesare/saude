package com.kaesar.saude.domain.documento;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class DocumentoResponseDTO {

    private Long id;
    private Long numero;
    private TipoDocumento tipoDocumento;
    private String descricao;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataInclusao;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataAtualizacao;

    public DocumentoResponseDTO(Documento documento) {
        this.id = documento.getId();
        this.numero = documento.getNumero();
        this.tipoDocumento = documento.getTipoDocumento();
        this.descricao = documento.getDescricao();
        this.dataInclusao = documento.getDataInclusao();
        this.dataAtualizacao = documento.getDataAtualizacao();
    }

    public static List<DocumentoResponseDTO> converteDocumentosEntidadeDTO(List<Documento> documentos) {
        List<DocumentoResponseDTO> listaDocumentosDTO = new ArrayList<>();

        for(Documento documento : documentos) {
            listaDocumentosDTO.add(new DocumentoResponseDTO(documento));
        }

        return listaDocumentosDTO;
    }
}
