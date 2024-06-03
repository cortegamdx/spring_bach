package com.springbacth.MigracaoDadosJob.writer;

import com.springbacth.MigracaoDadosJob.dominio.DadosBancarios;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Configuration
public class BancoDadosBancariosWriterConfig {

    @Bean
    public JdbcBatchItemWriter<DadosBancarios> bancoDadosBancariosWriter(
            @Qualifier("appDataSource") DataSource dataSource
    ){
        return new JdbcBatchItemWriterBuilder<DadosBancarios>()
                .dataSource(dataSource)
                .sql("INSERT INTO dados_bancarios (id, pessoa_id , agencia, conta, banco) VALUES (?, ?, ?, ?, ?)")
                .itemPreparedStatementSetter(itemPreparedStatementSetter())
                .build();
    }

    private ItemPreparedStatementSetter<DadosBancarios> itemPreparedStatementSetter() {
        return  new ItemPreparedStatementSetter<DadosBancarios>() {
            @Override
            public void setValues(DadosBancarios dadosBancarios, PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1, dadosBancarios.getId());
                preparedStatement.setInt(2, dadosBancarios.getPessoaId());
                preparedStatement.setInt(3, dadosBancarios.getAgencia());
                preparedStatement.setInt(4, dadosBancarios.getConta());
                preparedStatement.setInt(5, dadosBancarios.getBanco());
            }
        };
    }


}
