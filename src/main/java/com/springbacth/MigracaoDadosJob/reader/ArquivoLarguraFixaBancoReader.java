package com.springbacth.MigracaoDadosJob.reader;

import com.springbacth.MigracaoDadosJob.dominio.Pessoa;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

@Configuration
public class ArquivoLarguraFixaBancoReader {


    @Bean
    public JdbcCursorItemReader<Pessoa> larguraFixaReader(@Qualifier("appDataSource") DataSource dataSource){
        return new JdbcCursorItemReaderBuilder<Pessoa>()
                .name("larguraFixaReader")
                .dataSource(dataSource)
                .sql("select * from pessoa")
                .rowMapper(pessoaMapper())
                .build();
    }

    private RowMapper pessoaMapper() {
        return  new RowMapper<Pessoa>() {
            @Override
            public Pessoa mapRow(ResultSet resultSet, int i) throws SQLException {
                Pessoa pessoa = new Pessoa();
                pessoa.setId(resultSet.getInt("id"));
                pessoa.setNome(resultSet.getString("nome"));
                pessoa.setIdade(resultSet.getInt("idade"));
                pessoa.setDataNascimento(new Date(resultSet.getDate("data_nascimento").getTime()));
                pessoa.setEmail(resultSet.getString("email"));
                return pessoa;
            }
        };
    }

    public void deletarArquivo(){
        File file = new File("files/pessoas.csv");
        file.delete();
    }
}
