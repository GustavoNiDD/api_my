package com.example.api_my.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.example.api_my.dto.Usuario;

@Component
public class UsuarioDao {

    @Autowired
    JdbcTemplate db;

    public List<Usuario> getUsuarios() {
        String sql = "select * from usuarios";
        return db.query(sql, (res, rowNumber) -> new Usuario(res.getInt("id"), res.getString("nome"),
                res.getString("email"), res.getString("senha")));
    }

    public void salvar(Usuario usuario) {
        if (usuario.getNome() == null) {
            throw new IllegalArgumentException("Nome cannot be null");
        }
        String sql = "insert into usuarios (nome, email, senha) values (?, ?, ?)";
        db.update(sql, usuario.getNome(), usuario.getEmail(), usuario.getSenha() );
    }

    public void deletar(int id){
        String sql = "delete from usuarios where id = ?";
        db.update(sql, id);
    }

    public void editar(Usuario usuario){
        String sql = "update usuarios set nome = ?, email = ?, senha = ? where id = ?";
        db.update(sql, usuario.getNome(), usuario.getEmail(), usuario.getSenha(), usuario.getId());
    }

    public Usuario getUsuarioById(int id){
        String sql = "select * from usuarios where id = ?";
        return db.queryForObject(sql, (res, rowNumber) -> new Usuario(res.getInt("id"), res.getString("nome"),
                res.getString("email"), res.getString("senha")), id);
    }
}
