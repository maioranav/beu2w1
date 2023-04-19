--
-- PostgreSQL database dump
--

-- Dumped from database version 14.7 (Ubuntu 14.7-0ubuntu0.22.04.1)
-- Dumped by pg_dump version 15.2

-- Started on 2023-04-19 17:41:41

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 4 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

-- *not* creating schema, since initdb creates it


ALTER SCHEMA public OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 210 (class 1259 OID 16969)
-- Name: edifici; Type: TABLE; Schema: public; Owner: EXW3L1
--

CREATE TABLE public.edifici (
    id bigint NOT NULL,
    citta character varying(255),
    indirizzo character varying(255) NOT NULL,
    nome character varying(255) NOT NULL
);


ALTER TABLE public.edifici OWNER TO "EXW3L1";

--
-- TOC entry 209 (class 1259 OID 16968)
-- Name: edifici_id_seq; Type: SEQUENCE; Schema: public; Owner: EXW3L1
--

CREATE SEQUENCE public.edifici_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.edifici_id_seq OWNER TO "EXW3L1";

--
-- TOC entry 4020 (class 0 OID 0)
-- Dependencies: 209
-- Name: edifici_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: EXW3L1
--

ALTER SEQUENCE public.edifici_id_seq OWNED BY public.edifici.id;


--
-- TOC entry 212 (class 1259 OID 16978)
-- Name: langresp; Type: TABLE; Schema: public; Owner: EXW3L1
--

CREATE TABLE public.langresp (
    id bigint NOT NULL,
    description character varying(255),
    lang character varying(255) NOT NULL
);


ALTER TABLE public.langresp OWNER TO "EXW3L1";

--
-- TOC entry 211 (class 1259 OID 16977)
-- Name: langresp_id_seq; Type: SEQUENCE; Schema: public; Owner: EXW3L1
--

CREATE SEQUENCE public.langresp_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.langresp_id_seq OWNER TO "EXW3L1";

--
-- TOC entry 4021 (class 0 OID 0)
-- Dependencies: 211
-- Name: langresp_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: EXW3L1
--

ALTER SEQUENCE public.langresp_id_seq OWNED BY public.langresp.id;


--
-- TOC entry 214 (class 1259 OID 16987)
-- Name: postazioni; Type: TABLE; Schema: public; Owner: EXW3L1
--

CREATE TABLE public.postazioni (
    id bigint NOT NULL,
    description character varying(255),
    maxp integer,
    type character varying(255),
    edificio_id bigint NOT NULL
);


ALTER TABLE public.postazioni OWNER TO "EXW3L1";

--
-- TOC entry 213 (class 1259 OID 16986)
-- Name: postazioni_id_seq; Type: SEQUENCE; Schema: public; Owner: EXW3L1
--

CREATE SEQUENCE public.postazioni_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.postazioni_id_seq OWNER TO "EXW3L1";

--
-- TOC entry 4022 (class 0 OID 0)
-- Dependencies: 213
-- Name: postazioni_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: EXW3L1
--

ALTER SEQUENCE public.postazioni_id_seq OWNED BY public.postazioni.id;


--
-- TOC entry 216 (class 1259 OID 16996)
-- Name: prenotazioni; Type: TABLE; Schema: public; Owner: EXW3L1
--

CREATE TABLE public.prenotazioni (
    id bigint NOT NULL,
    data date NOT NULL,
    postazione_id bigint,
    utente_id bigint
);


ALTER TABLE public.prenotazioni OWNER TO "EXW3L1";

--
-- TOC entry 215 (class 1259 OID 16995)
-- Name: prenotazioni_id_seq; Type: SEQUENCE; Schema: public; Owner: EXW3L1
--

CREATE SEQUENCE public.prenotazioni_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.prenotazioni_id_seq OWNER TO "EXW3L1";

--
-- TOC entry 4023 (class 0 OID 0)
-- Dependencies: 215
-- Name: prenotazioni_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: EXW3L1
--

ALTER SEQUENCE public.prenotazioni_id_seq OWNED BY public.prenotazioni.id;


--
-- TOC entry 218 (class 1259 OID 17003)
-- Name: utenti; Type: TABLE; Schema: public; Owner: EXW3L1
--

CREATE TABLE public.utenti (
    id bigint NOT NULL,
    email character varying(255) NOT NULL,
    fullname character varying(255),
    username character varying(255) NOT NULL
);


ALTER TABLE public.utenti OWNER TO "EXW3L1";

--
-- TOC entry 217 (class 1259 OID 17002)
-- Name: utenti_id_seq; Type: SEQUENCE; Schema: public; Owner: EXW3L1
--

CREATE SEQUENCE public.utenti_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.utenti_id_seq OWNER TO "EXW3L1";

--
-- TOC entry 4024 (class 0 OID 0)
-- Dependencies: 217
-- Name: utenti_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: EXW3L1
--

ALTER SEQUENCE public.utenti_id_seq OWNED BY public.utenti.id;


--
-- TOC entry 3839 (class 2604 OID 16972)
-- Name: edifici id; Type: DEFAULT; Schema: public; Owner: EXW3L1
--

