package com.kaesar.saude.controller;

import com.kaesar.saude.domain.beneficiario.Beneficiario;
import com.kaesar.saude.domain.beneficiario.BeneficiarioRequestDTO;
import com.kaesar.saude.domain.beneficiario.BeneficiarioResponseDTO;
import com.kaesar.saude.domain.documento.DocumentoResponseDTO;
import com.kaesar.saude.repository.BeneficiarioRepository;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/beneficiarios")
public class BeneficiarioController {

    @Autowired
    private BeneficiarioRepository beneficiarioRepository;

    @Operation(description = "Lista beneficiários")
    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<BeneficiarioResponseDTO> listaBeneficiarios() {
        List<Beneficiario> beneficiarios = this.beneficiarioRepository.findAll();
        return BeneficiarioResponseDTO.converteBeneficiariosEntidadeDTO(beneficiarios);
    }

    @Operation(description = "Cria beneficiário")
    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void criaBeneficiario(@RequestBody @Valid final BeneficiarioRequestDTO request) {
        this.beneficiarioRepository.save(request.criaEntidade());
    }

    @Operation(description = "Remove beneficiário pelo seu ID. Necessário ADMIN")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void removeBeneficiario(@PathVariable("id") final Long id) {
        this.beneficiarioRepository.deleteById(id);
    }

    @Operation(description = "Atualiza dados do beneficiário pelo seu ID")
    @PatchMapping(value = "/{id}", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void atualizaBeneficiario(@PathVariable final Long id, @RequestBody final BeneficiarioRequestDTO dto) {
        Beneficiario beneficiario = this.beneficiarioRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        dto.atualizaEntidade(beneficiario);
        this.beneficiarioRepository.save(beneficiario);
    }

    @Operation(description = "Lista documentos pelo ID do beneficiário")
    @GetMapping(value = "/{id}/documentos", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<DocumentoResponseDTO> listaDocumentos(@PathVariable("id") final Long beneficiarioId) {
        Beneficiario beneficiario = this.beneficiarioRepository.findById(beneficiarioId).orElseThrow(EntityNotFoundException::new);
        return DocumentoResponseDTO.converteDocumentosEntidadeDTO(beneficiario.getDocumentos());
    }
}
