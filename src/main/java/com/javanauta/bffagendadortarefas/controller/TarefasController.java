package com.javanauta.bffagendadortarefas.controller;

import com.javanauta.bffagendadortarefas.business.TarefasService;
import com.javanauta.bffagendadortarefas.business.dto.in.TarefasDTORequest;
import com.javanauta.bffagendadortarefas.business.dto.out.TarefasDTOResponse;
import com.javanauta.bffagendadortarefas.infrastructure.enums.StatusNotificacao;
import com.javanauta.bffagendadortarefas.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
@Tag(name = "Tarefas", description = "Cadastra tarefas de usuários")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class TarefasController {

    private final TarefasService tarefasService;

    @PostMapping
    @Operation(summary = "Save Tasks", description = "Creates a new task")
    @ApiResponse(responseCode = "200", description = "Task successfully saved")
    @ApiResponse(responseCode = "500", description = "Server error")
    public ResponseEntity<TarefasDTOResponse> gravarTarefas(@RequestBody TarefasDTORequest dto,
                                                            @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(tarefasService.gravarTarefa(token, dto));
    }

    @GetMapping("/eventos")
    @Operation(summary = "Retrieve tasks by period", description = "Fetches registered tasks within a given period")
    @ApiResponse(responseCode = "200", description = "Tasks found")
    @ApiResponse(responseCode = "500", description = "Server error")
    @ApiResponse(responseCode = "401", description = "Unauthorized user")
    public ResponseEntity<List<TarefasDTOResponse>> buscaListaDeTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefasService.buscarTarefasAgendadasPorPeriodo(dataInicial, dataFinal, token));
    }

    @GetMapping
    @Operation(summary = "Retrieve task list by user email",
            description = "Fetches tasks registered for the user")
    @ApiResponse(responseCode = "200", description = "Tasks found")
    @ApiResponse(responseCode = "500", description = "Server error")
    @ApiResponse(responseCode = "403", description = "Email not found")
    @ApiResponse(responseCode = "401", description = "Unauthorized user")
    public ResponseEntity<List<TarefasDTOResponse>> buscaTarefasPorEmail(@RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(tarefasService.buscaTarefasPorEmail(token));
    }

    @DeleteMapping
    @Operation(summary = "Delete task by ID",
            description = "Deletes a registered task by its ID")
    @ApiResponse(responseCode = "200", description = "Task deleted")
    @ApiResponse(responseCode = "500", description = "Server error")
    @ApiResponse(responseCode = "403", description = "Task ID not found")
    @ApiResponse(responseCode = "401", description = "Unauthorized user")
    public ResponseEntity<Void> deletaTarefaPorId(@RequestParam("id") String id,
                                                  @RequestHeader(name = "Authorization", required = false) String token) {
        tarefasService.deletaTarefaPorId(id, token);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    @Operation(summary = "Update task status by ID",
            description = "Updates the status of a task by its ID")
    @ApiResponse(responseCode = "200", description = "Task status updated")
    @ApiResponse(responseCode = "500", description = "Server error")
    @ApiResponse(responseCode = "403", description = "Task ID not found")
    @ApiResponse(responseCode = "401", description = "Unauthorized user")
    public ResponseEntity<TarefasDTOResponse> alteraStatusNotificacao(@RequestParam("status") StatusNotificacao status,
                                                                      @RequestParam("id") String id,
                                                                      @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(tarefasService.alteraStatus(status, id, token));
    }

    @PutMapping
    @Operation(summary = "Update task by ID",
            description = "Updates a task by its ID")
    @ApiResponse(responseCode = "200", description = "Task updated")
    @ApiResponse(responseCode = "500", description = "Server error")
    @ApiResponse(responseCode = "403", description = "Task ID not found")
    @ApiResponse(responseCode = "401", description = "Unauthorized user")
    public ResponseEntity<TarefasDTOResponse> updateTarefas(@RequestBody TarefasDTORequest dto,
                                                            @RequestParam("id") String id,
                                                            @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(tarefasService.updateTarefas(dto, id, token));
    }
}
