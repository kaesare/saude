package com.kaesar.saude.controller;

import com.kaesar.saude.domain.documento.Documento;
import com.kaesar.saude.domain.documento.DocumentoRequestDTO;
import com.kaesar.saude.repository.DocumentoRepository;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/documentos")
public class DocumentoController {

    @Autowired
    private DocumentoRepository documentoRepository;

    @Operation(description = "Atualiza um documento pelo seu ID")
    @PatchMapping(value = "/{id}", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void atualizaDocumento(@PathVariable final Long id, @RequestBody final DocumentoRequestDTO dto) {
        Documento documento = this.documentoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        dto.atualizaEntidade(documento);
        this.documentoRepository.save(documento);
    }
}