ALTER TABLE ONLY public.edifici ALTER COLUMN id SET DEFAULT nextval('public.edifici_id_seq'::regclass);


--
-- TOC entry 3840 (class 2604 OID 16981)
-- Name: langresp id; Type: DEFAULT; Schema: public; Owner: EXW3L1
--

ALTER TABLE ONLY public.langresp ALTER COLUMN id SET DEFAULT nextval('public.langresp_id_seq'::regclass);


--
-- TOC entry 3841 (class 2604 OID 16990)
-- Name: postazioni id; Type: DEFAULT; Schema: public; Owner: EXW3L1
--

ALTER TABLE ONLY public.postazioni ALTER COLUMN id SET DEFAULT nextval('public.postazioni_id_seq'::regclass);


--
-- TOC entry 3842 (class 2604 OID 16999)
-- Name: prenotazioni id; Type: DEFAULT; Schema: public; Owner: EXW3L1
--

ALTER TABLE ONLY public.prenotazioni ALTER COLUMN id SET DEFAULT nextval('public.prenotazioni_id_seq'::regclass);


--
-- TOC entry 3843 (class 2604 OID 17006)
-- Name: utenti id; Type: DEFAULT; Schema: public; Owner: EXW3L1
--

ALTER TABLE ONLY public.utenti ALTER COLUMN id SET DEFAULT nextval('public.utenti_id_seq'::regclass);


--
-- TOC entry 4005 (class 0 OID 16969)
-- Dependencies: 210
-- Data for Name: edifici; Type: TABLE DATA; Schema: public; Owner: EXW3L1
--

COPY public.edifici (id, citta, indirizzo, nome) FROM stdin;
1	San Eliziario	Strada Furio 875, Appartamento 44	Einstein
2	Giordano salentino	Rotonda Pagano 153	Lou
4	Fabiano laziale	Incrocio Guerra 44, Piano 0	Lorraine Baines
5	Quarto Antonio	Via Luce 08, Appartamento 49	Mr. Peabody
6	Rosita calabro	Incrocio Bernardi 464, Piano 1	Sam Baines
\.


--
-- TOC entry 4007 (class 0 OID 16978)
-- Dependencies: 212
-- Data for Name: langresp; Type: TABLE DATA; Schema: public; Owner: EXW3L1
--

COPY public.langresp (id, description, lang) FROM stdin;
\.


--
-- TOC entry 4009 (class 0 OID 16987)
-- Dependencies: 214
-- Data for Name: postazioni; Type: TABLE DATA; Schema: public; Owner: EXW3L1
--

COPY public.postazioni (id, description, maxp, type, edificio_id) FROM stdin;
2	Romilda Vane	42	Openspace	1
3	Regulus Arcturus Black	5	Privato	5
4	Fang	76	SalaRiunioni	1
5	Enid	26	Openspace	1
6	Penelope Clearwater	51	SalaRiunioni	5
7	Fabian Prewett	78	SalaRiunioni	1
8	The Bloody Baron	49	Openspace	2
9	Rodolphus Lestrange	21	Openspace	1
11	Fluffy	87	SalaRiunioni	6
\.


--
-- TOC entry 4011 (class 0 OID 16996)
-- Dependencies: 216
-- Data for Name: prenotazioni; Type: TABLE DATA; Schema: public; Owner: EXW3L1
--

COPY public.prenotazioni (id, data, postazione_id, utente_id) FROM stdin;
\.


--
-- TOC entry 4013 (class 0 OID 17003)
-- Dependencies: 218
-- Data for Name: utenti; Type: TABLE DATA; Schema: public; Owner: EXW3L1
--

COPY public.utenti (id, email, fullname, username) FROM stdin;
1	costantino.sala@esempio.it	Costantino Sala	c.sala
3	grazia.bernardi@esempio.it	Grazia Bernardi	g.bernardi
4	brigitta.serr@esempio.it	Brigitta Serr	b.serr
5	timoteo.fior@esempio.it	Timoteo Fior	t.fior
\.


--
-- TOC entry 4025 (class 0 OID 0)
-- Dependencies: 209
-- Name: edifici_id_seq; Type: SEQUENCE SET; Schema: public; Owner: EXW3L1
--

SELECT pg_catalog.setval('public.edifici_id_seq', 6, true);


--
-- TOC entry 4026 (class 0 OID 0)
-- Dependencies: 211
-- Name: langresp_id_seq; Type: SEQUENCE SET; Schema: public; Owner: EXW3L1
--

SELECT pg_catalog.setval('public.langresp_id_seq', 1, false);


--
-- TOC entry 4027 (class 0 OID 0)
-- Dependencies: 213
-- Name: postazioni_id_seq; Type: SEQUENCE SET; Schema: public; Owner: EXW3L1
--

SELECT pg_catalog.setval('public.postazioni_id_seq', 11, true);


--
-- TOC entry 4028 (class 0 OID 0)
-- Dependencies: 215
-- Name: prenotazioni_id_seq; Type: SEQUENCE SET; Schema: public; Owner: EXW3L1
--

