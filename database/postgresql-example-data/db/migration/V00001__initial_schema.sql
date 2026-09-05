-- Schema for the tables the PostgresSql*Repository implementations read and write.

CREATE TABLE SILVA_OPTIONUM (
    INDEX_UNICUS                             uuid PRIMARY KEY,
    CAMPUS_TEXTUS_OBLIGATORIUS               text    NOT NULL,
    CAMPUS_TEXTUS_OPTIONALIS                 text,
    APPELLATIO                               text    NOT NULL,
    ARTICULUS_INTERIOR_SINGULARIS            jsonb   NOT NULL,
    ARTICULUS_INTERIOR_ITERATUS              jsonb   NOT NULL,
    ARTICULUS_INTERIOR_SINGULARIS_OPTIONALIS jsonb,
    ARTICULUS_INTERIOR_OPTIONALIS_ITERATUS   jsonb,
    APPELLATIO_OPTIONALIS_ITERATUS           jsonb,
    CAMPUS_DIEI                              date,
    CAMPUS_BIVALENS                          boolean NOT NULL,
    CAMPUS_NUMERORUM                         integer NOT NULL,
    ITERATIO_SIMPLICIUM_TEXTUUM              jsonb   NOT NULL,
    RELATIO_AD_ENTITATEM_OPTIONALIS_ITERATUS jsonb,
    RELATIO_AD_ENTITATEM_OPTIONALIS          uuid
);

CREATE TABLE MEMBRUM_RELATUM (
    CLAVIS_PRIMARIA        uuid PRIMARY KEY,
    DESCRIPTIO_EX_DISTANTI text NOT NULL
);
