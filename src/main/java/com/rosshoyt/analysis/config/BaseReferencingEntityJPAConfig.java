package com.rosshoyt.analysis.config;

import com.rosshoyt.analysis.repositories.abstractions.BaseReferencingRepositoryImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.rosshoyt.analysis.repositories",
      repositoryBaseClass = BaseReferencingRepositoryImpl.class)
public class BaseReferencingEntityJPAConfig {
}
