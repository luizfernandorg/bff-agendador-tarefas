package com.javanauta.bffagendadortarefas.business;


import com.javanauta.bffagendadortarefas.business.dto.in.EnderecoDTORequest;
import com.javanauta.bffagendadortarefas.business.dto.in.LoginDTORequest;
import com.javanauta.bffagendadortarefas.business.dto.in.TelefoneDTORequest;
import com.javanauta.bffagendadortarefas.business.dto.in.UsuarioDTORequest;
import com.javanauta.bffagendadortarefas.business.dto.out.EnderecoDTOResponse;
import com.javanauta.bffagendadortarefas.business.dto.out.TelefoneDTOResponse;
import com.javanauta.bffagendadortarefas.business.dto.out.UsuarioDTOResponse;
import com.javanauta.bffagendadortarefas.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioClient usuarioClient;

    public UsuarioDTOResponse salvaUsuario(UsuarioDTORequest usuarioDTO){
        return usuarioClient.salvaUsuario(usuarioDTO);
    }

    public String loginUsuario(LoginDTORequest usuarioDTO){
        return usuarioClient.login(usuarioDTO);
    }

    public UsuarioDTOResponse buscarUsuarioPorEmail(String email, String token){
        return usuarioClient.buscarUsuarioPorEmail(email, token);
    }

    public void deletaUsuarioPorEmail(String email, String token){
        usuarioClient.deletaUsuarioPorEmail(email, token);
    }

    public UsuarioDTOResponse atualizaDadosUsuario(String token, UsuarioDTORequest dto){
        return usuarioClient.atualizaDadoUsuario(dto, token);
    }

    public EnderecoDTOResponse atualizaEndereco(Long id, EnderecoDTORequest enderecoDTO, String token){
       return usuarioClient.atualizaEndereco(enderecoDTO, id, token);
    }

    public TelefoneDTOResponse atualizaTelefone(Long id, TelefoneDTORequest telefoneDTO, String token){
        return usuarioClient.atualizaTelefone(telefoneDTO, id, token);
    }

    public EnderecoDTOResponse cadastraEndereco(String token, EnderecoDTORequest enderecoDTO){
        return usuarioClient.cadastraEndereco(enderecoDTO, token);
    }

    public TelefoneDTOResponse cadastraTelefone(String token, TelefoneDTORequest telefoneDTO){
        return usuarioClient.cadastraTelefone(telefoneDTO, token);
    }
}
