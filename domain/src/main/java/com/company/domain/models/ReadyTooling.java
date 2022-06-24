package com.company.domain.models;

import javax.persistence.JoinColumn;

public class ReadyTooling {
    private Long id;

    @JoinColumn ("detail_id")
    private Detail detail;

    private Tooling tooling;
}
