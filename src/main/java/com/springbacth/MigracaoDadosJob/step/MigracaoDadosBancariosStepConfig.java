package com.springbacth.MigracaoDadosJob.step;

import com.springbacth.MigracaoDadosJob.dominio.DadosBancarios;
import com.springbacth.MigracaoDadosJob.dominio.Pessoa;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MigracaoDadosBancariosStepConfig {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;


    @Bean
    public Step migracaoDadosBancariosStep(ItemReader<DadosBancarios> arquivoDadosBancariosReader, ItemWriter<DadosBancarios> bancoDadosBancariosWriter){
        return  stepBuilderFactory
                .get("migracaoDadosBancariosStep")
                .<DadosBancarios,DadosBancarios>chunk(1)
                .reader(arquivoDadosBancariosReader)
                .writer(bancoDadosBancariosWriter)
                .build();
    }

}
