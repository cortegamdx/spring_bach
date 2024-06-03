package com.springbacth.MigracaoDadosJob.processor;

import com.springbacth.MigracaoDadosJob.dominio.DadosPessoaDTO;
import com.springbacth.MigracaoDadosJob.dominio.Pessoa;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;


@Configuration
public class LarguraFixaProcessorConfig implements ItemProcessor<Pessoa, DadosPessoaDTO> {

    @Autowired
    @Qualifier("appDataSource")
    private DataSource appDataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public DadosPessoaDTO process(Pessoa pessoa) throws Exception {
       jdbcTemplate.setDataSource(appDataSource);
        return jdbcTemplate.queryForObject("SELECT * FROM migracao.pessoa a inner join migracao.dados_bancarios b  on (a.id = b.id) and a.id = ?",
                dadosPessoaMapper(),
                new Object[]{pessoa.getId()});
    }

    private RowMapper<DadosPessoaDTO> dadosPessoaMapper() {
        return  new RowMapper<DadosPessoaDTO>() {
            @Override
            public DadosPessoaDTO mapRow(ResultSet resultSet, int i) throws SQLException {
               DadosPessoaDTO dadosPessoaDTO = new DadosPessoaDTO();
               dadosPessoaDTO.setNome(resultSet.getString("nome"));
               dadosPessoaDTO.setAgencia(resultSet.getInt("agencia"));
               dadosPessoaDTO.setEmail(resultSet.getString("email"));
               dadosPessoaDTO.setBanco(resultSet.getInt("banco"));
               dadosPessoaDTO.setConta(resultSet.getInt("conta"));

                return dadosPessoaDTO;
            }
        };
    }
}