SELECT pg_catalog.setval('public.prenotazioni_id_seq', 3, true);


--
-- TOC entry 4029 (class 0 OID 0)
-- Dependencies: 217
-- Name: utenti_id_seq; Type: SEQUENCE SET; Schema: public; Owner: EXW3L1
--

SELECT pg_catalog.setval('public.utenti_id_seq', 5, true);


--
-- TOC entry 3845 (class 2606 OID 16976)
-- Name: edifici edifici_pkey; Type: CONSTRAINT; Schema: public; Owner: EXW3L1
--

ALTER TABLE ONLY public.edifici
    ADD CONSTRAINT edifici_pkey PRIMARY KEY (id);


--
-- TOC entry 3849 (class 2606 OID 16985)
-- Name: langresp langresp_pkey; Type: CONSTRAINT; Schema: public; Owner: EXW3L1
--

ALTER TABLE ONLY public.langresp
    ADD CONSTRAINT langresp_pkey PRIMARY KEY (id);


--
-- TOC entry 3853 (class 2606 OID 16994)
-- Name: postazioni postazioni_pkey; Type: CONSTRAINT; Schema: public; Owner: EXW3L1
--

ALTER TABLE ONLY public.postazioni
    ADD CONSTRAINT postazioni_pkey PRIMARY KEY (id);


--
-- TOC entry 3855 (class 2606 OID 17001)
-- Name: prenotazioni prenotazioni_pkey; Type: CONSTRAINT; Schema: public; Owner: EXW3L1
--

ALTER TABLE ONLY public.prenotazioni
    ADD CONSTRAINT prenotazioni_pkey PRIMARY KEY (id);


--
-- TOC entry 3857 (class 2606 OID 17016)
-- Name: utenti uk_9b90mk1nolf3ou90p42a93tjo; Type: CONSTRAINT; Schema: public; Owner: EXW3L1
--

ALTER TABLE ONLY public.utenti
    ADD CONSTRAINT uk_9b90mk1nolf3ou90p42a93tjo UNIQUE (email);


--
-- TOC entry 3847 (class 2606 OID 17012)
-- Name: edifici uk_m2fp43ag5qmpeolaht2r702uc; Type: CONSTRAINT; Schema: public; Owner: EXW3L1
--

ALTER TABLE ONLY public.edifici
    ADD CONSTRAINT uk_m2fp43ag5qmpeolaht2r702uc UNIQUE (indirizzo);


--
-- TOC entry 3851 (class 2606 OID 17014)
-- Name: langresp uk_nm55cnfw456m3f5tfjpp2cvor; Type: CONSTRAINT; Schema: public; Owner: EXW3L1
--

ALTER TABLE ONLY public.langresp
    ADD CONSTRAINT uk_nm55cnfw456m3f5tfjpp2cvor UNIQUE (lang);


--
-- TOC entry 3859 (class 2606 OID 17018)
-- Name: utenti uk_tn8mwk6h2wn28yyj7fco65gls; Type: CONSTRAINT; Schema: public; Owner: EXW3L1
--

ALTER TABLE ONLY public.utenti
    ADD CONSTRAINT uk_tn8mwk6h2wn28yyj7fco65gls UNIQUE (username);


--
-- TOC entry 3861 (class 2606 OID 17010)
-- Name: utenti utenti_pkey; Type: CONSTRAINT; Schema: public; Owner: EXW3L1
--

ALTER TABLE ONLY public.utenti
    ADD CONSTRAINT utenti_pkey PRIMARY KEY (id);


--
-- TOC entry 3862 (class 2606 OID 17019)
-- Name: postazioni fk567dcy1xrko75goaugbwnfl8p; Type: FK CONSTRAINT; Schema: public; Owner: EXW3L1
--

ALTER TABLE ONLY public.postazioni
    ADD CONSTRAINT fk567dcy1xrko75goaugbwnfl8p FOREIGN KEY (edificio_id) REFERENCES public.edifici(id);


--
-- TOC entry 3863 (class 2606 OID 17029)
-- Name: prenotazioni fk8kg9d6tr3jt4dmyxxwamn41r9; Type: FK CONSTRAINT; Schema: public; Owner: EXW3L1
--

ALTER TABLE ONLY public.prenotazioni
    ADD CONSTRAINT fk8kg9d6tr3jt4dmyxxwamn41r9 FOREIGN KEY (utente_id) REFERENCES public.utenti(id);


--
-- TOC entry 3864 (class 2606 OID 17024)
-- Name: prenotazioni fk8nhrjygc992dv2glaots97puk; Type: FK CONSTRAINT; Schema: public; Owner: EXW3L1
--

ALTER TABLE ONLY public.prenotazioni
    ADD CONSTRAINT fk8nhrjygc992dv2glaots97puk FOREIGN KEY (postazione_id) REFERENCES public.postazioni(id);


--
-- TOC entry 4019 (class 0 OID 0)
-- Dependencies: 4
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE USAGE ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2023-04-19 17:41:41

--
-- PostgreSQL database dump complete
--

