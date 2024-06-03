package com.springbacth.MigracaoDadosJob.step;

import com.springbacth.MigracaoDadosJob.dominio.DadosPessoaDTO;
import com.springbacth.MigracaoDadosJob.dominio.Pessoa;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArquivoLarguraFixaStepConfig {

   @Autowired
   private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step arquivoLarguraFixaStep(ItemReader<Pessoa> larguraFixaReader, ItemWriter<DadosPessoaDTO> larguraFixaWriter, ItemProcessor<Pessoa,DadosPessoaDTO> larguraFixaProcessor){
        return stepBuilderFactory
                .get("arquivoLarguraFixaStep")
                .<Pessoa,DadosPessoaDTO>chunk(1)
                .reader(larguraFixaReader)
                .processor(larguraFixaProcessor)
                .writer(larguraFixaWriter)
                .build();
    }

}
