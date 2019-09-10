package io.github.mat3e.lang;

import java.util.List;
import java.util.stream.Collectors;

class LangService {
    private LangRepository repository;

    /**
     * Servlet needs it.
     */
    LangService(){
        this(new LangRepository());
    }

    LangService(LangRepository repository) {
        this.repository=repository;
    }

    List<LangDTO> findAll(){
        return repository
                .findAll()
                .stream()
                .map(LangDTO::new)
                .collect(Collectors.toList());

    }
}
