package com.springbacth.MigracaoDadosJob.writer;

import com.springbacth.MigracaoDadosJob.dominio.DadosPessoaDTO;
import com.springbacth.MigracaoDadosJob.dominio.Pessoa;
import org.springframework.batch.item.file.FlatFileFooterCallback;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.io.IOException;
import java.io.Writer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
public class ArquivoLarguraFixaWriterConfig {

    @Bean
    public FlatFileItemWriter<DadosPessoaDTO> larguraFixaWriter(){
        return new FlatFileItemWriterBuilder <DadosPessoaDTO>()
                .name("larguraFixaWriter")
                .resource(new FileSystemResource("files/pessoas_formatado.txt"))
                .formatted()
                .format("%-20s%-40s%-10s%-10s%-10s")
                .names("nome", "email", "agencia", "conta", "banco")
                .headerCallback(cabecalhoCallback())
                .footerCallback(footCallback())
                .build();

    }

    private FlatFileFooterCallback footCallback() {
        return  new FlatFileFooterCallback() {
            @Override
            public void writeFooter(Writer writer) throws IOException {
                writer.append("Fim do arquivo");
            }
        };
    }

    private FlatFileHeaderCallback cabecalhoCallback() {
        return new FlatFileHeaderCallback() {
            @Override
            public void writeHeader(Writer writer) throws IOException {
                writer.append("BanrisulEnvio_" + LocalDate.now().format(DateTimeFormatter.ofPattern("ddMMyyyy")));
            }
        };
    }
}
