package com.springbacth.MigracaoDadosJob.reader;

import com.springbacth.MigracaoDadosJob.dominio.DadosBancarios;
import com.springbacth.MigracaoDadosJob.dominio.Pessoa;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class DadosBancariosReaderConfig {

    @Bean
    public FlatFileItemReader<DadosBancarios> arquivoDadosBancariosReader(){
        return new FlatFileItemReaderBuilder<DadosBancarios>()
                .name("arquivoDadosBancariosReader")
                .resource(new FileSystemResource("files/dados_bancarios.csv"))
                .delimited()
                .names("pessoaId", "agencia", "conta", "banco", "id")
                .comments("--")//ignora a linha que começa com -- (linha de comentario)
                .targetType(DadosBancarios.class)
                .build();

    }
}
