package com.springbacth.MigracaoDadosJob.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@Configuration
public class MigracaoDadosJobConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job migracaoDadosJob(Step migracaoPessoaStep,
                                Step migracaoDadosBancariosStep,
                                Step arquivoLarguraFixaStep){
        return jobBuilderFactory
                .get("migracaoDadosJob")
                .start(arquivoLarguraFixaStep)
//                .next(migracaoDadosBancariosStep)
//                .next(arquivoLarguraFixaStep)
                .incrementer(new RunIdIncrementer())
                .build();
    }

}
